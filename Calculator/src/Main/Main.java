package Main;

import java.util.Scanner;

import Calculator.Calculator;

public class Main {
	public static void main(String[] args) {
	Calculator clt = new Calculator();
	String inputString = "";
	Scanner scn = new Scanner(System.in);
	System.out.println("Please input the math expression");
	inputString = scn.nextLine();
	/*try {
		clt.initialInfixArray(inputString);
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}*/
	try {
		clt.initialInfixArray(inputString);
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	try {
		clt.infixToRPN();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	clt.printInfixArray();
	clt.printRPA();
	System.out.println("The calculate Result = ");
	try {
		System.out.println(clt.calculate());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
