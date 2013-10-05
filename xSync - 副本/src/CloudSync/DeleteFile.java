package CloudSync;

import java.io.File;
import java.util.ArrayList;

public class DeleteFile {
	public void deleteFile(ArrayList<String> delete_list){
		if(delete_list.isEmpty()){
			return;
		}
		String delete_target_file = delete_list.get(delete_list.size() - 1);
		delete_list.remove(delete_list.size() - 1);
		File delete_instance = new File(delete_target_file);
		try{
			delete_instance.delete();
			if(delete_list.size() != 0){
				deleteFile(delete_list);
			}
		}catch(SecurityException e){
			e.printStackTrace();
		}
	}
}
