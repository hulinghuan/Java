import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

public class TrayDemo extends JFrame{
    private TrayIcon trayIcon;//托盘图标
    private SystemTray systemTray;//系统托盘
         
    public TrayDemo()
    {
        //super("系统托盘图标");
        systemTray = SystemTray.getSystemTray();//获得系统托盘的实例 
        setSize(150,150); 
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setVisible(true);    
        try {
            trayIcon = new TrayIcon(ImageIO.read(new File("Sync-icon3.png")));
            systemTray.add(trayIcon);//设置托盘的图标，0.gif与该类文件同一目录
        }
        catch (IOException e1) {e1.printStackTrace();}
        catch (AWTException e2) {e2.printStackTrace();}
        
        this.addWindowListener(
                new WindowAdapter(){   
                    public void windowIconified(WindowEvent e){   
                        dispose();//窗口最小化时dispose该窗口 
                    }   
                });
        
        trayIcon.addMouseListener(
                new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        if(e.getClickCount() == 1)//双击托盘窗口再现
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