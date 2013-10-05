import java.util.ArrayList;
import java.util.Scanner;


public class InputTest {
	public static void main(String[] args){
		double[] test = new double[100];
		test = new InputTest().inputDouble();
		for(int i = 0 ; i < 100; i++){
			System.out.println(test[i]);
		}
	}
	
	/**
	 * 
	 * @return return the double array which user input(split by ",")
	 */
	public double[] inputDouble(){
		String input = null;
		Scanner scn = new Scanner(System.in);
		System.out.println("Please input the INTEGER");
		input = scn.nextLine();
		ArrayList<Double> input_list = new ArrayList<Double>();
		//int temp;
		for(int i = 0; i < input.length(); i = 1 + input.indexOf(",", i)){
			if(input.indexOf(",", i) == -1){
				input_list.add(Double.valueOf(input.substring(i, input.length())));
				break;
			}
			input_list.add(Double.valueOf(input.substring(i, input.indexOf(",", i))));
		}
		double[] return_value= new double[100];
		for(int i = 0 ; i < input_list.size(); i++){
			return_value[i] = input_list.get(i);
		}
		return return_value;
	}
}
