package CloudSync;

import java.util.ArrayList;
import java.util.Map;

public class FileSyncOperation {
	/*private Map<String, String> file_;	//first String denotes filename, second String denotes 
											//whether this file can be found in sync directory
											//1 means can be found, 0 means can not
	
	private Map<String, String> file_new;	//first String denotes filename, second String denotes
											//whether this file should be moved to target directory
											//1 means should be moved, 0 means should not
	*/
	
	private ArrayList<String> file_delete = new ArrayList();
	private ArrayList<String> file_moved = new ArrayList();
	private String sync_directory_path;
	private String original_directory_path;
	
	public void fileSyncOperation(ArrayList<String> file_sync, ArrayList<String> file_original){
		int delete_symbol;
		int move_symbol;
		DeleteFile delete_file = new DeleteFile();
		MoveFile move_file = new MoveFile();
		move_file.set_original_directory_father_path(original_directory_path);
		move_file.set_sync_directory_father_path(sync_directory_path);
		//determine which files should be moved to the sync directory
		for(int file_original_iter = 0; file_original_iter < file_original.size(); file_original_iter += 2){
			move_symbol = 1;
			for(int file_sync_iter = 0; file_sync_iter < file_sync.size(); file_sync_iter += 2){
				if(file_original.get(file_original_iter).equals(file_sync.get(file_sync_iter))){
					if(file_original.get(file_original_iter + 1).equals(file_sync.get(file_sync_iter + 1))){
						move_symbol = 0;
					}else{
						move_symbol = 1;
					}
				}
			}
			if(move_symbol == 1){
				file_moved.add(file_original.get(file_original_iter));
			}
		}
		
		//determine which files in sync directory should be deleted 
		for(int file_sync_iter = 0; file_sync_iter < file_sync.size(); file_sync_iter += 2){
			delete_symbol = 1;
			for(int file_original_iter = 0; file_original_iter < file_original.size(); file_original_iter += 2){
				if(file_sync.get(file_sync_iter).equals(file_original.get(file_original_iter))){
					delete_symbol = 0;
				}
			}
			if(delete_symbol == 1){
				file_delete.add(file_sync.get(file_sync_iter));
			}
		}
		delete_file.deleteFile(file_delete);
		move_file.copyFile(file_moved, sync_directory_path);
		
		
		
		
		//System.out.println("Sync Finished!");
	
	}

	public String getSync_directory_path() {
		return sync_directory_path;
	}

	public void setSync_directory_path(String sync_directory_path) {
		this.sync_directory_path = sync_directory_path;
	}

	public String getOriginal_directory_path() {
		return original_directory_path;
	}

	public void setOriginal_directory_path(String original_directory_path) {
		this.original_directory_path = original_directory_path;
	}

	

}