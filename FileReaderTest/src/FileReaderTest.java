import java.io.*;
/*
 * FileReader Unit is a char
 */
public class FileReaderTest 
{
	public static void main(String[] args) throws IOException
	{
		FileReader fr=null;
		try
		{
			fr=new FileReader("D:\\Coding\\Java\\FileStreamInputTest\\src\\FileStreamInputTest.java");
			char[] cbuf=new char[32];
			int hasread=0;
			while((hasread=fr.read(cbuf))>0)
			{
				System.out.print(new String(cbuf,0,hasread));
			}
		}catch(IOException iox)
		{}
		
		finally
		{
			fr.close();
		}
	}

}
