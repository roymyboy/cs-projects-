/**
 * Node represents a double node in a linked list.
 * 
 * @author Java Foundations and uroy
 * @version 2.0
 */
public class Node<T> {

	private Node<T> previous;
	private Node<T> next;
	private T element;

	/**
	 * Creates an empty node.
	 */
	public Node() {
		previous = next = null;
		element = null;
	}

	/**
	 * Creates a node storing the specified element.
	 * 
	 * @param elem
	 *            the element to be stored within the new node
	 */
	public Node(T elem) {
		previous = next = null;
		element = elem;
	}

	/**
	 * Returns the node that follows this one.
	 * 
	 * @return the node that follows the current one
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Sets the node that follows this one.
	 * 
	 * @param node
	 *            the node to be set to follow the current one
	 */
	public void setNext(Node<T> node) {
		next = node;
	}

	/**
	 * Returns the element stored in this node.
	 * 
	 * @return the element stored in this node
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Sets the element stored in this node.
	 * 
	 * @param elem
	 *            the element to be stored in this node
	 */
	public void setElement(T elem) {
		element = elem;
	}

	/**
	 * Returns the node that precedes this one.
	 * 
	 * @return the node that precedes the current one
	 */
	public Node<T> getPreviousNode() {
		return previous;
	}

	/**
	 * Sets the node that precedes this one.
	 * 
	 * @param node
	 *            the node to be set to that precedes the current one
	 */
	public void setPreviousNode(Node<T> node) {
		previous = node;
	}

}
