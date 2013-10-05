import java.io.*;
import java.util.Scanner;
public class main {
	public static void main(String argc[]) {
		String read;
		double douRadius;
		System.out.println("请输入圆的半径（以小数形式输入）");
		Scanner in= new Scanner(System.in);
		read=in.nextLine();
		//BufferedReader String=new BufferedReader(new InputStreamReader(System.in));
		douRadius=Double.parseDouble(read);
		Circle X= new Circle(douRadius);
		X.printCircle();
		
	}
}
