package com.umlparser;

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
	

	private CompilationUnit cUnit;
	private Model model = new Model();
	
	ClassDetails cDetails;
	

	@SuppressWarnings("unchecked")
	public UMLParser(String filePath) {
		
		cUnit = null;
		
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
		cDetails = new ClassDetails(cid);
		super.visit(cid, obj);
	}
	
	@Override
	public void visit(ConstructorDeclaration cd, Object obj){
		
		String constant = "";
		char constantAccessModifier;
		String constantParameter;
		

		
		constant = cd.getName();
		constantAccessModifier = model.getAccessModifier(ModifierSet.getAccessSpecifier(cd.getModifiers()));
		constantParameter = model.adjustSqrBrackets(cd.getParameters());
		constantParameter = model.buildProcessType(constantParameter, 2, cDetails);
		
		if(constantAccessModifier == '+'){
			MethodDetails mdetails = new MethodDetails(constant, constantAccessModifier, constantParameter, "");
			cDetails.hashMeth.put(constant, mdetails);
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
		methodAccessModifier = model.getAccessModifier(ModifierSet.getAccessSpecifier(md.getModifiers()));
		methodParameter = model.adjustSqrBrackets(md.getParameters());
		methodParameter = model.buildProcessType(methodParameter, 2, cDetails);
		methodReturnType = model.buildProcessType(md.getType().toString(),1, cDetails);
		
		if(methodAccessModifier == '+'){
			MethodDetails mdetails = new MethodDetails(name, methodAccessModifier, methodParameter, methodReturnType);
			cDetails.hashMeth.put(name, mdetails);
		}	
		super.visit(md, obj);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void visit(FieldDeclaration fd, Object obj) {
		
		char variableAccessModifier;
		String name = "";
		String variableReturnType = "";
		
		variableAccessModifier = model.getAccessModifier(ModifierSet.getAccessSpecifier(fd.getModifiers()));
		
		name = model.adjustSqrBrackets(fd.getVariables());
		variableReturnType = model.buildProcessType(fd.getType().toString(),1, cDetails);
		
		if(variableAccessModifier == '+' || variableAccessModifier == '-'){
		VariableDetails vdet = new VariableDetails(variableAccessModifier, name, variableReturnType);
		cDetails.hashVar.put(name, vdet);
		}		
		
	    super.visit(fd, obj);
	}
	
}
