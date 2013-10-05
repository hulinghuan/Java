import java.util.ArrayList;


public class Stack {
	private ArrayList<String> _stack = new ArrayList<String>();
	
	public String pop() {
		String popResult;
		popResult = _stack.get(_stack.size() - 1);
		_stack.remove(_stack.size() - 1);
		return popResult;
	} 
	public void push(String elements) {
		_stack.add(elements);
	}
}
