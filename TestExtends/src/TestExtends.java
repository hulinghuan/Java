
public class TestExtends 
{
	protected String Supername;
	
	public TestExtends()
	{
		System.out.println("���๹�캯��������");
		Supername="���Ǹ���";
	}
	
	public static void main(String[] args)
	{
		SubTestExtends ste=new SubTestExtends();
		ste.print();
		
	}
}
