import java.io.*;
public class FileCopyTest 
{
	public static void main(String[] args)throws IOException
	{
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try
		{
			fis=new FileInputStream("D:\\Coding\\Java\\FileCopyTest\\src\\FileCopyTest.java");
			fos=new FileOutputStream("D:\\Coding\\Java\\FileCopyTest\\src\\Output.txt");
			byte[] bbuf=new byte[32];
			int hasread=0;
			while((hasread=fis.read(bbuf))>0)
			{
				fos.write(bbuf,0,bbuf.length);
			}
		}catch(IOException iox)
		{
			
		}
		finally
		{
			fos.close();
			fis.close();
		}
		System.out.println("application is terminated\nCheck the output file in your directory");
	}

}
