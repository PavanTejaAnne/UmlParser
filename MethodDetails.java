package com.umlparser;

public class MethodDetails {

	
	
	private String name;
	private char methodAccessModifier;
	private String parameters;
	private String methodReturnType;

	public MethodDetails(String name, char methodAccessModifier, String parameters,
			String methodReturnType) {
		
		this.name = name;
		this.methodAccessModifier = methodAccessModifier;
		this.parameters = parameters;
		this.methodReturnType = methodReturnType;

	}
	
	public String getname() {
		return name;
	}
	
	public char getMethodAccessModifier() {
		return methodAccessModifier;
	}

	public String getFullMethodString(){
		
		String string = "";
		string = methodAccessModifier + name + "(" + parameters +")" + methodReturnType + ";";
		return string;
	}

}
