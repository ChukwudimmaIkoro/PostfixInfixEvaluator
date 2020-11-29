package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {

  private LinkedStack<String> operators = new LinkedStack<String>();
  private LinkedStack<Integer> operands  = new LinkedStack<Integer>();

  /** return stack object (for testing purpose). */
  public LinkedStack<String> getOperatorStack() { 
    return operators; 
  }
  
  public LinkedStack<Integer> getOperandStack() { 
    return operands;
  }

  
  public void evaluate_step(String token) throws Exception {
	  
    if (isOperand(token)) {
    	
    	operands.push(Integer.valueOf(token));
    }
    
     else {
    	 
    	 if (token.equals("(")) {
    		 
    		 operators.push(token);
    		 
    		 return;
    	 }
    	 
    	 else if (operators.isEmpty() || precedence(token) > (precedence(operators.top()))) {   
    		 
    		 operators.push(token);
    		 return;
    	 }
    	 
    	 else if (token.equals(")")) {
    		 
    		 while (!operators.top().equals("(")) { // change?
    			 
    			 operands.push(process(operators.pop()));
    			
    			 
    			if (operators.isEmpty()) { 
		    			 
				 throw new Exception("missing (");
			 
			 }
    	 
    	 }
    			 operators.pop();
    			 return;
    		 
    	 }
    	
    else {
    	
    		 while (!operators.isEmpty() && !(precedence(token) > precedence(operators.top()))) {
    			 
    			 
    			 operands.push(process(operators.pop()));
    		 
    		 }
    		 operators.push(token);
    		 
    	 }
     }
     }
  
    
    
  
  
  private Integer process(String token) throws Exception {
	  	      	
    	Integer a = null;			
    	Integer b = null;
    	//String operator = operators.pop();
    	
    	
    	if (token.equals("!")) {
    		   		
    		a = operands.pop();
    		
    		if (a == null) {
    			
    			throw new Exception("too few operands");
    		}
    		
    		return -a;
    		
    	}
    	
    	else {
    		
    		a = operands.pop();
    		b = operands.pop();
    		
    		if (a == null || b == null) {
    			
    			throw new Exception("too few operands");
    			
    		}
    	}
    	
    	Integer processed;
    	
    	if (token.equals("+")) {
    
    		processed = (b + a);
    	}
    	
    	else if (token.equals("-")) {
    
    		processed = (b - a);
    	}
    	
    	else if (token.equals("*")) {
    
    		processed = (b * a);
    	}
    	
    	else if (token.equals("/")) {
    		
    		if (a == 0) {
    			
    			throw new Exception("Division by zero");
    		}
    		
    		processed = (b / a);
    	}
      	
        else {
        		
        		throw new Exception("invalid operator");
        	}
    	
    	return processed;
    }
  
  	
	



/** This method evaluates an infix expression (defined by expr)
   *  and returns the evaluation result. It throws an Exception object
   *  if the infix expression is invalid.
   */
  
  public Integer evaluate(String expr) throws Exception {

    for (String token : ArithParser.parse(expr)) {
      evaluate_step(token);
    }

    
    while (!operators.isEmpty()) {
    	
    	operands.push(process(operators.pop()));
    	
    }
    
    
    if (operands.size() > 1) {
      throw new Exception("too many operands");
    } else if (operands.size() < 1) {
      throw new Exception("too few operands");
    }

    return operands.pop();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
  }
}
