package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {

	private LLNode<T> head;
	private int size;
	
	
  public T pop() {
    
	  if (isEmpty()) {
		  
		  return null;		  
	  }
	  
	T temp = head.info;
		
	head = head.link;
		
	size--;
		
	return temp;     
  }

 
  
  public T top() {
    
	if (isEmpty() == true) {
		
		return null;
	}
	
	T temp = head.info;
	
	return temp;
  }

  
  public boolean isEmpty() {
	  
    return (head == null);
  }

  
  public int size() {
	  
    return size;
  }


  public void push(T elem) {
    
	  LLNode<T> temp = new LLNode<T>(elem);
	  
	  temp.link = head;
	  
	  head = temp;
	  
	  size++;
  }

}
