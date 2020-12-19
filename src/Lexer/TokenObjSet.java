package Lexer;

import java.util.regex.Pattern;



public enum TokenObjSet {
  // Token types cannot have underscores
  
  //basic data types
  DECIMAL("([0-9]+\\.[0-9]+)(?=[^0-9\\.])", true),
  INTEGER("([0-9]+)(?=[^0-9\\.])", true),
  STRING("\"[^\"]*\"", true),
  BOOLEAN("true(?=[^_A-Za-z])|false(?=[^_A-Za-z])", true),
  
  //keywords
  KEYIF("if(?=[^_A-Za-z])", false),
  KEYFOR("for(?=[^_A-Za-z])", false),
  KEYWHILE("while(?=[^_A-Za-z])", false),
  KEYBLOCK("block(?=[^_A-Za-z])", false),
  KEYEMIT("emit(?=[^_A-Za-z])", false),
  KEYTASK("task(?=[^_A-Za-z])", false),
  KEYNULL("null(?=[^_A-Za-z])", false),
  KEYPASS("pass(?=[^_A-Za-z])", false),
  KEYSET("set(?=[^_A-Za-z])", false),
  
  //identifiers
  IDENTCAPITALIZED("[A-Z][_A-Za-z]*(?=[^_A-Za-z])", true),
  IDENTLOWERCASE("[a-z][_A-Za-z]*(?=[^_A-Za-z])", true),
  IDENTUNDERSCORE("_[_A-Za-z]*(?=[^_A-Za-z])", true),
  
  //punctuators
  PUNCQUESTION("[?]", false),
  PUNCCOLON("[:]", false),
  PUNCPERIOD("[.]", false),
  PUNCEQUALSIGN("[=]", false),
  PUNCSEMICOLON("[;]", false),
  PUNCCOMMA("[,]", false),
  PUNCLEFTPAREN("[(]", false),
  PUNCRIGHTPAREN("[)]", false),
  PUNCLEFTCURLY("[{]", false),
  PUNCLEFTBLOCK("[\\[]", false),
  PUNCRIGHTBLOCK("[\\]]", false),
  PUNCRIGHTCURLY("[}]", false),
  PUNCOPENARROW("\\->", false),
  PUNCCLOSEARROW("\\<-", false),

  
  //operators
  OPADD("(?<!\\S)*\\+", false),
  OPSUBTRACT("(?<=[ \\t])(\\-)(?=[ \\t])", false),
  OPMULTIPLY("(?<!\\S)*\\*", false),
  OPDIVIDE("(?<!\\S)*\\/", false),
  OPMODULO("(?<!\\S)*\\%", false),
  OPEQUALTO("(?<!\\S)*\\==", false),
  OPNOTEQUALTO("!=", false),
  OPGREATERTHAN("(?<=[ \\t])(\\>)(?=[ \\t])", false),
  OPLESSTHAN("(?<=[ \\t])(\\<)(?=[ \\t])", false),
  OPGREATERTHANEQUALTO(">=", false),
  OPLESSTHANEQUALTO("<=", false),
  OPAND("&&", false),
  OPOR("\\|\\|", false),
  OPNOT("!", false),
  
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

