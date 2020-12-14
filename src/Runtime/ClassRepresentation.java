package Runtime;

import java.util.LinkedHashMap;

public class ClassRepresentation<B> {
  private DefaultPropsContainer overridenProps;
  private DefaultPropsContainer defaultProps;
  private MethodContainer<B> methods;
  private String name;
  private ClassRepresentation extendsClass;
  
  public ClassRepresentation(
    DefaultPropsContainer overridenProps,
    DefaultPropsContainer defaultProps,
    MethodContainer<B> methods,
    String name,
    ClassRepresentation extendsClass
  ) {
    this.overridenProps = overridenProps;
    this.defaultProps = defaultProps;
    this.methods = methods;
    this.name = name;
    this.extendsClass = extendsClass;
  }
  
  
  public ClassRepresentation(
    DefaultPropsContainer defaultProps,
    MethodContainer<B> methods,
    String name,
    ClassRepresentation extendsClass) {
    this(new DefaultPropsContainer(), defaultProps, methods, name, extendsClass);
  }
  
  public ClassRepresentation(String name, ClassRepresentation extendsClass) {
    this(new DefaultPropsContainer(), new MethodContainer<B>(), name, extendsClass);
  }
  
  public ObjectRepresentation<B> createObject(){
    return createObject(new ObjectRepresentation[0], null);
  }
  
  public ObjectRepresentation<B> createObject(B val){
    return createObject(new ObjectRepresentation[0], val);
  }
  
  public ObjectRepresentation<B> createObject(ObjectRepresentation[] args, B val){
    ObjectRepresentation<B> createdObject = new ObjectRepresentation<B>(this, val);
    
    if(hasMethod("init")) {
      createdObject.runMethod("init", args);
    }
    
    return createdObject;
  }
  
  public FunctionRepresentation<B> getMethod(String name){
    if(methods.containsKey(name)) {
      return methods.get(name);
    }
    
    if(extendsClass != null) {
      return extendsClass.getMethod(name);
    }
    
    throw new MethodNameException(
        "Method name `" + name + "` not found."
    );
  }
  
  public boolean hasMethod(String methodName) {
    if(extendsClass != null) {
      return methods.containsKey(methodName) || extendsClass.hasMethod(methodName);
    }else {
      return methods.containsKey(methodName);
    }
  }
  
  protected void addMethod(String name, FunctionRepresentation func) {
    if(methods.containsKey(func)) {
      throw new MethodNameException(
          "The method: `" + name + "` already exists."
      );
    }
    
    methods.put(name, func);
  }
  
  public PropertyContainer getProps() {
    PropertyContainer props = new PropertyContainer();
    
    if(extendsClass != null) {
      props.putAll(extendsClass.getProps());
    }
    
    for(String overrideKey: overridenProps.keySet()) {
      if(!props.containsKey(overrideKey)) {
        throw new OverrideException(
            "Overridden key: `" + overrideKey + "` does not exist."
        );
      }
      
      if(props.get(overrideKey).getObjectClassRepresentation() != overridenProps.get(overrideKey).get) {
        throw new OverrideException(
            "Expected type `"
                + props.get(overrideKey).getObjectClassRepresentation().toString()
                + "`, but found `"
                + props.get(overrideKey).getObjectClassRepresentation()
                + "`."
        );
      }
      
      props.put(overrideKey, new ObjectRepresentation<Object>(overridenProps.get(overrideKey)));
    }
    
    for(String defKey: defaultProps.keySet()) {
      props.put(defKey, new ObjectRepresentation<Object>(defaultProps.get(defKey)));
    }
    
    return props;
  }
  
  @Override public String toString() {
    return name;
  }
  
}

class MethodNameException extends RuntimeException {
  MethodNameException(String message) {
      super(message);
  }
}

class OverrideException extends RuntimeException {
  OverrideException(String message) {
      super(message);
  }
}

class MethodContainer<B> extends LinkedHashMap<String, FunctionRepresentation<Object>> {
}

class DefaultPropsContainer extends LinkedHashMap<String, ClassRepresentation> {
}