
/**
 * SingleLinkedList represents a LinearNode-based implementation of both an unordered and indexed list.
 *
 * @author Java Foundations, 
 * @version 4.0
 */
public class SingleLinkedList<T> extends AbstractLinkedList<T> implements IndexedUnorderedListADT<T>
{	
	/**
	 * Adds the specified element to the front of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void addToFront(T element)
	{
		LinearNode<T> node = new LinearNode<T>(element);
		if (tail==null){
			tail= node;
		}
		node.setNext(head);
		head = node;
		count++;
		modCount++;
		
	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void addToRear(T element)
	{
		LinearNode<T> node = new LinearNode<T>(element);
		if (tail == null)
		{
			head = node;
		}
		else{
		tail.setNext(node);
		}
		tail = node;
		count++;
		modCount++;
		
	}	

	/**
	 * Adds the specified element to this list after the given target.
	 *
	 * @param  element the element to be added to this list
	 * @param  target the target element to be added after
	 * @throws ElementNotFoundException if the target is not found
	 */
	public void addAfter(T element, T target)
	{
		
	LinearNode<T> current =head;
		 while(current != null && !current.getElement().equals(target)){
	            current = current.getNext();
	        }
	        if(current == null){
	            throw new ElementNotFoundException("LinkedList");
	        }
	        LinearNode<T> newNode = new LinearNode<T>(element);
	        newNode.setNext(current.getNext());
	        current.setNext(newNode);
	        if(current == tail){
	            tail = newNode;
	        }
	        count++;
	        modCount++;

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
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("LinkedList");
		}
		if (index == 0 ) {
			addToFront(element);
		} else if (index == count) {
			addToRear(element);
		} else {
			LinearNode<T> newNode = new LinearNode<T>(element);
			LinearNode<T> current = head;
			for (int i=0; i< index-1; i++) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			count++;
			modCount++;
		}		
	}

	/**  
	 * Adds the specified element to the rear of this list. 
	 *
	 * @param element  the element to be added to the rear of the list    
	 */
	public void add(T element) {
		addToRear(element);
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
		LinearNode<T> node = head;
		 if(index < 0 || index > count-1){
		        throw new IndexOutOfBoundsException("LinkedList");
		    }
		 LinearNode<T> current = head;
			
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			current.setElement(element);
			modCount++;
	}

	/**  
	 * Returns a reference to the element at the specified index. 
	 *
	 * @param index  the index to which the reference is to be retrieved from
	 * @return the element at the specified index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T get(int index) {
		 if(index < 0 || index > count-1){
		        throw new IndexOutOfBoundsException("LinkedList");
		    }
		    LinearNode<T>current = head;
		    for(int i = 0; i < index; i++){
		        current = current.getNext();
		    }
		    return current.getElement();
	}

	/**  
	 * Returns the index of the specified element. 
	 *
	 * @param element  the element for the index is to be retrieved
	 * @return the integer index for this element or -1 if element is not in the list
	 */
	public int indexOf(T element) {

		int index = -1;
		LinearNode<T> current = head;
		int idx = 0;
		while (current != null && !current.getElement().equals(element)) {
			current = current.getNext();
			idx++;
		}
		if( current != null) {
			index = idx;
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

		if (index<0 || index >= size())
			   {
			       throw new  IndexOutOfBoundsException("Error ");
			   }
		else if (index == 0){
			return removeFirst();
		}
		else if (index == size()-1)
		{
			return removeLast();
		}
		else {
			LinearNode<T> previous = head;
			for(int i = 1; i< index; i++){
				previous = previous.getNext();
			}
			LinearNode<T> current = previous.getNext();
			previous.getNext().equals(current.getNext());
			count--;
			return current.getElement();
			
			
				
		}
	}
}
