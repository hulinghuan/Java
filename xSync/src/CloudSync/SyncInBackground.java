package CloudSync;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class SyncInBackground extends Thread{
	private JLabel label_sync_status_content = null;
	private DefaultListModel default_list_model1 = null;
	private JTextField text_sync_directory_path = null;
	private CountDownLatch running_thread_num = null;
	private SyncLock _sync_lock = null;
	private int _sync_hour;
	private int _sync_minute;
	
	public SyncInBackground(JLabel label_sync_status_content, JTextField text_sync_directory_path,
			DefaultListModel default_list_model, SyncLock sync_lock){
		this.default_list_model1 = default_list_model;
		this.label_sync_status_content = label_sync_status_content;
		this.text_sync_directory_path = text_sync_directory_path;
		this._sync_lock = sync_lock;
		this._sync_hour = 2;
		this._sync_minute = 0;
	}
	
	public void setSyncTime(int hour, int minute){
		this._sync_hour = hour;
		this._sync_minute = minute;
	}
	
	@Override
	public void run(){
		Calendar ca;
		while(true){
			ca = Calendar.getInstance();
			if(ca.get(Calendar.HOUR_OF_DAY) == this._sync_hour)
				if(ca.get(Calendar.MINUTE) == this._sync_minute)
					if(_sync_lock.IsLockOpen() == 1){
						_sync_lock.open_lock();
						label_sync_status_content.setText("同步中!");
						label_sync_status_content.repaint();
						new SwingWorker(){
							protected Object doInBackground() throws Exception{	
								try {
									/*update_j_label = new Update_JLabel(label_sync_status_content, "同步中");
        				update_j_label.run();*/
									ArrayList<String> original_array_list = new ArrayList<String>();
									for(int i = 0; i < default_list_model1.size(); i++){
										original_array_list.add(default_list_model1.get(i).toString());
									} 
									running_thread_num = new CountDownLatch(original_array_list.size());
									CloudSync cloud_sync = new CloudSync(original_array_list, running_thread_num);
									cloud_sync.setsync_directory_path(text_sync_directory_path.getText());
									cloud_sync.syncAction();
        				//cloud_sync.resetM_threads_exe_iter();		// have no idea why some problem will come when i reset 
            	 												//m_threads_exe_iter() in here? Synchronized reason?
									running_thread_num.await();					// wait untill all the threads are terminated
									_sync_lock.open_lock();
									label_sync_status_content.setText("同步完成!(空闲中)");
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								return "";
							}
							protected void done(){
								label_sync_status_content.setText("同步完成!(空闲中)");
							}
						}.execute();
						try{
							this.sleep(60000);
						}catch(Exception e){
							e.printStackTrace();
						}
					}else{
						try{
							this.sleep(1000);
						}catch(Exception e){
							e.printStackTrace();
						}
						continue;
					}
			System.out.println("Hour: " + ca.get(Calendar.HOUR_OF_DAY));
			System.out.println("Minute: " + ca.get(Calendar.MINUTE));
			try{
				this.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
}
