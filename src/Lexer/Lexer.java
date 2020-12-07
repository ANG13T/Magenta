package Lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
  private final static String[] keywordsList = {"task", "block", "if", "true", "false", "null"};
  static List<String> keywords = Arrays.asList(keywordsList);
  

  public static void main(String[] args) {
    
    try {
        File file = new File("src/tests/lexerText2.txt"); 
        Scanner sc = new Scanner(file); 
        String tokensString = "";
        while (sc.hasNextLine()) {
          tokensString += sc.nextLine(); 
        }
        printTokens(lexenize(tokensString));
    }catch(Exception error) {
      System.out.println(error);
      System.exit(1);
    }
  }
  
  private static List<TokenObj> lexenize(String input) {
    List<TokenObj> tokens = new ArrayList<TokenObj>();
     
   
   // Lexer logic begins here
      StringBuffer tokenPatternsBuffer = new StringBuffer();
      for (TokenObjSet tokenType : TokenObjSet.values()) {
        tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
      Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

      // Begin matching tokens
      Matcher matcher = tokenPatterns.matcher(input);
      while (matcher.find()) {
        if (matcher.group(TokenObjSet.NUMBER.name()) != null) {
          tokens.add(new TokenObj(TokenObjSet.NUMBER, matcher.group(TokenObjSet.NUMBER.name())));
          continue;
        } else if (matcher.group(TokenObjSet.BINARYOP.name()) != null) {
          tokens.add(new TokenObj(TokenObjSet.BINARYOP, matcher.group(TokenObjSet.BINARYOP.name())));
          continue;
        } else if (matcher.group(TokenObjSet.WHITESPACE.name()) != null)
          continue;
      }
    }
    
    
    return tokens;
  }
  
  private static void printTokens(List<TokenObj> tokens) {
    for(TokenObj token: tokens) {
      System.out.println(token);
    }
  }
    
}



