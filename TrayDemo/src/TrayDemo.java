import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

public class TrayDemo extends JFrame{
    private TrayIcon trayIcon;//����ͼ��
    private SystemTray systemTray;//ϵͳ����
         
    public TrayDemo()
    {
        //super("ϵͳ����ͼ��");
        systemTray = SystemTray.getSystemTray();//���ϵͳ���̵�ʵ�� 
        setSize(150,150); 
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setVisible(true);    
        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("Sync-icon3.png")));
            systemTray.add(trayIcon);//�������̵�ͼ�꣬0.gif������ļ�ͬһĿ¼
        }
        catch (IOException e1) {e1.printStackTrace();}
        catch (AWTException e2) {e2.printStackTrace();}
        
        this.addWindowListener(
                new WindowAdapter(){   
                    public void windowIconified(WindowEvent e){   
                        dispose();//������С��ʱdispose�ô��� 
                    }   
                });
        
        trayIcon.addMouseListener(
                new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        if(e.getClickCount() == 1)//˫�����̴�������
                            setExtendedState(Frame.NORMAL);
                            setVisible(true);
                    }
                });        
    }  

    public static void main(String args[])
    {
        new TrayDemo();
    }
}