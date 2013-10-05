import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LibraryCard extends Card
{
	private int m_bookconstraint;
	private int m_clientid;
	
	public int getBookConstraint()
	{
		return m_bookconstraint;
	}
	
	public LibraryCard()
	{
		m_bookconstraint=0;
		m_clientid=0;
	}
	public void SetCardInfo(ResultSet rs)
	{
		
	}
	
}
