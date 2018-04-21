package testingLIB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

	public static ArrayList<String[]> readFile(File file) {		 
		ArrayList<String[]> results = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null)
				results.add(st.split(","));
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return results;
	}
}
