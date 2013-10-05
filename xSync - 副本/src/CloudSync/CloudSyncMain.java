package CloudSync;
import java.awt.Container;  
import java.awt.GridLayout;
import java.awt.Point;  
import java.awt.Toolkit;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.File;  
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JList;
import javax.swing.JOptionPane;  
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;  
import javax.swing.JTextField;  
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class CloudSyncMain implements ActionListener{
	//private CloudSync cloud_sync;
	private JFrame frame = new JFrame("xSync - Leon");// 框架布局    
	private Container con = new Container();//
	//private JLabel label1 = new JLabel("原文件夹");  
	//private JLabel label2 = new JLabel("同步到");
	private static JLabel label_sync_status_title = new JLabel("同步状态:");
	private static JLabel label_sync_status_content = new JLabel("");
	private static JLabel list_title = new JLabel("需要同步的文件夹");
	//private JTextField text1 = new JTextField();// TextField 目录的路径  
	private static JTextField text_sync_directory_path = new JTextField();// 目录的路径  
	private static JButton button_add_directory = new JButton("添加");// 选择  
	private static JButton button_delete_directory = new JButton("不再同步此文件夹");
	private static JButton button_sync_to = new JButton("同步到");// 选择  
	private static JFileChooser jfc = new JFileChooser();// 文件选择器  
	private static JButton button_sync_directory = new JButton("开始同步");// 
	private static JList list_sync = null;
	private static Vector<String> list_vector = new Vector<String>();
	private static JScrollPane scroll_pane = null;
	private static DefaultListModel default_list_model1 = new DefaultListModel();
	CountDownLatch running_thread_num = null;
	private Update_JLabel update_j_label;
	
	
	
    public CloudSyncMain(){  
    	try{
    		//this.frame.setLayout(new GridLayout(1,3));		// Have no idea what will happen after set this layout
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        	SwingUtilities.updateComponentTreeUI(frame.getContentPane());
        }catch(Exception e){
        	e.printStackTrace();
        }
    	jfc.setCurrentDirectory(new File("\\"));// 文件选择器的初始目录定为d盘  
        frame.add(con);
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
        frame.setLocation(new Point((int) (lx / 2) - 250, (int) (ly / 2) - 250));// 设定窗口出现位置  
        frame.setSize(400, 410);// 设定窗口大小  
        //label1.setBounds(10, 10, 70, 20);  
        //text1.setBounds(75, 10, 120, 20);  
        
        //label2.setBounds(310, 35, 70, 20);
        label_sync_status_title.setBounds(110, 330, 70, 20);
        label_sync_status_content.setBounds(190, 330, 70, 20);
        
        list_title.setBounds(10, 10, 110, 20);
        text_sync_directory_path.setBounds(110, 300, 250, 20);
        button_add_directory.setBounds(120, 10, 90, 20);  
        button_delete_directory.setBounds(220, 10, 140, 20);
        button_delete_directory.addActionListener(this);
        button_sync_to.setBounds(10, 300, 90, 20);  
        button_sync_directory.setBounds(10, 330, 90, 20);  
        button_add_directory.addActionListener(this); // 添加事件处理  
        button_sync_to.addActionListener(this); // 添加事件处理  
        button_sync_directory.addActionListener(this); // 添加事件处理  
        //con.add(label1);  
        //con.add(text1);  
        con.add(button_add_directory);  
        //con.add(label2);  
        con.add(text_sync_directory_path);  
        con.add(button_sync_to);  
        con.add(button_sync_directory); 
        con.add(button_delete_directory);
        con.add(label_sync_status_title);
        con.add(label_sync_status_content);
        con.add(list_title);
       
        list_sync = new JList(list_vector);
        scroll_pane = new JScrollPane(list_sync);
        scroll_pane.setBounds(10, 35, 350, 250);
        con.add(scroll_pane);
        
        frame.setVisible(true);// 窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序 
    }  
   
    
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        if (e.getSource().equals(button_add_directory)) {// 判断触发方法的按钮是哪个  
            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();// f为选择到的目录  
                //text1.setText(f.getAbsolutePath());
                default_list_model1.addElement(f.getAbsolutePath());
                list_sync.setModel(default_list_model1);
                scroll_pane.repaint();
            }  
        }  
        // 绑定到选择文件，先择文件事件  
        if (e.getSource().equals(button_sync_to)) {  
            jfc.setFileSelectionMode(1);// 设定只能选择到文件  
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
            if (state == 1) {  
                return;// 撤销则返回  
            } else {  
                File f = jfc.getSelectedFile();// f为选择到的文件  
                text_sync_directory_path.setText(f.getAbsolutePath());
            }  
        }  
        if (e.getSource().equals(button_sync_directory)) {
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
        				label_sync_status_content.setText("同步完成!");
        			} catch (Exception e1) {
        				e1.printStackTrace();
        			}
				return "";
        		}
        		
        		protected void done(){
        			label_sync_status_content.setText("同步完成！");
        		}
        	}.execute();
        }
        if(e.getSource().equals(button_delete_directory)){
        	try{
        		int selected;
        		selected = list_sync.getSelectedIndex();
        		if(selected == -1){
        			return;
        		}else{
        			default_list_model1.remove(selected);	
        		}
        		list_sync.setModel(default_list_model1);
        		scroll_pane.repaint();
        	}catch(Exception e1){
        		e1.printStackTrace();
        	}
        }
    }
	
	public static void main(String[] args){
		CloudSyncMain csm = new CloudSyncMain();
	}


}
  
 