import java.util.Scanner;
public class student{
	private String sname;
	private String sage;
	public void prtstuinfo()
	{
		System.out.println("the student name is:"+sname);
		System.out.println("the student age is:"+sage);
		
	}
	public void getstuinfo()
	{
		Scanner in =new Scanner(System.in);
		System.out.println("������ѧ��������");
		sname=in.nextLine();
		System.out.println("������ѧ��������");
		sage=in.nextLine();
	}
	
}