package CloudSync;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream; 
import java.util.ArrayList;

public class MoveFile {
	private String _old_path;
	private String _new_path;
	private String _original_directory_father_path;
	private String _sync_directory_father_path;
	
	public void copyDirectory(String old_path, String new_path){	//copy directory only, do not copy
																	//file
		File f_old_file = new File(old_path);  
        File[] fs = f_old_file.listFiles();  
        File f_new_file = new File(new_path);  
        if(!f_new_file.exists()){  
            f_new_file.mkdirs();  
        }  
        for (File f_iter : fs) {  
            if(f_iter.isFile()){
                //copyFile(f_iter.getPath(),new_path+"\\"+f_iter.getName()); //调用文件拷贝的方法  
            }else if(f_iter.isDirectory()){  
                copyDirectory(f_iter.getPath(),new_path+"\\"+f_iter.getName());
            }  
        }  
	}
	
	public void copyFile(String old_file, String new_file){
		try{
			FileInputStream input = new FileInputStream(old_file);  
	        BufferedInputStream inBuff=new BufferedInputStream(input);
	        FileOutputStream output = new FileOutputStream(new_file);  
	        BufferedOutputStream outBuff=new BufferedOutputStream(output);
			byte[] read_buffer = new byte[1024 * 5];
			int content_readed_length;
	
			while((content_readed_length = inBuff.read(read_buffer)) != -1){
				outBuff.write(read_buffer, 0, content_readed_length);
				
			}
			outBuff.flush();
			inBuff.close();
			outBuff.close();
			input.close();
			output.close();
			/*while((read_buffer = buffered_reader.readLine()) != null){
				print_stream.
				print_stream.flush();
			}*/
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	public void copyFile(ArrayList<String> file_moved,String new_root_directory){
		try{
			if(file_moved.isEmpty()){
				return;
			}
			String move_instance = file_moved.get(file_moved.size() - 1);
			file_moved.remove(file_moved.size() - 1);
			File file = new File(move_instance);
			String father_name;
			File tmp = new File(_original_directory_father_path);
			father_name = tmp.getName();
			int father_name_length = father_name.length();
			FileInputStream input = new FileInputStream(move_instance);
			BufferedInputStream inBuff=new BufferedInputStream(input);
			String substring = null;
			String file_path = file.getAbsolutePath();
			if(_original_directory_father_path.isEmpty()){
				
			}else{
				substring = file_path.substring(file_path.indexOf(father_name) + father_name_length + 1);
			}
	        FileOutputStream output = new FileOutputStream(new_root_directory+ "\\" + substring);
			//FileOutputStream output = new FileOutputStream(new_root_directory);
	        BufferedOutputStream outBuff=new BufferedOutputStream(output);
			
	        byte[] read_buffer = new byte[1024 * 5];
			int content_readed_length;
			while((content_readed_length = inBuff.read(read_buffer)) != -1){
				outBuff.write(read_buffer, 0, content_readed_length);
				
			}
			outBuff.flush();
			inBuff.close();
			outBuff.close();
			input.close();
			output.close(); 
			
			if(file_moved.size() != 0){
				copyFile(file_moved, new_root_directory);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String get_old_path() {
		return _old_path;
	}
	public void set_old_path(String _old_path) {
		this._old_path = _old_path;
	}
	public String get_new_path() {
		return _new_path;
	}
	public void set_new_path(String _new_path) {
		this._new_path = _new_path;
	}

	public void set_original_directory_father_path(
			String _original_directory_father_path) {
		this._original_directory_father_path = _original_directory_father_path;
	}

	public void set_sync_directory_father_path(String _sync_directory_father_path) {
		this._sync_directory_father_path = _sync_directory_father_path;
	}
	
	
	
	
}
