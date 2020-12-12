package Runtime;

import java.util.LinkedHashMap;

public class RuntimeContext {
  private static LinkedHashMap<String, ClassRepresentation> globalClasses = new LinkedHashMap<String, ClassRepresentation>();
  private static LinkedHashMap<String, FunctionRepresentation> globalFunctions = new LinkedHashMap<String, FunctionRepresentation>();
  private static LinkedHashMap<String, ObjectRepresentation> globalObjects = new LinkedHashMap<String, ObjectRepresentation>();
  private LinkedHashMap<String, ObjectRepresentation> localObjects = new LinkedHashMap<String, ObjectRepresentation>();
  
  private ObjectRepresentation openObject = null;
  
  public RuntimeContext(ObjectRepresentation obj) {
    this.openObject = obj;
  }
  
  public static ObjectRepresentation getGlobalObject(String identifier) {
    if(globalObjects.containsKey(identifier)) {
      return globalObjects.get(identifier);
    }
    
    return getGlobalObject("undefined");
  } 
  
  public static void setGlobalObject(String identifier, ObjectRepresentation obj) {
    globalObjects.put(identifier, obj);
  }
  
  public void setLocal(String identifier, ObjectRepresentation obj) {
    localObjects.put(identifier, obj);
  }
  
  public void updateObject(String identifier, ObjectRepresentation obj) {
    if(localObjects.containsKey(identifier)) {
      localObjects.put(identifier, obj);
      return;
    }
    
    if(openObject.hasProp(identifier)) {
      openObject.setProp(identifier, obj);
      return;
    }
    
    if(globalObjects.containsKey(identifier)) {
      globalObjects.put(identifier, obj);
    }
  }
  
  public static void setClass(String identifier, ClassRepresentation classRep) {
    globalClasses.put(identifier, classRep);
  }
  
  public ObjectRepresentation getObject(String identifier) {
    if(localObjects.containsKey(identifier)) {
      return localObjects.get(identifier);
    }
    
    if(openObject.hasProp(identifier)) {
      return openObject.getProp(identifier);
    }
    
    if(globalObjects.containsKey(identifier)) {
      return globalObjects.get(identifier);
    }
    
    return getGlobalObject("undefined");
  }
  
  public FunctionRepresentation getFunction(String identifier) {
    if(openObject != null && openObject.getObjectClassRepresentation().hasMethod(identifier)) {
      return openObject.getObjectClassRepresentation().getMethod(identifier);
    }
    
    if(globalFunctions.containsKey(identifier)) {
      return globalFunctions.get(identifier);
    }
    
    throw new RuntimeContextException(
        "Function: `" + identifier + "` not be found."
    );
  }
  
  public ObjectRepresentation getOpenObject() {
    return this.openObject;
  }
  
  public void setOpenObject(ObjectRepresentation obj) {
    this.openObject = obj;
  }
  
  public static ClassRepresentation getClass(String identifier) {
    if(globalClasses.containsKey(identifier)) {
      return globalClasses.get(identifier);
    }
    
    throw new RuntimeContextException(
        "Class: `" + identifier + "` not found."
    );
  }
  
  public static void setGlobalFunction(String identifier, FunctionRepresentation funcRep) {
    globalFunctions.put(identifier, funcRep);
  }
  
  public Object getOpenBaseValue() {
    return openObject.getBaseVal();
  }
  
}

class RuntimeContextException extends RuntimeException {
  RuntimeContextException(String message) {
      super(message);
  }
}

