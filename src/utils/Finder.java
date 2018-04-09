package utils;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;

public class Finder {
	
	public class MYDataCache<T1, T2> {

		private Cache<T1, T2> cache;
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		private CacheManager cacheManager=null;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public MYDataCache(Class c1, Class c2) {	
			Configuration xmlConfig = new XmlConfiguration(this.getClass().getResource("ehcache.xml"));
			cacheManager = newCacheManager(xmlConfig);
			cacheManager.init();

			cache = cacheManager.getCache("basicCache", c1, c2);
			
			logger.info("Cache setup is done");
		}

		public T2 getFromCache(T1 key) {
			return cache.get(key);
		}

		public ArrayList<T1> getKeysFromCache() {
			ArrayList<T1> keys = new ArrayList<T1>();
			cache.forEach((cur) -> keys.add(cur.getKey()));
			return keys;
		}

		public boolean contains(T1 key) {
			return cache.containsKey(key);
		}

		public void addToCache(T1 key, T2 data) {
			cache.put(key, data);
		}

		public void removeFromCache(T1 key) {
			cache.remove(key);
		}

		public void clearCache() {
			cache.clear();
		}
		
		public void close() {
			cacheManager.close();
		}
	}
	//https://developers.google.com/places/web-service/

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/*Database credentials for AFS */
	static final String DB_URL ="jdbc:mysql://games3dcreations.ddns.net:3306/NJIT_CS684";
	static final String USER = "NJIT_CS684";
	static final String PASS = "NJIT_CS684";
	Connection conn = null;

	HttpClientContext context = HttpClientContext.create();
	HttpClient client = HttpClientBuilder.create().build();
	ArrayList<String> DATA = new ArrayList<String>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	MYDataCache<String, String> dataCache = new MYDataCache(String.class, String.class);

	public static void main(String[] args) {
		new Finder("1200 Grand St, Hoboken, NJ|100 1st St, Jersey City, NJ|Jared");
	}

