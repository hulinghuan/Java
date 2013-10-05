
public class SecondThread extends Thread{
	@Override
	public void run(){
		for(int i = 0; i < 10; i ++){
			System.out.print("Class 2: " + this.getName() + ": "+ i + " ");
		}
		System.out.println("");
	}
}
