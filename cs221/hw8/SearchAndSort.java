

import java.util.*;

/**
 * Class for searching and sorting DoubleLinkedLists 
 * using either natural order or a Comparator.
 *
 * @author utroy, spanter, mvail
 */
public class SearchAndSort
{
	/**
	 * Sorts a list that implements the DoubleLinkedListADT using the
	 * natural ordering of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The data type in the list must extend Comparable
	 * @param list
	 *            The list that will be sorted
	 * @see DoubleLinkedListADT 
	 */
	public static <T extends Comparable<T>> void sort(DoubleLinkedListADT<T> list) {
		//check for the empty list or size 0
//		if(list.isEmpty()){
//			throw new EmptyCollectionException("list");
//		}
//		
		//check for the base case
		if(list.size() == 1){
			return;
		}
		
		//size greater than 1
		if(list.size() > 1){
			DoubleLinkedListADT<T> leftPart = new WrappedDLL<T>();
			DoubleLinkedListADT<T> rightPart = new WrappedDLL<T>();
			
			//splitting the list in two halves
			twoPart(list, leftPart, rightPart);
			
			//sorting left and right part
			sort(leftPart);
			sort(rightPart);
			
			//merging the list again
			merge(list, leftPart, rightPart);
		}
	}

	/**
	 * Sorts a DoubleLinkedListADT with the provided Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of list to sort
	 * @param list
	 *            The list to sort
	 * @param c
	 *            The Comparator to use
	 * @see DoubleLinkedListADT
	 */
	public static <T> void sort(DoubleLinkedListADT<T> list, Comparator<T> c) {
		
		//check for the empty lsit
//		if(list.isEmpty()){
//			throw new EmptyCollectionException("list");
//		}
		
		//check for the base case
		if(list.size() == 1){
			return;
		}
		
		//size greater than 1
		if(list.size() > 1){
		DoubleLinkedListADT<T> leftPart = new WrappedDLL<T>();
		DoubleLinkedListADT<T> rightPart = new WrappedDLL<T>();
					
			//splitting the list in two halves
			twoPart(list, leftPart, rightPart);
					
			//sorting left and right part
			sort(leftPart, c);
			sort(rightPart, c);
					
			//merging the list again
			merge(list, leftPart, rightPart, c);
		}
	}

	/**
	 * Finds the smallest element in a list according to the natural ordering of 
	 * list elements. Does not alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @return The smallest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T extends Comparable<T>> T findSmallest(DoubleLinkedListADT<T> list) {
		// check if the list is empty
		if(list.isEmpty()){
			return null;
		}
		
		//check for the base case
		if(list.size() == 1){
			return list.first();
		}
		
		ListIterator<T> lt = list.listIterator();
		//saving the smallest element temporarily
		T small = lt.next();
		
		//looking until the list is empty
		while(lt.hasNext()){
			T nextElement = lt.next();
			//checking for the smallest
			if(nextElement.compareTo(small) < 0){
				small = nextElement;
			}
		}
		
		return small;
	}

	/**
	 * Finds the smallest element in a list with a Custom comparator. Does not
	 * alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @param c
	 *            The comparator to use
	 * @return The smallest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T> T findSmallest(DoubleLinkedListADT<T> list, Comparator<T> c) {
		// check if the list is empty
		if(list.isEmpty()){
			return null;
		}
				
		//check for the base case
		if(list.size() == 1){
			return list.first();
		}
				
		ListIterator<T> lt = list.listIterator();
		//saving the smallest element temporarily
		T small = lt.next();
				
		//looking until the list is empty
		while(lt.hasNext()){
			T nextElement = lt.next();
			//checking for the smallest
			if(c.compare(nextElement, small) < 0){
				small = nextElement;
			}
		}
		return small;
	}

	/**
	 * Finds the largest element in a list according to the natural ordering of
	 * list elements. Does not alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @return The largest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T extends Comparable<T>> T findLargest(DoubleLinkedListADT<T> list) {
		// check if the list is empty
		if(list.isEmpty()){
			return null;
		}
		
		//check for the base case
		if(list.size() == 1){
			return list.first();
		}
				
		ListIterator<T> lt = list.listIterator();
		//saving the largest element temporarily
		T large = lt.next();
				
		//looking until the list is empty
		while(lt.hasNext()){
			T nextElement = lt.next();
			//checking for the largest
			if(nextElement.compareTo(large) > 0){
				large = nextElement;
			}
		}
		return large;
	}

	/**
	 * Finds the largest element in a list with a Custom comparator. Does not
	 * alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @param c
	 *            The comparator to use
	 * @return The largest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T> T findLargest(DoubleLinkedListADT<T> list, Comparator<T> c) {
		// check if the list is empty
		if(list.isEmpty()){
			return null;
		}
				
		//check for the base case
		if(list.size() == 1){
			return list.first();
		}
						
		ListIterator<T> lt = list.listIterator();
		//saving the largest element temporarily
		T large = lt.next();
						
		//looking until the list is empty
		while(lt.hasNext()){
			T nextElement = lt.next();
			//checking for the largest
			if(c.compare(nextElement, large) > 0){
				large = nextElement;
			}
		}
		return large;
	}
	
	/**
	 * split the list into two different part left and right
	 * 
	 * @param list
	 * @param rightPart
	 * @param leftPart
	 */
	 public static <T> void twoPart(DoubleLinkedListADT<T> list, DoubleLinkedListADT<T> rightPart, DoubleLinkedListADT<T> leftPart){
		 //setting the length into two half
		 int length = list.size()/2; 
		 int index = 0;
		 
		 //run the loop untill the list is empty
		 while(!list.isEmpty()){
			 index++;
			 
			 //setting the first half to the left part
			 if(index <= length){
				 leftPart.add(list.removeFirst());
			 } else if(index > length){ //adding other half to the right part
				 rightPart.add(list.removeFirst());
			 }
			 
		 }
	 }
	 
