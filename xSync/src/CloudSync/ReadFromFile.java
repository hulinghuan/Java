package CloudSync;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ReadFromFile {
	
	public ArrayList<String> readFromFile(String input_file_path){
		ArrayList<String> input_result = new ArrayList();
		try{
			String string_read;
			BufferedReader buffered_reader = new BufferedReader(new FileReader(input_file_path));
			while((string_read = buffered_reader.readLine()) != null){
				input_result.add(string_read);
			}
			buffered_reader.close();
			return input_result;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;  
	}
}
