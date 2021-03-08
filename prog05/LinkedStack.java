package prog05;

import java.util.EmptyStackException;

/** Class to implement interface StackInt<E> as a linked list.
*   @author vjm
* */

public class LinkedStack<E> implements StackInt<E> {

  /** A Node is the building block for a single-linked list. */
  private static class Node<E> {
    // Data Fields
    /** The reference to the data. */
    private E data;

    /** The reference to the next node. */
    private Node next;

    // Constructors
    /** Creates a new node with a null next field.
        @param data The data stored
     */
    private Node (E data) {
      this.data = data;
      next = null; // Necessary in C++ but not in Java.
    }

    /** Creates a new node that references another node.
        @param data The data stored
        @param next The next node referenced by new node.
     */
    private Node (E data, Node<E> next) {
      this.data = data;
      this.next = next;
    }
  } //end class Node

  // Data Fields
  /** The reference to the top stack node. */
  private Node<E> top = null;

  /** Pushes an item onto the top of the stack and returns the item
      pushed.
      @param obj The object to be inserted.
      @return The object inserted.
   */
  public E push (E obj) {
    top = new Node<E>(obj, top);
    return obj;
  }

@Override
public E peek() {
	// TODO Auto-generated method stub
	if (empty())
		  throw new EmptyStackException();
	return top.data;
}

@Override
public E pop() {
	// TODO Auto-generated method stub
	if (empty())
		  throw new EmptyStackException();
	E tempNext = top.data;
	top = top.next;
	return tempNext;
}

@Override
public boolean empty() {
	// TODO Auto-generated method stub
	if (top != null)
		return false;
	return true;
}

  /**** EXERCISE ****/

}
