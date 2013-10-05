import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PrintInfo 
{
	/**
	 * 打印Client信息
	 * @param rs
	 */
	public void PrintClientInfo(ResultSet rs)
	{
		try
		{
			System.out.println(rs.getString("c_id")+" \t"+rs.getString("c_name")
			+" \t"+rs.getString("c_gender")+" \t"
			+rs.getString("c_age")+"\t"+rs.getString("c_bookid1")+"\t"
			+rs.getString("c_bookid2")+"\t"+rs.getString("c_bookid3")+"\t"
			+rs.getString("c_bookid4")+"\t"+rs.getString("c_bookid5"));
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
	}
	/**
	 * 打印Card信息
	 * @param rs
	 */
	public void PrintCardInfo(ResultSet rs)
	{
		try
		{
			System.out.println(rs.getString("ca_id")+" \t\t"+rs.getString("ca_cid")+" \t\t"
			+rs.getString("ca_level")+"\t\t"+rs.getString("ca_date"));
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
	}
	/**
	 * 打印Book信息
	 * @param rs
	 */
	public void PrintBookInfo(ResultSet rs)
	{
		try
		{
			System.out.println(rs.getString("b_id")+" \t"+rs.getString("b_name")+" \t\t"
					+rs.getString("b_publisher")+" \t"+rs.getString("b_status"));
		}catch(SQLException ex)
		{
			System.out.println("SQLException:"+ex.getMessage());
			System.out.println("SQLState:"+ex.getSQLState());
			System.out.println("ErrorCode:"+ex.getErrorCode());
		}
	}
	/**
	 * 打印Client所有信息
	 * @param client
	 */
	public void PrintCLientAllInfo(Client client)
	{
		int[] book=new int[5];
		System.out.println("客户ID：\t客户名：\t性别\t年龄\t持卡卡号\t\t已借书籍");
		System.out.print(client.getId()+"\t");
		System.out.print(client.getName()+"\t");
		System.out.print(client.getGender()+"\t");
		System.out.print(client.getAge()+"\t");
		System.out.print(client.getCardid()+"\t\t");
		book=client.getBookId();
		for(int i=0;i<5;i++)
			System.out.print(book[i]+"\t");
		//System.out.println("已借书籍数:"+client.getBookAmount());
		System.out.println();
		/*try
		{
			System.out.println("客户ID：\t客户名：\t性别\t年龄\t持卡卡号\t\t已借书籍");
			System.out.println(rs.getInt("u_cid")+"\t"+rs.getString("c_name")+"\t"+
			rs.getString("c_gender")+"\t"+rs.getInt("c_age")+"\t"+rs.getInt("ca_cid")+"\t\t"+
			rs.getString("c_bookid1")+"\t"+rs.getString("c_bookid2")+"\t"+rs.getString("c_bookid3")+"\t"+
			rs.getString("c_bookid4")+"\t"+rs.getString("c_bookid5"));
			
		}catch(SQLException ex)
		{
			
		}*/
	}

}
