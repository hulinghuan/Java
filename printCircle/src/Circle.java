import java.lang.Math;
public class Circle {
	
	public Circle(double x){
		douRadius=x;
	}
	
	public void printCircle(){
		long lRange;
		lRange=Math.round(douRadius);
		int intRange;
		intRange=(int)lRange;
		long[][] coordinate = new long[2*intRange][2];
		
		
		
		//����lRange�Ƿ�ΪdouRadius������������
		//System.out.println("lRange��");
		//System.out.println(lRange);
		
		//����Բ������
		int temp=0;
		for(int i=intRange;i>0;i--){
			coordinate[temp][0]=Math.round((long)Math.sqrt(douRadius*douRadius-i*i));
			coordinate[temp][1]=Math.round((long)(0-Math.sqrt(douRadius*douRadius-i*i)));
			temp++;
		}
			temp=temp-1;
		for(int i=intRange;i<2*intRange;i++){
			coordinate[i][0]=coordinate[temp][0];
			coordinate[i][1]=coordinate[temp][1];
			temp--;
			}
	
		
		//������Բ��������
		for(int i=0;i<2*intRange;i++){
			coordinate[i][0]+=intRange;
			coordinate[i][1]+=intRange;
		}
			
		//��ӡ������������
		/*for(int i=0;i<2*intRange;i++){
			for(int k=0;k<2;k++){
			System.out.println(coordinate[i][k]);
			}
		}*/
		//�������Բ
		long lblank1,lblank2;
		for(int i=0;i<2*intRange;i++){
			lblank1=coordinate[i][0];
			lblank2=coordinate[i][1];
			for(int k=0;k<lblank2;k++)
				System.out.print(" ");
			System.out.print("*");
			
			for(int k=0;k<lblank1-lblank2;k++)
				System.out.print(" ");
			System.out.print("*");
			System.out.println("");
		}
		
		
	}
	private double douRadius;
}
