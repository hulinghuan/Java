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
		System.out.println("��ӭʹ��ͼ�����ϵͳ");
		jdb.Login();
		while(true)
		{
			System.out.println("��ӭ��½ͼ�����ϵͳ�����ԣ���");
			System.out.println("1:�鿴ͼ�����ݿ�");
			System.out.println("2:����ͼ��");
			System.out.println("3:���ͼ��");
			System.out.println("4:ɾ��ͼ��");
			System.out.println("5.�޸�ͼ��");
			System.out.println("6.�˳�ϵͳ");
			System.out.println("7:�鿴��Ա�����ݿ�");
			System.out.println("8.���һ�Ա��");
			System.out.println("9.��ӻ�Ա��");
			System.out.println("10.�޸Ļ�Ա��");
			System.out.println("11.ɾ����Ա��");
			System.out.println("12.�鿴�ͻ����ݿ�");
			System.out.println("13.���ҿͻ�");
			System.out.println("14.��ӿͻ�");
			System.out.println("15.���Ŀͻ���Ϣ");
			System.out.println("16.ɾ���ͻ�");
			System.out.println("17.�鿴�˻���Ϣ");
			System.out.println("18.����");
			System.out.println("19.�黹ͼ��");
			
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
			default: System.out.println("�������ָ������");
			}
		}
	}

	
}

