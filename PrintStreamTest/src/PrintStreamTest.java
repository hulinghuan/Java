import java.io.*;
public class PrintStreamTest 
{
	public static void main(String[] args)throws IOException
	{
		FileOutputStream fos=null;
		PrintStream ps=null;
		try
		{	fos=new FileOutputStream("C:\\Users\\Leon.Leon-XPS\\Desktop\\Output.txt");
			ps=new PrintStream(fos);
			ps.println("Hello world!\nThis is the beginning of the learning!");
		}catch(IOException iox)
		{
			
		}
		finally
		{
			if(fos!=null)
				fos.close();
			if(ps!=null)
				ps.close();
		}
		System.out.println("Application is going to be terminated!\nPlz check the Output file on your" +
				"desktop");
	}

}
