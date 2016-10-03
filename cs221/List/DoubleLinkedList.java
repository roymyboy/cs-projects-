import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * DoubleLinkedList represents a Node-based implementation of both an unordered and indexed list.
 *
 * @author utroy, 
 * @version 4.0
 */
public class DoubleLinkedList<T> implements DoubleLinkedListADT<T>{
	private Node<T> head;//first element of the list
	private Node<T> tail;//last element of list
	private int count; //to keeps track of the size of list
	private int modCount;//count the number of modification
	
	private enum Pointers{
		next, prev, other
	}
	/**
	 * Adds the specified element to the front of this list.
	 *
	 * @param element the element to be added to the list
	 */
	
	public void addToFront(T element) {
		 Node<T> tmp = new Node<T>(element);//temporary node
		 //check for an empty list
         if(head == null ) {
        	 head = tail = tmp; //set head and tail to the temporary variable
        	 }else{
        	 tmp.setNext(head);//set the first node to new node
        	 head.setPreviousNode(null);//set the previous node to null
        	 head = tmp;//set new node to the head of the list
         }
         count++;//increase the size
         modCount++;//increase the number of modification	
	}
	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the list
	 */
	
	public void addToRear(T element) {
		Node<T> tmp = new Node<T>(element);//temporary node
		//check if the tail position is empty
		if(tail ==  null ){
			head = tail = tmp;//set head and tail to the temporary variable
		}else {
			tail.setNext(tmp);//set the new node to the tail
			tmp.setPreviousNode(tail);//set the previous node to null
			tail = tmp;//set new node to the tail
		}
		count++;//increase the size
		modCount++;//increase the count of modification
	}
	
	/**
	 * Adds the specified element to this list after the given target.
	 *
	 * @param  element the element to be added to this list
	 * @param  target the target element to be added after
	 * @throws ElementNotFoundException if the target is not found
	 */
	public void addAfter(T element, T target) {
		Node<T> current = head;//new Node<T>();
	//	current = head;
		Node<T> newNode = new Node<T>(element);
		boolean found = false; // set the boolean found to false

		// check if the list is empty
		if (isEmpty()) {
			throw new ElementNotFoundException("list");
		}

		// loop until the target is found and the cursor hit null
		while (current != null && !found) {
			if (current.getElement().equals(target)) {
				found = true;
				if (current == head) { // if current is pointing at head
					if (head == tail) {// if current is pointing at tail
						head.setNext(newNode); // set the node next to head to
						// the new temporary node
						newNode.setPreviousNode(head);
						tail = newNode; // tail gets the new temporary node
					} else {
						newNode.setPreviousNode(current); // the previous node
						newNode.setNext(current.getNext()); // next to new node
						// will be the
						// the element next to current
						current.getNext().setPreviousNode(newNode);
						current.setNext(newNode);
					}
				} else if (current == tail) { // if the cursor is pointing at
					// the tail
					newNode.setPreviousNode(tail);
					tail.setNext(newNode); // set the next node of tail to the
					// new node
					tail = newNode; // tail gets the new node
				} else {
					newNode.setPreviousNode(current);
					newNode.setNext(current.getNext());
					current.getNext().setPreviousNode(newNode);
					current.setNext(newNode);
				}
			} else {
				current = current.getNext(); // current gets the node next to it
			}
		}
		// if not found
		if (!found) {
			throw new ElementNotFoundException("Element not found");
		}
		count++;
		modCount++;
	}
	
	/**
	 * Removes the first element in this list and returns a reference
	 * to it. Throws an EmptyCollectionException if the list is empty.
	 *
	 * @return a reference to the first element of this list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T removeFirst() {
		T result = null;
		Node<T> current =head; 
		//check if the list is empty
				if(isEmpty()){
					throw new EmptyCollectionException("DoubleLinkedList");
				}
				result = head.getElement();//initialize with first element
				
				//check for only one element
				if(current == tail){
					result = head.getElement();
					current = head = null;
				}else{ //for the populated list
					current = current.getNext();
					current.setPreviousNode(null); //set previous to current and current to null
				}
				count--;
				modCount++;
		return result;
	}

	/**
	 * Removes the last element in this list and returns a reference
	 * to it. Throws an EmptyCollectionException if the list is empty.
	 *
	 * @return the last element in this list
	 * @throws EmptyCollectionException if the list is empty    
	 */
	public T removeLast() {
		T result = null;//new element
		Node<T> current = head; //initializing temporary node
		
		//check if the list is empty
		if(isEmpty()){
			throw new EmptyCollectionException("DoubleLinkedList");
		}
		
		if(current == tail){
			result = tail.getElement();
			head = tail = null;
		}else{
			while(tail != current){
				current = current.getNext(); //current gets the element next to it
				if(current == tail){
					result = current.getElement();
					tail = current.getPreviousNode();
					break;
				}
			}
		}
		count--;
		modCount++;
		return result;
	}
	
