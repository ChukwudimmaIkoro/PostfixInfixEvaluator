package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * An {@code ArithParser} is a parser for arithmetic expressions.
 */
public class ArithParser {

  /**
   * Parse String.
   * @param expr expression
   * @return parsed String.
   */
  public static String[] parse(String expr) {
    /* first pass: scan for number of tokens */
    Pattern pattern = Pattern.compile("-?[0-9]+|[-+*/%?()]|>=|>|==|<=|!=|!");
    Matcher match = pattern.matcher(expr);
    int ntokens = 0;
    while (match.find()) {
      ntokens++;
    }
    // second pass: store tokens into String array
    match = pattern.matcher(expr);
    String[] tokens = new String[ntokens];
    ntokens = 0;
    while (match.find()) {
      tokens[ntokens++] = match.group();
    }
    return tokens;
  }

  /**
   * Parse token.
   * @param expr expression
   */
  public static void parse_tokens(String expr) {
    for (String token : ArithParser.parse(expr)) {
      System.out.print(token + " ");
    }
    System.out.println("");
  }

  /**
   * Run parsers.
   * @param args arguments
   */
  public static void main(String[] args) {
    try {
      System.out.print("Example postfix expression: ");
      parse_tokens("10 20! +");
      System.out.print("Example infix expression: ");
      parse_tokens("10 + 20 * (30+!40)");
    } catch (RuntimeException e) {
      System.out.println(e);
    }
  }
}
