package umlparser.cmpe202;

import java.io.FileInputStream;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

@SuppressWarnings("rawtypes")
public class UMLParser extends VoidVisitorAdapter {
	
	ConfigClasses configCls;
	public CompilationUnit cUnit = null;
	private Decorate dec = new Decorate();
	
	@SuppressWarnings("unchecked")
	public UMLParser(String filePath) {
		
		try {
			FileInputStream fin = new FileInputStream(filePath);
			cUnit = JavaParser.parse(fin);
			this.visit(cUnit, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void visit(ClassOrInterfaceDeclaration cid, Object obj){
		configCls = new ConfigClasses(cid);
		super.visit(cid, obj);
	}
	
	@Override
	public void visit(ConstructorDeclaration cd, Object obj){
		
		String constant = "";
		char constantAccessModifier;
		String constantParameter;
		
		constant = cd.getName();
		constantAccessModifier = dec.getAccessModifier(ModifierSet.getAccessSpecifier(cd.getModifiers()));
		constantParameter = dec.adjustSqrBrackets(cd.getParameters());
		constantParameter = dec.buildProcessType(constantParameter, 2, configCls);
		
		if(constantAccessModifier == '+'){
			ConfigMethods mdetails = new ConfigMethods(constant, constantAccessModifier, constantParameter, "");
			configCls.hashMeth.put(constant, mdetails);
		}	
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void visit(MethodDeclaration md, Object obj){
		char methodAccessModifier;
		String name = "";
		String methodParameter = "";
		String methodReturnType = "";
		
		name = md.getName();		
		methodAccessModifier = dec.getAccessModifier(ModifierSet.getAccessSpecifier(md.getModifiers()));
		methodParameter = dec.adjustSqrBrackets(md.getParameters());
		methodParameter = dec.buildProcessType(methodParameter, 2, configCls);
		methodReturnType = dec.buildProcessType(md.getType().toString(),1, configCls);
		
		if(methodAccessModifier == '+'){
			ConfigMethods mdetails = new ConfigMethods(name, methodAccessModifier, methodParameter, methodReturnType);
			configCls.hashMeth.put(name, mdetails);
		}	
		super.visit(md, obj);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void visit(FieldDeclaration fd, Object obj) {
		
		char variableAccessModifier;
		String name = "";
		String variableReturnType = "";
		
		variableAccessModifier = dec.getAccessModifier(ModifierSet.getAccessSpecifier(fd.getModifiers()));
		
		name = dec.adjustSqrBrackets(fd.getVariables());
		variableReturnType = dec.buildProcessType(fd.getType().toString(),1, configCls);
		
		if(variableAccessModifier == '+' || variableAccessModifier == '-'){
		ConfigVariables vdet = new ConfigVariables(variableAccessModifier, name, variableReturnType);
		configCls.hashVar.put(name, vdet);
		}		
		
	    super.visit(fd, obj);
	}
	public void test(){}
	
}
