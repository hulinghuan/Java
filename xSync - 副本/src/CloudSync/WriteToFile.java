package CloudSync;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile {
	public void writeToFile(ArrayList<String> raw_content, String output_file_absolute_path){
		try{
			if(raw_content.isEmpty()){
				FileWriter fw = new FileWriter(output_file_absolute_path);
				fw.close();
				return;
			}
			FileWriter fw = new FileWriter(output_file_absolute_path);
			for(int raw_content_iter = 0; raw_content_iter < raw_content.size(); raw_content_iter++){
			fw.write(raw_content.get(raw_content_iter));
			fw.write("\r\n");
			}
			fw.close();
		}catch(IOException ioexception){
			System.out.println(ioexception.getMessage());	
		}
	}

}
