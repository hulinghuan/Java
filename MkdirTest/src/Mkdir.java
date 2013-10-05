import java.io.File;


public class Mkdir {
	public static void main(String[] args){
		File f = new File("D:\\Program Files");
		if(f.exists()){
			
		}else{
			f.mkdir();
		}
	}
}
