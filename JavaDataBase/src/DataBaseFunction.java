import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DataBaseFunction 
{

	private static Statement stmt;
	private static ResultSet rs;
	private static Connection connection;
	/**
	 * ͨ��jdbc�������ݿ������URL
	 */
	private static String URL ="jdbc:mysql://localhost:3306/hulinghuan?user=root&password=2qase3W3wsdr4E";
	/**
	 * ���ڵ�½�õ�user
	 */
	private static String user;
	/**
	 * ���ڵ�½�õ�password
	 */
	private static String password;
	private static Client client;
	private static Card card;
	private static Administrator admin;
	private static int status;
	
	public ResultSet getrs()
	{
		return this.rs;
	}
	public DataBaseFunction()
	{
		stmt=null;
		rs=null;
		connection= null;
		user=null;
		password=null;
		client=new Client();
		card=new Card();
		admin=new Administrator();
		status=0;
		
	}
	/**
	 * //���JDBC���ݿ�����ӳ�ʼ������
	 */
	public void initialConnection() 
	{
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			
		}catch (Exception ex)
		{
		}
		
		
		try
		{
	//		connection = DriverManager.getConnection("jdbc:mysql://localhost/hulinghuan?"+
		//"user=root&password=2qase3W3wsdr4E");
			connection = DriverManager.getConnection(URL);
			
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
			
		}	
	}

	/**
	 * ���BOOK�������е�����
	 */
	public void bookExecuteSelectAll()

	{
		int beg=0;
		int scale=10;
		Scanner input=new Scanner(System.in);
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		
		
		System.out.println("ͼ�� ID \t ���� \t\t\t ������\t�鼮״̬");
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLBookSelectAll(beg, scale)))
			{
				rs= stmt.getResultSet();
				rs.beforeFirst();	
				while(rs.next())
				{
					pi.PrintBookInfo(rs);
				}
				stmt.execute(lsql.SQLBookSelectAll(beg+10, scale));
				rs= stmt.getResultSet();
				rs.beforeFirst();
				if(!rs.next())
				{
					System.out.println("��������������˵�");
					Scanner send=new Scanner(System.in);
					ctr=send.nextLine();
					return;
				}
				rs.beforeFirst();
				
			}
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("������������һҳ");
				ctr=input.nextLine();
				beg+=10;
				if(stmt.execute(lsql.SQLBookSelectAll(beg, scale)))
				{
					rs= stmt.getResultSet();
					rs.beforeFirst();	
					while(rs.next())
					{
						pi.PrintBookInfo(rs);
					}
					
				}
			rs.beforeFirst();
			//stmt.execute("SELECT * FROM book limit "+String.valueOf(beg+10)+","+String.valueOf(scale));
			stmt.execute(lsql.SQLBookSelectAll(beg+10, scale));
			rs= stmt.getResultSet();
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("��������������˵�");
				Scanner send=new Scanner(System.in);
				ctr=send.nextLine();
				return;
			}
			rs.beforeFirst();
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
		
		System.out.println("��������������˵�");
		Scanner send=new Scanner(System.in);
		ctr=send.nextLine();
	}
	
	/**
	 * ��BOOK����а������Ʋ�ѯ
	 */
	public void bookExecuteSelect()
	{
		String b_name=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		
		Scanner input=new Scanner(System.in);
		System.out.println("������ͼ�����ƣ�");
		b_name=input.nextLine();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLBookSelect(b_name)));
			{
				rs= stmt.getResultSet();
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
		
		try
		{
			rs.beforeFirst();
			
			if(!rs.next())
			{
				System.out.println("δ�ҵ��κ�ͼ�飡");
				System.out.println("��������������˵�");
				ctr=input.nextLine();
				return;
			}
			System.out.println("ͼ�� ID \t ���� \t\t\t ������\t\t�鼮״̬");
		
		rs.beforeFirst();
		while(rs.next())
		{
			
			pi.PrintBookInfo(rs);
		}
		
		}catch (SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("��������������˵�");
		ctr=input.nextLine();
	}

	/**
	 * �������ݵ�BOOK��
	 */
	public void bookExecuteInsert()
	{
		String b_name=null;
		String b_publisher=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("������������ݣ�\n����\t\t\t������");
		b_name=input.next();
		b_publisher=input.next();
		try
		{
			stmt=connection.createStatement();
			stmt.executeUpdate(lsql.SQLBookInsert(b_name, b_publisher));
		}catch (SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("������ݳɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}
	
	/**
	 * ��BOOK���޸�����
	 */
	public void bookExecuteModify()
	{
		String b_name=null;
		String b_newname=null;
		String b_publisher=null;
		LibrarySQL lsql=new LibrarySQL();
		String ctr=null;
		Scanner input=new Scanner(System.in);
		System.out.println("����������鱾��������");
		b_name=input.next();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLBookSelect(b_name)))
			{
				rs= stmt.getResultSet();
			}
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("Ҫ�޸ĵ�ͼ��δ�ҵ���");
				System.out.println("��������������˵�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				System.out.println("�������ͼ�������Ϣ\n����\t\t\t������");
				b_newname=input.next();
				b_publisher=input.next();
				stmt.executeUpdate(lsql.SQLBookModify(b_newname, b_publisher, b_name));
			}
			
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("�޸ĳɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}
	
	/**
	 * ɾ��BOOK��������
	 */
	public void bookExecuteDelete()
	{
		String b_name=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("��������Ҫɾ���鱾��������");
		b_name=input.next();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLBookSelect(b_name)))
			{
				rs= stmt.getResultSet();
			}
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("Ҫ�޸ĵ�ͼ��δ�ҵ���");
				System.out.println("��������������˵�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				stmt.executeUpdate(lsql.SQLBookDelete(b_name));
			}
			
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("ɾ���ɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
		
	}
	
	/**
	 * �Ի�Ա�����ݿ�������
	 */
	public void cardExecuteSelectAll()
	{
		int beg=0;
		int scale=10;
		Scanner input=new Scanner(System.in);
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		
		System.out.println("��Ա��ID \t ������ID \t ��Ա���ȼ� \t ����ʱ��");
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLCardSelectAll(beg, scale)))
			{
				rs= stmt.getResultSet();
				rs.beforeFirst();	
				while(rs.next())
				{
					pi.PrintCardInfo(rs);
				}
				stmt.execute(lsql.SQLCardSelectAll(beg+10, scale));
				rs= stmt.getResultSet();
				rs.beforeFirst();
				if(!rs.next())
				{
					System.out.println("��������������˵�");
					Scanner send=new Scanner(System.in);
					ctr=send.nextLine();
					return;
				}
				
			}
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("������������һҳ");
				ctr=input.nextLine();
				beg+=10;
				if(stmt.execute(lsql.SQLCardSelectAll(beg, scale)))
				{
					rs= stmt.getResultSet();
					rs.beforeFirst();	
					while(rs.next())
					{
						pi.PrintCardInfo(rs);
					}
					
				}
			rs.beforeFirst();
			//stmt.execute("SELECT * FROM book limit "+String.valueOf(beg+10)+","+String.valueOf(scale));
			stmt.execute(lsql.SQLCardSelectAll(beg+10, scale));
			rs= stmt.getResultSet();
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("��������������˵�");
				Scanner send=new Scanner(System.in);
				ctr=send.nextLine();
				return;
			}
			rs.beforeFirst();
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
		
		System.out.println("��������������˵�");
		Scanner send=new Scanner(System.in);
		ctr=send.nextLine();
	}
	
	/**
	 * ��Ա�����ݿ��ѯ
	 */
	public void cardExecuteSelect()
	{
		int ca_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		
		Scanner input=new Scanner(System.in);
		System.out.println("�������Ա�����ţ�");
		ca_id=input.nextInt();
		ctr=input.nextLine();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLCardSelect(ca_id)));
			{
				rs= stmt.getResultSet();
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
		
		try
		{
			rs.beforeFirst();
			
			if(!rs.next())
			{
				System.out.println("δ�ҵ��˻�Ա����Ϣ��");
				System.out.println("��������������˵�");
				ctr=input.nextLine();
				return;
			}
			System.out.println("��Ա��ID \t ������ID \t ��Ա���ȼ� \t ����ʱ��");
		
		rs.beforeFirst();
		while(rs.next())
		{
			
			pi.PrintCardInfo(rs);
		}
		
		}catch (SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("��������������˵�");
		ctr=input.nextLine();
	}

	/**
	 * ��Ա����ӹ���
	 */
	public void cardExecuteInsert()
	{
		int ca_cid=0;
		int ca_level=0;
		String ca_date=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("������������ݣ�\n�ֿ���ID\t\t\t��Ա���ȼ�\t\t\t��Ա������ʱ��");
		ca_cid=input.nextInt();
		ca_level=input.nextInt();
		ca_date=input.next();
		ctr=input.nextLine();
		try
		{
			stmt=connection.createStatement();
			stmt.executeUpdate(lsql.SQLCardInsert(ca_cid, ca_level,ca_date));
		}catch (SQLException ex)
		{
			/*System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());*/
			if(ex.getSQLState().compareTo("22001")==0&&ex.getErrorCode()==1292)
			{
				System.out.println("�������ʱ�䲻�ԣ�");
			}
			
			if(ex.getSQLState().compareTo("23000")==0&&ex.getErrorCode()==1452)
			{
				System.out.println("û�д˳ֿ��ˣ�");
			}
			
			System.out.println("��������������˵�");
			Scanner end=new Scanner(System.in);
			ctr=end.nextLine();
			return;
		}
		System.out.println("������ݳɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * ��card�����ݽ����޸�
	 */
	public void cardExecuteModify()
	{
		int ca_cid=0;
		int ca_id=0;
		int ca_level=0;
		String ca_date=null;
		LibrarySQL lsql=new LibrarySQL();
		String ctr=null;
		Scanner input=new Scanner(System.in);
		System.out.println("��������Ļ�Ա��ID��");
		ca_id=input.nextInt();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLCardSelect(ca_id)))
			{
				rs= stmt.getResultSet();
			}
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("û�д˻�Ա����");
				System.out.println("��������������˵�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				System.out.println("�������µĻ�Ա�����ݣ�\n�ֿ���ID\t\t\t��Ա���ȼ�\t\t\t��Ա������ʱ��");
				ca_cid=input.nextInt();
				ca_level=input.nextInt();
				ca_date=input.nextLine();
				//ctr=input.nextLine();
				stmt.executeUpdate(lsql.SQLCardModify(ca_id, ca_cid, ca_level, ca_date));
			}
			
		}catch(SQLException ex)
		{
			if(ex.getSQLState().compareTo("22001")==0&&ex.getErrorCode()==1292)
			{
				System.out.println("�������ʱ�䲻�ԣ�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			if(ex.getSQLState().compareTo("23000")==0&&ex.getErrorCode()==1452)
			{
				System.out.println("û�д˳ֿ��ˣ�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("�޸ĳɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}
	
	/**
	 * ɾ��Card���е�����
	 */
	public void cardExecuteDelete()
	{
		int ca_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("��������Ҫɾ���Ļ�Ա��ID��");
		ca_id=input.nextInt();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLCardSelect(ca_id)))
			{
				rs= stmt.getResultSet();
			}
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("û�д˻�Ա��");
				System.out.println("��������������˵�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				stmt.executeUpdate(lsql.SQLCardDelete(ca_id));
			}
			
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("ɾ���ɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
		
	}

	/**
	 * ���Client������������
	 */
	public void clientExecuteSelectAll()
	{
		int beg=0;
		int scale=10;
		Scanner input=new Scanner(System.in);
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		
		
		System.out.println("�ͻ�ID \t���� \t �Ա� \t ���� \t �����鼮��");
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLClientSelectAll(beg, scale)))
			{
				rs= stmt.getResultSet();
				rs.beforeFirst();	
				while(rs.next())
				{
					System.out.println(rs.getString("c_id")+" \t"+rs.getString("c_name")
					+" \t"+rs.getString("c_gender")+" \t"
					+rs.getString("c_age")+"\t"+rs.getString("c_bookid1")+"\t"
					+rs.getString("c_bookid2")+"\t"+rs.getString("c_bookid3")+"\t"
					+rs.getString("c_bookid4")+"\t"+rs.getString("c_bookid5"));
				}
				stmt.execute(lsql.SQLClientSelectAll(beg+10, scale));
				rs= stmt.getResultSet();
				rs.beforeFirst();
				if(!rs.next())
				{
					System.out.println("��������������˵�");
					Scanner send=new Scanner(System.in);
					ctr=send.nextLine();
					return;
				}
				
			}
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("������������һҳ");
				ctr=input.nextLine();
				beg+=10;
				if(stmt.execute(lsql.SQLClientSelectAll(beg, scale)))
				{
					rs= stmt.getResultSet();
					rs.beforeFirst();	
					while(rs.next())
					{
						pi.PrintClientInfo(rs);
					}
					
				}
			rs.beforeFirst();
			//stmt.execute("SELECT * FROM book limit "+String.valueOf(beg+10)+","+String.valueOf(scale));
			stmt.execute(lsql.SQLClientSelectAll(beg+10, scale));
			rs= stmt.getResultSet();
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("��������������˵�");
				Scanner send=new Scanner(System.in);
				ctr=send.nextLine();
				return;
			}
			rs.beforeFirst();
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
		
		System.out.println("��������������˵�");
		Scanner send=new Scanner(System.in);
		ctr=send.nextLine();
	}

	/**
	 * ��ѯClient��������
	 */
	public void clientExecuteSelect()
	{
		int c_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		
		Scanner input=new Scanner(System.in);
		System.out.println("������ͻ�ID��");
		c_id=input.nextInt();
		ctr=input.nextLine();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLClientSelect(c_id)));
			{
				rs= stmt.getResultSet();
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
		
		try
		{
			rs.beforeFirst();
			
			if(!rs.next())
			{
				System.out.println("δ�ҵ��˿ͻ���Ϣ��");
				System.out.println("��������������˵�");
				ctr=input.nextLine();
				return;
			}
			System.out.println("�ͻ�ID \t �Ա� \t ���� \t �����鼮��");
		
		rs.beforeFirst();
		while(rs.next())
		{
			
			pi.PrintClientInfo(rs);
		}
		
		}catch (SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("��������������˵�");
		ctr=input.nextLine();
	}

	/**
	 * ������Ϣ��Client����
	 */
	public void clientExecuteInsert()
	{
		int c_id=0;
		String c_name;
		String c_gender=null;
		int c_age=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("������������ݣ�\n ���� \t �Ա� \t ����");
		c_name=input.next();
		c_gender=input.next();
		c_age=input.nextInt();
		ctr=input.nextLine();
		try
		{
			stmt=connection.createStatement();
			stmt.executeUpdate(lsql.SQLClientInsert(c_name, c_gender, c_age));
		}catch (SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
			
			System.out.println("��������������˵�");
			Scanner end=new Scanner(System.in);
			ctr=end.nextLine();
			return;
		}
		System.out.println("������ݳɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * �޸�Client�е�����
	 */
	public void clientExecuteModify()
	{
		
		int c_id=0;
		int c_age=0;
		String c_name=null;
		String c_gender=null;
		LibrarySQL lsql=new LibrarySQL();
		String ctr=null;
		Scanner input=new Scanner(System.in);
		System.out.println("��������Ŀͻ���ID��");
		c_id=input.nextInt();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLClientSelect(c_id)))
			{
				rs= stmt.getResultSet();
			}
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("û�д˿ͻ���");
				System.out.println("��������������˵�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				System.out.println("�������µĻ�Ա�����ݣ�\n ���� \t �Ա� \t ����");
				c_name=input.next();
				c_gender=input.next();
				c_age=input.nextInt();
				ctr=input.nextLine();
				stmt.executeUpdate(lsql.SQLClientModify(c_id, c_name, c_gender, c_age));
			}
			
		}catch(SQLException ex)
		{
			if(ex.getSQLState().compareTo("22001")==0&&ex.getErrorCode()==1292)
			{
				System.out.println("�������ʱ�䲻�ԣ�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			if(ex.getSQLState().compareTo("23000")==0&&ex.getErrorCode()==1452)
			{
				System.out.println("û�д˳ֿ��ˣ�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("�޸ĳɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * ɾ��Client���е�����
	 */
	public void clientExecuteDelete()
	{
		int c_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("��������Ҫɾ���Ŀͻ�ID��");
		c_id=input.nextInt();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLClientSelect(c_id)))
			{
				rs= stmt.getResultSet();
			}
			rs.beforeFirst();
			if(!rs.next())
			{
				System.out.println("û�д˿ͻ�");
				System.out.println("��������������˵�");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				stmt.executeUpdate(lsql.SQLClientDelete(c_id));
			}
			
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("ɾ���ɹ���");
		System.out.println("��������������˵�");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * ��½���ݿ�
	 */
	public void Login()
	{
		int login=-1;
		String ctr=null;
		Scanner input=new Scanner(System.in);
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		while(login<0)
		{
			System.out.println("�������û�����");
			user=input.nextLine();
			System.out.println("���������룺");
			password=input.nextLine();
			try
			{
				stmt=connection.createStatement();
				if(stmt.execute(lsql.SQLLogin(user, password)));
				{
					client.getLoginInfo(stmt);
				}
			
		
			if(client.getId()==-1||client.getCardid()==-1)
			{
				System.out.println("�û������������\n�����µ�¼");
			}
			else
			{
				System.out.println("��½�ɹ���");
				stmt=connection.createStatement();
				stmt.execute(lsql.SQLClientGetAllInfo(client.getId()));
				client.getClientInfo(stmt);
				status=1;
				login=1;
			}
			}catch(SQLException ex)
			{
				System.out.println("SQLException:"+ex.getMessage());
				System.out.println("SQLState:"+ex.getSQLState());
				System.out.println("ErrorCode:"+ex.getErrorCode());
			}
		}
		System.out.println("��������������˵�");
		ctr=input.nextLine();
	}
	
	/**
	 * ����ѵ�½�˻���Ϣ
	 */
	public void ViewAccountInfo()
	{
		PrintInfo pi=new PrintInfo();
		LibrarySQL lsql=new LibrarySQL();
		if(status==0)
		{
			System.out.println("����δ��½��");
		}
		else
		{
			if(status==1)
			{
				pi.PrintCLientAllInfo(client);
			}
		}
		String ctr=null;
		Scanner input=new Scanner(System.in);
		System.out.println("�밴�����������");
		ctr=input.nextLine();
	}
	
	/**
	 * �û�����
	 */
	public void BookCheckOut()
	{
		int b_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		bookExecuteSelect();
		Scanner input=new Scanner(System.in);
		System.out.println("���������ͼ��ID");
		b_id=input.nextInt();
		try
		{
			stmt=connection.createStatement();
			if(stmt.execute(lsql.SQLBookSelect(b_id)));
			{
				rs= stmt.getResultSet();
			}
			
		
			rs.beforeFirst();
			
			if(!rs.next())
			{
				System.out.println("δ�ҵ���ͼ�飡");
				System.out.println("��������������˵�");
				ctr=input.nextLine();
				ctr=input.nextLine();
				return;
			}
			//�ж�Ŀ��ͼ���Ƿ���ͼ�����
			if(rs.getString("b_status").compareTo("in")==0)
			{
				//�ж��û��Ƿ��н����ʸ�
				if(client.addBook(stmt,b_id)==1)
				{
					
				}
				
			}
			else
			{
				System.out.println("��ͼ���ѱ�����");
			}
			
				
		}catch(SQLException ex)
		{	
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
			
		}
		ctr=input.nextLine();
		System.out.println("�밴�����������");
		ctr=input.nextLine();
		
		
	}
	
	/**
	 * �黹ͼ��
	 */
	public void BookReturn()
	{
		int b_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		Scanner input=new Scanner(System.in);
		System.out.println("������Ҫ�黹��ͼ��ID");
		b_id=input.nextInt();
		if(client.ReturnBookDelete(b_id,stmt)==1)
		{
			System.out.println("�黹�ɹ�");
			System.out.println("�밴�����������");
			ctr=input.nextLine();
			return;
		}
		
		System.out.println("��û�д�ͼ��");
		System.out.println("�밴�����������");
		ctr=input.nextLine();
	}
}
