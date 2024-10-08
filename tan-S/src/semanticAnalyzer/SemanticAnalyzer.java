package semanticAnalyzer;

import parseTree.*;
import parseTree.nodeTypes.IdentifierNode;
import semanticAnalyzer.types.Type;
import symbolTable.Binding;
import symbolTable.Scope;


public class SemanticAnalyzer {
	ParseNode ASTree;
	
	public static ParseNode analyze(ParseNode ASTree) {
		SemanticAnalyzer analyzer = new SemanticAnalyzer(ASTree);
		return analyzer.analyze();
	}
	public SemanticAnalyzer(ParseNode ASTree) {
		this.ASTree = ASTree;
	}
	
	public ParseNode analyze() {
		ASTree.accept(new FirstVisitor());
		ASTree.accept(new SemanticAnalysisVisitor());
		
		return ASTree;
	}
	
	
	
}
