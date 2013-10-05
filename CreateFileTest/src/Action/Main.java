package Action;

import java.io.IOException;

import Module.CopyFile;

public class Main {
	public static void main(String[] args) throws IOException{
		CopyFile copy_file = new CopyFile();
		copy_file.copyFile();
	}

}
