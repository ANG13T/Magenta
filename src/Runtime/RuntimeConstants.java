package Runtime;

public class RuntimeConstants {
  private static ClassRepresentation<Object> objectClass;
  private static ClassRepresentation<Boolean> booleanClass;
  private static ClassRepresentation<Long> integerClass;
  private static ClassRepresentation<Double> decimalClass;
  private static ClassRepresentation<String> stringClass;
  
  public static void setGlobalConstants() {
    objectClass = new ClassRepresentation("Object", null);
    ClassRepresentation<Object> voidClass = new ClassRepresentation<Object>("Vacant", null);
    RuntimeContext.setClass("Object", objectClass);
    RuntimeContext.setGlobalObject("undefined", objectClass.createObject(null));
    RuntimeContext.setGlobalObject("null", objectClass.createObject(null));
    RuntimeContext.setClass("Vacant", voidClass);
    
    //classes
    integerClass = new ClassRepresentation<Long>("Integer", objectClass);
    decimalClass = new ClassRepresentation<Double>("Decimal", objectClass);
    stringClass = new ClassRepresentation<String>("String", objectClass);
    booleanClass = new ClassRepresentation<Boolean>("Boolean", objectClass);
    
    //functions
    RuntimeFunction equalsFunc = (RuntimeContext context) -> {
      if(context.getOpenBaseValue().equals(context.getObject("other").getBaseValue())) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(integerClass, "equals", equalsFunc);
    addOperationFunction(decimalClass, "equals", equalsFunc);
    addOperationFunction(stringClass, "equals", equalsFunc);
    addOperationFunction(booleanClass, "equals", equalsFunc);
    
    RuntimeFunction notEqualsFunc = (RuntimeContext context) -> {
      if(context.getOpenBaseValue().equals(context.getObject("other").getBaseValue())) {
        return booleanClass.createObject(false);
      }
      
      return booleanClass.createObject(true);
    }
    
    addOperationFunction(integerClass, "notEqual", notEqualsFunc);
    addOperationFunction(decimalClass, "notEqual", notEqualsFunc);
    addOperationFunction(stringClass, "notEqual", notEqualsFunc);
    addOperationFunction(booleanClass, "notEqual", notEqualsFunc);
    
    //Basic Operations with multiple data types
    
    //Addition
    
    RuntimeFunction stringAddition = (RuntimeContext context) -> {
      String str = (String) context.getOpenBaseValue();
      String str2 = (String) context.getObject("other").getBaseVal();
      
      return stringClass.createObject(str + str2);
    }
    
    addOperationFunction(stringClass, "addition", stringAddition);
    
    RuntimeFunction booleanAddition = (RuntimeContext context) -> {
      Boolean bool = (boolean)context.getOpenBaseValue();
      Boolean bool2 = (boolean)context.getObject("other").getBaseVal();
      
      if(bool || bool2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(booleanClass, "addition", booleanAddition);
    
    RuntimeFunction numberAddition = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      return integerClass.createObject(num + num2);
    }
    
    addOperationFunction(integerClass, "addition", numberAddition);
    
    RuntimeFunction decimalAddition = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      return decimalClass.createObject(num + num2);
    }
    
    addOperationFunction(decimalClass, "addition", decimalAddition);
    
    //Subtraction
    
    RuntimeFunction integerSubtraction = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      return integerClass.createObject(num - num2);
    }
    
    addOperationFunction(integerClass, "subtraction", integerSubtraction);
    
    RuntimeFunction decimalSubtraction = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      return decimalClass.createObject(num - num2);
    }
    
    addOperationFunction(decimalClass, "subtraction", decimalSubtraction);
    
    //Division
    
