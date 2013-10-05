import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Client extends People
{
	//private int m_id;
	private int m_uid;					//Client���û���¼id
	private int m_cid;					//Client��cardid
	private int[] m_bookid;				//Client��book����
	private String[] sm_bookid;			//Client��bookString����
	private int m_bookamount;			//Client��������
	private String[] m_logs;			//���������¼�޸���־��δʵ�֣�
	private int m_level;				//Client�Ľ���ȼ�
	
	/**
	 * ��ʼ���û���¼UID
	 * @param nuid 
	 */
	public void SetUid(int nuid)
	{
		m_uid=nuid;	
		
	}
	/**
	 * ��ʼ��Client�Ŀ�ID
	 * @param ncardid
	 */
	public void SetCardid (int ncardid)
	{
		this.m_cid=ncardid;
	}
	/**
	 * ��ʼ��Client��ӵ�е�bookid
	 * @param book
	 */
	public void SetBook(int[] book)
	{
		//�����ڲ�book���飬�����0���򽫡�null��д���Ӧ��sm_bookid�У�������m_bookid�Զ�Ӧ��sm_bookid��ֵ
		//���Ǩ�ƹ���
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
	 * ��ʼ��Client�Ѿ�����鿯������
	 * @param nbookamount
	 */
	public void SetBookAmount(int nbookamount)
	{
		m_bookamount=nbookamount;
	}
	
	/**
	 * ����Clientӵ�еĿ��ĵȼ�
	 * @param level
	 */
	public void SetClientLevel(int level)
	{
		m_level=level;
	}
	
	/**
	 * ���캯������Client����һЩ��ʼ������
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
	 * �õ���¼�����Ϣ����
	 * @param stmt
	 */
	public void getLoginInfo(Statement stmt)
	{
		try
		{
			//�ж�rs�Ƿ�����Ϣ��û�н�m_cid��ֵΪ-1�����н�������ʾ��½�ɹ�����u_cid��ֵ
			//��ֵ��Client�е�m_cid
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
	 * �õ�Client������Ϣ
	 * @param stmt
	 */
	public void getClientInfo(Statement stmt)
	{
		PrintInfo pi=new PrintInfo();
		int[] book=new int[5];
		try
		{
			//�����ⲿִ��SQL���֮���ж�rs���Ƿ�����Ϣ�����У�������е�private��Ա���и�ֵ
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
	 * ��ͼ����ع���
	 * @param stmt
	 * @param bid
	 * @return
	 */
	public int addBook(Statement stmt,int bid)
	{
		//���ֿ��˵ȼ��������鼮����
		LibrarySQL lsql=new LibrarySQL();
		//�жϵ��ڳֿ������ֻ�Ա���ĵȼ��������ݴ˵ȼ�������ֿ����鼮����ܽ��������
		//�ȼ�1Ϊ�������ȼ�2Ϊ3�����ȼ�3Ϊ5��
		int nAmountCtr=0;
		
		if(m_level==1)
			nAmountCtr=2;
		if(m_level==2)
			nAmountCtr=3;
		if(m_level==3)
			nAmountCtr=5;
		//�жϵ��ڴ�Client�Ƿ��н����ʸ�
		if(nAmountCtr<=m_bookamount)
		{
			System.out.println("���������Ѵ����ޣ����ȹ黹ͼ�飬�ٽ���ͼ��");
			return -1;
		}
		try
		{
			//�����ݿ���book���еĶ�Ӧͼ���b_status���Ϊout
			stmt.executeUpdate(lsql.SQLBookCheckOut(bid));
			//��������Ȿ���ID��ֵ��Client�пյ�m_bookid��
			for(int i=0;i<nAmountCtr;i++)
			{
				if(m_bookid[i]==0)
					{
						m_bookid[i]=bid;
						break;
					}
			}
			//��ǰ���������Լ�1
			m_bookamount++;
			//����Client�е�bookid����
			SetBook(m_bookid);
			//�����ݿ�ִ�и���Client�Ĺ���
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
	 * ����Client��������Ϣ�����ݿ�
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
	 * �黹ͼ��
	 * @param b_id
	 * @param stmt
	 * @return
	 */
	public int ReturnBookDelete(int b_id,Statement stmt)
	{
		//�ж������b_id�Ƿ�����Client��m_bookid���ҵ������ҵ���ֵΪnull�����������Լ�1����������1
		for(int i=0;i<m_bookamount;i++)
			if(m_bookid[i]==b_id)
			{
				m_bookid[i]=0;
				sm_bookid[i]="null";
				m_bookamount--;
				this.UpdateInfo(stmt);
				return 1;
			}
		//��û�ҵ�����������0
		return 0;
	}

	/**
	 * ����Client�����еĻ�Ա���ȼ�
	 * @return
	 */
	public int getLevel()
	{
		return m_level;
	}
	
	/**
	 * ����Client����ͼ����Ϣ
	 * @return
	 */
	public int[] getBookId()
	{
		return m_bookid;
	}
	
	/**
	 * �����ѽ�ͼ������
	 * @return
	 */
	public int getBookAmount()
	{
		return m_bookamount;
	}
	
	/**
	 * ����Client���ֻ�Ա��ID
	 * @return
	 */
	public int getCardid()
	{
		return m_cid;
	}
	
	/**
	 * �����û���¼��UID
	 * @return
	 */
	public int getUID()
	{
		return m_uid;
	}
}
