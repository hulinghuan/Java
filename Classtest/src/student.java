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
		System.out.println("请输入学生的姓名");
		sname=in.nextLine();
		System.out.println("请输入学生的年龄");
		sage=in.nextLine();
	}
	
}