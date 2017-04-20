package com.umlparser;

import java.io.FileInputStream;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

@SuppressWarnings("rawtypes")
public class UMLParser extends VoidVisitorAdapter {	

	private CompilationUnit cUnit;
	private Model model = new Model();
	
	ClassDetails cDetails;
	public void testRun(){
		
  }
}
