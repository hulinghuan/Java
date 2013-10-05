
public class SubTestExtends extends TestExtends
{
	private String Subname;
	public SubTestExtends()
	{
		System.out.println("子类构造程序已启用！");
		Subname="这是子类";
	}
	public void print()
	{
		System.out.println(Subname);
		System.out.println(Supername);
	}
	
}
