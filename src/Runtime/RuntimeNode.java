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
    
    
    if(n instanceof AST_class_declaration) {
      ASTNode[] children = n.getChildren();
      String className = children[0].getNodeValue();
      String extendsClassName = children[1].getNodeValue();
      ClassRepresentation extendsClass;
      
      if(extendsClassName == null || extendsClassName == "") {
        extendsClass = RuntimeConstants.getObjectClass();
      }else {
        extendsClass = RuntimeContext.getClass(extendsClassName);
      }
      
      DefaultPropertyMap extendsOverrides = new DefaultPropertyMap();
      DefaultPropertyMap defProps = new DefaultPropertyMap();
      MethodContainer<ObjectRepresentation> methods = new MethodContainer<ObjectRepresentation>();
      ASTNode[] overrideIdentifiers = children[2].getChildren();
      ASTNode[] propNodes = children[3].getChildren();
      ASTNode[] functionNodes = children[4].getChildren();
      
      for(int i = 0; i < overrideIdentifiers.length; i++) {
        String propName = overrideIdentifiers[i].getChild(0).getChild(0).getNodeValue();
        ObjectRepresentation assessedValue = assessASTNode(overrideIdentifiers[i].getChild(1), context);
        extendsOverrides.put(propName, assessedValue);
      }
      
      for(int i = 0; i < propNodes.length; i++) {
        String typeIdentifier = propNodes[i].getChild(0).getChild(0).getNodeValue();
        String propIdentifier = propNodes[i].getChild(1).getChild(0).getNodeValue();
        ObjectRepresentation assessedValue = assessASTNode(propNodes[i].getChild(2), context); 
        
        if(RuntimeContext.getClass(className) != assessedValue.getObjectClassRepresentation()) {
          throw new RuntimeNodeException(
              "Type `" + typeIdentifier + "` does not match found type."
          );
        }
        defProps.put(propIdentifier, assessedValue);
      }
      
      for(int i = 0; i < functionNodes.length; i++) {
        String funcName = functionNodes[i].getChild(0).getChild(0).getNodeValue();
        ASTNode[] paramNodes = functionNodes[i].getChild(1).getChildren();
        String returnTypeIdentifier = functionNodes[i].getChild(2).getChild(0).getNodeValue();
        ASTNode body = functionNodes[i].getChild(3);
        ParameterContainer params = new ParameterContainer();
        
        for(int j = 0; j < paramNodes.length; j++) {
          String typeName = paramNodes[j].getChild(0).getChild(0).getNodeValue();
          String paramName = paramNodes[j].getChild(1).getChild(0).getNodeValue();
          params.put(paramName, RuntimeContext.getClass(typeName));
        }
        
        methods.put(funcName, new FunctionRepresentation<Object>(params, body, RuntimeContext.getClass(returnTypeIdentifier), funcName));
      }
      
      RuntimeContext.setClass(className, new ClassRepresentation<Object>(extendsOverrides, defProps, methods, className, extendsClass));
      
    }
    
    if(n instanceof AST_function_declaration) {
      String funcName = n.getChild(0).getChild(0).getNodeValue();
      ASTNode[] params = n.getChild(1).getChildren();
      String returnTypeIdentifier = n.getChild(2).getChild(0).getNodeValue();
      ASTNode body = n.getChild(3);
      ParameterContainer parameters = new ParameterContainer();
      
      for(int j = 0; j < params.length; j++) {
        String typeIdentifier = params[j].getChild(0).getChild(0).getNodeValue();
        String paramIdentifier = params[j].getChild(1).getChild(0).getNodeValue();
        parameters.put(typeIdentifier, RuntimeContext.getClass(typeIdentifier));
      }
      
      RuntimeContext.setGlobalFunction(funcName, new ClassRepresentation<Object>(params, body, RuntimeContext.getClass(returnTypeIdentifier), funcName));
    }
  }
  
  public static ObjectRepresentation runASTNodes(ASTNode[] nodes, RuntimeContext context, boolean insideFunc) {
    for(int i = 0; i < nodes.length; i++) {
      if(insideFunc) {
        ASTNode n = nodes[i];
        
        if(n instanceof AST_Generated_inside_function_action) {
          n = n.getChild(0);
        }
        
        if(n instanceof AST_Generated_statement_call) {
          n = n.getChild(0);
        }
        
        if(n instanceof AST_Generated_return_call) {
          return assessASTNode(n.getChildren()[0], context);
        }
        
      }
      
      runASTNode(nodes[i], context, insideFunction);
    }
    
    if(!insideFunc) {
      return null;
    }else {
      return RuntimeContext.getGlobalObject("undefined");
    }
  }
  
  
  public static ObjectRepresentation assessASTNode(ASTNode node, RuntimeContext context) {
    if(node instanceof AST_Generated_Value || node instanceof ASTGenerated_value_without_expression || node instanceof ASTGenerated_value_without_expression_without_parenthesis) {
      if(node.getChild(0) instanceof ASTGenerated_exclamation_point) {
        ObjectRepresentation assessedChildObj = assessASTNode(node.getChild(1), context);
        
        if(assessedChildObj.getObjectClassRepresentation() != RuntimeConstants.getBooleanClass()) {
          throw new RuntimeNodeException("Expected `Boolean` type, but was not found.");
        }
        
        return assessedChildObj.runMethod(
            "equals",
            new ObjectRepresentation[] {
                RuntimeConstants.getBooleanClass().createObject(false)
            }
        );
      }
      return assessASTNode(node.getChildren()[0], context);
    }
    
    if(node instanceof ASTGenerated_literal) {
      return assessASTNode(node.getChildren()[0], context);
    }
    
    if(node instanceof ASTGenerated_literal_boolean) {
      if(node.getNodeValue() == "true") {
        return RuntimeConstants.getBooleanClass().createObject(true);
      }else if(node.getNodeValue() == "false"){
        return RuntimeConstants.getBooleanClass().createObject(false);
      }else {
        throw new RuntimeNodeException("The values true or false were expected, but got: " + node.getNodeValue());
      }
    }
    
    if(node instanceof AST_Generated_string) {
      if(!node.getNodeValue().startsWith("\"")) {
        throw new RuntimeNodeException(
            "Expected string to begin with `\"`, but none was found."
        );
      }
      
      if(!node.getNodeValue().endsWith("\"")) {
        throw new RuntimeNodeException(
            "Expected string to end with `\"`, but none was found."
        );
      }
      
      return RuntimeConstants.getStringClass().createObject(node.getNodeValue().substring(1, node.getNodeValue().length() - 1));
    }
    
    if(node instanceof AST_Generated_integer) {
      return RuntimeConstants.getIntegerClass().createObject(Long.parseLong(node.getNodeValue()));
    }
    
    if(node instanceof ASTGenerated_normal_name) {
      return context.getObject(node.getChild(0).getNodeValue());
    }
    
    if(node instanceof AST_Generated_decimal) {
      return RuntimeConstants.getDoubleClass().createObject(Double.parseDouble(node.getNodeValue()));
    }
    
    if(node instanceof AST_Generated_function_call) {
      String funcName = node.getChild(0).getChild(0).getNodeValue();
      ASTNode[] vals = node.getChild(1).getChildren();
      List<ObjectRepresentation> args = new LinkedList<ObjectRepresentation>();
      
      for(int i = 0; i < vals.length; i++) {
        args.add(assessASTNode(vals[i], context));
      }
      
      FunctionRepresentation funcRep = context.getFunction(funcName);
      return funcRep.call(
          context.getOpenObject(),
          args.toArray(new ObjectRepresentation[0])
      );
    }
    
    if(node instanceof AST_Generated_class_call) {
      String classIdentifier = node.getChild(0).getChild(0).getNodeValue();
      ASTNode[] vals = node.getChild(1).getChildren();
      List<ObjectRepresentation> args = new LinkedList<ObjectRepresentation>();
      
      for(int i = 0; i < vals.length; i++) {
        args.add(assessASTNode(vals[i], context));
      }
      
      ClassRepresentation createdClass = RuntimeContext.getClass(classIdentifier);
      return createdClass.createObject(
          args.toArray(new ObjectRepresentation[0]),
          null
      );
    }
    
    if(node instanceof ASTGenerated_class_method_call) {
      String objIdentifier = node.getChild(0).getChild(0).getNodeValue();
      String methodName = node.getChild(1).getChild(0).getNodeValue();
      ASTNode[] vals = node.getChild(1).getChild(1).getChildren();
      List<ObjectRepresentation> args = new LinkedList<ObjectRepresentation>();
      
      for(int i = 0; i < vals.length; i++) {
        args.add(assessASTNode(vals[i], context));
      }
      
      return context.getObject(objIdentifier).runMethod(methodName, args.toArray(new ObjectRepresentation[0]));
    }
    
    if(node instanceof ASTGenerated_class_property_get) {
      String objIdentifier = node.getChild(0).getChild(0).getNodeValue();
      String propIdentifier = node.getChild(1).getNodeValue();
      
      return context.getObject(objIdentifier).getProp(propIdentifier);
    }
    
    if(node instanceof ASTGenerated_expression_with_parenthesis
        || node instanceof ASTGenerated_expression_without_parenthesis) {
      ASTNode[] expressChildren = node.getChildren();
      List<Object> expressChildrenRemaining = new ArrayList<Object>(Arrays.asList(expressChildren));
      int expressSteps = (expressChildren.length - 1) / 2;
      ObjectRepresentation curObj = null;
      
      while(1 < expressChildrenRemaining.size()) {
        int highestPrecedence = -1;
        int curHighestPrecedenceIndex = -1;
        
        for(int i = 0; i < expressChildrenRemaining.size(); i+=2) {
          int precedence = precedenceForBinaryOperator(
              (ASTNode) expressChildrenRemaining.get(i)
          );

          if (highestPrecedence < precedence) {
            highestPrecedence = precedence;
              curHighestPrecedenceIndex = i;
          }
        }
        
        ObjectRepresentation newObj = evaluateObjectExpressionStep(
            expressChildrenRemaining.get(curHighestPrecedenceIndex - 1),
            (ASTNode) expressChildrenRemaining.get(curHighestPrecedenceIndex),
            expressChildrenRemaining.get(curHighestPrecedenceIndex + 1),
            context
        );
        
        expressChildrenRemaining.remove(curHighestPrecedenceIndex - 1);
        expressChildrenRemaining.remove(curHighestPrecedenceIndex - 1);
        expressChildrenRemaining.remove(curHighestPrecedenceIndex - 1);
        expressChildrenRemaining.add(curHighestPrecedenceIndex - 1, newObj);
      }
      return (ObjectRepresentation) expressChildrenRemaining.get(0);
    }
    return RuntimeContext.getGlobalObject("undefined");
  }
  
  private static ObjectRepresentation evaluateObjectExpressionStep(
      Object term,
      ASTNode operatorNode,
      Object term2,
      RuntimeContext context) {
    ObjectRepresentation termObject;
    ObjectRepresentation term2Object;
    
    if (term instanceof ASTNode) {
      termObject = assessASTNode((ASTNode) term, context);
    } else if (term instanceof ObjectRepresentation) {
      termObject = (ObjectRepresentation) term;
    } else {
        throw new RuntimeNodeException(
            "Term 0 was neither a node nor a created object."
        );
    }
  
    if (term2 instanceof ASTNode) {
        term2Object = assessASTNode((ASTNode) term2, context);
    } else if (term2 instanceof ObjectRepresentation) {
      `term2Object = (ObjectRepresentation) term2;
    } else {
        throw new RuntimeNodeException(
            "Term 1 was neither a node nor a created object."
        );
    }
    
    return evaluateExpressionStep(
        termObject,
        operatorNode,
        term2Object,
        context
    );
  }
  
  public static ObjectRepresentation evaluateExpressionStep(Object term,
      ASTNode operatorNode,
      Object term2,
      RuntimeContext context) {
    return term.runMethod(methodNameForBinaryOperator(operatorNode),
        new ObjectRepresentation[] { term });
  }
  
  private static String methodNameForBinaryOperator(ASTNode operatorNode) {
    if (operatorNode instanceof ASTGenerated_binary_operator) {
        operatorNode = operatorNode.getChild(0);
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_equality) {
        return "equals";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_negated_equality) {
        return "doesNotEqual";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_plus) {
        return "add";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_minus) {
        return "subtract";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_times) {
        return "multiply";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_divide) {
        return "divide";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_modulo) {
        return "modulo";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_exponential) {
        return "exponent";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_and) {
        return "and";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_or) {
        return "or";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_greater_than) {
        return "isGreater";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_less_than) {
        return "isLess";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_greater_or_equal) {
        return "isGreaterOrEqual";
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_less_or_equal) {
        return "isLessOrEqual";
    }

    throw new RuntimeNodeException("Binary operator found is unsupported.");
}

private static int precedenceForBinaryOperator(ASTNode operatorNode) {
    if (operatorNode instanceof ASTGenerated_binary_operator) {
        operatorNode = operatorNode.getChild(0);
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_equality) {
        return 30;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_negated_equality) {
        return 30;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_plus) {
        return 40;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_minus) {
        return 40;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_times) {
        return 50;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_divide) {
        return 50;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_modulo) {
        return 50;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_exponential) {
        return 60;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_and) {
        return 20;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_or) {
        return 10;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_greater_than) {
        return 30;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_less_than) {
        return 30;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_greater_or_equal) {
        return 30;
    }

    if (operatorNode instanceof ASTGenerated_binary_operator_less_or_equal) {
        return 30;
    }

    throw new RuntimeNodeException("Binary operator found is unsupported.");
}
  
}

class RuntimeNodeException extends RuntimeException {
  public RuntimeNodeException(String message) {
      super(message);
  }
}
