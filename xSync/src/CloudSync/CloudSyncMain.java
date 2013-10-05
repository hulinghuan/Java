package CloudSync;
import java.awt.AWTException;
import java.awt.Container;  
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;  
import java.awt.SystemTray;
import java.awt.Toolkit;  
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;  
import javax.swing.JComboBox;
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

public class CloudSyncMain implements ActionListener, WindowListener{
	//---xSync_Mark_beta2
	//---Written By Linghuan Hu, University Of Texas At Dalls

	private TrayIcon trayIcon;//����ͼ��
    private SystemTray systemTray;//ϵͳ����
	private JFrame frame = new JFrame("xSync 1.1 - Leon");// ��ܲ���    
	private Container con = new Container();//
	//private JLabel label1 = new JLabel("ԭ�ļ���");
	//private JLabel label2 = new JLabel("ͬ����");
	private static JLabel label_sync_status_title = new JLabel("ͬ��״̬:");
	private static JLabel label_sync_status_content = new JLabel("������");
	private static JLabel list_title = new JLabel("��Ҫͬ�����ļ���");
	private static JLabel label_sync_hour = new JLabel("ʱ");
	private static JLabel label_sync_minute = new JLabel("��");
	private static JLabel label_auto_sync = new JLabel("�Զ�ͬ��ʱ��: ");
	private static JTextField text_sync_directory_path = new JTextField();// Ŀ¼��·��  
	private static JButton button_add_directory = new JButton("���");// ѡ��  
	private static JButton button_delete_directory = new JButton("����ͬ�����ļ���");
	private static JButton button_sync_to = new JButton("ͬ����");// ѡ��  
	private static JFileChooser jfc = new JFileChooser();// �ļ�ѡ����  
	private static JButton button_sync_directory = new JButton("��ʼͬ��");// 
	private static JList list_original = null;
	private static Vector<String> list_vector = new Vector<String>();
	private static JScrollPane scroll_pane = null;
	private static DefaultListModel default_list_model1 = new DefaultListModel();
	private static JComboBox cbox_hour = new JComboBox();
	private static JComboBox cbox_minute = new JComboBox();
	CountDownLatch running_thread_num = null;
	private Update_JLabel update_j_label;
	private WriteToFile write_to_file = new WriteToFile();
	private ReadFromFile read_from_file = new ReadFromFile();
	
	private String original_directory_conf = "D:\\Program Files\\xSync\\original_directory_list";
	private String sync_directory_conf = "D:\\Program Files\\xSync\\sync_directory_list";
	private String auto_sync_time_conf = "D:\\Program Files\\xSync\\auto_sync_time";
	private SyncLock _sync_lock = new SyncLock();
	private static SyncInBackground sync_in_background = null;
	//private int _sync_hour;
	//private int _sync_minute;
	
