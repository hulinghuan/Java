
public class LibrarySQL 
{
	private String SQL;
	public LibrarySQL()
	{
		SQL=null;
	}
	/**
	 * ����鼮���ݿ�
	 * @param beg
	 * @param scale
	 * @return
	 */
	public String SQLBookSelectAll(int beg,int scale)
	{
		SQL="SELECT * FROM book limit "+String.valueOf(beg)+","+String.valueOf(scale);
		return SQL;
	}
	/**
	 * ���鼮���ݿ��������ֲ�ѯ
	 * @param bname
	 * @return
	 */
	public String SQLBookSelect(String bname)
	{
		SQL="SELECT * FROM book where b_name like'%"+bname+"%'";
		return SQL;
	}
	/**
	 * ���鼮���ݿ��а���id��ѯ
	 * @param bid
	 * @return
	 */
	public String SQLBookSelect(int bid)
	{
		SQL="SELECT * FROM book where b_id="+bid+";";
		return SQL;
	}
	/**
	 * ������ݽ����鼮���ݿ�
	 * @param bname
	 * @param bpublisher
	 * @return
	 */
	public String SQLBookInsert(String bname,String bpublisher)
	{
		SQL="Insert INTO book value(null,'"+bname+"','"+bpublisher+"');";
		return SQL;
	}
	/**
	 * �޸��鼮���ݿ��е�ĳһ��Ϣ
	 * @param bnewname	������
	 * @param bnewpublisher	�³�����
	 * @param bname	������
	 * @return
	 */
	public String SQLBookModify(String bnewname,String bnewpublisher,String bname)
	{
		SQL="Update book set b_name='"+bnewname+"', b_publisher='"
				+bnewpublisher+"'where b_name='"+bname+"'";
		return SQL;
	}
	
	/**
	 * ɾ���鼮���ݿ��е�ĳһ��Ϣ
	 * @param bname
	 * @return
	 */
	public String SQLBookDelete(String bname)
	{
		SQL="DELETE FROM book WHERE b_name='"+bname+"'";
		return SQL;
	}
	/**
	 * ���Card���ݿ�ȫ����Ϣ
	 * @param beg
	 * @param scale
	 * @return
	 */
	public String SQLCardSelectAll(int beg,int scale)
	{
		SQL="SELECT * FROM card limit "+String.valueOf(beg)+","+String.valueOf(scale);
		return SQL;
	}
	/**
	 * ��Card���ݿ�����id��ѯ��Ϣ
	 * @param caid
	 * @return
	 */
	public String SQLCardSelect(int caid)
	{
		SQL="SELECT * FROM card where ca_id like'%"+caid+"%'";
		return SQL;
	}
	/**
	 * �����Ϣ����Card���ݿ�
	 * @param cacid
	 * @param calevel
	 * @param cadate
	 * @return
	 */
	public String SQLCardInsert(int cacid,int calevel,String cadate)
	{
		SQL="Insert INTO card value(null,'"+cacid+"','"+calevel+"','"+cadate+"');";
		return SQL;
	}
	/**
	 * �޸�Card���ݿ��е���Ϣ
	 * @param caid
	 * @param cacid
	 * @param calevel
	 * @param cadate
	 * @return
	 */
	public String SQLCardModify(int caid,int cacid,int calevel,String cadate)
	{
		SQL="Update card set ca_cid='"+cacid+"', ca_level='"
				+calevel+"',"+"ca_date='"+cadate+"' where ca_id="+caid+";";
		return SQL;
	}
	/**
	 * ɾ��Card���ݿ��е���Ϣ
	 * @param caid
	 * @return
	 */
	public String SQLCardDelete(int caid)
	{
		SQL="DELETE FROM card WHERE ca_id="+caid+";";
		return SQL;
	}
	/**
	 * ��ѯClient���ݿ��е�������Ϣ
	 * @param beg
	 * @param scale
	 * @return
	 */
	public String SQLClientSelectAll(int beg,int scale)
	{
		SQL="SELECT * FROM client limit "+String.valueOf(beg)+","+String.valueOf(scale);
		return SQL;
	}
	/**
	 * ��Client���ݿ���id��ѯ��Ϣ
	 * @param cid
	 * @return
	 */
	public String SQLClientSelect(int cid)
	{
		SQL="SELECT * FROM client where c_id like'%"+cid+"%'";
		return SQL;
	}
	/**
	 * �����Ϣ����Client���ݿ�
	 * @param cname
	 * @param cgender
	 * @param cage
	 * @return
	 */
	public String SQLClientInsert(String cname,String cgender,int cage)
	{
		SQL="Insert INTO client value(null,'"+cname+"','"+cgender+"',"+cage+",null,null,null,null" +
				",null,null);";
		return SQL;
	}
	/**
	 * �޸�Client��ĳһ����
	 * @param cid
	 * @param cname
	 * @param cgender
	 * @param cage
	 * @return
	 */
	public String SQLClientModify(int cid,String cname,String cgender,int cage)
	{
		SQL="Update client set c_name='"+cname+"', c_gender='"
				+cgender+"',"+"c_age='"+cage+"' where c_id="+cid+";";
		return SQL;
	}
	/**
	 * ɾ��Client���ݿ��е�ĳһ��Ϣ
	 * @param cid
	 * @return
	 */
	public String SQLClientDelete(int cid)
	{
		SQL="DELETE FROM client WHERE c_id="+cid+";";
		return SQL;
	}
	/**
	 * ���û����������½���ݿ�
	 * @param name
	 * @param password
	 * @return
	 */
	public String SQLLogin(String name,String password)
	{
		SQL="Select * from userlogin where u_name='"+name+"'"+"and u_password='"+password+"';";
		return SQL;
	}
	/**
	 * �õ�Client������Ϣ
	 * @param uid
	 * @return
	 */
	public String SQLClientGetAllInfo(int uid)
	{
		SQL="select * from userlogin,client,card where userlogin.u_cid=client.c_id and " +
				"client.c_cardid=card.ca_id and userlogin.u_id="+uid+";";
		return SQL;
	}
	/**
	 * �õ�Client���ֻ�Ա���Ļ�Ա���ȼ�
	 * @param cid
	 * @return
	 */
	public String SQLGetClientCardLevel(int cid)
	{
		SQL="select ca_level from card,client where c_cardid=ca_id and c_id="+cid+";";
		return SQL;
	}
	/**
	 * ͼ����
	 * @param bid
	 * @return
	 */
	public String SQLBookCheckOut(int bid)
	{
		SQL="update book set b_status='out' where b_id="+bid+";";
		return SQL;
	}
	/**
	 * �����ݿ��и���Client��Ϣ
	 * @param cid
	 * @param cname
	 * @param cgender
	 * @param cage
	 * @param book
	 * @param cardid
	 * @param cbookamount
	 * @return
	 */
	public String SQLClientInfoUpdate(int cid,String cname,String cgender,int cage,String[] book,int cardid,
			int cbookamount)
	{
		SQL="update client set c_gender='"+cgender+"',c_age="+cage+",c_cardid="+cardid+
				",c_bookid1="+book[0]+",c_bookid2="+book[1]+",c_bookid3="+book[2]+",c_bookid4="+book[3]+
				",c_bookid5="+book[4]+",bookamount="+cbookamount+" where c_id="+cid+";";
		return SQL;
	}

}
