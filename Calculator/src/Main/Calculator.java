package Main;

import LogicalModule.Stack;

public class Calculator {
	private static Stack stk = new Stack();
	
	public static void main(String args[]) {
		stk.push("384");
		stk.push("111");
		System.out.println(stk.pop());
		System.out.println(stk.pop());

	}

}
