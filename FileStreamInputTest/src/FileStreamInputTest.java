import java.io.*;
/*
 * FileInputStream Unit is a Byte
 */
public class FileStreamInputTest 
{
	public static void main(String[] args) throws IOException
	{
		FileInputStream fis=null;
		try{
			fis=new FileInputStream("D:\\Coding\\Java\\FileStreamInputTest\\src\\FileStreamInputTest.java");
		
			byte[] bbuf=new byte[1024];
			int hasread=0;
			while((hasread=fis.read(bbuf))>0)
			{
				System.out.print(new String(bbuf,0,hasread));
				//for(int i=0;i<bbuf.length;i++)
				//	System.out.println(bbuf[i]);
			}
			}
		catch(IOException iox)
		{}
		finally
		{
		System.out.println("FileInputStream is going to close");
		fis.close();
		}
	}

}
