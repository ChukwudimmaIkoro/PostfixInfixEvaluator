package evaluator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PublicInfixEvaluatorTest {

  private InfixEvaluator evaluator;

  @Before
  public void setup() {
    evaluator = new InfixEvaluator();
  }

  @Test
  public void testEvaluateSimple() throws Exception {
    assertEquals(new Integer(1), evaluator.evaluate("1"));
  }

  @Test
  public void testEvaluatePlus() throws Exception {
    assertEquals(new Integer(3), evaluator.evaluate("1 + 2"));

    assertEquals(new Integer(6), evaluator.evaluate("1 + 2 + 3"));

    assertEquals(new Integer(11111), evaluator.evaluate("10000 + 1000 + 100 + 10 + 1"));
  }

  @Test
  public void testEvaluateSub() throws Exception {
    assertEquals(new Integer(-1), evaluator.evaluate("1 - 2"));

    assertEquals(new Integer(-4), evaluator.evaluate("1 - 2 - 3"));

    assertEquals(new Integer(889), evaluator.evaluate("1000 - 100 - 10 - 1"));
  }

  @Test
  public void testEvaluateMixed() throws Exception {
    assertEquals(new Integer(10), evaluator.evaluate("2 * 3 + 4"));

    assertEquals(new Integer(14), evaluator.evaluate("2 + 3 * 4"));

    assertEquals(new Integer(12), evaluator.evaluate("2 - 3 + 4 * 10 / 5 + 4 + 1"));
  }

  @Test
  public void testEvaluateNegate() throws Exception {
    assertEquals(new Integer(-1), evaluator.evaluate("!1"));

    assertEquals(new Integer(15), evaluator.evaluate("! -15"));

    assertEquals(new Integer(-1), evaluator.evaluate("!4 + 3"));

  }

  @Test
  public void testParentheses() throws Exception {
    assertEquals(new Integer(14), evaluator.evaluate("2 * (3 + 4)"));

    assertEquals(new Integer(29), evaluator.evaluate("2 + (3 * (4 + 5))"));

    assertEquals(new Integer(10), evaluator.evaluate("(10)"));

    assertEquals(new Integer(5), evaluator.evaluate("!(-5)"));

    assertEquals(new Integer(5), evaluator.evaluate("!(-15 + 10)"));

    assertEquals(new Integer(2), evaluator.evaluate("! (! 2)"));
  }

  @Test
  public void testInvalidExpression1() throws Exception {
    String msg = null;
    try {
      evaluator.evaluate("1 2");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("too many operands", msg);
  }

  @Test
  public void testInvalidExpression2() throws Exception {
    String msg = null;
    try {
      evaluator.evaluate("1 % 2");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("invalid operator", msg);
  }

  @Test
  public void testInvalidExpression3() throws Exception {
    String msg = null;
    try {
      evaluator.evaluate("1 -");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("too few operands", msg);
  }

  @Test
  public void testInvalidExpression4() throws Exception {
    String msg = null;
    try {
      evaluator.evaluate("!");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("too few operands", msg);

    msg = null;
    try {
      evaluator.evaluate("/");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("too few operands", msg);

    msg = null;
    try {
      evaluator.evaluate("( )");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("too few operands", msg);
  }

  @Test
  public void testInvalidExpression5() throws Exception {
    String msg = null;
    try {
      evaluator.evaluate("1 + 5)");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("missing (", msg);
  }

  @Test
  public void testDivisionByZero() throws Exception {
    assertEquals(new Integer(0), evaluator.evaluate("0 / 10"));
    String msg = null;
    try {
      evaluator.evaluate("10 / 0");
    } catch (Exception e) {
      msg = e.getMessage().toLowerCase();
    }
    assertEquals("division by zero", msg);
  }
}
