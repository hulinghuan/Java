package LogicalModule;
import java.util.ArrayList;
import java.util.EmptyStackException;


public class Stack {
	private ArrayList<String> _stack = new ArrayList<String>();
	
	public int getStkSize() {
		return _stack.size();
	}
	/**
	 * Pop the top String element of the stack out.
	 * @return return the element which is popped out. If there is no elements in stack, throw EmptyStackException.
	 */
	public String pop() {
		String popResult;
		
		if(_stack.size() == 0) {
		throw new EmptyStackException();
		}
		popResult = _stack.get(_stack.size() - 1);
		_stack.remove(_stack.size() - 1);
		return popResult;
	} 
	/**
	 * Push the String element into the stack.
	 * @param element The element which is going to be popped into the stack.
	 */
	public void push(String element) {
		_stack.add(element);
	}
	/**
	 * Get the top String element of the stack
	 * @return The top String element of the stack. If there is no elements in stack, throw EmptyStackException.
	 */
	public String get() {
		if(_stack.size() == 0) {
			throw new EmptyStackException();
		}
		return _stack.get(_stack.size() - 1);
	}
}
