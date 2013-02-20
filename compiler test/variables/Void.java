package oop.ex2.variables;

public class Void extends Variable {

	public Void(java.lang.String type, Object value, boolean isFinal) {
		super(type, value, isFinal);
	}

	@Override
	public boolean validateType() {
		if(value != null){
			return false;
		}
		return true;
	}

	@Override
	public java.lang.String getExample() {
		return null;
	}

}
