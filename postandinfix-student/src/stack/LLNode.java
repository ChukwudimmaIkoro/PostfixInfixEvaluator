package stack;


// A generic Linked List Node
public class LLNode<T> {

  // The two class variables are both public
  public T info;
  public LLNode<T> link;

  /**
   * Constructor.
   * @throws NullPointerException if {@code info} is {@code null}
   */
  public LLNode(T info) {
    if (info == null) {
      throw new NullPointerException();
    }
    this.info = info;
  }
}
