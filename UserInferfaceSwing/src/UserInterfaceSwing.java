import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class UserInterfaceSwing implements ActionListener{
	private static JFrame frame = new JFrame("xSync 1.1 - Leon");   
	private static Container con = new Container();
	private static JButton button_add_directory = new JButton("Submit");
	private static JTextField text_filed = new JTextField("Neel");
	private static JTextField text_filed2 = new JTextField("");
	
	
	public static void main(String[] args) {
		UserInterfaceSwing uif = new UserInterfaceSwing();
	}
	
	public UserInterfaceSwing(){
		frame.add(con);
		//frame.setLocation(new Point((int) (lx / 2) - 250, (int) (ly / 2) - 250));// ����������������  
        frame.setSize(400, 440);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con.add(button_add_directory);
        con.add(text_filed);
        con.add(text_filed2);
        button_add_directory.setBounds(200, 100, 90, 20);
        text_filed2.setBounds(250, 150, 100, 25);
        text_filed.setBounds(140, 15, 100, 25);
        button_add_directory.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(button_add_directory)){
			this.text_filed2.setText(this.text_filed.getText());
		}
		
	}

}