	/**
	 * Removes the first instance of the specified element from this
	 * list and returns a reference to it. Throws an EmptyCollectionException 
	 * if the list is empty. Throws a ElementNotFoundException if the 
	 * specified element is not found in the list.
	 *
	 * @param  targetElement the element to be removed from the list
	 * @return a reference to the removed element
	 * @throws ElementNotFoundException if the target element is not found
	 */
	public T remove(T targetElement)throws ElementNotFoundException{

		if (isEmpty()){
			throw new ElementNotFoundException("LinkedList");
		}
		boolean found = false;
		Node<T> previous = null;
		Node<T> current = head;

		while (current != null && !found)
			if (targetElement.equals(current.getElement()))
				found = true;
			else
			{
				previous = current;
				current = current.getNext();
			}

		if (!found)
			throw new ElementNotFoundException("LinkedList");

		if (size() == 1)  // only one element in the list
			head = tail = null;
		else if (current.equals(head))  // target is at the head 
			head = current.getNext();
		else if (current.equals(tail))  // target is at the tail
		{
			tail = previous;
			tail.setNext(null);
		}
		else  // target is in the middle
			previous.setNext(current.getNext());

		count--;
		modCount++;

		return current.getElement();
	}
	
	/**
	 * Returns the first element in this list without removing it. 
	 *
	 * @return the first element in this list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T first() {
		// check for an empty list
				if (isEmpty()) {
					throw new EmptyCollectionException("DoubleLinkedList");
				}
				// return the first element of the list
				return head.getElement();
	}
	
	/**
	 * Returns the last element in this list without removing it. 
	 *
	 * @return the last element in this list  
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T last() {
		// check for an empty list
				if (isEmpty()) {
					throw new EmptyCollectionException("DoubleLinkedList");
				}
				// return the first element of the list
				return tail.getElement();
	}

	/**
	 * Returns true if the specified element is found in this list and 
	 * false otherwise.
	 *
	 * @param  targetElement the element that is sought in the list
	 * @return true if the element is found in this list
	 */
	public boolean contains(T target) {
		Node<T> current = head;
		
		while(current != null){
			if(current.getElement().equals(target)){
				return true;
			}
			current = current.getNext();
			
		}
		return false;
	}
	
	/**
	 * Returns an iterator for the elements in this list. 
	 *
	 * @return an iterator over the elements of the list
	 */
	public Iterator<T> iterator() {
		return listIterator();
	}

	/**  
	 * Inserts the specified element at the specified index. 
	 * 
	 * @param index   the index into the array to which the element is to be
	 *                inserted.
	 * @param element the element to be inserted into the array   
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public void add(int index, T element) {
		//check if the index is out of bound
		if(index<0 || index>size()){
			throw new IndexOutOfBoundsException("DoubleLinkedList");
		}
		//if the index is at 0
		if(index == 0){
			addToFront(element);
		}
		//if the index is at the end of the list
		if(index ==size()){
			add(element);
		} else {
			Node<T> newNode = null;
			Node<T> current = head;
			current.setPreviousNode(newNode);
			
			//find the index
			for(int i = 0; i<index; i++){
				newNode = current;
				current = current.getNext();
				
				if(i == index-1){
					Node<T> newNode1 = new Node<T>();
					newNode1.setElement(element);
					newNode1.setPreviousNode(newNode);
					newNode1.setNext(current);
					current.setPreviousNode(newNode1);
					newNode.setNext(newNode1);
					count++;
					modCount++;
				}
			}
		}
	}
	
	/**  
	 * Sets the element at the specified index. 
	 *
	 * @param index   the index into the array to which the element is to be set
	 * @param element the element to be set into the list
	 * @return 
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public void set(int index, T element) {
		Node<T> current = head;
		Node<T> NewNode = new Node<T>(element);
		//check if index is out of bounds
		if (index >= size() || index < 0){
			throw new IndexOutOfBoundsException("Index out of bounds man");
		}
			int scan = 0;
		//loop until scan hits the index and current hits the null
		while (scan <= index && current != null) {
			if (scan == index) //when scan is equal to the index
				current.setElement(element);
			else
				//current points to the node next to it 
				current = current.getNext();
			scan++;
		}
	}

	/**  
	 * Adds the specified element to the rear of this list. 
	 *
	 * @param element  the element to be added to the rear of the list    
	 */
	public void add(T element) {
		addToRear(element); //adds element after the last element
		
	}