	 /**
	  * Merges the leftPart and the right part of the list 
	  * that implements the DoubleLinkedListADT using the
	  * natural ordering of list elements.
	  * 
	  * @param list
	  * @param leftPart
	  * @param rightPart
	  */
	 public static <T extends Comparable<T>> void merge(DoubleLinkedListADT<T> list, DoubleLinkedListADT<T> rightPart, DoubleLinkedListADT<T> leftPart){
	 
		 //loop until the left and right part of the list is empty
		 while(!leftPart.isEmpty() && !rightPart.isEmpty()){
			 //check for each element
			 if(leftPart.first().compareTo(rightPart.first()) <= 0){
				 //removeFirst until the left part is empty
				 T element = leftPart.removeFirst();
				 //add the elements to the list
				 list.add(element);
			 } else {
				 //removeFirst until the left part is empty
				 T element = rightPart.removeFirst();
				 //add the elements to the list
				 list.add(element); 
			 }
		 }
		 
		 //loop until the left half of the list is empty
		 while(!leftPart.isEmpty()){
			 list.add(leftPart.removeFirst());
		 }
		 
		 //loop until the right half of the list is empty
		 while(!rightPart.isEmpty()){
			 list.add(rightPart.removeFirst());
		 }
	 }
	 
	 /**
	  * Merges the leftPart and the right part of the list 
	  * that implements the DoubleLinkedListADT using the
	  * provided Comparator
	  * 
	  * @param list
	  * @param leftPart
	  * @param rightPart
	  * @param c
	  */
	 public static <T> void merge(DoubleLinkedListADT<T> list, DoubleLinkedListADT<T> rightPart, DoubleLinkedListADT<T> leftPart, Comparator<T> c){
		 //loop until the left and right part of the list is empty
		 while(!leftPart.isEmpty() && !rightPart.isEmpty()){
			 //check for each element
			 if(c.compare(leftPart.first(), rightPart.first()) <= 0){
				 //removeFirst until the left part is empty
				 T element = leftPart.removeFirst();
				 //add the elements to the list
				 list.add(element);
			 } else {
				 //removeFirst until the left part is empty
				 T element = rightPart.removeFirst();
				 //add the elements to the list
				 list.add(element); 
			 }
		 }
		 
		 //loop until the left half of the list is empty
		 while(!leftPart.isEmpty()){
			 list.add(leftPart.removeFirst());
		 }
		 
		 //loop until the right half of the list is empty
		 while(!rightPart.isEmpty()){
			 list.add(rightPart.removeFirst());
		 }
	 }
}
