package Lexer;

public class TokenObj {
  public TokenObjSet type;
  public String data;

  public TokenObj(TokenObjSet type, String data) {
    this.type = type;
    this.data = data;
  }

  @Override
  public String toString() {
    return String.format("(%s %s)", type.name(), data);
  }
}
