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
	private JFrame frame = new JFrame("xSync - Leon");// ��ܲ���    
	private Container con = new Container();//
	//private JLabel label1 = new JLabel("ԭ�ļ���");  
	//private JLabel label2 = new JLabel("ͬ����");
	private static JLabel label_sync_status_title = new JLabel("ͬ��״̬:");
	private static JLabel label_sync_status_content = new JLabel("");
	private static JLabel list_title = new JLabel("��Ҫͬ�����ļ���");
	//private JTextField text1 = new JTextField();// TextField Ŀ¼��·��  
	private static JTextField text_sync_directory_path = new JTextField();// Ŀ¼��·��  
	private static JButton button_add_directory = new JButton("���");// ѡ��  
	private static JButton button_delete_directory = new JButton("����ͬ�����ļ���");
	private static JButton button_sync_to = new JButton("ͬ����");// ѡ��  
	private static JFileChooser jfc = new JFileChooser();// �ļ�ѡ����  
	private static JButton button_sync_directory = new JButton("��ʼͬ��");// 
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
    	jfc.setCurrentDirectory(new File("\\"));// �ļ�ѡ�����ĳ�ʼĿ¼��Ϊd��  
        frame.add(con);
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
        frame.setLocation(new Point((int) (lx / 2) - 250, (int) (ly / 2) - 250));// �趨���ڳ���λ��  
        frame.setSize(400, 410);// �趨���ڴ�С  
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
        button_add_directory.addActionListener(this); // ����¼�����  
        button_sync_to.addActionListener(this); // ����¼�����  
        button_sync_directory.addActionListener(this); // ����¼�����  
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
        
        frame.setVisible(true);// ���ڿɼ�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ʹ�ܹرմ��ڣ��������� 
    }  
   
    
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        if (e.getSource().equals(button_add_directory)) {// �жϴ��������İ�ť���ĸ�  
            jfc.setFileSelectionMode(1);// �趨ֻ��ѡ���ļ���  
            int state = jfc.showOpenDialog(null);// �˾��Ǵ��ļ�ѡ��������Ĵ������  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();// fΪѡ�񵽵�Ŀ¼  
                //text1.setText(f.getAbsolutePath());
                default_list_model1.addElement(f.getAbsolutePath());
                list_sync.setModel(default_list_model1);
                scroll_pane.repaint();
            }  
        }  
        // �󶨵�ѡ���ļ��������ļ��¼�  
        if (e.getSource().equals(button_sync_to)) {  
            jfc.setFileSelectionMode(1);// �趨ֻ��ѡ���ļ�  
            int state = jfc.showOpenDialog(null);// �˾��Ǵ��ļ�ѡ��������Ĵ������  
            if (state == 1) {  
                return;// �����򷵻�  
            } else {  
                File f = jfc.getSelectedFile();// fΪѡ�񵽵��ļ�  
                text_sync_directory_path.setText(f.getAbsolutePath());
            }  
        }  
        if (e.getSource().equals(button_sync_directory)) {
        	label_sync_status_content.setText("ͬ����!");
    	 	label_sync_status_content.repaint();
    	 	
        	new SwingWorker(){
        		protected Object doInBackground() throws Exception{	
        		try {
        			/*update_j_label = new Update_JLabel(label_sync_status_content, "ͬ����");
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
        				label_sync_status_content.setText("ͬ�����!");
        			} catch (Exception e1) {
        				e1.printStackTrace();
        			}
				return "";
        		}
        		
        		protected void done(){
        			label_sync_status_content.setText("ͬ����ɣ�");
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
  
 