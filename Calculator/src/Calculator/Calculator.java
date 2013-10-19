package Calculator;

import java.util.ArrayList;

import LogicalModule.Operator;
import LogicalModule.Stack;

public class Calculator {
	private ArrayList<String> _infixArray = new ArrayList<String>();
	private ArrayList<String> _reversePolishArray = new ArrayList<String>();
	private Stack calculateStack = new Stack();
	
	/**
	 * 
	 * @return Return the given math expression. If math expression is not given return 0;
	 * @throws Exception 
	 */
	public double calculate() throws Exception {
		
		int cStackLength = _reversePolishArray.size();
		String operator = "";
		String firstNum = "";
		String secondNum = "";
		double fn = 0;
		double sn = 0;
		double result = 0;
		
		for(int i = 0; i < cStackLength; i ++) {
			calculateStack.push(_reversePolishArray.get(i));
			if(isOperator(calculateStack.get()) == true) {
				operator = calculateStack.pop();
				//need check if there are no less than 2 elements in stack in JavaScript
				secondNum = calculateStack.pop();
				firstNum = calculateStack.pop();
				if(isOperator(firstNum) == true || isOperator(secondNum)) {
					throw new Exception();
				}
				fn = Double.parseDouble(firstNum);
				sn = Double.parseDouble(secondNum);
				if(operator.equals("+") == true) {
					result = fn + sn;
					calculateStack.push(String.valueOf(result));
				}
				if(operator.equals("-") == true) {
					result = fn - sn;
					calculateStack.push(String.valueOf(result));
				}
				if(operator.equals("*") == true) {
					result = fn * sn;
					calculateStack.push(String.valueOf(result));
				}
				if(operator.equals("/") == true) {
					result = fn / sn;
					calculateStack.push(String.valueOf(result));
				}
			}
		
			operator = "";
			secondNum = "";
			firstNum = "";
		}
	//check if there are invalid parentheses
	if(operator != "") {
		throw new Exception();
	}
		return result;
	}
	/**
	 * Convert the infix Stack to Reverse Polish Notation Stack
	 * @throws Exception 
	 */
	public void infixToRPN() throws Exception {
		String pushBuffer = "";
		Stack operatorStk = new Stack();
		//String operatorBuffer = "";
		int infixStkLength = _infixArray.size();
		
		_reversePolishArray.add("0");
		for(int i = 0; i < infixStkLength; i++) {
			pushBuffer = _infixArray.get(i);
			if((!isOperator(pushBuffer) && !isParentheses(pushBuffer)) == true) {
				_reversePolishArray.add(pushBuffer);
				pushBuffer = "";
			}
			if(isOperator(pushBuffer) == true) {
				if(operatorStk.getStkSize() == 0) {
					operatorStk.push(pushBuffer);
					pushBuffer = "";
				}else {
					Operator stkTop = new Operator(operatorStk.get());
					Operator newOperator = new Operator(pushBuffer);
					if(newOperator.get_operatorPrecedence() > stkTop.get_operatorPrecedence()) {
						operatorStk.push(pushBuffer);
						pushBuffer = "";
					}else {
						_reversePolishArray.add(operatorStk.pop());
						operatorStk.push(pushBuffer);
						pushBuffer = "";
					}
				}
			}
			if(isParentheses(pushBuffer) == true) {			
				if(pushBuffer.equals("(")) {
					operatorStk.push(pushBuffer);
					pushBuffer = "";
				}
				if(pushBuffer.equals(")")) {
					while(operatorStk.getStkSize() != 0) {
						pushBuffer = operatorStk.pop();
						if(!pushBuffer.equals("(")) {
							_reversePolishArray.add(pushBuffer);
						} else {
							pushBuffer = "";
							break;
						}
					pushBuffer = "";
					}
					/*while(true) {
						pushBuffer = operatorStk.pop();
						if(pushBuffer.equals("(")) {
							break;
						}
						_reversePolishArray.add(pushBuffer);
					}
					pushBuffer = "";*/
				}
			}
		}
		
		if(operatorStk.getStkSize() != 0) {
			int operatorStkSize = operatorStk.getStkSize();
			for(int i = 0; i < operatorStkSize; i++) {
				_reversePolishArray.add(operatorStk.pop());
			}
		}
		_reversePolishArray.add("+");
		
	}
	/**
	 * Print the _reversePolishArray. For testing only.
	 */
	public void printRPA() {
		System.out.println("This is the _reversePolishArray:");
		for(int i = 0; i < _reversePolishArray.size() ; i++) {
			System.out.println(_reversePolishArray.get(i));
		}
	}
	/**
	 * Initial the Infix Stack by giving the ifix expression.
	 * @param infixExp The math infix expression.
	 * @throws Exception 
	 */
	public void initialInfixArray(String infixExp) throws Exception {		
		char[] charInfixExp = infixExp.toCharArray();
		String pushNumberBuffer = "";
		//String pushOperationBuffer = "";
		ArrayList<String> pushOperationBuffer = new ArrayList<String>();
		int needPushOperator = 0;
		
		for(int i = 0; i < infixExp.length(); i++) {
			if(isSpace(charInfixExp[i]) == true) {
				continue;
			}
			if(isOperator("" + charInfixExp[i]) ||
				charInfixExp[i] == '(' ||
				charInfixExp[i] == ')'
										) {
				if(pushNumberBuffer == "") {
					if(needPushOperator == 1) {
						_infixArray.add("" + charInfixExp[i]);
						needPushOperator = 0;
						continue;
					}
					if(charInfixExp[i] == '(') {
						_infixArray.add("" + charInfixExp[i]);
						continue;
					}
					if(charInfixExp[i] == ')') {
						throw new Exception();
					}
					//pushOperationBuffer = pushOperationBuffer + charInfixExp[i];
					pushOperationBuffer.add("" + charInfixExp[i]);
					//pushOperationBuffer = "";
				}
				else {
					if(pushOperationBuffer.size() == 1) {
						if(pushOperationBuffer.equals("-")) {
							pushOperationBuffer.clear();
							pushOperationBuffer.add("" + charInfixExp[i]);
							_infixArray.add("-" + pushNumberBuffer);
							_infixArray.add(pushOperationBuffer.get(0));
							pushOperationBuffer.clear();
							pushNumberBuffer = "";
							continue;
						}
						if(pushOperationBuffer.equals("+")) {
							pushOperationBuffer.clear();
							pushOperationBuffer.add("" + charInfixExp[i]);
							_infixArray.add(pushNumberBuffer);
							_infixArray.add(pushOperationBuffer.get(0));
							pushOperationBuffer.clear();
							pushNumberBuffer = "";
							continue;
						}
					}
					if(pushOperationBuffer.size() > 1) {
						throw new Exception();
					}
					if(pushOperationBuffer.size() == 0) {
						if(charInfixExp[i] == ')') {
							needPushOperator = 1;
							if(pushNumberBuffer != "") {
								_infixArray.add(pushNumberBuffer);
							}
							pushNumberBuffer = "";
							_infixArray.add(")");
							continue;
						}
						pushOperationBuffer.add("" + charInfixExp[i]);
						_infixArray.add(pushNumberBuffer);
						_infixArray.add(pushOperationBuffer.get(0));
						pushOperationBuffer.clear();
						pushNumberBuffer = "";
					}
				}
			} 
			else {
				pushNumberBuffer = pushNumberBuffer + charInfixExp[i];
			}
		}
		if(pushNumberBuffer != "") {
			if(pushOperationBuffer.size() == 0) {
				_infixArray.add(pushNumberBuffer);
				return;
			} else{
				if(pushOperationBuffer.get(0).equals("+")) {
					_infixArray.add(pushNumberBuffer);
					return;
				}
				if(pushOperationBuffer.get(0).equals("-")) {
					_infixArray.add("-" + pushNumberBuffer);
					return;
				}
				throw new Exception();	
			}
		}
		
	}
	/**
	 * Pop and print all elements of _infixStk. For testing only.  
	 */
	public void initialInfixArray2(String infixExp) throws Exception {
		char[] charInfixExp = infixExp.toCharArray();
		String NumBuffer = "";
		String PNBuffer = "";
		int needPush = 0;
		
		for(int i = 0; i < charInfixExp.length; i++) {
			if(isSpace(charInfixExp[i]) == true) {
				continue;
			}
			if(isOperator("" + charInfixExp[i]) || isParentheses("" + charInfixExp[i])) {
				if(isOperator("" + charInfixExp[i])) {
					if(NumBuffer == "") {
						if(needPush == 1) {
							_infixArray.add("" + charInfixExp[i]);
							needPush = 0;
							continue;
						} else {
							PNBuffer = "" + charInfixExp[i];
							needPush = 1;
							continue;
						}
					} else {
						if(PNBuffer != ""){
							if(PNBuffer.equals("+")) {
								_infixArray.add(NumBuffer);
								NumBuffer = "";
								PNBuffer = "";
								_infixArray.add("" + charInfixExp[i]);
								continue;
							}
							if(PNBuffer.equals("-")) {
								_infixArray.add("-" + NumBuffer);
								NumBuffer = "";
								PNBuffer = "";
								_infixArray.add("" + charInfixExp[i]);
								continue;
							}
						} else {
							_infixArray.add(NumBuffer);
							_infixArray.add("" + charInfixExp[i]);
							NumBuffer = "";
							continue;
						}
					}
				}
			if(isParentheses("" + charInfixExp[i])) {
				if(charInfixExp[i] == '(') {
					_infixArray.add("(");
					continue;
				}
				if(charInfixExp[i] == ')') {
						if(PNBuffer != ""){
							if(PNBuffer.equals("+")) {
								_infixArray.add(NumBuffer);
								NumBuffer = "";
								PNBuffer = "";
								_infixArray.add(")");
								needPush = 1;
								continue;
							}
							if(PNBuffer.equals("-")) {
								_infixArray.add("-" + NumBuffer);
								NumBuffer = "";
								PNBuffer = "";
								_infixArray.add(")");
								needPush = 1;
								continue;
							}
						} else {
							if(NumBuffer != "") {
								_infixArray.add(NumBuffer);	
							}
							_infixArray.add(")");
							NumBuffer = "";
							needPush = 1;
							continue;
						}
					}
				}
			} else {
				NumBuffer = NumBuffer + charInfixExp[i];
			}
		}
		if(NumBuffer != "") {
			if(PNBuffer != ""){
				if(PNBuffer.equals("+")) {
					_infixArray.add(NumBuffer);
					NumBuffer = "";
					PNBuffer = "";
					return;
				}
				if(PNBuffer.equals("-")) {
					_infixArray.add("-" + NumBuffer);
					NumBuffer = "";
					PNBuffer = "";
					return;
				}
			} else {
				_infixArray.add(NumBuffer);
				NumBuffer = "";
				return;
			}
		}
	}
	public void printInfixArray() {
		int stkLength = _infixArray.size();
		System.out.println("This is the _infixArray");
		for(int i = 0; i < stkLength; i++) {
			System.out.println(_infixArray.get(i));
		}
	}
	/**
	 * Determine if the input is space.
	 * @param input the input char.
	 * @return Return true if the input is space. Return false if the input is not space.
	 */
	private boolean isSpace(char input) {
		if(input == ' ') {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Determine if the input is parentheses
	 * @param input The input char.
	 * @return Return true if the input is a operator. Return false if the input is not operator.
	 */
	private boolean isParentheses(String input) {
		if(input.equals("(") || input.equals(")")) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Determine if the input is Operator + - * /
	 * @param input The input char.
	 * @return Return true if the input is a operator. Return false if the input is not operator.
	 */
	private boolean isOperator(String input) {
		if(input.equals("+") ||
			input.equals("-") ||
			input.equals("*") ||
			input.equals("/")
						) {
			return true;
		}else {
			return false;
		}
	}
	public ArrayList<String> get_infixArray() {
		return _infixArray;
	}
	public void set_infixStk(ArrayList<String> _infixArray) {
		this._infixArray = _infixArray;
	}
	public ArrayList<String> get_reversePolishArray() {
		return _reversePolishArray;
	}
	public void set_reversePolishArray(ArrayList<String> _reversePolishArray) {
		this._reversePolishArray = _reversePolishArray;
	}
	public void set_infixArray(ArrayList<String> _infixArray) {
		this._infixArray = _infixArray;
	}
	

}
