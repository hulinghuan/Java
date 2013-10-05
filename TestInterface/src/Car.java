
public class Car implements info
{
	public void printinfo()
	{
		System.out.println("We produced a car!");
	}
	
	public static void main(String[] args)
	{
		Car a=new Car();
		a.printinfo();
	}
	
}

interface info
{
	void printinfo();
}
