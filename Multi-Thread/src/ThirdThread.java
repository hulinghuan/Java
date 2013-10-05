
public class ThirdThread extends Thread{
	@Override
	public void run(){
		for(int i = 0; i < 10; i ++){
			System.out.print("Class 3: " + this.getName() + ": "+ i + " ");
		}
		System.out.println("");
	}
}
