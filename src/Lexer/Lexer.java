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

public class Lexer {
  private final static String[] keywordsList = {"task", "block", "if", "true", "false", "null"};
  static List<String> keywords = Arrays.asList(keywordsList);

  public static void main(String[] args) {
    
    try {
      File file = 
          new File("src/tests/lexerTest1.txt"); 
        Scanner sc = new Scanner(file); 
        lexenize(sc);
        
    }catch(Exception error) {
      System.out.println(error);
      System.exit(1);
    }
  }
  
  private static void lexenize(Scanner sc) {
    List<TokenObj> tokens = new ArrayList<TokenObj>();
    String tokensString = "";
    while (sc.hasNextLine()) {
      tokensString += sc.nextLine(); 
    }
    
    String[] pieces = tokensString.split("\\s+");
    for(String piece: pieces) {
      
      if(piece.matches("^[A-Z]+[a-zA-Z]*")) {
        System.out.println(piece);
        if(keywords.contains(piece)) {
          tokens.add(new TokenObj("IDENTIFIER", piece));
        }
      }
      
    }
  }
    
}

