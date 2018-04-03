package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Random;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

public class HelloEhCache{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static DataCache<String, String> dataCache = new DataCache(String.class, String.class);

	public static void main(String[] args) {
		try {
			new HelloEhCache();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		//dataCache.addToCache("me", "meeeeeeeeeeeeee");
		//dataCache.addToCache("me2", "meeeeejjjeee");
		//dataCache.addToCache("me3", "meeehgheeeeeeee");

		//System.out.println(dataCache.getFromCache("me"));
		//System.out.println(dataCache.getKeysFromCache());
		
	}

	public HelloEhCache() throws InstantiationException, IllegalAccessException{

		//CacheManager cm = CacheManager.class.newInstance();

		URL myUrl = getClass().getResource("ehcache.xml"); 
		XmlConfiguration xmlConfig = new XmlConfiguration(myUrl); 
		CacheManager cm = CacheManagerBuilder.newCacheManager(xmlConfig);
		cm.init();
		System.out.println(cm.getStatus());
		Cache<Long, String> cache = cm.getCache("basicCache", Long.class, String.class);
		//cm.createCache("cache1", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Integer.class,ResourcePoolsBuilder.heap(10)));

		cache.put(1L, "da one!");

		Random rand = new Random();
		Timestamp start = new Timestamp(System.currentTimeMillis());
		while (true) {
			cache.put(rand.nextLong(), "da one!");
			Timestamp cur = new Timestamp(System.currentTimeMillis());
			System.out.println(rand.nextLong()+"|"+(cur.getTime()-start.getTime())/1000+"|"+cache.containsKey(1L));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(cache.containsKey("1"));
		//System.out.println(cache.containsKey("5"));

		//8. shut down the cache manager
		//cm.close();

	}

}