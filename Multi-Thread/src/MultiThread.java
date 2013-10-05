public class MultiThread{
	public static void main(String[] args) throws InterruptedException{
		new FirstThread().start();
		new FirstThread().start();
		new SecondThread().start();
		new SecondThread().start();
		new ThirdThread().start();
		new ThirdThread().start();
		new FourthThread().start();
		new FourthThread().start();
	}

	
}
