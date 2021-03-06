package umlparser.cmpe202;

public class ConfigVariables {

	private char variableAccessModifier;
	private String name;
	private String variableReturnType;
	
	public ConfigVariables(char variableAccessModifier, String name, String variableReturnType) {
		
		this.variableAccessModifier = variableAccessModifier;
		this.name = name;
		this.variableReturnType = variableReturnType;
	}
	
	public char getVariableAccessModifier() {
		return variableAccessModifier;
	}
	
	public void setVariableAccessModifier(char variableAccessModifier){
		this.variableAccessModifier = variableAccessModifier;
	}
	
	public String getVariableString(){
		String result = "";
		
		if(variableReturnType == null){
			result = "";
		}else {
			result = variableAccessModifier + name + variableReturnType + ";";
		}
		return result;
	}
}