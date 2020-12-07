package Lexer;

import java.util.regex.Pattern;



public enum TokenObjSet {
  // Token types cannot have underscores
  NUMBER("-?[0-9]+"), BINARYOP("[*|/|+|-]"), WHITESPACE("[ \t\f\r\n]+");

  public final String pattern;

  private TokenObjSet(String pattern) {
    this.pattern = pattern;
  }
}