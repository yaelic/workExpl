package oop.ex2.variables;

import oop.ex2.parsers.StructureValidator;

public class Double extends Variable {
	public static final java.lang.String DOUBLE_REGEXP = "-??[0-9]+.??[0-9]+";
	
	public Double(java.lang.String type, Object value, boolean isFinal) {
		super(type, value, isFinal);
	}

	@Override
	public boolean validateType() {
		if(value ==null){
			return true;
		}
		if (StructureValidator.matchStringToRegexp(DOUBLE_REGEXP, value.toString()) || StructureValidator.matchStringToRegexp(Int.INT_REGEXP, value.toString())){
			return true;
		}
		return false;
	}

	@Override
	public java.lang.String getExample() {
		return "7.6";
	}

}