	/**  
	 * Returns a reference to the element at the specified index. 
	 *
	 * @param index  the index to which the reference is to be retrieved from
	 * @return the element at the specified index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T get(int index) {
		//check for emptylist or outofbound
		if(isEmpty() || index > size()-1 || index <0){
			throw new IndexOutOfBoundsException("DoubleLinkedList");
		}
		Node<T> newNode = head;
		for(int i = 0; i <index; i++){
			newNode = newNode.getNext();
		}
		return newNode.getElement();
	}
	
	/**  
	 * Returns the index of the specified element. 
	 *
	 * @param element  the element for the index is to be retrieved
	 * @return the integer index for this element or -1 if element is not in the list
	 */
	public int indexOf(T element) {
		Node<T> current = head;
		int index = 0;
		boolean found = false;
		while (current != null){
			//if found
			if(current.getElement().equals(element)){
				found = true;
				break;
			}
			current = current.getNext();
			index++;
		}
		//if not found
		if(!found){
			return -1;
		}
		return index;
	}

	/**  
	 * Returns the element at the specified element. 
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the given index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T remove(int index) {
	Node<T> current = new Node<T>();
			current =head;
	T temp = null;
	int i = 0;
	if(index <0 || index >= size()){
		throw new IndexOutOfBoundsException("DoubleLinkedList");
	}
	while(i != index && current != null){
		current = current.getNext();
		i++;
	}
	temp = current.getElement();
	if(current.getPreviousNode() != null){
		current.getPreviousNode().setNext(current.getNext());
	} else{
		head = current.getNext();
	}
	if(current.getNext() != null){
		current.getNext().setPreviousNode(current.getPreviousNode());
	}else{
		tail = current.getPreviousNode();
	}
	count--;
	modCount++;
		return temp;
	}
	
	/**
	 * LinkedIterator represents an iterator for a linked list of linear nodes.
	 */
	public ListIterator<T> listIterator() {
		return new LinkedListIterator(0);
	}

	public ListIterator<T> listIterator(int startingIndex) {
		return new LinkedListIterator(startingIndex);
	}

