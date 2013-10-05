
public class main {
	public static void main(String argc[]){
		double douRannum=0;
		String strCode="";
		/*int intTemp=0;
			for(int k=0;k<2000;k++){
				
				while( douRannum<48 || (douRannum>57 && douRannum<65) ||
						(douRannum>90 && douRannum<97) || douRannum>122 ){
					douRannum=Math.random()*74+48;
					intTemp=(int)douRannum;
				}
				strCode+=(char)intTemp;
				douRannum=0;
				//System.out.print(code+"\t");
			}*/
		douRannum = Math.random() * 100;
		int i = (int) douRannum;
		i = i % 4;
			System.out.println(i);
	}
}
