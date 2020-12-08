package Lexer;

import java.util.regex.Pattern;



public enum TokenObjSet {
  // Token types cannot have underscores
  
  //basic data types
  DECIMAL("\\d+(\\.\\d{1,2})?", false),
  NUMBER("(?<=\\s|^)\\d+(?=\\s|$)", false),
  STRING("\"[^\"]*\"", false),
  BOOLEAN("true(?=[^_A-Za-z])|false(?=[^_A-Za-z])", false),
  
  //identifiers
  IDENTCAPITALIZED("[A-Z][_A-Za-z]*(?=[^_A-Za-z])", false),
  IDENTLOWERCASE("[a-z][_A-Za-z]*(?=[^_A-Za-z])", false),
  IDENTUNDERSCORE("_[_A-Za-z]*(?=[^_A-Za-z])", false),
  
  //operators
  OPADD("[+]", false),
  OPSUBTRACT("[-]", false),
  OPMULTIPLY("[*]", false),
  OPDIVIDE("[/]", false),
  OPMODULO("[%]", false),
  OPEQUALTO("==", false),
  OPNOTEQUALTO("!=", false),
  OPGREATERTHAN(">", false),
  OPLESSTHAN("<", false),
  OPGREATERTHANEQUALTO(">=", false),
  OPLESSTHANEQUALTO("<=", false),
  OPAND("&&", false),
  OPOR("\\|\\|", false),
  OPNOT("!", false),
  
  //punctuators
  PUNCQUESTION("[?]", false),
  PUNCCOLON("[:]", false),
  PUNCSEMICOLON("[;]", false),
  PUNCCOMMA("[,]", false),
  PUNCLEFTPAREN("[(]", false),
  PUNCRIGHTPAREN("[)]", false),
  PUNCLEFTCURLY("[{]", false),
  PUNCRIGHTCURLY("[}]", false),

  
  //keywords
  KEYIF("if(?=[^_A-Za-z])", false),
  KEYFOR("for(?=[^_A-Za-z])", false),
  KEYWHILE("while(?=[^_A-Za-z])", false),
  KEYBLOCK("block(?=[^_A-Za-z])", false),
  KEYEMIT("emit(?=[^_A-Za-z])", false),
  KEYTASK("task(?=[^_A-Za-z])", false),
  KEYNULL("null(?=[^_A-Za-z])", false),
  KEYPASS("pass(?=[^_A-Za-z])", false),
 
  WHITESPACE("[ \t\f\r\n]+", false), 
  COMMENT("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", false);
 
  

  public String regex;
  private boolean includeValue;

  private TokenObjSet(String regex, boolean includeVal) {
    this.regex = regex;
    this.includeValue = includeVal;
  }
  
  Pattern getPattern() {
    return Pattern.compile(regex);
  }
  
  boolean getIncludeValue() {
    return includeValue;
  }
}

//PUNCLEFTSQUARE("[", false),
//PUNCRIGHTSQUARE("]", false),
