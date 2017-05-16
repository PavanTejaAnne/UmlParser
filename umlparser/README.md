Introduction

Built a UMLParser that  takes java code as an input and generates its UML class diagram as an output PNG file.

Libraries and Tools: 

JavaParser:
This UMLParser uses the following javaparser libraries from GitHub: https://github.com/javaparser/javaparser
com.github.javaparser.JavaParser 
com.github.javaparser.ast.CompilationUnit 
com.github.javaparser.ast.body.ClassOrInterfaceDeclaration 
com.github.javaparser.ast.body.ConstructorDeclaration 
com.github.javaparser.ast.body.FieldDeclaration 
com.github.javaparser.ast.body.MethodDeclaration 
com.github.javaparser.ast.body.ModifierSet 
com.github.javaparser.ast.visitor.VoidVisitorAdapter

YUml:
This Parser  generates a distinct URL for the input java test files. This URL will generate an image from the  http://yuml.me/ website.

Steps to Execute:
JRE Requirements: JRE v1.5(Minimum)
1. Place the 'umlparser.jar' file in any folder/directory on the system.
2. Navigate to or open the terminal from that folder/directory.
3. Copy the complete path to the input java test files.
4. Enter the following command:
java -jar umlparser.jar <Complete Path to the test files> <Output filename>
5. The PNG file should be generated in the same folder where the jar file resides. 

Note: Do not provide any extensions to the output filename. PNG extension will be generated automatically. 