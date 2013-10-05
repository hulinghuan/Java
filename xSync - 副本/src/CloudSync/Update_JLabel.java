package CloudSync;

import javax.swing.JLabel;

public class Update_JLabel extends Thread{
	
	public Update_JLabel(JLabel j_label, String text_content){
		this.j_label = j_label;
		this.text_content = text_content;
	}
	private JLabel j_label;
	private String text_content;
	
	@Override
	public void run(){
		j_label.setText(text_content);
	}
	

}
