package oop.ex2.variables;



public abstract class Variable {
	java.lang.String type;
	Object value;
	boolean isFinal=false;
	
	public Variable(java.lang.String type, Object value, boolean isFinal){
		this.type= type;
		this.value=value;
		this.isFinal= isFinal;
	}
	public java.lang.String getType(){
		return type;
	}
	public Object getValue(){
		return value;
	}
	public boolean isFinal(){
		return isFinal;
	}
	
	public void setType(java.lang.String type){
		this.type=type;
	}
	public void setValue(Object value){
		this.value=value;
	}
	public void setFinal(boolean isFinal){
		this.isFinal= isFinal;
	}
	
	public abstract java.lang.String getExample();
	public abstract boolean validateType();
	

}