    public CloudSyncMain(){

    	// initial SystemTray function
    	systemTray = SystemTray.getSystemTray();//���ϵͳ���̵�ʵ��
    	java.net.URL imgURL = this.getClass().getResource("images/sync-icon3.png");	//can not use "image\\sync-icon3.png"
        try {
            trayIcon = new TrayIcon(ImageIO.read(imgURL));
            systemTray.add(trayIcon);//�������̵�ͼ�꣬0.gif������ļ�ͬһĿ¼
        }
        catch (IOException e1) {e1.printStackTrace();}
        catch (AWTException e2) {e2.printStackTrace();}
        frame.addWindowListener(new WindowAdapter(){   
                    public void windowIconified(WindowEvent e){   
                        frame.dispose();//������С��ʱdispose�ô��� 
                    	//frame.setVisible(false);
                    }   
                });
        trayIcon.addMouseListener(
                new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        if(e.getClickCount() == 1){
                            frame.setExtendedState(Frame.NORMAL);
                            frame.setVisible(true);
                        }
                        if(e.getClickCount() == 2){
                        	frame.setExtendedState(Frame.NORMAL);
                        	frame.setVisible(true);
                        }
                    }
                });
        try{
    		//this.frame.setLayout(new GridLayout(1,3));		// Have no idea what will happen after set this layout
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        	SwingUtilities.updateComponentTreeUI(frame.getContentPane());
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        try {
			frame.setIconImage(ImageIO.read(imgURL));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	jfc.setCurrentDirectory(new File("\\"));// �ļ�ѡ�����ĳ�ʼĿ¼��Ϊd��  
        frame.add(con);
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
        frame.setLocation(new Point((int) (lx / 2) - 250, (int) (ly / 2) - 250));// �趨���ڳ���λ��  
        frame.setSize(400, 440);// �趨���ڴ�С  
       
        label_sync_status_title.setBounds(110, 360, 70, 20);
        label_sync_status_content.setBounds(190, 360, 150, 20);
        
        list_title.setBounds(10, 10, 110, 20);
        text_sync_directory_path.setBounds(110, 330, 250, 20);
        button_add_directory.setBounds(120, 10, 90, 20);  
        button_delete_directory.setBounds(220, 10, 140, 20);
        button_delete_directory.addActionListener(this);
        button_sync_to.setBounds(10, 330, 90, 20);  
        button_sync_directory.setBounds(10, 360, 90, 20);  
        button_add_directory.addActionListener(this); // ����¼�����  
        button_sync_to.addActionListener(this); // ����¼�����  
        button_sync_directory.addActionListener(this); // ����¼�����  
        cbox_hour.setBounds(110, 295, 40, 20);
        cbox_minute.setBounds(180, 295, 40, 20);
        label_auto_sync.setBounds(10, 295, 100, 20);
        label_sync_hour.setBounds(155, 295, 40, 20);
        label_sync_minute.setBounds(225, 295, 40, 20);
        
        //initial ComboBox: cbox_hour & cbox_minute
        for(int i = 0; i < 24; i++){
        	cbox_hour.insertItemAt(i, i);
        }
        cbox_hour.setSelectedIndex(0);
        
        for(int i = 0, k = 0; i < 60; i = i + 5, k++){
        	cbox_minute.insertItemAt(i, k);
        }
        cbox_minute.setSelectedIndex(0);
        
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
        con.add(cbox_hour);
        con.add(cbox_minute);
        con.add(label_sync_hour);
        con.add(label_sync_minute);
        con.add(label_auto_sync);
       
        list_original = new JList(list_vector);
        scroll_pane = new JScrollPane(list_original);
        scroll_pane.setBounds(10, 35, 350, 250);
        con.add(scroll_pane);
        
        frame.setVisible(true);// ���ڿɼ�
        frame.addWindowListener(this);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ʹ�ܹرմ��ڣ���������
        
        initial_list_original();		// initial the JList (also the JScrollPane scroll_pane);
        initial_list_sync();			//initial the JText
        initial_auto_sync_cbox();		//initial the auto_sync_cbox hour & minute
        
        _sync_lock.open_lock();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		//dont kill the application when click "X" 
        sync_in_background = new SyncInBackground(label_sync_status_content, text_sync_directory_path
        		, default_list_model1, _sync_lock);
        
        int hour = (int) cbox_hour.getItemAt(cbox_hour.getSelectedIndex());
        int minute = (int) cbox_minute.getItemAt(cbox_minute.getSelectedIndex());
        cbox_hour.addActionListener(this);
        cbox_minute.addActionListener(this);
        sync_in_background.setSyncTime(hour, minute);
        sync_in_background.start();
    }  
    
    
    /**
     * read the sync directory information, load and update them to text_sync_directory_path 
     */
    public void initial_list_sync(){
    	File file = new File(sync_directory_conf);
    	if(file.exists()){
    		ArrayList<String> sync_directory_list = new ArrayList<String>();
            sync_directory_list = read_from_file.readFromFile(sync_directory_conf);
            text_sync_directory_path.setText(sync_directory_list.get(0));
    	}else{
    		return;
    	}
    		
    }
    /**
     * read the original directory information, load and update them to scroll_pane
     */
    public void initial_list_original(){
    	File file = new File(original_directory_conf);
    	if(file.exists()){
    		ArrayList<String> original_directory_list = new ArrayList<String>();
            original_directory_list = read_from_file.readFromFile(original_directory_conf);
            for(int i = 0; i < original_directory_list.size(); i++){
            	default_list_model1.addElement(original_directory_list.get(i));
            }
            list_original.setModel(default_list_model1);
            scroll_pane.repaint();
    	}else{
    		return;
    	}
    	
    }
   
    public void initial_auto_sync_cbox(){
    	File file = new File(auto_sync_time_conf);
    	if(file.exists()){
    		ArrayList<String> auto_sync_time = new ArrayList<String>();
            auto_sync_time = read_from_file.readFromFile(auto_sync_time_conf);
            int hour, minute;
            hour = Integer.parseInt(auto_sync_time.get(0));
            minute = Integer.parseInt(auto_sync_time.get(1));
            for(int i = 0; i < 2; i++){
            	cbox_hour.setSelectedIndex(hour);
            	cbox_minute.setSelectedIndex(minute / 5);
            }
    	}
    }
    
    public void actionPerformed(ActionEvent e){  
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
                list_original.setModel(default_list_model1);
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
    	 	
    	 	if(_sync_lock.IsLockOpen() == 1){
    	 		_sync_lock.lock_lock();
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
    	 					label_sync_status_content.setText("ͬ�����!(������)");
    	 					_sync_lock.open_lock();
    	 				} catch (Exception e1) {
    	 					e1.printStackTrace();
    	 				}
    	 				return "";
    	 			}
    	 				protected void done(){
    	 					label_sync_status_content.setText("ͬ����ɣ�(������)");
    	 				}
    	 			}.execute();
    	 		}
        	}
        if(e.getSource().equals(button_delete_directory)){
        	try{
        		int selected;
        		selected = list_original.getSelectedIndex();
        		if(selected == -1){
        			return;
        		}else{
        			default_list_model1.remove(selected);
        			
        			//adjust the selected index of scroll_pane
        			if(selected == 0){
        				
        			}
        			
        			if(selected == default_list_model1.size()){
        				selected--;
        			}
        			
        			if(selected != 0 && selected != default_list_model1.size()){
        				
        			}
        		}
        		list_original.setModel(default_list_model1);
        		list_original.setSelectedIndex(selected);
        		scroll_pane.repaint();

        	}catch(Exception e1){
        		e1.printStackTrace();
        	}
        }
        if(e.getSource().equals(cbox_minute)){
        	int minute = (int) cbox_minute.getItemAt(cbox_minute.getSelectedIndex());
        	int hour = hour = (int) cbox_hour.getItemAt(cbox_hour.getSelectedIndex());
        	sync_in_background.setSyncTime(hour, minute);
        }
        if(e.getSource().equals(cbox_hour)){
        	int minute = (int) cbox_minute.getItemAt(cbox_minute.getSelectedIndex());
        	int hour = hour = (int) cbox_hour.getItemAt(cbox_hour.getSelectedIndex());
        	sync_in_background.setSyncTime(hour, minute);
        }
    }
    
    
    
	public static void main(String[] args){
		CloudSyncMain csm = new CloudSyncMain();
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
        //System.out.println("Window Activated");
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
      //  System.out.println("Window close event occur");
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
        //System.out.println("Window Closing");
		File output_file = new File("D:\\Program Files");
		if(!output_file.exists()){
			output_file.mkdir();
		}
		output_file = new File("D:\\Program Files\\xSync");
		if(!output_file.exists()){
			output_file.mkdir();
		}
		
		ArrayList<String> original_array_list = new ArrayList<String>();
		for(int i = 0; i < default_list_model1.size(); i++){
			original_array_list.add(default_list_model1.get(i).toString());
		}
		write_to_file.writeToFile(original_array_list, original_directory_conf);
		
		ArrayList<String> sync_array_list = new ArrayList<String>();
		sync_array_list.add(text_sync_directory_path.getText());
		write_to_file.writeToFile(sync_array_list, sync_directory_conf);
		
		ArrayList<String> auto_sync_time = new ArrayList<String>();
		auto_sync_time.add(cbox_hour.getItemAt(cbox_hour.getSelectedIndex()).toString());
		auto_sync_time.add(cbox_minute.getItemAt(cbox_minute.getSelectedIndex()).toString());
		write_to_file.writeToFile(auto_sync_time, auto_sync_time_conf);
		
		System.exit(0);
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
       // System.out.println("Window Deactivated");
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
      //  System.out.println("Window Deiconified");
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
       // System.out.println("Window Iconified");
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
        //System.out.println("Window Opened");
	}
}
  
 