	@SuppressWarnings({ "rawtypes", "unlikely-arg-type" })
	public Finder(String parms) {
		connect();

		String[] args=parms.split("|");

		//get directions from point a to point b
		//String DIRECTIONS = Directions("1200 Grand St, Hoboken, NJ", "100 1st St, Jersey City, NJ");
		String DIRECTIONS = Directions(args[0], args[0]);


		//get waypoints from the route
		try {
			JSONObject jo = (JSONObject) new JSONParser().parse(DIRECTIONS);
			JSONArray l1 = (JSONArray) jo.get("routes");
			JSONObject l2 = (JSONObject) l1.get(0);
			JSONArray l3 = (JSONArray) l2.get("legs");
			JSONObject l4 = (JSONObject) l3.get(0);
			JSONArray l5 = (JSONArray) l4.get("steps");

			for (Object node:l5) {
				JSONObject obj = (JSONObject) node;
				Map map=(Map) obj.get("startLocation");
				Double startLng = (Double) map.get("lat");
				Double startLat = (Double) map.get("lng");
				System.out.println(startLng+"|"+startLat);

				//test each waypoint for nearest resturant the matches the user's settings
				DATA.addAll(findNearBy(startLng,startLat,500,"food"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//figures out if the item is black listed or white listed and adjusts the rating for the next sort to move it
		final ArrayList<String> black=getBlackList(args[2]);
		final ArrayList<String> white=getWhiteList(args[2]);

		for(int g=0; g<DATA.size(); g++) {
			String curLineBIG=DATA.get(g);
			String[] curLine = curLineBIG.split("`");
			
			//sorting by white list
			if (Arrays.asList(white).contains(curLine[3]))
				curLine[3]="5";

			//sorting by black list
			if (Arrays.asList(black).contains(curLine[3]))
				curLine[3]="1";
			
			DATA.set(g, String.join("`", curLine));
		}
		
		//takes the list from all waypoints and finds the top 20 results
		DATA=(ArrayList<String>) DATA.stream().distinct().collect(Collectors.toList());
		Collections.sort(DATA, new Comparator<String>() {
			@Override
			public int compare(String lhs, String rhs) {		    	
				String[] lineA = lhs.split("`");
				String[] lineB = rhs.split("`");

				if (lineA[3].equals("null"))
					return 1;
				if (lineB[3].equals("null"))
					return -1;

				// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				try {
					double a=Double.parseDouble(lineA[3]);
					double b=Double.parseDouble(lineB[3]);

					//System.out.print("{"+a+"|"+b+"}");

					return a > b ? -1 : a < b ? 1 : 0;
				} catch (Exception e) {
					return 0;
				}
			}
		});

		int STOP=20;
		if (DATA.size()<20)
			STOP = DATA.size();
		for(int count=0; count<STOP; count++) {
			String[] line = DATA.get(count).split("`");
			DATA.set(count, DATA.get(count)+"`"+getInfoAboutPlace(line[6])+"~");
			//System.out.println(DATA);
		}

		System.out.println("Total found: "+DATA.size()+"\n\nKEYS:"+dataCache.getKeysFromCache().size());
		dataCache.close();
	}
	
	private void connect() {
		try {
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error while connecting to database");
			fail("Connection issue");
		}
		System.out.println("Connected");
	}

	public String getDATA() {
		return DATA.toString();
	}

	private ArrayList<String> findNearBy(double lon, double lat, int radius, String type) {
		String out="";
		ArrayList<String> OUT = new ArrayList<String>();

		//String url = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&radius=%s&types=%s&name=cruise&key=AIzaSyBdPB2XptecXbdMUmzzN1vAEd7y5GlJWws" + 
		String url = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&radius=%s&types=%s&key=AIzaSyBdPB2XptecXbdMUmzzN1vAEd7y5GlJWws",
				lon, lat, radius, type);
		//System.out.println(url);
		HttpGet request = new HttpGet(url);

		request.addHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
		try {
			HttpResponse response = client.execute(request, context);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedInputStream bis = new BufferedInputStream(entity.getContent());
				byte[] contents = new byte[1024];

				int bytesRead = 0;
				String strFileContents = ""; 
				while((bytesRead = bis.read(contents)) != -1) { 
					strFileContents += new String(contents, 0, bytesRead);              
				}

				//System.out.println(strFileContents);

				JSONObject jo = (JSONObject) new JSONParser().parse(strFileContents);
				JSONArray l1 = (JSONArray) jo.get("results");

				for(int cur=0; cur<l1.size(); cur++) {
					JSONObject DATA = (JSONObject) l1.get(cur);

					out=new String(
							DATA.get("name")+ "`" +
									DATA.get("types")+ "`" +
									DATA.get("opening_hours")+ "`" +
									DATA.get("rating")+ "`" +
									DATA.get("vicinity")+ "`" +
									DATA.get("icon"))+ "`" +
									DATA.get("place_id");

					OUT.add(out);
				}	
			}
			HttpClientUtils.closeQuietly(response);

		} catch (Exception e) {e.printStackTrace();}

		return OUT;
	}

	@SuppressWarnings("rawtypes")
	private String getInfoAboutPlace(String place) {
		String out="";

		//info from cache
		if (dataCache.contains(place)) {
			System.out.print("Cache|");
			out=dataCache.getFromCache(place);
		}

		//get info from google
		else {
			System.out.print("Search|");
			String url = String.format("https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&key=AIzaSyBdPB2XptecXbdMUmzzN1vAEd7y5GlJWws" + 
					"%s", "");
			HttpGet request = new HttpGet(url);

			request.addHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
			try {
				HttpResponse response = client.execute(request, context);
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					BufferedInputStream bis = new BufferedInputStream(entity.getContent());
					byte[] contents = new byte[1024];

					int bytesRead = 0;
					String strFileContents = ""; 
					while((bytesRead = bis.read(contents)) != -1) { 
						strFileContents += new String(contents, 0, bytesRead);              
					}

					//System.out.println(strFileContents);

					JSONObject jo = (JSONObject) new JSONParser().parse(strFileContents);
					Map DATA = (Map) jo.get("result");
					// System.out.println(DATA.keySet().toString());

					out=new String(
							DATA.get("formatted_address")+ "`" +
									DATA.get("opening_hours")+ "`" +
									DATA.get("formatted_phone_number")+ "`" +
									DATA.get("website"));//+ "`" +
									//DATA.get("photos")+ "`" +
									//DATA.get("reviews"));
					
					dataCache.addToCache(place, out);//adds this object to the cache
				}
				HttpClientUtils.closeQuietly(response);

			} catch (Exception e) {e.printStackTrace();}
		}

		return out;
	}

	private String Directions(String Start, String End) {
		DirectionsResult directionResult=null;

		try {
			GeoApiContext context = new GeoApiContext.Builder()
					.apiKey("AIzaSyBdPB2XptecXbdMUmzzN1vAEd7y5GlJWws")
					.build();
			directionResult = 
					DirectionsApi.getDirections(context, Start, End).await();

		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		return gson.toJson(directionResult);
	}

	public ArrayList<String> getBlackList(String User) {
		ArrayList<String> results=new ArrayList<String>();
		try {
			Statement st=conn.createStatement();	
			ResultSet RS=st.executeQuery("select * from Favorites where username='"+User+"' and BLACKLIST='B'");

			while(RS.next()) {
				results.add(RS.getString("FAVNAME"));
			}

			RS.close();
			st.close();				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return results;
	}

	public ArrayList<String> getWhiteList(String User) {
		ArrayList<String> results=new ArrayList<String>();
		try {
			Statement st=conn.createStatement();	
			ResultSet RS=st.executeQuery("select * from Favorites where username='"+User+"' and BLACKLIST='W'");

			while(RS.next()) {
				results.add(RS.getString("FAVNAME"));
			}

			RS.close();
			st.close();				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return results;
	}
}