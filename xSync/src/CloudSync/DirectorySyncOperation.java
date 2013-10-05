package CloudSync;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class DirectorySyncOperation {
	private ArrayList<String> _directory_make = new ArrayList<String>();
	private ArrayList<String> _directory_delete = new ArrayList<String>();
	private ArrayList<String> _original_directory_list = new ArrayList<String>();
	private ArrayList<String> _sync_directory_list = new ArrayList<String>();
	
	public void directorySyncOperation(ArrayList<String> directory_sync, ArrayList<String> directory_original){
		
		String original_directory_path;
		String sync_directory_path;
		original_directory_path = directory_original.get(0);
		sync_directory_path = directory_sync.get(0);
		String original_directory_father_name;
		String sync_directory_father_name;
		File directory_instance;
		directory_instance = new File(directory_original.get(0));
		original_directory_father_name = directory_instance.getName();
		directory_instance = new File(directory_sync.get(0));
		sync_directory_father_name = directory_instance.getName();
		String substring_path;
		String mkdir_path;
		String delete_path;
		int original_directory_father_length = original_directory_father_name.length();
		//create directory in sync_directory
		for(int directory_original_iter = 0; directory_original_iter < directory_original.size(); directory_original_iter ++){
			original_directory_path = directory_original.get(directory_original_iter);
			substring_path = original_directory_path.substring(original_directory_path.indexOf(original_directory_father_name)
					+ original_directory_father_length);
			//System.out.println(substring_path);
			mkdir_path = sync_directory_path + "\\" + substring_path;
			directory_instance = new File(mkdir_path);
			if(directory_instance.exists()){
			}else{
				directory_instance.mkdir();
			}
		}
		
		original_directory_path = directory_original.get(0);
		
		//delete directory in sync_directory
		for(int directory_sync_iter = 2; directory_sync_iter < directory_sync.size(); directory_sync_iter ++){
			sync_directory_path = directory_sync.get(directory_sync_iter);
			substring_path = sync_directory_path.substring(sync_directory_path.indexOf(original_directory_father_name)
					+ original_directory_father_length + 1);
			//System.out.println(substring_path);
			delete_path = original_directory_path + "\\" + substring_path;
			directory_instance = new File(delete_path);
			if(directory_instance.exists()){
			}else{
				directory_instance = new File(directory_sync.get(directory_sync_iter));
				org.apache.commons.io.FileUtils.deleteQuietly(directory_instance);
			}
			
		}
	}
	
	
	
}