	/**
	 * Returns true if this list is empty and false otherwise.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	
	public boolean isEmpty() {
		return(size() == 0); //returns 0 if the list is empty 
	}
	
	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in the list
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Returns a string representation of this list.
	 *
	 * @return a string representation of the list    
	 */
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("[");
		Node<T> current = head;
		while(current != null){
			str.append(current.getElement());
			if(current.getNext() != null)
				str.append(", ");
				current = current.getNext();
		}
		str.append("]");
		return str.toString();
	}
	
	/**
	 * LinkedIterator represents an iterator for a linked list of linear nodes.
	 */
	private class LinkedListIterator implements ListIterator<T>{

		private int itrModCount;
		private Node<T> nextNode;
		private Node<T> previous;
		private int nextIndex;
		private Pointers last;
		
		/**
		 * Sets up this iterator using the specified items.
		 *
		 * @param collection  the collection the iterator will move over
		 * @param size        the integer size of the collection
		 */
		public LinkedListIterator(int startingIndex){
			
			if(startingIndex < 0 || startingIndex > size()){
				throw new IndexOutOfBoundsException("DoubleLinkedList");
			}
			
			nextNode = head;
			nextIndex = 0;
			previous = null;
			itrModCount = modCount;
			last = Pointers.other;
			for(int i = 0; i< startingIndex; i++){
				next();
				nextIndex++;
			}
		}
		

		/**
		 * Returns true if this iterator has at least one more element
		 * to deliver in the iteration.
		 *
		 * @return  true if this iterator has at least one more element to deliver
		 *          in the iteration
		 * @throws  ConcurrentModificationException if the collection has changed
		 *          while the iterator is in use
		 */
		public boolean hasNext() {
			if(itrModCount != modCount){
				throw new ConcurrentModificationException("DoubleLinkedList");
			}
			return (nextNode != null);
		}

		/**
		 * Returns the next element in the iteration. If there are no
		 * more elements in this iteration, a NoSuchElementException is
		 * thrown.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iterator is empty
		 */
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException("DoubleLinkedList");
			}
			T temp = nextNode.getElement();
			previous = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			last = Pointers.next;
			return temp;
		}

		/**
		 * Returns true if this iterator has at least one more previous element
		 * to deliver in the iteration.
		 *
		 * @return  true if this iterator has at least one more previous element to deliver
		 *          in the iteration
		 * @throws  ConcurrentModificationException if the collection has changed
		 *          while the iterator is in use
		 */
		public boolean hasPrevious() {
			if(itrModCount != modCount){
				throw new ConcurrentModificationException("DoubleLinkedList");
			}
			return (nextNode != head);
		}

		/**
		 * Returns the previous element in the iteration. If there are no
		 * more elements in this iteration, a NoSuchElementException is
		 * thrown.
		 *
		 * @return the previous element in the iteration
		 * @throws NoSuchElementException if the iterator is empty
		 */
		public T previous() {
			if(!hasPrevious()){
				throw new NoSuchElementException("Iterator");
			}
			if(nextNode != null){
				nextNode.getPreviousNode();
			}else{
				nextNode = tail;
			}
			previous = nextNode;
			last = Pointers.next;
			nextIndex--;
			return nextNode.getElement();
		}

		public int nextIndex() {
			if(itrModCount != modCount){
				throw new ConcurrentModificationException("DoubleLinkedList");
			}
			return nextIndex;
		}

		public int previousIndex() {
			if(itrModCount != modCount){
				throw new ConcurrentModificationException("DoubleLinkedList");
			}
			return nextIndex-1;
		}

		/**  
		 * Removes the called next element. 
		 *
		 * @param index the index of the element to be retrieved   
		 * @throws ConcurrentModificationException for invalid index
		 */
		public void remove() {
			if(itrModCount != modCount){
				throw new ConcurrentModificationException("DoubleLinkedList");
			}
			if(last == Pointers.other){
				throw new IllegalStateException("Iterator");
			}
			if(last == Pointers.prev){
				nextNode = nextNode.getNext();
			}
			if(last == Pointers.next){
				nextIndex--;
			}
			if(previous == head){
				removeFirst();
			}
			else if(previous == tail){
				removeLast();
			} else {
				previous.getPreviousNode().setNext(previous.getNext());
				previous.getNext().setPreviousNode(previous.getPreviousNode());
				count--;
				modCount++;
			}
			itrModCount++;
			previous = null;
			last = Pointers.other;
		}

		/**  
		 * Sets the element. 
		 *
		 * @param index   the index into the array to which the element is to be set
		 * @param element the element to be set into the list
		 * @throws IndexOutOfBoundsException for invalid index
		 */
		public void set(T e) {
		if(isEmpty() || itrModCount != modCount){
			throw new IllegalStateException("DoubleLinkedList");
		}
		Node<T> newNode = new Node<T>(e);
		nextNode.setElement(e);
		Node<T> prevNode = nextNode.getPreviousNode();
		DoubleLinkedList.this.set(nextIndex, e);
			
		}
		/**  
		 * Adds the specified element to the rear of this list. 
		 *
		 * @param element  the element to be added to the rear of the list    
		 */
		public void add(T e) {
			if (itrModCount != modCount) //check for the discrepancy between the iterator
				//modcount and the class modcount
				throw new IllegalStateException(); //throw exception
			Node<T> newNode = new Node<T>(e);
			if (nextNode != null) { 
				nextNode.setNext(newNode);
				newNode.setPreviousNode(nextNode);
				nextNode = newNode; //nextnode gets the new node
				tail = nextNode;
			} else {
				if (tail == null) {
					tail = head = newNode;
				} else {

					nextNode = tail; //new node gets the tail
					nextNode.setNext(newNode);
					newNode.setPreviousNode(nextNode);
					nextNode = newNode;
					tail = nextNode; //tail gets the next node
				}
			}
			modCount++;
			itrModCount++;
			count++;
		}
		
	}

}