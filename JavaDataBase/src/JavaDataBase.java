import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;


public class JavaDataBase 
{
	

	public static void main(String[] args)
	{
		DataBaseFunction jdb= new DataBaseFunction();
		jdb.initialConnection();
		Scanner input=new Scanner(System.in);
		int ctr=0;
		System.out.println("欢迎使用图书管理系统");
		jdb.Login();
		while(true)
		{
			System.out.println("欢迎登陆图书管理系统（测试）！");
			System.out.println("1:查看图书数据库");
			System.out.println("2:查找图书");
			System.out.println("3:添加图书");
			System.out.println("4:删除图书");
			System.out.println("5.修改图书");
			System.out.println("6.退出系统");
			System.out.println("7:查看会员卡数据库");
			System.out.println("8.查找会员卡");
			System.out.println("9.添加会员卡");
			System.out.println("10.修改会员卡");
			System.out.println("11.删除会员卡");
			System.out.println("12.查看客户数据库");
			System.out.println("13.查找客户");
			System.out.println("14.添加客户");
			System.out.println("15.更改客户信息");
			System.out.println("16.删除客户");
			System.out.println("17.查看账户信息");
			System.out.println("18.借书");
			System.out.println("19.归还图书");
			
			ctr=input.nextInt();	
			switch(ctr)
			{
			case 1 :jdb.bookExecuteSelectAll(); break;
			case 2 :jdb.bookExecuteSelect(); break;
			case 3 :jdb.bookExecuteInsert(); break;
			case 4 :jdb.bookExecuteDelete(); break;
			case 5 :jdb.bookExecuteModify(); break;
			case 6: return;
			case 7 :jdb.cardExecuteSelectAll(); break;
			case 8 :jdb.cardExecuteSelect(); break;
			case 9 :jdb.cardExecuteInsert(); break;
			case 10:jdb.cardExecuteModify(); break;
			case 11:jdb.cardExecuteDelete(); break;
			case 12:jdb.clientExecuteSelectAll(); break;
			case 13:jdb.clientExecuteSelect(); break;
			case 14:jdb.clientExecuteInsert(); break;
			case 15:jdb.clientExecuteModify(); break;
			case 16:jdb.clientExecuteDelete(); break;
			//case 17:jdb.Login();break;
			case 17:jdb.ViewAccountInfo();break;
			case 18:jdb.BookCheckOut();break;
			case 19:jdb.BookReturn();break;
			default: System.out.println("你输入的指令有误！");
			}
		}
	}

	
}

