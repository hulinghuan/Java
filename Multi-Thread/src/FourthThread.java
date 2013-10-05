
public class FourthThread extends Thread{
	@Override
	public void run(){
		for(int i = 0; i < 10; i ++){
			System.out.print("Class 4: " + this.getName() + ": "+ i + " ");
		}
		System.out.println("");
	}
}
