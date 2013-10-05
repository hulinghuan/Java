import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Client extends People
{
	//private int m_id;
	private int m_uid;					//Client的用户登录id
	private int m_cid;					//Client的cardid
	private int[] m_bookid;				//Client的book数组
	private String[] sm_bookid;			//Client的bookString数组
	private int m_bookamount;			//Client的书总量
	private String[] m_logs;			//人物操作记录修改日志（未实现）
	private int m_level;				//Client的借书等级
	
	/**
	 * 初始化用户登录UID
	 * @param nuid 
	 */
	public void SetUid(int nuid)
	{
		m_uid=nuid;	
		
	}
	/**
	 * 初始化Client的卡ID
	 * @param ncardid
	 */
	public void SetCardid (int ncardid)
	{
		this.m_cid=ncardid;
	}
	/**
	 * 初始化Client所拥有的bookid
	 * @param book
	 */
	public void SetBook(int[] book)
	{
		//遍历内部book数组，如果遇0，则将“null”写入对应的sm_bookid中，否则用m_bookid对对应的sm_bookid赋值
		//完成迁移工作
		for(int i=0;i<5;i++)
		{
			this.m_bookid[i]=book[i];
			if(m_bookid[i]!=0)
			{
				
				sm_bookid[i]=String.valueOf(m_bookid[i]);
				
			}
			else
				sm_bookid[i]="null";
		}
		
	}
	
	/**
	 * 初始化Client已经借出书刊的总数
	 * @param nbookamount
	 */
	public void SetBookAmount(int nbookamount)
	{
		m_bookamount=nbookamount;
	}
	
	/**
	 * 设置Client拥有的卡的等级
	 * @param level
	 */
	public void SetClientLevel(int level)
	{
		m_level=level;
	}
	
	/**
	 * 构造函数，对Client进行一些初始化工作
	 */
	public Client()
	{
		
		m_cid=0;
		m_bookid=new int[5];
		sm_bookid=new String[5];
		for(int i=0;i<m_bookid.length;i++)
			m_bookid[i]=0;
		m_bookamount=0;
		m_logs=null;
		m_level=0;
	}

	/**
	 * 得到登录相关信息功能
	 * @param stmt
	 */
	public void getLoginInfo(Statement stmt)
	{
		try
		{
			//判断rs是否有信息，没有将m_cid赋值为-1，若有结果，则表示登陆成功，将u_cid的值
			//赋值给Client中的m_cid
			ResultSet rs=null;
			rs=stmt.getResultSet();
			rs.beforeFirst();
			if(rs.next())
			{
				this.SetId(rs.getInt("u_id"));
				m_cid=rs.getInt("u_cid");
				//stmt.execute(sql)
				//m_level=1;
			}
			else
			{
				this.SetId(-1);
				m_cid=-1;
			}
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
	}
	
	/**
	 * 得到Client所有信息
	 * @param stmt
	 */
	public void getClientInfo(Statement stmt)
	{
		PrintInfo pi=new PrintInfo();
		int[] book=new int[5];
		try
		{
			//函数外部执行SQL语句之后，判断rs中是否有信息，若有，则对所有的private成员进行赋值
			ResultSet rs=null;
			rs=stmt.getResultSet();
			rs.beforeFirst();
			if(rs.next())
			{
				//pi.PrintCLientAllInfo(rs);
				SetUid(rs.getInt("u_id"));
				SetCardid(rs.getInt("c_cardid"));
				SetClientLevel(rs.getInt("ca_level"));
				book[0]=rs.getInt("c_bookid1");
				book[1]=rs.getInt("c_bookid2");
				book[2]=rs.getInt("c_bookid3");
				book[3]=rs.getInt("c_bookid4");
				book[4]=rs.getInt("c_bookid5");
				SetBook(book);
				this.SetId(rs.getInt("c_id"));
				this.SetName(rs.getString("c_name"));
				this.SetGender(rs.getString("c_gender"));
				this.SetAge(rs.getInt("c_age"));
				m_bookamount=rs.getInt("bookamount");
				
			}
			
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
	}
	
	/**
	 * 借图书相关功能
	 * @param stmt
	 * @param bid
	 * @return
	 */
	public int addBook(Statement stmt,int bid)
	{
		//检查持卡人等级和现有书籍数量
		LibrarySQL lsql=new LibrarySQL();
		//判断当期持卡人所持会员卡的等级，并根据此等级来定义持卡人书籍最多能借出的上限
		//等级1为两本，等级2为3本，等级3为5本
		int nAmountCtr=0;
		
		if(m_level==1)
			nAmountCtr=2;
		if(m_level==2)
			nAmountCtr=3;
		if(m_level==3)
			nAmountCtr=5;
		//判断当期此Client是否还有借书资格
		if(nAmountCtr<=m_bookamount)
		{
			System.out.println("借书数量已达上限，请先归还图书，再借阅图书");
			return -1;
		}
		try
		{
			//将数据库中book表中的对应图书的b_status标记为out
			stmt.executeUpdate(lsql.SQLBookCheckOut(bid));
			//将借出的这本书的ID赋值给Client中空的m_bookid中
			for(int i=0;i<nAmountCtr;i++)
			{
				if(m_bookid[i]==0)
					{
						m_bookid[i]=bid;
						break;
					}
			}
			//当前借书总量自加1
			m_bookamount++;
			//更新Client中的bookid数组
			SetBook(m_bookid);
			//对数据库执行更新Client的工作
			UpdateInfo(stmt);
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
		return 1;
	}
	
	/**
	 * 更新Client的所有信息到数据库
	 * @param stmt
	 */
	public void UpdateInfo(Statement stmt)
	{
		LibrarySQL lsql=new LibrarySQL();
		
		try
		{
			stmt.executeUpdate(lsql.SQLClientInfoUpdate(this.getId(), this.getName(), this.getGender(),
			this.getAge(), sm_bookid, m_cid, m_bookamount));
			
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
		
	}
	
	/**
	 * 归还图书
	 * @param b_id
	 * @param stmt
	 * @return
	 */
	public int ReturnBookDelete(int b_id,Statement stmt)
	{
		//判断输入的b_id是否能在Client的m_bookid中找到，若找到则赋值为null，借书总数自减1，函数返回1
		for(int i=0;i<m_bookamount;i++)
			if(m_bookid[i]==b_id)
			{
				m_bookid[i]=0;
				sm_bookid[i]="null";
				m_bookamount--;
				this.UpdateInfo(stmt);
				return 1;
			}
		//若没找到，则函数返回0
		return 0;
	}

	/**
	 * 返回Client所持有的会员卡等级
	 * @return
	 */
	public int getLevel()
	{
		return m_level;
	}
	
	/**
	 * 返回Client所有图书信息
	 * @return
	 */
	public int[] getBookId()
	{
		return m_bookid;
	}
	
	/**
	 * 返回已借图书总数
	 * @return
	 */
	public int getBookAmount()
	{
		return m_bookamount;
	}
	
	/**
	 * 返回Client所持会员卡ID
	 * @return
	 */
	public int getCardid()
	{
		return m_cid;
	}
	
	/**
	 * 返回用户登录的UID
	 * @return
	 */
	public int getUID()
	{
		return m_uid;
	}
}
