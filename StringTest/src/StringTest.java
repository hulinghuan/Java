
public class StringTest {
	public static void main(String args[]){
		String directory = "D:\\directory1\\111";
		System.out.println(directory.indexOf("directory1"));
		String directory2 = directory.substring(directory.indexOf("directory1"));
		System.out.println(directory2);
	}

}
