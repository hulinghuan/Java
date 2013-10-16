package LogicalModule;

public class Operator {
	private String _operatorType = "";
	private int _operatorPrecedence = 0;
	
	public Operator(String operatorType) {
		this._operatorType = operatorType;
		if(_operatorType.equals("-") || _operatorType.equals("+")) {
			this._operatorPrecedence = 2;
		}
		if(_operatorType.equals("*") || _operatorType.equals("/")) {
			this._operatorPrecedence = 3;
		}
		if(_operatorType.equals("^")) {
			this._operatorPrecedence = 4;
		}
	}

	public void set_operatorType(String _operatorType) {
		this._operatorType = _operatorType;
	}

	public void set_operatorPrecedence(int _operatorPrecedence) {
		this._operatorPrecedence = _operatorPrecedence;
	}

	public String get_operatorType() {
		return _operatorType;
	}

	public int get_operatorPrecedence() {
		return _operatorPrecedence;
	}
}
