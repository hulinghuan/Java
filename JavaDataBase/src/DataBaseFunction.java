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
	 * 通过jdbc连接数据库的连接URL
	 */
	private static String URL ="jdbc:mysql://localhost:3306/hulinghuan?user=root&password=2qase3W3wsdr4E";
	/**
	 * 用于登陆用的user
	 */
	private static String user;
	/**
	 * 用于登陆用的password
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
	 * //完成JDBC数据库的连接初始化工作
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
	 * 浏览BOOK表中所有的数据
	 */
	public void bookExecuteSelectAll()

	{
		int beg=0;
		int scale=10;
		Scanner input=new Scanner(System.in);
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		
		
		System.out.println("图书 ID \t 书名 \t\t\t 出版商\t书籍状态");
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
					System.out.println("按任意键返回主菜单");
					Scanner send=new Scanner(System.in);
					ctr=send.nextLine();
					return;
				}
				rs.beforeFirst();
				
			}
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("按任意键浏览下一页");
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
				System.out.println("按任意键返回主菜单");
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
		
		
		System.out.println("按任意键返回主菜单");
		Scanner send=new Scanner(System.in);
		ctr=send.nextLine();
	}
	
	/**
	 * 对BOOK表进行按照名称查询
	 */
	public void bookExecuteSelect()
	{
		String b_name=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入图书名称：");
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
				System.out.println("未找到任何图书！");
				System.out.println("按任意键返回主菜单");
				ctr=input.nextLine();
				return;
			}
			System.out.println("图书 ID \t 书名 \t\t\t 出版商\t\t书籍状态");
		
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
		System.out.println("按任意键返回主菜单");
		ctr=input.nextLine();
	}

	/**
	 * 插入数据到BOOK表
	 */
	public void bookExecuteInsert()
	{
		String b_name=null;
		String b_publisher=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入添加数据：\n书名\t\t\t出版社");
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
		System.out.println("添加数据成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}
	
	/**
	 * 对BOOK表修改数据
	 */
	public void bookExecuteModify()
	{
		String b_name=null;
		String b_newname=null;
		String b_publisher=null;
		LibrarySQL lsql=new LibrarySQL();
		String ctr=null;
		Scanner input=new Scanner(System.in);
		System.out.println("请输入需改书本的书名：");
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
				System.out.println("要修改的图书未找到！");
				System.out.println("按任意键返回主菜单");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				System.out.println("请输入此图书的新信息\n书名\t\t\t出版社");
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
		System.out.println("修改成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}
	
	/**
	 * 删除BOOK表中数据
	 */
	public void bookExecuteDelete()
	{
		String b_name=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入需要删除书本的书名：");
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
				System.out.println("要修改的图书未找到！");
				System.out.println("按任意键返回主菜单");
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
		System.out.println("删除成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
		
	}
	
	/**
	 * 对会员卡数据库进行浏览
	 */
	public void cardExecuteSelectAll()
	{
		int beg=0;
		int scale=10;
		Scanner input=new Scanner(System.in);
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		
		System.out.println("会员卡ID \t 持有人ID \t 会员卡等级 \t 建卡时间");
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
					System.out.println("按任意键返回主菜单");
					Scanner send=new Scanner(System.in);
					ctr=send.nextLine();
					return;
				}
				
			}
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("按任意键浏览下一页");
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
				System.out.println("按任意键返回主菜单");
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
		
		
		System.out.println("按任意键返回主菜单");
		Scanner send=new Scanner(System.in);
		ctr=send.nextLine();
	}
	
	/**
	 * 会员卡数据库查询
	 */
	public void cardExecuteSelect()
	{
		int ca_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入会员卡卡号：");
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
				System.out.println("未找到此会员卡信息！");
				System.out.println("按任意键返回主菜单");
				ctr=input.nextLine();
				return;
			}
			System.out.println("会员卡ID \t 持有人ID \t 会员卡等级 \t 建卡时间");
		
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
		System.out.println("按任意键返回主菜单");
		ctr=input.nextLine();
	}

	/**
	 * 会员卡添加工作
	 */
	public void cardExecuteInsert()
	{
		int ca_cid=0;
		int ca_level=0;
		String ca_date=null;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入添加数据：\n持卡人ID\t\t\t会员卡等级\t\t\t会员卡创建时间");
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
				System.out.println("您输入的时间不对！");
			}
			
			if(ex.getSQLState().compareTo("23000")==0&&ex.getErrorCode()==1452)
			{
				System.out.println("没有此持卡人！");
			}
			
			System.out.println("按任意键返回主菜单");
			Scanner end=new Scanner(System.in);
			ctr=end.nextLine();
			return;
		}
		System.out.println("添加数据成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * 对card表数据进行修改
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
		System.out.println("请输入需改会员卡ID：");
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
				System.out.println("没有此会员卡！");
				System.out.println("按任意键返回主菜单");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				System.out.println("请输入新的会员卡数据：\n持卡人ID\t\t\t会员卡等级\t\t\t会员卡创建时间");
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
				System.out.println("您输入的时间不对！");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			if(ex.getSQLState().compareTo("23000")==0&&ex.getErrorCode()==1452)
			{
				System.out.println("没有此持卡人！");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("修改成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}
	
	/**
	 * 删除Card表中的数据
	 */
	public void cardExecuteDelete()
	{
		int ca_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入需要删除的会员卡ID：");
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
				System.out.println("没有此会员卡");
				System.out.println("按任意键返回主菜单");
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
		System.out.println("删除成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
		
	}

	/**
	 * 浏览Client表中所有数据
	 */
	public void clientExecuteSelectAll()
	{
		int beg=0;
		int scale=10;
		Scanner input=new Scanner(System.in);
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		
		
		System.out.println("客户ID \t名称 \t 性别 \t 年龄 \t 所持书籍：");
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
					System.out.println("按任意键返回主菜单");
					Scanner send=new Scanner(System.in);
					ctr=send.nextLine();
					return;
				}
				
			}
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("按任意键浏览下一页");
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
				System.out.println("按任意键返回主菜单");
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
		
		
		System.out.println("按任意键返回主菜单");
		Scanner send=new Scanner(System.in);
		ctr=send.nextLine();
	}

	/**
	 * 查询Client表中数据
	 */
	public void clientExecuteSelect()
	{
		int c_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入客户ID：");
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
				System.out.println("未找到此客户信息！");
				System.out.println("按任意键返回主菜单");
				ctr=input.nextLine();
				return;
			}
			System.out.println("客户ID \t 性别 \t 年龄 \t 所持书籍：");
		
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
		System.out.println("按任意键返回主菜单");
		ctr=input.nextLine();
	}

	/**
	 * 插入信息到Client表中
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
		System.out.println("请输入添加数据：\n 名称 \t 性别 \t 年龄");
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
			
			System.out.println("按任意键返回主菜单");
			Scanner end=new Scanner(System.in);
			ctr=end.nextLine();
			return;
		}
		System.out.println("添加数据成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * 修改Client中的数据
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
		System.out.println("请输入需改客户的ID：");
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
				System.out.println("没有此客户！");
				System.out.println("按任意键返回主菜单");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			else
			{
				System.out.println("请输入新的会员卡数据：\n 名称 \t 性别 \t 年龄");
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
				System.out.println("您输入的时间不对！");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			if(ex.getSQLState().compareTo("23000")==0&&ex.getErrorCode()==1452)
			{
				System.out.println("没有此持卡人！");
				Scanner end=new Scanner(System.in);
				ctr=end.nextLine();
				return;
			}
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		System.out.println("修改成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * 删除Client表中的数据
	 */
	public void clientExecuteDelete()
	{
		int c_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		
		Scanner input=new Scanner(System.in);
		System.out.println("请输入需要删除的客户ID：");
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
				System.out.println("没有此客户");
				System.out.println("按任意键返回主菜单");
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
		System.out.println("删除成功！");
		System.out.println("按任意键返回主菜单");
		Scanner end=new Scanner(System.in);
		ctr=end.nextLine();
	}

	/**
	 * 登陆数据库
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
			System.out.println("请输入用户名：");
			user=input.nextLine();
			System.out.println("请输入密码：");
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
				System.out.println("用户名或密码错误！\n请重新登录");
			}
			else
			{
				System.out.println("登陆成功！");
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
		System.out.println("按任意键返回主菜单");
		ctr=input.nextLine();
	}
	
	/**
	 * 浏览已登陆账户信息
	 */
	public void ViewAccountInfo()
	{
		PrintInfo pi=new PrintInfo();
		LibrarySQL lsql=new LibrarySQL();
		if(status==0)
		{
			System.out.println("您还未登陆！");
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
		System.out.println("请按任意键继续！");
		ctr=input.nextLine();
	}
	
	/**
	 * 用户借书
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
		System.out.println("请输入借阅图书ID");
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
				System.out.println("未找到此图书！");
				System.out.println("按任意键返回主菜单");
				ctr=input.nextLine();
				ctr=input.nextLine();
				return;
			}
			//判断目标图书是否在图书馆里
			if(rs.getString("b_status").compareTo("in")==0)
			{
				//判断用户是否还有借书资格
				if(client.addBook(stmt,b_id)==1)
				{
					
				}
				
			}
			else
			{
				System.out.println("此图书已被借走");
			}
			
				
		}catch(SQLException ex)
		{	
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
			
		}
		ctr=input.nextLine();
		System.out.println("请按任意键继续！");
		ctr=input.nextLine();
		
		
	}
	
	/**
	 * 归还图书
	 */
	public void BookReturn()
	{
		int b_id=0;
		String ctr=null;
		LibrarySQL lsql=new LibrarySQL();
		PrintInfo pi=new PrintInfo();
		//String b_publisher=null;
		Scanner input=new Scanner(System.in);
		System.out.println("请输入要归还的图书ID");
		b_id=input.nextInt();
		if(client.ReturnBookDelete(b_id,stmt)==1)
		{
			System.out.println("归还成功");
			System.out.println("请按任意键继续！");
			ctr=input.nextLine();
			return;
		}
		
		System.out.println("您没有此图书");
		System.out.println("请按任意键继续！");
		ctr=input.nextLine();
	}
}
