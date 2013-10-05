package LogicalModule;
import java.util.ArrayList;
import java.util.EmptyStackException;


public class Stack {
	private ArrayList<String> _stack = new ArrayList<String>();
	
	/**
	 * Pop the top String element of the stack out.
	 * @return return the element which is poped out. If there is no elements in stack, throw exception
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
	 * @param String elements which is going to be pushed into stack.
	 */
	public void push(String elements) {
		_stack.add(elements);
	}
	public String get() {
		return _stack.get(_stack.size() - 1);
	}
}
