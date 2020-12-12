package Runtime;

public class RuntimeConstants {
  private static ClassRepresentation<Object> objectClass;
  private static ClassRepresentation<Boolean> booleanClass;
  private static ClassRepresentation<Long> integerClass;
  private static ClassRepresentation<Double> decimalClass;
  private static ClassRepresentation<String> stringClass;
  
  public static void setGlobalConstants() {
    objectClass = new ClassRepresentation("Object", null);
    ClassRepresentation<Object> voidClass = new ClassRepresentation<Object>("vacant", null);
    RuntimeContext.setClass("Object", objectClass);
  }
}
