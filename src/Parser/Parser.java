/* Generated By:JavaCC: Do not edit this line. Parser.java */
public class Parser implements ParserConstants {
    public static void main(String[] args) {

        try {
            Parser parser = new Parser(new FileInputStream(args[0]));
            parser.parse();
        } catch (Exception exception) {
            System.out.println("Something went wrong.");
            System.out.println("Exception: ");
            System.out.println(exception);
        }
    }

//basic data types of the programming language (decimal, integers, strings, and booleans)
  static final public void data_types() throws ParseException {
                     Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DECIMAL:
      data_type_decimal();
      break;
    case INTEGER:
      data_type_integer();
      break;
    case STRING:
      data_type_string();
      break;
    case BOOLEAN:
      data_type_boolean();
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void data_type_decimal() throws ParseException {
                             Token t;
    t = jj_consume_token(DECIMAL);
          jjtThis.setFilteredValueForToken(t);
  }

  static final public void data_type_integer() throws ParseException {
                             Token t;
    t = jj_consume_token(INTEGER);
          jjtThis.setFilteredValueForToken(t);
  }

  static final public void data_type_string() throws ParseException {
                            Token t;
    t = jj_consume_token(STRING);
          jjtThis.setFilteredValueForToken(t);
  }

  static final public void data_type_boolean() throws ParseException {
                             Token t;
    t = jj_consume_token(BOOLEAN);
          jjtThis.setFilteredValueForToken(t);
  }

//used to detect the operator tokens used (addition, subtraction, multiplication, division, modulo, and logical operaters such as &&, ||, >=, <=, ==, etc) are all included
  static final public void operators() throws ParseException {
                    Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPADD:
      jj_consume_token(OPADD);
      break;
    case OPSUBTRACT:
      jj_consume_token(OPSUBTRACT);
      break;
    case OPDIVIDE:
      jj_consume_token(OPDIVIDE);
      break;
    case OPMULTIPLY:
      jj_consume_token(OPMULTIPLY);
      break;
    case OPMODULO:
      jj_consume_token(OPMODULO);
      break;
    case OPEQUALTO:
      jj_consume_token(OPEQUALTO);
      break;
    case OPNOTEQUALTO:
      jj_consume_token(OPNOTEQUALTO);
      break;
    case OPGREATERTHAN:
      jj_consume_token(OPGREATERTHAN);
      break;
    case OPLESSTHAN:
      jj_consume_token(OPLESSTHAN);
      break;
    case OPGREATERTHANEQUALTO:
      jj_consume_token(OPGREATERTHANEQUALTO);
      break;
    case OPLESSTHANEQUALTO:
      jj_consume_token(OPLESSTHANEQUALTO);
      break;
    case OPAND:
      jj_consume_token(OPAND);
      break;
    case OPOR:
      jj_consume_token(OPOR);
      break;
    case OPNOT:
      jj_consume_token(OPNOT);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x3c0,0x7ffe000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[43];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 43; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
