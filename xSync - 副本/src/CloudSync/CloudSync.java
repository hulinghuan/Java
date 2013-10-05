package CloudSync;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CloudSync extends Thread{
	
	private ArrayList<String> md5_array_original = new ArrayList<String>();
	private ArrayList<String> md5_array_sync = new ArrayList<String>();
	private ArrayList<String> ergodic_result;
	private ArrayList<String> sync_directory_ergodic_result;
	private ArrayList<String> original_directory_ergodic_result;
	private String original_directory_path;
	private static String sync_directory_path;
	private ArrayList<String> original_directory_paths;
	private static int m_threads_exe_iter = 0;
	private CountDownLatch running_thread_num;
	
	public CloudSync(ArrayList<String> original_directory_paths, CountDownLatch running_thread_num){
		this.original_directory_paths = original_directory_paths;
		this.running_thread_num = running_thread_num;
		
	}
	@Override
	/**
	 *  execute the sync action in multi-thread
	 */
	public void run() {
		// TODO Auto-generated method stub
		
		ErgodicDirectory ergodic_directory = new ErgodicDirectory();
		WriteToFile write_to_file = new WriteToFile();
		HashFile hash_file = new HashFile();
		FileSyncOperation file_sync_operation = new FileSyncOperation();
		DirectorySyncOperation directory_sync_operation = new DirectorySyncOperation();
		
		synchronized(original_directory_paths){
			if(m_threads_exe_iter < original_directory_paths.size())
			original_directory_path = original_directory_paths.get(m_threads_exe_iter);
			System.out.println("m_threads_exe_iter: " + m_threads_exe_iter);
			m_threads_exe_iter++;
			if(m_threads_exe_iter >= original_directory_paths.size()){ 		// reset m_threads_exe_iter to 0
				m_threads_exe_iter = 0;
			}
		}
		
		String sync_directory_path_bak = sync_directory_path;
		
		//System.out.println("Processing!");
		
		File file_instance = new File(original_directory_path);
		String original_directory_name = file_instance.getName();
		//sync_directory_path = sync_directory_path + "\\" + original_directory_name;
		sync_directory_path_bak = sync_directory_path_bak + "\\" + original_directory_name;
		file_instance = new File(sync_directory_path_bak);
		if(file_instance.exists()){
		}else{
			file_instance.mkdir();
		}
		
		
		//System.out.println("Ergodicing original directory!");
		ergodic_directory.ergodicDirectory(original_directory_path);
		ergodic_result = ergodic_directory.get_file_ergodic_result();
		original_directory_ergodic_result = new ArrayList
				<String>(ergodic_directory.get_directory_ergodic_result());
		//System.out.println("Hashing original directory!");
		for(String iter : ergodic_result){
			md5_array_original.add(iter);
			try {
				md5_array_original.add(hash_file.getHash(iter, "MD5"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("Ergodicing sync directory!");
		ergodic_directory.cleanErgodicResult();
		ergodic_directory.ergodicDirectory(sync_directory_path_bak);
		ergodic_result = ergodic_directory.get_file_ergodic_result();
		sync_directory_ergodic_result = new ArrayList
				<String>(ergodic_directory.get_directory_ergodic_result());
		
		//System.out.println("Hashing sync directory!");
		for(String iter : ergodic_result){
			md5_array_sync.add(iter);
			try {
				md5_array_sync.add(hash_file.getHash(iter, "MD5"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		directory_sync_operation.directorySyncOperation(sync_directory_ergodic_result, original_directory_ergodic_result);
		file_sync_operation.setOriginal_directory_path(original_directory_path);
		file_sync_operation.setSync_directory_path(sync_directory_path_bak);
		file_sync_operation.fileSyncOperation(md5_array_sync, md5_array_original);
		
		//sync_directory_path = sync_directory_path_bak;
		//System.out.println("Process Complete!");
		synchronized(running_thread_num){
			running_thread_num.countDown();
		}
	}
	
	public void syncAction() throws Exception {
		for(int i = 0; i < original_directory_paths.size(); i ++){
			new CloudSync(original_directory_paths, running_thread_num).start();
		}
			
	}


	public ArrayList<String> getmd5_array_original() {
		return md5_array_original;
	}


	public void setmd5_array_original(ArrayList<String> md5_array_original) {
		this.md5_array_original = md5_array_original;
	}


	public ArrayList<String> getmd5_array_sync() {
		return md5_array_sync;
	}


	public void setmd5_array_sync(ArrayList<String> md5_array_sync) {
		this.md5_array_sync = md5_array_sync;
	}


	public ArrayList<String> getErgodic_result() {
		return ergodic_result;
	}


	public void setErgodic_result(ArrayList<String> ergodic_result) {
		this.ergodic_result = ergodic_result;
	}


	public String getoriginal_directory_path() {
		return original_directory_path;
	}


	public void setoriginal_directory_path(String original_directory_path) {
		this.original_directory_path = original_directory_path;
	}


	public String getsync_directory_path() {
		return sync_directory_path;
	}


	public void setsync_directory_path(String sync_directory_path) {
		this.sync_directory_path = sync_directory_path;
	}
	public void setOriginal_directory_paths(
			ArrayList<String> original_directory_paths) {
		this.original_directory_paths = original_directory_paths;
	}

	public void resetM_threads_exe_iter(){
		m_threads_exe_iter = 0;
	}

	

	


	
}
