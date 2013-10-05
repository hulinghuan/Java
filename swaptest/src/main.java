
public class main {
	public static void main(String argc[])
	{
		int a=3,b=4;
		swap(a,b);
		System.out.printf("%d %d", a,b);
	}
	public static void swap(int a,int b)
	{
		int temp=a;
		a=b;
		b=temp;
	}
}
