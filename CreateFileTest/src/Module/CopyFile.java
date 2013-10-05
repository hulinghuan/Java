package Module;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFile {
	public void copyFile() throws IOException
	{
		String new_file_path = "D:\\directory1\\new";
		//FileWriter fw = new FileWriter(new_file_path);
		File file = new File(new_file_path);
		if(!file.exists()){
			file.mkdir();
		}
		
	}

}
