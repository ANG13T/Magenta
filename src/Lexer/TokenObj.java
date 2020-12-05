package Lexer;

public class TokenObj {
  
  public String identifier;
  public String text;
  
  public TokenObj(String identifier, String text) {
    this.identifier = identifier;
    this.text = text;
  }
  
  public String getText() {
    return "[ " + identifier + " : " + text + " ]";
  }
}
