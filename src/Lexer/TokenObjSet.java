package Lexer;

import java.util.regex.Pattern;



public enum TokenObjSet {
  // Token types cannot have underscores
  
  //basic data types
  NUMBER("(?<=\\s|^)\\d+(?=\\s|$)"),
  DECIMAL("\\d+(\\.\\d{1,2})?"),
  STRING("\"[^\"]*\""),
  BOOLEAN("true(?=[^_A-Za-z])|false(?=[^_A-Za-z])"),
  WHITESPACE("[ \t\f\r\n]+"),
  
  //operators
  OPADD("[+]"),
  OPSUBTRACT("[-]"),
  OPMULTIPLY("[*]"),
  OPDIVIDE("[/]"),
  OPMODULO("[%]"),
  OPEQUALTO("=="),
  OPNOTEQUALTO("!="),
  OPGREATERTHAN(">"),
  OPLESSTHAN("<"),
  OPGREATERTHANEQUALTO(">="),
  OPLESSTHANEQUALTO("<="),
  OPAND("&&"),
  OPOR("||"),
  OPNOT("!"),
  
  //punctuators
  PUNCQUESTION("[?]"),
  PUNCCOLON("[:]"),
  PUNCSEMICOLON("[;]"),
  PUNCPERIOD("[.]"),
  PUNCCOMMA("[,]"),
  PUNCLEFTPAREN("[(]"),
  PUNCRIGHTPAREN("[)]"),
  PUNCLEFTCURLY("[{]"),
  PUNCRIGHTCURLY("[}]"),
  PUNCLEFTSQUARE("["),
  PUNCRIGHTSQUARE("]"),
  
  //keywords
  KEYIF("if(?=[^_A-Za-z])"),
  KEYFOR("for(?=[^_A-Za-z])"),
  KEYWHILE("while(?=[^_A-Za-z])"),
  KEYBLOCK("block(?=[^_A-Za-z])"),
  KEYEMIT("emit(?=[^_A-Za-z])"),
  KEYTASK("task(?=[^_A-Za-z])"),
  KEYNULL("null(?=[^_A-Za-z])"),
  
  //identifiers
  IDENTCAPITALIZED("[A-Z][_A-Za-z]*(?=[^_A-Za-z])"),
  IDENTLOWERCASE("[a-z][_A-Za-z]*(?=[^_A-Za-z])"),
  IDENTUNDERSCORE("_[_A-Za-z]*(?=[^_A-Za-z])");
 
  

  public final String pattern;

  private TokenObjSet(String pattern) {
    this.pattern = pattern;
  }
}
