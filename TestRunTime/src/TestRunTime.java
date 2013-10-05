
public class TestRunTime 
{
	public static void main(String[] args)
	{
		Runtime rt= Runtime.getRuntime();
		System.out.println("Max Memory:"+rt.maxMemory());
		System.out.println("Free Memory:"+rt.freeMemory());
		System.out.println("Total Memory:"+rt.totalMemory());
		System.out.println("Available Processors:"+rt.availableProcessors());
		
	}
}
