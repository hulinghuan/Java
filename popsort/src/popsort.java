import java.util.Scanner;
public class popsort {
	
	private int[] a;
	
	public void imput()
	{
		System.out.println("please input 10 numbers:");
		a =new int[10];
		Scanner in= new Scanner(System.in);
		for(int i=0;i<a.length;i++)
		{
			a[i]=in.nextInt();
		}
		
	}
	
	public void sort()
	{
		int temp;
		for(int i=0;i<a.length;i++)
			for(int j=0;j<a.length-i-1;j++)
			{
				if(a[j]>a[j+1])
				{
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
	}
	
	public void prtsortedarray()
	{
		System.out.println("the array which have been sorted is:");
		for(int i=0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
	}
	

}