    RuntimeFunction integerDivision = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      return integerClass.createObject(num / num2);
    }
    
    addOperationFunction(integerClass, "division", integerDivision);
    
    RuntimeFunction decimalDivison = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      return decimalClass.createObject(num / num2);
    }
    
    addOperationFunction(decimalClass, "division", decimalDivison);
    
    //Multiplication
    
    RuntimeFunction integerMultiplication = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      return integerClass.createObject(num * num2);
    }
    
    addOperationFunction(integerClass, "multiplication", integerMultiplication);
    
    RuntimeFunction decimalMultiplication = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      return decimalClass.createObject(num * num2);
    }
    
    addOperationFunction(decimalClass, "multiplication", decimalMultiplication);
    
    //Modulo
    
    RuntimeFunction integerModulo = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      return integerClass.createObject(num % num2);
    }
    
    addOperationFunction(integerClass, "modulo", integerModulo);
    
    RuntimeFunction decimalModulo = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      return decimalClass.createObject(num % num2);
    }
    
    addOperationFunction(decimalClass, "modulo", decimalModulo);
    
    //Logic Operations
    
    RuntimeFunction orStatement = (RuntimeContext context) -> {
      boolean bool = (boolean) context.getOpenBaseValue();
      boolean bool1 = (boolean) context.getObject("other").getBaseVal();
      
      if(bool || bool1) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(booleanClass, "orStatement", orStatement);
    
    RuntimeFunction andStatement = (RuntimeContext context) -> {
      boolean bool = (boolean) context.getOpenBaseValue();
      boolean bool1 = (boolean) context.getObject("other").getBaseVal();
      
      if(bool && bool1) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(booleanClass, "andStatement", andStatement);
    
    //greater than statements
    
    RuntimeFunction integerGreaterThan = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      if(num > num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(integerClass, "greaterThan", integerGreaterThan);
    
    RuntimeFunction decimalGreaterThan = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      if(num > num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(decimalClass, "greaterThan", decimalGreaterThan);
    
    //less than statements
    
    RuntimeFunction integerLessThan = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      if(num < num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(integerClass, "lessThan", integerLessThan);
    
    RuntimeFunction decimalLessThan = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      if(num < num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(decimalClass, "lessThan", decimalLessThan);
    
    // >= statements
    
    RuntimeFunction integerGreaterThanEqual = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      if(num >= num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(integerClass, "greaterThanEqual", integerGreaterThanEqual);
    
    RuntimeFunction decimalGreaterThanEqual = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      if(num >= num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(decimalClass, "greaterThanEqual", decimalGreaterThanEqual);
    
    // <= statements
    RuntimeFunction integerLessThanEqual = (RuntimeContext context) -> {
      Long num = (long) context.getOpenBaseValue();
      Long num2 = (long) context.getObject("other").getBaseVal();
      
      if(num <= num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(integerClass, "lessThanEqual", integerLessThanEqual);
    
    RuntimeFunction decimalLessThanEqual = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      Double num2 = (double) context.getObject("other").getBaseVal();
      
      if(num <= num2) {
        return booleanClass.createObject(true);
      }
      
      return booleanClass.createObject(false);
    }
    
    addOperationFunction(decimalClass, "lessThanEqual", decimalLessThanEqual);
    
    // toString methods
    
    RuntimeFunction integerToString = (RuntimeContext context) -> {
      Long num = (Long) context.getOpenBaseValue();
      return stringClass.createObject(num.toString());
    }
    
    addIndependentFunction(integerClass, "toString", integerToString, stringClass);
    
    RuntimeFunction booleanToString = (RuntimeContext context) -> {
      boolean bool = (boolean) context.getOpenBaseValue();
      
      if(bool) {
        return stringClass.createObject("true");
      }
      return stringClass.createObject("false");
    }
    
    addIndependentFunction(booleanClass, "toString", booleanToString, stringClass);
    
    RuntimeFunction decimalToString = (RuntimeContext context) -> {
      Double num = (double) context.getOpenBaseValue();
      
      return stringClass.createObject(num.toString());
    }
    
    addIndependentFunction(decimalClass, "toString", decimalToString, stringClass);
    
    // string to integer
    RuntimeFunction stringToInteger = (RuntimeContext context) -> {
      String value = (String) context.getOpenBaseValue();
      Long num = Long.parseLong(value);
      
      return integerClass.createObject(num);
    }
    
    addIndependentFunction(stringClass, "toInteger", stringToInteger, integerClass);
    
    // decimal to integer
    RuntimeFunction stingToDecimal = (RuntimeContext context) -> {
      String value = (String) context.getOpenBaseValue();
      Double num = Double.parseDouble(value);
      
      return decimalClass.createObject(num);
    }
    
    addIndependentFunction(stringClass, "toDecimal", stingToDecimal, decimalClass);
    
    RuntimeContext.setClass("String", stringClass);
    RuntimeContext.setClass("Decimal", decimalClass);
    RuntimeContext.setClass("Integer", integerClass);
    RuntimeContext.setClass("Boolean", booleanClass);
    
  }
  
  static <B, RB> void addIndependentFunction(ClassRepresentation<B> classRep, String identifier, FunctionRepresentation func, ClassRepresentation returnRep) {
    ParameterContainer params = new ParameterContainer();
    FunctionRepresentation funcRep = new FunctionRepresentation<B>(params, func, returnRep, identifier);
    classRep.addMethod(identifier, funcRep);
  } 
  
  static <B> void addOperationFunction(ClassRepresentation<B> classToUse, String identifier, RuntimeFunction function) {
    ParameterContainer params = new ParameterContainer();
    params.put("other", classToUse);
    FunctionRepresentation funcRep = new FunctionRepresentation(params, function, classToUse, identifier);
    classToUse.addMethod(identifier, funcRep);
  }
  
  static ClassRepresentation<Long> getIntegerClass(){
   return integerClass; 
  }
  
  static ClassRepresentation<Double> getDoubleClass(){
    return decimalClass; 
   }
  
  static ClassRepresentation<String> getStringClass(){
    return stringClass; 
  }
  
  static ClassRepresentation<Boolean> getBooleanClass(){
    return booleanClass; 
  }
  
  static ClassRepresentation<Object> getObjectClass(){
    return objectClass; 
  }
  
}

class BooleanFunctionRepresentation extends ObjectRepresentation<Boolean>{

  BooleanFunctionRepresentation(ParameterContainer params, RuntimeFunction func, ClassRepresentation returnRep) {
    super(params, func, returnRep);
  }
  
}

class StringFunctionRepresentation extends ObjectRepresentation<String>{

  StringFunctionRepresentation(ParameterContainer params, RuntimeFunction func, ClassRepresentation returnRep) {
    super(params, func, returnRep);
  }
  
}

class DecimalFunctionRepresentation extends ObjectRepresentation<Double>{

  DecimalFunctionRepresentation(ParameterContainer params, RuntimeFunction func, ClassRepresentation returnRep) {
    super(params, func, returnRep);
  }
  
}


class IntegerFunctionRepresentation extends ObjectRepresentation<Long>{

  IntegerFunctionRepresentation(ParameterContainer params, RuntimeFunction func, ClassRepresentation returnRep) {
    super(params, func, returnRep);
  }
  
}

class BaseValueException extends RuntimeException {
  public BaseValueException(String message) {
      super(message);
  }
}

