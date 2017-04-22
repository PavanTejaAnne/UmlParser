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
	
	public void testRun(){
		
	}
}