
public class TestExtends 
{
	protected String Supername;
	
	public TestExtends()
	{
		System.out.println("父类构造函数启动！");
		Supername="这是父类";
	}
	
	public static void main(String[] args)
	{
		SubTestExtends ste=new SubTestExtends();
		ste.print();
		
	}
}
