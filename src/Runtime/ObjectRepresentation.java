package Runtime;

import java.util.LinkedHashMap;

public class ObjectRepresentation<B> {
  private PropertyContainer props;
  private CreatedClass<B> objectClass;
  private B baseVal;
  
  
  ObjectRepresentation(CreatedClass<B> object,
      B value){
    this.objectClass = object;
    this.baseVal = value;
    this.props = objectClass.getProps();
  }
  
  ObjectRepresentation(ObjectRepresentation<B> obj){
    this.objectClass = obj.objectClass;
    this.baseVal = obj.baseVal;
    
    if(obj.props.size() > 0) {
      this.props = obj.getObjectClassRepresentation().getPropertiesCopy();
    }else {
      this.props = new PropertyMap();
    }
  }
  
  public CreatedClass getObjectClassRepresentation() {
    return objectClass;
  }
  
  public boolean hasProp(String identifier) {
    return props.containsKey(identifier);
  }
  
  public ObjectRepresentation getProp(String identifier) {
    return props.get(identifier);
  }
  
  public void setProp(String identifier, ObjectRepresentation val) {
    props.put(identifier, val);
  }
  
  public ObjectRepresentation runMethod(String name, CreatedClass args) {
    return objectClass.getMethod(name).call(this, args);
  }
  
  public B getBaseVal() {
    return baseVal;
  }
  
}

class PropertyContainer extends LinkedHashMap<String, ObjectRepresentation<Object>> {
}

class ObjectException extends RuntimeException {
  public ObjectException(String message) {
      super(message);
  }
}
