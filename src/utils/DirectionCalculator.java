package utils;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;

public class DirectionCalculator {

	public static void main(String[] args) {

		try {
			GeoApiContext context = new GeoApiContext.Builder()
				    .apiKey("AIzaSyBdPB2XptecXbdMUmzzN1vAEd7y5GlJWws")
				    .build();
			DirectionsResult directionResult = 
					DirectionsApi.getDirections(context, "1200 Grand St, Hoboken, NJ", "110 1st St, Jersey City, NJ").await();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(directionResult));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
