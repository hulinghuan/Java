
public class main {
	public static void main(String argc[]){
		double a;
		long result;
		
		for(int i=0;i<10;i++){
			for(int k=0;k<10;k++){
				a=Math.random();
				result=(long)(a*99+1);
				System.out.print(result+"\t");
			}
			System.out.println();
		}
	}
}
