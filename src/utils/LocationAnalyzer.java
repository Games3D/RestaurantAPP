package utils;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class LocationAnalyzer {
	
	public static void main(String args[]) {
		
	}
	
	public void something() throws ApiException, InterruptedException, IOException {
	
	GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("AIzaSyBdPB2XptecXbdMUmzzN1vAEd7y5GlJWws")
		    .build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(results[0].addressComponents));
	}
}
