package umlparser.cmpe202;

public class ConfigMethods {

	
	
	private String name;
	private char methodAccessModifier;
	private String parameters;
	private String methodReturnType;

	public ConfigMethods(String name, char methodAccessModifier, String parameters,
			String methodReturnType) {
		
		this.name = name;
		this.methodAccessModifier = methodAccessModifier;
		this.parameters = parameters;
		this.methodReturnType = methodReturnType;

	}
	
	public String methodName() {
		return name;
	}
	
	public char getMethodAccessModifier() {
		return methodAccessModifier;
	}

	public String getMethodString(){
		
		String string = "";
		string = methodAccessModifier + name + "(" + parameters +")" + methodReturnType + ";";
		return string;
	}

}