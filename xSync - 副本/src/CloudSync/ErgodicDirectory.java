package CloudSync;

import java.io.File;
import java.util.ArrayList;

public class ErgodicDirectory {
	private ArrayList<String> _file_ergodic_result = new ArrayList<String>();
	private ArrayList<String> _directory_ergodic_result = new ArrayList<String>();
	private String _dir_absolute_path; 
	
	
	public void ergodicDirectory(String dir_absolute_path){
		File parentF = new File(dir_absolute_path);   
		if (!parentF.exists()) {   
			System.out.println("given directory does not exist!");
			return;   
			}   
		if (parentF.isFile()) {   
			_file_ergodic_result.add(parentF.getAbsolutePath());
		    return;   
			}else{
				_directory_ergodic_result.add(parentF.getAbsolutePath());
			}
		String[] subFiles = parentF.list();
		
		for (int i = 0; i < subFiles.length; i++) {
		    ergodicDirectory(dir_absolute_path + "\\" + subFiles[i]);
			}
		
	}
	public void cleanErgodicResult()
	{
		_file_ergodic_result.clear();
		_directory_ergodic_result.clear();
	}
	public ArrayList<String> get_file_ergodic_result() {
		return _file_ergodic_result;
	}

	public void set_file_ergodic_result(ArrayList<String> _file_ergodic_result) {
		this._file_ergodic_result = _file_ergodic_result;
	}

	public String get_dir_absolute_path() {
		return _dir_absolute_path;
	}

	public void set_dir_absolute_path(String _dir_absolute_path) {
		this._dir_absolute_path = _dir_absolute_path;
	}
	public ArrayList<String> get_directory_ergodic_result() {
		return _directory_ergodic_result;
	}
	public void set_directory_ergodic_result(
			ArrayList<String> _directory_ergodic_result) {
		this._directory_ergodic_result = _directory_ergodic_result;
	}

	



}
