package Runtime;

import java.util.LinkedHashMap;
import java.util.function.Function;

import Magenta.parser.generatednodes.ASTNode;

public class FunctionRepresentation<B> {
  private ParameterContainer params;
  private ASTNode body;
  private RuntimeFunction baseBody;
  private ClassRepresentation returnClass;
  private String name;
  
  public FunctionRepresentation(ParameterContainer params, ASTNode body, ClassRepresentation returnClass, String name) {
    this.params = params;
    this.body = body;
    this.returnClass = returnClass;
    this.name = name;
  }
  
  public FunctionRepresentation(ParameterContainer params, RuntimeFunction baseBody, ClassRepresentation returnClass, String name) {
    this.params = params;
    this.baseBody = baseBody;
    this.returnClass = returnClass;
    this.name = name;
  }
  
  public ObjectRepresentation call(ObjectRepresentation<B> receiever, ObjectRepresentation[] args) {
      RuntimeContext currentContext = new RuntimeContext(receiver);
      int i = 0;
      
      if (args.length != params.size()) {
        throw new ArgumentException(
            "Expected " + params.size() + "arguments, but "
                + args.length + " were found."
        );
    }
  
    for (String parameterName : params.keySet()) {
        if (params.get(parameterName) != args[i].getObjectClass()) {
            throw new ArgumentException(
                "Parameter " + i + " is not of the correct type. Expected type `"
                    + params.get(parameterName).toString() + "` but found `"
                    + args[i].getObjectClass().toString() + "` for function `"
                    + name + "`."
            );
        }
  
        currentContext.setLocal(parameterName, args[i]);
        i++;
    }
  
    if (baseBody != null) {
        return baseBody.apply(currentContext);
    }
  
    ObjectRepresentation result = RuntimeNode.runASTNodes(
        body.getChildren(),
        currentContext,
        true
    );
  
    if (returnClass == RuntimeContext.getClass("Void")) {
        if (result != RuntimeContext.getGlobal("und")) {
            throw new ReturnException(
                "Function returned a result, but `und` was expected."
            );
        }
    } else {
        if (
            result.getObjectClass() != returnClass
                && result != RuntimeContext.getGlobal("nil")
                || result == RuntimeContext.getGlobal("und")
        ) {
            throw new ReturnException(
                "An incorrect return type was found."
            );
        }
    }
  
    return result;
  }
  
  @Override
  public String toString() {
      return name;
  }
}

class ArgumentException extends RuntimeException {
  ArgumentException(String message) {
      super(message);
  }
}

class ReturnException extends RuntimeException {
  ReturnException(String message) {
      super(message);
  }
}

class ParameterContainer extends LinkedHashMap<String, ClassRepresentation> {
}

interface RuntimeFunction extends Function<RuntimeContext, ObjectRepresentation> {
}