package Runtime;

import java.util.LinkedList;
import java.util.List;

import Magenta.parser.generatednodes.ASTNode;

public class RuntimeNode {
  public static void runRootNode(ASTNode node) {
    RuntimeConstants.setGlobalConstants();
    RuntimeNode.runASTNodes(node.getChildren(), new RuntimeContext(null), false);
  }
  
  public static void runASTNode(ASTNode n, RuntimeContext context, boolean insideFunc) {
    if(n instanceof ASTGenerated_inside_function_action) {
      runASTNode(n.getChild(0), context, insideFunc);
      return;
    }
    
    if(n instanceof ASTGenerated_statement_call) {
      runASTNode(node.getChild(0), context, insideFunc);
      return;
    }
    
    if(n instanceof ASTGenerated_variable_declaration) {
      String type = n.getChild(0).getChild(0).getNodeValue();
      String varName = n.getChild(1).getChild(0).getNodeValue();
      ObjectRepresentation assessedValue = assessASTNode(n.getChild(2), context);
      
      if(RuntimeContext.getClass(type) != assessedValue.getObjectClassRepresentation()) {
        throw new RuntimeNodeException(
            "Type: `" + type + "` does not match given type."
        );
      }
      
      context.setLocal(varName, assessedValue);
    }
    
    if(n instanceof ASTGenerated_variable_assignment) {
      String varName = n.getChild(0).getChild(0).getNodeValue();
      ObjectRepresentation assessedValue = assessASTNode(n.getChild(1), context);
      
      context.updateObject(varName, assessedValue);
    }
    
    if(n instanceof ASTGenerated_function_call) {
      String funcName = n.getChild(0).getChild(0).getNodeValue();
      ASTNode[] vals = n.getChild(1).getChildren();
      List<ObjectRepresentation> args = new LinkedList<ObjectRepresentation>();
      for(int i = 0; i < vals.length; i++) {
        args.add(assessASTNode(vals[i], context));
      }
      
      FunctionRepresentation funcRep = context.getFunction(funcName);
      funcRep.call(context.getOpenObject(), args.toArray(new ObjectRepresentation[0]));
    }
    
    if(n instanceof AST_class_method_call) {
      String objName = n.getChild(0).getChild(0).getNodeValue();
      String funcName = n.getChild(1).getChild(0).getChild(1).getNodeValue();
      ASTNode[] vals = n.getChild(1).getChild(1).getChildren();
      List<ObjectRepresentation> args = new LinkedList<ObjectRepresentation>();
      
      for(int i = 0; i < vals.length; i++) {
        args.add(assessASTNode(vals[i], context));
      }
      
      context.getObject(objName).runMethod(funcName, args.toArray(new ObjectRepresentation[0]));
    }
    
    if(n instanceof AST_emit_statement) {
      ObjectRepresentation assessedValue = assessASTNode(n.getChild(0), context);
      if(assessedValue.getObjectClassRepresentation() == RuntimeContext.getClass("String")) {
        System.out.println(assessedValue.getBaseVal());
        return;
      }
      
      System.out.println(
          assessedValue.runMethod("getString", new ObjectRepresentation[] {}).getBaseVal()
      );
      return;
    }
    
    if(n instanceof AST_return_statement) {
      System.out.println("returned: " + assessASTNode(n.getChild(0), context));
      System.exit(0);
    }
    
    if(n instanceof AST_while_statement) {
      ObjectRepresentation assessedValue = assessASTNode(n.getChild(0), context);
      if(assessedValue.getObjectClassRepresentation() != RuntimeConstants.getBooleanClass()) {
        throw new RuntimeNodeException("Expected `Boolean` type, but was not found.");
      }
      
      while ((Boolean) assessedValue, context).getBaseValue() == true) {
        runASTNodes(n.getChild(1).getChildren(), context, insideFunc);
      }
    }
    
    
    if(n instanceof AST_if_statement) {
      ObjectRepresentation assessedValue = assessASTNode(n.getChild(0), context);
      if(assessedValue.getObjectClassRepresentation() != RuntimeConstants.getBooleanClass()) {
        throw new RuntimeNodeException("Expected `Boolean` type, but was not found.");
      }
      
      if((boolean) assessedValue.getBaseVal() == true) {
        runASTNodes(n.getChild(1).getChildren(), context, insideFunc);
      }
    }
    
  }
}

class RuntimeNodeException extends RuntimeException {
  public RuntimeNodeException(String message) {
      super(message);
  }
}
