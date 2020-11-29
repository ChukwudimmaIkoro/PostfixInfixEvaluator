package evaluator;

/**
 * An Integer Evaluator class.
 */
public abstract class Evaluator {

  /** Check if a given token is a valid integer operand.
   *  Return true if it is, false otherwise
   */
  protected static boolean isOperand(String token) {
    try {
      Integer.parseInt(token);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /** Return the precedence / priority of each operator. */
  protected static int precedence(String op) {
    int priority = 1;
    if (op.equals("<") || op.equals(">") || op.equals("<=") || op.equals(">=") || op.equals("==") || op.equals("!=")) {
      return priority;
    }
    priority++;
    if (op.equals("+") || op.equals("-")) {
      return priority;
    }
    priority++;
    if (op.equals("*") || op.equals("/") || op.equals("%")) {
      return priority;
    }
    priority++;
    if (op.equals("!")) {
      return priority;
    }

    return 0;
  }

  public abstract Integer evaluate(String expr) throws Exception;

}
