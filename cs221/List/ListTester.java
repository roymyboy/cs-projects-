/**
 * NOTE:
 * I have caught all the exception of badlist so that it does not throw any NullPointerException.
 * I have also done all extra iterators.
 * It runs and passes all the goodlist.
 * @utroy 
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A unit test class for lists that implement DoubleLinkedListADT
 * This is a set of black box tests that should work for any
 * implementation of this interface.
 * 
 * NOTE: One example test is given for each interface method using a new list to
 * get you started.
 * 
 * @author mvail, mhthomas
 */
public class ListTester {
	//possible lists that could be tested
	private enum ListToUse {
		goodList, badList, arrayList, singleLinkedList, doubleLinkedList
	};
	// TODO: THIS IS WHERE YOU CHOOSE WHICH LIST TO TEST
	private final ListToUse LIST_TO_USE = ListToUse.doubleLinkedList;

	// possible results expected in tests
	private enum Result {
		EmptyCollection, ElementNotFound, IndexOutOfBounds, IllegalState, ConcurrentModification, NoSuchElement, 
		NoException, UnexpectedException,
		True, False, Pass, Fail, 
		MatchingValue,
		ValidString
	};

	// named elements for use in tests
	private static final Integer ELEMENT_A = new Integer(1);
	private static final Integer ELEMENT_B = new Integer(2);
	private static final Integer ELEMENT_C = new Integer(3);
	private static final Integer ELEMENT_D = new Integer(4);

	// instance variables for tracking test results
	private int passes = 0;
	private int failures = 0;
	private int total = 0;

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		// to avoid every method being static
		ListTester tester = new ListTester();
		tester.runTests();
	}

	/**
	 * Print test results in a consistent format
	 * 
	 * @param testDesc description of the test
	 * @param result indicates if the test passed or failed
	 */
	private void printTest(String testDesc, boolean result) {
		total++;
		if (result) { passes++; }
		else { failures++; }
		System.out.printf("%-46s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
	}

	/** Print a final summary */
	private void printFinalSummary() {
		System.out.printf("\nTotal Tests: %d,  Passed: %d,  Failed: %d\n",
				total, passes, failures);
	}

	/** XXX <- see the blue box on the right of the scroll bar? this tag aids in navigating long files
	 * Run tests to confirm required functionality from list constructors and methods */
	private void runTests() {
		//recommended scenario naming: start_change_result
		test_newList(); //aka noList_constructor_emptyList
		test_emptyList_addToFrontA_A();
		test_emptyList_addToRearA_A();
		test_emptyList_addA_A();
		test_emptyList_add0A_A();
		test_A_removeFirst_emptyList();
		test_A_removeLast_emptyList();
		test_A_removeA_emptyList();
		test_A_addToFrontB_BA();
		test_A_addToRearB_AB();
		test_A_addAfterAB_AB();
		test_A_remove0_emptyList();
		test_A_set0B_B();
		test_A_add0B_BA();
		test_A_add1B_AB();
		test_AB_addAfterCA_ACB();
		test_AB_addToFrontC_CAB();
		test_AB_addToRearC_ABC();
		test_ABC_removeFirstA_BC();
		// report final verdict
		printFinalSummary();
	}

	//////////////////////////////////////
	// SCENARIO: NEW EMPTY LIST
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	//////////////////////////////////////

	/**
	 * Returns a DoubleLinkedListADT for the "new empty list" scenario.
	 * Scenario: no list -> constructor -> [ ]
	 * 
	 * @return a new, empty DoubleLinkedListADT
	 */
	private DoubleLinkedListADT<Integer> newList() {
		DoubleLinkedListADT<Integer> listToUse;
		switch (LIST_TO_USE) {
		case doubleLinkedList:
			listToUse = new DoubleLinkedList<Integer>();
			break;
		default:
			listToUse = null;
		}
		return listToUse;
	}

	/** Run all tests on scenario: no list -> constructor -> [ ] */
	private void test_newList() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			printTest("newList_testRemoveFirst", testRemoveFirst(newList(), null, Result.EmptyCollection));
			printTest("newList_testRemoveLast", testRemoveLast(newList(), null, Result.EmptyCollection));
			printTest("newList_testRemoveA", testRemoveElement(newList(), null, Result.ElementNotFound));
			printTest("newList_testFirst", testFirst(newList(), null, Result.EmptyCollection));
			printTest("newList_testLast", testLast(newList(), null, Result.EmptyCollection));
			printTest("newList_testContainsA", testContains(newList(), ELEMENT_A, Result.False));
			printTest("newList_testIsEmpty", testIsEmpty(newList(), Result.True));
			printTest("newList_testSize", testSize(newList(), 0));
			printTest("newList_testToString", testToString(newList(), Result.ValidString));
			// UnorderedListADT
			printTest("newList_testAddToFrontA", testAddToFront(newList(), ELEMENT_A, Result.NoException));
			printTest("newList_testAddToRearA", testAddToRear(newList(), ELEMENT_A, Result.NoException));
			printTest(	"newList_testAddAfterBA", testAddAfter(newList(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// DoubleListADT
			printTest("newList_testAddAtIndexNeg1", testAddAtIndex(newList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testAddAtIndex0", testAddAtIndex(newList(), 0, ELEMENT_A, Result.NoException));
			printTest("newList_testAddAtIndex1", testAddAtIndex(newList(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testSetNeg1A", testSet(newList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testSet0A", testSet(newList(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testAddA", testAdd(newList(), ELEMENT_A, Result.NoException));
			printTest("newList_testGetNeg1", testGet(newList(), -1, null, Result.IndexOutOfBounds));
			printTest("newList_testGet0", testGet(newList(), 0, null, Result.IndexOutOfBounds));
			printTest("newList_testIndexOfA", testIndexOf(newList(), ELEMENT_A, -1));
			printTest("newList_testRemoveNeg1", testRemoveIndex(newList(), -1, null, Result.IndexOutOfBounds));
			printTest("newList_testRemove0", testRemoveIndex(newList(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("newList_testIterator", testIterator(newList(), Result.NoException));
			printTest("newList_testIteratorHasNext", testIteratorHasNext(newList().iterator(), Result.False));
			printTest("newList_testIteratorNext", testIteratorNext(newList().iterator(), null, Result.NoSuchElement));
			printTest("newList_testIteratorRemove", testIteratorRemove(newList().iterator(), Result.IllegalState));
			printTest("newList_testListIteratorHasPrevious", testIteratorHasPrevious(newList().listIterator() , Result.False));
			printTest("newList_testListIteratorPrevious", testIteratorPrev(newList().listIterator(), null, Result.NoSuchElement));
			printTest("newList_testListIteratorNextIndex", testIteratorNextIndex(newList().listIterator(),0,Result.MatchingValue));
			printTest("newList_testListIteratorPreviousIndex", testIteratorPrevIndex(newList().listIterator(),-1,Result.MatchingValue));
			printTest("newList_testListIteratorAddA", testIteratorAdd(newList().listIterator(), ELEMENT_A, Result.NoException));
			printTest("newList_testIteratorHasNext_testListIterator2", testIteratorHasNext(testListIterator2(newList()), Result.ConcurrentModification));
			printTest("newList_testIteratorHasNext_testListIterator3", testIteratorHasNext(testListIterator3(newList()), Result.ConcurrentModification));
			printTest("newList_testIteratorHasNext_testListIterator4", testIteratorHasNext(testListIterator4(newList()), Result.ConcurrentModification));
			printTest("newList_testIteratorHasNext_testListIterator5", testIteratorHasNext(testListIterator5(newList()), Result.ConcurrentModification));
			printTest("newList_testIteratorHasNext_testListIterator8", testIteratorHasNext(testListIterator8(newList()), Result.ConcurrentModification));
			printTest("newList_testIteratorHasNext_testListIterator9", testIteratorHasNext(testListIterator9(newList()), Result.ConcurrentModification));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_newList");
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////
	// SCENARIO: [ ] -> addToFront(A) -> [A]
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: empty list -> addToFront(A) -> [A] 
	 * @return [A] after addToFront(A)
	 */

	private DoubleLinkedListADT<Integer> emptyList_addToFrontA_A() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.addToFront(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToFront(A) -> [A] */
	private void test_emptyList_addToFrontA_A() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			printTest("emptyList_addToFrontA_A_testRemoveFirst", testRemoveFirst(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemoveLast", testRemoveLast(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemoveA", testRemoveElement(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemoveB", testRemoveElement(emptyList_addToFrontA_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addToFrontA_A_testFirst", testFirst(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testLast", testLast(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testContainsA", testContains(emptyList_addToFrontA_A(), ELEMENT_A, Result.True));
			printTest("emptyList_addToFrontA_A_testContainsB", testContains(emptyList_addToFrontA_A(), ELEMENT_B, Result.False));
			printTest("emptyList_addToFrontA_A_testIsEmpty", testIsEmpty(emptyList_addToFrontA_A(), Result.False));
			printTest("emptyList_addToFrontA_A_testSize", testSize(emptyList_addToFrontA_A(), 1));
			printTest("emptyList_addToFrontA_A_testToString", testToString(emptyList_addToFrontA_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_addToFrontA_A_testAddToFrontB", testAddToFront(emptyList_addToFrontA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testAddToRearB", testAddToRear(emptyList_addToFrontA_A(), ELEMENT_B, Result.NoException));
			printTest(	"emptyList_addToFrontA_A_testAddAfterCB", testAddAfter(emptyList_addToFrontA_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_addToFrontA_A_testAddAfterAB", testAddAfter(emptyList_addToFrontA_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// DoubleListADT
			printTest("emptyList_addToFrontA_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_addToFrontA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testAddAtIndex0B", testAddAtIndex(emptyList_addToFrontA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testAddAtIndex1B", testAddAtIndex(emptyList_addToFrontA_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testSetNeg1B", testSet(emptyList_addToFrontA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testSet0B", testSet(emptyList_addToFrontA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testAddB", testAdd(emptyList_addToFrontA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testGetNeg1", testGet(emptyList_addToFrontA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testGet0", testGet(emptyList_addToFrontA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testIndexOfA", testIndexOf(emptyList_addToFrontA_A(), ELEMENT_A, 0));
			printTest("emptyList_addToFrontA_A_testIndexOfB", testIndexOf(emptyList_addToFrontA_A(), ELEMENT_B, -1));
			printTest("emptyList_addToFrontA_A_testRemoveNeg1", testRemoveIndex(emptyList_addToFrontA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testRemove0", testRemoveIndex(emptyList_addToFrontA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemove1", testRemoveIndex(emptyList_addToFrontA_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_addToFrontA_A_testIterator", testIterator(emptyList_addToFrontA_A(), Result.NoException));
			printTest("emptyList_addToFrontA_A_testIteratorHasNext", testIteratorHasNext(emptyList_addToFrontA_A().iterator(), Result.True));
			printTest("emptyList_addToFrontA_A_testIteratorNext", testIteratorNext(emptyList_addToFrontA_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testIteratorRemove", testIteratorRemove(emptyList_addToFrontA_A().iterator(), Result.IllegalState));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addToFrontA_A(), 1), Result.False));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addToFrontA_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1), Result.NoException));
			printTest("emptyList_addToFrontA_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1)), Result.False));
			printTest("emptyList_addToFrontA_A_testIteratorHasPrevious", testIteratorHasPrevious(newList().listIterator() , Result.False));
			printTest("emptyList_addToFrontA_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorNextRemove_testIteratorRemove_iteratorAfterNext", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1)), Result.IllegalState));
			printTest("emptyList_addToFrontA_A_testIteratorPrevious", testIteratorPrev(emptyList_addToFrontA_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_testIteratorNextIndex", testIteratorNextIndex(emptyList_addToFrontA_A().listIterator(),0,Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testIteratorPreviousIndex", testIteratorPrevIndex(emptyList_addToFrontA_A().listIterator(),-1,Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testIteratorAddA", testIteratorAdd(emptyList_addToFrontA_A().listIterator(), ELEMENT_A, Result.NoException));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(emptyList_addToFrontA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToFrontA_A_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(emptyList_addToFrontA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(emptyList_addToFrontA_A()), Result.False));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIterator13", testIteratorNext(testListIterator13(emptyList_addToFrontA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIterator14", testIteratorNext(testListIterator14(emptyList_addToFrontA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIterator15", testIteratorNext(testListIterator15(emptyList_addToFrontA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorRemove_testIterator16", testIteratorRemove((emptyList_addToFrontA_A().listIterator()), Result.IllegalState));
			printTest("emptyList_addToFrontA_A_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(emptyList_addToFrontA_A()), Result.IllegalState));
			printTest("emptyList_addToFrontA_A_iteratorHasRemove_testIterator18", testIteratorHasPrevious(emptyList_addToFrontA_A().listIterator(), Result.False));
			printTest("emptyList_addToFrontA_A_iteratorHasPrev_testIterator19", testIteratorPrev(emptyList_addToFrontA_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(emptyList_addToFrontA_A()), Result.True));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIterator20", testIteratorNext(testListIterator20(emptyList_addToFrontA_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(emptyList_addToFrontA_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIterator22", testIteratorNext(testListIterator22(emptyList_addToFrontA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorPrev_testIterator24", testIteratorPrev(emptyList_addToFrontA_A().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////
	// SCENARIO: [ ] -> addToRear(A) -> [A]
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: empty list -> addToRear(A) -> [A] 
	 * @return [A] after addToRear(A) 
	 */

	private DoubleLinkedListADT<Integer> emptyList_addToRearA_A() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.addToRear(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToRear(A) -> [A] */

	private void test_emptyList_addToRearA_A() {
		try {
			// ListADT
			printTest("emptyList_addToRearA_A_testRemoveFirst", testRemoveFirst(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemoveLast", testRemoveLast(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemoveA", testRemoveElement(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemoveB", testRemoveElement(emptyList_addToRearA_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addToRearA_A_testFirst", testFirst(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testLast", testLast(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testContainsA", testContains(emptyList_addToRearA_A(), ELEMENT_A, Result.True));
			printTest("emptyList_addToRearA_A_testContainsB", testContains(emptyList_addToRearA_A(), ELEMENT_B, Result.False));
			printTest("emptyList_addToRearA_A_testIsEmpty", testIsEmpty(emptyList_addToRearA_A(), Result.False));
			printTest("emptyList_addToRearA_A_testSize", testSize(emptyList_addToRearA_A(), 1));
			printTest("emptyList_addToRearA_A_testToString", testToString(emptyList_addToRearA_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_addToRearA_A_testAddToFrontB", testAddToFront(emptyList_addToRearA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testAddToRearB", testAddToRear(emptyList_addToRearA_A(), ELEMENT_B, Result.NoException));
			printTest(	"emptyList_addToRearA_A_testAddAfterCB", testAddAfter(emptyList_addToRearA_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_addToRearA_A_testAddAfterAB", testAddAfter(emptyList_addToRearA_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// DoubleListADT
			printTest("emptyList_addToRearA_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_addToRearA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testAddAtIndex0B", testAddAtIndex(emptyList_addToRearA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testAddAtIndex1B", testAddAtIndex(emptyList_addToRearA_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testSetNeg1B", testSet(emptyList_addToRearA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testSet0B", testSet(emptyList_addToRearA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testAddB", testAdd(emptyList_addToRearA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testGetNeg1", testGet(emptyList_addToRearA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testGet0", testGet(emptyList_addToRearA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testIndexOfA", testIndexOf(emptyList_addToRearA_A(), ELEMENT_A, 0));
			printTest("emptyList_addToRearA_A_testIndexOfB", testIndexOf(emptyList_addToRearA_A(), ELEMENT_B, -1));
			printTest("emptyList_addToRearA_A_testRemoveNeg1", testRemoveIndex(emptyList_addToRearA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testRemove0", testRemoveIndex(emptyList_addToRearA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemove1", testRemoveIndex(emptyList_addToRearA_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_addToRearA_A_testIterator", testIterator(emptyList_addToRearA_A(), Result.NoException));
			printTest("emptyList_addToRearA_A_testIteratorHasNext", testIteratorHasNext(emptyList_addToRearA_A().iterator(), Result.True));
			printTest("emptyList_addToRearA_A_testIteratorNext", testIteratorNext(emptyList_addToRearA_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testIteratorRemove", testIteratorRemove(emptyList_addToRearA_A().iterator(), Result.IllegalState));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addToRearA_A(), 1), Result.False));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addToRearA_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1), Result.NoException));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), Result.False));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), Result.IllegalState));
			printTest("emptyList_addToRearA_A_testListIteratorHasPrevious", testIteratorHasPrevious(emptyList_addToRearA_A().listIterator() , Result.False));
			printTest("emptyList_addToRearA_A_testListIteratorPrevious", testIteratorPrev(emptyList_addToRearA_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_testListIteratorNextIndex", testIteratorNextIndex(emptyList_addToRearA_A().listIterator(),0,Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorPreviousIndex", testIteratorPrevIndex(emptyList_addToRearA_A().listIterator(),-1,Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorAddA", testIteratorAdd(emptyList_addToRearA_A().listIterator(), ELEMENT_A, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorSetB", testIteratorSet(emptyList_addToRearA_A().listIterator(), ELEMENT_B, Result.NoException));		
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(emptyList_addToRearA_A()), Result.ConcurrentModification));
			printTest("emptyList_addToRearA_A_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(emptyList_addToRearA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(emptyList_addToRearA_A()), Result.False));
			printTest("emptyList_addToRearA_A_iteratorNext_testIterator13", testIteratorNext(testListIterator13(emptyList_addToRearA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNext_testIterator14", testIteratorNext(testListIterator14(emptyList_addToRearA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNext_testIterator15", testIteratorNext(testListIterator15(emptyList_addToRearA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorRemove_testIterator16", testIteratorRemove((emptyList_addToRearA_A().listIterator()), Result.IllegalState));
			printTest("emptyList_addToRearA_A_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(emptyList_addToRearA_A()), Result.IllegalState));
			printTest("emptyList_addToRearA_A_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(emptyList_addToRearA_A().listIterator(), Result.False));
			printTest("emptyList_addToRearA_A_iteratorPrev_testIterator19", testIteratorPrev(emptyList_addToRearA_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(emptyList_addToRearA_A()), Result.True));
			printTest("emptyList_addToRearA_A_iteratorNext_testIterator20", testIteratorNext(testListIterator20(emptyList_addToRearA_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(emptyList_addToRearA_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_iteratorNext_testIterator22", testIteratorNext(testListIterator22(emptyList_addToRearA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorPrev_testIterator24", testIteratorPrev(emptyList_addToRearA_A().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToRearA_A");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////
	// SCENARIO: [ ] -> add(A) -> [A]  
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: empty list -> add(A) -> [A] 
	 * @return [A] after add(A)
	 */
	private DoubleLinkedListADT<Integer> emptyList_addA_A() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> add(A) -> [A] */
	private void test_emptyList_addA_A() {
		try {
			// ListADT
			printTest("emptyList_addA_A_testRemoveFirst", testRemoveFirst(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemoveLast", testRemoveLast(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemoveA", testRemoveElement(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemoveB", testRemoveElement(emptyList_addA_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addA_A_testFirst", testFirst(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testLast", testLast(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testContainsA", testContains(emptyList_addA_A(), ELEMENT_A, Result.True));
			printTest("emptyList_addA_A_testContainsB", testContains(emptyList_addA_A(), ELEMENT_B, Result.False));
			printTest("emptyList_addA_A_testIsEmpty", testIsEmpty(emptyList_addA_A(), Result.False));
			printTest("emptyList_addA_A_testSize", testSize(emptyList_addA_A(), 1));
			printTest("emptyList_addA_A_testToString", testToString(emptyList_addA_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_addA_A_testAddToFrontB", testAddToFront(emptyList_addA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testAddToRearB", testAddToRear(emptyList_addA_A(), ELEMENT_B, Result.NoException));
			printTest(	"emptyList_addA_A_testAddAfterCB", testAddAfter(emptyList_addA_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_addA_A_testAddAfterAB", testAddAfter(emptyList_addA_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// DoubleListADT
			printTest("emptyList_addA_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_addA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testAddAtIndex0B", testAddAtIndex(emptyList_addA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testAddAtIndex1B", testAddAtIndex(emptyList_addA_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testSetNeg1B", testSet(emptyList_addA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testSet0B", testSet(emptyList_addA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testAddB", testAdd(emptyList_addA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testGetNeg1", testGet(emptyList_addA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testGet0", testGet(emptyList_addA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testIndexOfA", testIndexOf(emptyList_addA_A(), ELEMENT_A, 0));
			printTest("emptyList_addA_A_testIndexOfB", testIndexOf(emptyList_addA_A(), ELEMENT_B, -1));
			printTest("emptyList_addA_A_testRemoveNeg1", testRemoveIndex(emptyList_addA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testRemove0", testRemoveIndex(emptyList_addA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemove1", testRemoveIndex(emptyList_addA_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_addA_A_testIterator", testIterator(emptyList_addA_A(), Result.NoException));
			printTest("emptyList_addA_A_testIteratorHasNext", testIteratorHasNext(emptyList_addA_A().iterator(), Result.True));
			printTest("emptyList_addA_A_testIteratorNext", testIteratorNext(emptyList_addA_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testIteratorRemove", testIteratorRemove(emptyList_addA_A().iterator(), Result.IllegalState));
			printTest("emptyList_addA_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addA_A(), 1), Result.False));
			printTest("emptyList_addA_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addA_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addA_A(), 1), Result.NoException));
			printTest("emptyList_addA_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addA_A(), 1)), Result.False));
			printTest("emptyList_addA_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addA_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addA_A(), 1)), Result.IllegalState));
			printTest("emptyList_addA_A_testListIteratorHasPrevious", testIteratorHasPrevious(emptyList_addA_A().listIterator() , Result.False));
			printTest("emptyList_addA_A_testListIteratorPrevious", testIteratorPrev(emptyList_addA_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_testListIteratorNextIndex", testIteratorNextIndex(emptyList_addA_A().listIterator(),0,Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorPreviousIndex", testIteratorPrevIndex(emptyList_addA_A().listIterator(),-1,Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorAddA", testIteratorAdd(emptyList_addA_A().listIterator(), ELEMENT_A, Result.NoException));
			printTest("emptyList_addA_A_testListIteratorSetB", testIteratorSet(emptyList_addA_A().listIterator(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(emptyList_addA_A()), Result.ConcurrentModification));
			printTest("emptyList_addA_A_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(emptyList_addA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorHasNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(emptyList_addA_A()), Result.False));
			printTest("emptyList_addA_A_iteratorNext_testIterator13", testIteratorNext(testListIterator13(emptyList_addA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorNext_testIterator14", testIteratorNext(testListIterator14(emptyList_addA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorNext_testIterator15", testIteratorNext(testListIterator15(emptyList_addA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorRemove_testIterator16", testIteratorRemove((emptyList_addA_A().listIterator()), Result.IllegalState));
			printTest("emptyList_addA_A_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(emptyList_addA_A()), Result.IllegalState));
			printTest("emptyList_addA_A_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(emptyList_addA_A().listIterator(), Result.False));
			printTest("emptyList_addA_A_iteratorPrev_testIterator19", testIteratorPrev(emptyList_addA_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(emptyList_addA_A()), Result.True));
			printTest("emptyList_addA_A_iteratorNext_testIterator20", testIteratorNext(testListIterator20(emptyList_addA_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(emptyList_addA_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_iteratorNext_testIterator22", testIteratorNext(testListIterator22(emptyList_addA_A()), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorPrev_testIterator24", testIteratorPrev(emptyList_addA_A().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addA_A");
			e.printStackTrace();
		}


	}

	////////////////////////////////////////////////
	// SCENARIO: [ ] -> add(0, A) -> [A]
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: empty list -> add(0, A) -> [A] 
	 * @return [A] after add(0, A)
	 */

	private DoubleLinkedListADT<Integer> emptyList_add0A_A() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(0, ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> add(0, A) -> [A] */

	private void test_emptyList_add0A_A() {
		try {
			// ListADT
			printTest("emptyList_add0A_A_testRemoveFirst", testRemoveFirst(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemoveLast", testRemoveLast(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemoveA", testRemoveElement(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemoveB", testRemoveElement(emptyList_add0A_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_add0A_A_testFirst", testFirst(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testLast", testLast(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testContainsA", testContains(emptyList_add0A_A(), ELEMENT_A, Result.True));
			printTest("emptyList_add0A_A_testContainsB", testContains(emptyList_add0A_A(), ELEMENT_B, Result.False));
			printTest("emptyList_add0A_A_testIsEmpty", testIsEmpty(emptyList_add0A_A(), Result.False));
			printTest("emptyList_add0A_A_testSize", testSize(emptyList_add0A_A(), 1));
			printTest("emptyList_add0A_A_testToString", testToString(emptyList_add0A_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_add0A_A_testAddToFrontB", testAddToFront(emptyList_add0A_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testAddToRearB", testAddToRear(emptyList_add0A_A(), ELEMENT_B, Result.NoException));
			printTest(	"emptyList_add0A_A_testAddAfterCB", testAddAfter(emptyList_add0A_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_add0A_A_testAddAfterAB", testAddAfter(emptyList_add0A_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// DoubleListADT
			printTest("emptyList_add0A_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_add0A_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testAddAtIndex0B", testAddAtIndex(emptyList_add0A_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testAddAtIndex1B", testAddAtIndex(emptyList_add0A_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testSetNeg1B", testSet(emptyList_add0A_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testSet0B", testSet(emptyList_add0A_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testAddB", testAdd(emptyList_add0A_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testGetNeg1", testGet(emptyList_add0A_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testGet0", testGet(emptyList_add0A_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testIndexOfA", testIndexOf(emptyList_add0A_A(), ELEMENT_A, 0));
			printTest("emptyList_add0A_A_testIndexOfB", testIndexOf(emptyList_add0A_A(), ELEMENT_B, -1));
			printTest("emptyList_add0A_A_testRemoveNeg1", testRemoveIndex(emptyList_add0A_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testRemove0", testRemoveIndex(emptyList_add0A_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemove1", testRemoveIndex(emptyList_add0A_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_add0A_A_testIterator", testIterator(emptyList_add0A_A(), Result.NoException));
			printTest("emptyList_add0A_A_testIteratorHasNext", testIteratorHasNext(emptyList_add0A_A().iterator(), Result.True));
			printTest("emptyList_add0A_A_testIteratorNext", testIteratorNext(emptyList_add0A_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testIteratorRemove", testIteratorRemove(emptyList_add0A_A().iterator(), Result.IllegalState));
			printTest("emptyList_add0A_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_add0A_A(), 1), Result.False));
			printTest("emptyList_add0A_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_add0A_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_add0A_A(), 1), Result.NoException));
			printTest("emptyList_add0A_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_add0A_A(), 1)), Result.False));
			printTest("emptyList_add0A_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_add0A_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_add0A_A(), 1)), Result.IllegalState));
			printTest("emptyList_add0A_A_testListIteratorHasPrevious", testIteratorHasPrevious(emptyList_add0A_A().listIterator() , Result.False));
			printTest("emptyList_add0A_A_testListIteratorPrevious", testIteratorPrev(emptyList_add0A_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_testListIteratorNextIndex", testIteratorNextIndex(emptyList_add0A_A().listIterator(),0,Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorPreviousIndex", testIteratorPrevIndex(emptyList_add0A_A().listIterator(),-1,Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorAddA", testIteratorAdd(emptyList_add0A_A().listIterator(), ELEMENT_A, Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorSetB", testIteratorSet(emptyList_add0A_A().listIterator(), ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(emptyList_add0A_A()), Result.ConcurrentModification));
			printTest("emptyList_add0A_A_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(emptyList_add0A_A()), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorHasNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(emptyList_add0A_A()), Result.False));
			printTest("emptyList_add0A_A_iteratorNext_testIterator13", testIteratorNext(testListIterator13(emptyList_add0A_A()), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorNext_testIterator14", testIteratorNext(testListIterator14(emptyList_add0A_A()), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorNext_testIterator15", testIteratorNext(testListIterator15(emptyList_add0A_A()), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorRemove_testIterator16", testIteratorRemove((emptyList_add0A_A().listIterator()), Result.IllegalState));
			printTest("emptyList_add0A_A_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(emptyList_add0A_A()), Result.IllegalState));
			printTest("emptyList_add0A_A_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(emptyList_add0A_A().listIterator(), Result.False));
			printTest("emptyList_add0A_A_iteratorPrev_testIterator19", testIteratorPrev(emptyList_add0A_A().listIterator(), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(emptyList_add0A_A()), Result.True));
			printTest("emptyList_add0A_A_iteratorNext_testIterator20", testIteratorNext(testListIterator20(emptyList_add0A_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(emptyList_add0A_A()), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_iteratorNext_testIterator22", testIteratorNext(testListIterator22(emptyList_add0A_A()), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorPrev_testIterator24", testIteratorPrev(emptyList_add0A_A().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_add0A_A");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////
	// SCENARIO: [A] -> addToFront(B) -> [B, A]
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: empty list -> addToFront(B) -> [B, A] 
	 * @return [B, A] after addToFront(B)
	 */
	private DoubleLinkedListADT<Integer> A_addToFrontB_BA() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.addToFront(ELEMENT_B);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToFront(B) -> [B, A] */

	private void test_A_addToFrontB_BA() {
		try {
			// ListADT
			printTest("A_addToFrontB_BA_testRemoveFirst", testRemoveFirst(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveLast", testRemoveLast(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveA", testRemoveElement(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveB", testRemoveElement(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveC", testRemoveElement(A_addToFrontB_BA(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_addToFrontB_BA_testFirst", testFirst(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testLast", testLast(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testContainsA", testContains(A_addToFrontB_BA(), ELEMENT_A, Result.True));
			printTest("A_addToFrontB_BA_testContainsB", testContains(A_addToFrontB_BA(), ELEMENT_B, Result.True));
			printTest("A_addToFrontB_BA_testContainsC", testContains(A_addToFrontB_BA(), ELEMENT_C, Result.False));
			printTest("A_addToFrontB_BA_testIsEmpty", testIsEmpty(A_addToFrontB_BA(), Result.False));
			printTest("A_addToFrontB_BA_testSize", testSize(A_addToFrontB_BA(), 2));
			printTest("A_addToFrontB_BA_testToString", testToString(A_addToFrontB_BA(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addToFrontB_BA_testAddToFrontA", testAddToFront(A_addToFrontB_BA(), ELEMENT_A, Result.NoException));
			printTest("A_addToFrontB_BA_testAddToRearB", testAddToRear(A_addToFrontB_BA(), ELEMENT_B, Result.NoException));
			printTest(	"A_addToFrontB_BA_testAddAfterCB", testAddAfter(A_addToFrontB_BA(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"A_addToFrontB_BA_testAddAfterAB", testAddAfter(A_addToFrontB_BA(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"A_addToFrontB_BA_testAddAfterDB", testAddAfter(A_addToFrontB_BA(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_addToFrontB_BA_testAddAtIndexNeg1B", testAddAtIndex(A_addToFrontB_BA(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testAddAtIndex0B", testAddAtIndex(A_addToFrontB_BA(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testAddAtIndex1B", testAddAtIndex(A_addToFrontB_BA(), 1, ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testSetNeg1B", testSet(A_addToFrontB_BA(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testSet0B", testSet(A_addToFrontB_BA(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testAddB", testAdd(A_addToFrontB_BA(), ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testGetNeg1", testGet(A_addToFrontB_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testGet0", testGet(A_addToFrontB_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testIndexOfA", testIndexOf(A_addToFrontB_BA(), ELEMENT_A, 1));
			printTest("A_addToFrontB_BA_testIndexOfB", testIndexOf(A_addToFrontB_BA(), ELEMENT_B, 0));
			printTest("A_addToFrontB_BA_testIndexOfC", testIndexOf(A_addToFrontB_BA(), ELEMENT_C, -1));
			printTest("A_addToFrontB_BA_testRemoveNeg1", testRemoveIndex(A_addToFrontB_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testRemove0", testRemoveIndex(A_addToFrontB_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemove1", testRemoveIndex(A_addToFrontB_BA(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemove2", testRemoveIndex(A_addToFrontB_BA(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_addToFrontB_BA_testIterator", testIterator(A_addToFrontB_BA(), Result.NoException));
			printTest("A_addToFrontB_BA_testIteratorHasNext", testIteratorHasNext(A_addToFrontB_BA().iterator(), Result.True));
			printTest("A_addToFrontB_BA_testIteratorNext", testIteratorNext(A_addToFrontB_BA().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testIteratorRemove", testIteratorRemove(A_addToFrontB_BA().iterator(), Result.IllegalState));
			printTest("A_addToFrontB_BA_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_addToFrontB_BA(), 2), Result.False));
			printTest("A_addToFrontB_BA_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_addToFrontB_BA(), 2), null, Result.NoSuchElement));
			printTest("A_addToFrontB_BA_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_addToFrontB_BA(), 2), Result.NoException));
			printTest("A_addToFrontB_BA_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addToFrontB_BA(), 2)), Result.False));
			printTest("A_addToFrontB_BA_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addToFrontB_BA(), 2)), null, Result.NoSuchElement));
			printTest("A_addToFrontB_BA_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_addToFrontB_BA(), 1)), Result.IllegalState));
			printTest("A_addToFrontB_BA_testListIteratorHasPrevious", testIteratorHasPrevious(A_addToFrontB_BA().listIterator() , Result.False));
			printTest("A_addToFrontB_BA_testListIteratorPrevious", testIteratorPrev(A_addToFrontB_BA().listIterator(), null, Result.NoSuchElement));
			printTest("A_addToFrontB_BA_testListIteratorNextIndex", testIteratorNextIndex(A_addToFrontB_BA().listIterator(),0,Result.MatchingValue));
			printTest("A_addToFrontB_BA_testListIteratorPreviousIndex", testIteratorPrevIndex(A_addToFrontB_BA().listIterator(),-1,Result.MatchingValue));
			printTest("A_addToFrontB_BA_testListIteratorAddA", testIteratorAdd(A_addToFrontB_BA().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_addToFrontB_BA_testListIteratorSetB", testIteratorSet(A_addToFrontB_BA().listIterator(), ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_iteratorNext_testIterator1", testIteratorNext(testListIterator1(A_addToFrontB_BA()), null, Result.NoSuchElement));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(A_addToFrontB_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(A_addToFrontB_BA()), Result.True));
			printTest("A_addToFrontB_BA_iteratorNext_testIterator13", testIteratorNext(testListIterator13(A_addToFrontB_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorNext_testIterator14", testIteratorNext(testListIterator14(A_addToFrontB_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorNext_testIterator15", testIteratorNext(testListIterator15(A_addToFrontB_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorRemove_testIterator16", testIteratorRemove((A_addToFrontB_BA().listIterator()), Result.IllegalState));
			printTest("A_addToFrontB_BA_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(A_addToFrontB_BA()), Result.IllegalState));
			printTest("A_addToFrontB_BA_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(A_addToFrontB_BA().listIterator(), Result.False));
			printTest("A_addToFrontB_BA_iteratorPrevious_testIterator19", testIteratorPrev(A_addToFrontB_BA().listIterator(), null, Result.NoSuchElement));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(A_addToFrontB_BA()), Result.True));
			printTest("A_addToFrontB_BA_iteratorNext_testIterator20", testIteratorNext(testListIterator20(A_addToFrontB_BA()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(A_addToFrontB_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorHasNext_testIterator21", testIteratorHasNext(testListIterator9(A_addToFrontB_BA()), Result.ConcurrentModification));
			printTest("A_addToFrontB_BA_iteratorNext_testIterator22", testIteratorNext(testListIterator22(A_addToFrontB_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorPrev_testIterator24", testIteratorPrev(A_addToFrontB_BA().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addToFrontB_BA");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////
	// SCENARIO: [A] -> addToRear(B) -> [A, B]
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: empty list -> addToRear(B) -> [A, B] 
	 * @return [A, B] after addToRear(B)
	 */
	private DoubleLinkedListADT<Integer> A_addToRearB_AB() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.addToRear(ELEMENT_B);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToRear(B) -> [A, B] */


	private void test_A_addToRearB_AB() {
		try{
			// ListADT
			printTest("A_addToRearB_AB_testRemoveFirst", testRemoveFirst(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveLast", testRemoveLast(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveA", testRemoveElement(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveB", testRemoveElement(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveC", testRemoveElement(A_addToRearB_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_addToRearB_AB_testFirst", testFirst(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testLast", testLast(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testContainsA", testContains(A_addToRearB_AB(), ELEMENT_A, Result.True));
			printTest("A_addToRearB_AB_testContainsB", testContains(A_addToRearB_AB(), ELEMENT_B, Result.True));
			printTest("A_addToRearB_AB_testContainsC", testContains(A_addToRearB_AB(), ELEMENT_C, Result.False));
			printTest("A_addToRearB_AB_testIsEmpty", testIsEmpty(A_addToRearB_AB(), Result.False));
			printTest("A_addToRearB_AB_testSize", testSize(A_addToRearB_AB(), 2));
			printTest("A_addToRearB_AB_testToString", testToString(A_addToRearB_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addToRearB_AB_testAddToFrontA", testAddToFront(A_addToRearB_AB(), ELEMENT_A, Result.NoException));
			printTest("A_addToRearB_AB_testAddToRearB", testAddToRear(A_addToRearB_AB(), ELEMENT_B, Result.NoException));
			printTest(	"A_addToRearB_AB_testAddAfterCB", testAddAfter(A_addToRearB_AB(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"A_addToRearB_AB_testAddAfterAB", testAddAfter(A_addToRearB_AB(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"A_addToRearB_AB_testAddAfterDB", testAddAfter(A_addToRearB_AB(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_addToRearB_AB_testAddAtIndexNeg1B", testAddAtIndex(A_addToRearB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testAddAtIndex0B", testAddAtIndex(A_addToRearB_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testAddAtIndex1B", testAddAtIndex(A_addToRearB_AB(), 1, ELEMENT_A, Result.NoException));
			printTest("A_addToRearB_AB_testSetNeg1B", testSet(A_addToRearB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testSet0B", testSet(A_addToRearB_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testAddB", testAdd(A_addToRearB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testGetNeg1", testGet(A_addToRearB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testGet0", testGet(A_addToRearB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testGet1", testGet(A_addToRearB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testIndexOfA", testIndexOf(A_addToRearB_AB(), ELEMENT_B, 1));
			printTest("A_addToRearB_AB_testIndexOfB", testIndexOf(A_addToRearB_AB(), ELEMENT_A, 0));
			printTest("A_addToRearB_AB_testIndexOfC", testIndexOf(A_addToRearB_AB(), ELEMENT_C, -1));
			printTest("A_addToRearB_AB_testRemoveNeg1", testRemoveIndex(A_addToRearB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testRemove0", testRemoveIndex(A_addToRearB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemove1", testRemoveIndex(A_addToRearB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemove2", testRemoveIndex(A_addToRearB_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_addToRearB_AB_testIterator", testIterator(A_addToRearB_AB(), Result.NoException));
			printTest("A_addToRearB_AB_testIteratorHasNext", testIteratorHasNext(A_addToRearB_AB().iterator(), Result.True));
			printTest("A_addToRearB_AB_testIteratorNext", testIteratorNext(A_addToRearB_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testIteratorRemove", testIteratorRemove(A_addToRearB_AB().iterator(), Result.IllegalState));
			printTest("A_addToRearB_AB_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_addToRearB_AB(), 2), Result.False));
			printTest("A_addToRearB_AB_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_addToRearB_AB(), 2), null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_addToRearB_AB(), 2), Result.NoException));
			printTest("A_addToRearB_AB_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addToRearB_AB(), 2)), Result.False));
			printTest("A_addToRearB_AB_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addToRearB_AB(), 2)), null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_addToRearB_AB(), 1)), Result.IllegalState));
			printTest("A_addToRearB_AB_testListIteratorHasPrevious", testIteratorHasPrevious(A_addToRearB_AB().listIterator() , Result.False));
			printTest("A_addToRearB_AB_testListIteratorPrevious", testIteratorPrev(A_addToRearB_AB().listIterator(), null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIteratorNextIndex", testIteratorNextIndex(A_addToRearB_AB().listIterator(),0,Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorPreviousIndex", testIteratorPrevIndex(A_addToRearB_AB().listIterator(),-1,Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorAddA", testIteratorAdd(A_addToRearB_AB().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorSetB", testIteratorSet(A_addToRearB_AB().listIterator(), ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_iteratorNext_testIterator1", testIteratorNext(testListIterator1(A_addToRearB_AB()), null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(A_addToRearB_AB()), Result.ConcurrentModification));
			printTest("A_addToRearB_AB_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(A_addToRearB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(A_addToRearB_AB()), Result.True));
			printTest("A_addToRearB_AB_iteratorNext_testIterator13", testIteratorNext(testListIterator13(A_addToRearB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorNext_testIterator14", testIteratorNext(testListIterator14(A_addToRearB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorNext_testIterator15", testIteratorNext(testListIterator15(A_addToRearB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorRemove_testIterator16", testIteratorRemove((A_addToRearB_AB().listIterator()), Result.IllegalState));
			printTest("A_addToRearB_AB_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(A_addToRearB_AB()), Result.IllegalState));
			printTest("A_addToRearB_AB_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(A_addToRearB_AB().listIterator(), Result.False));
			printTest("A_addToRearB_AB_iteratorPrev_testIterator19", testIteratorPrev(A_addToRearB_AB().listIterator(), null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(A_addToRearB_AB()), Result.True));
			printTest("A_addToRearB_AB_iteratorNext_testIterator20", testIteratorNext(testListIterator20(A_addToRearB_AB()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(A_addToRearB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorNext_testIterator22", testIteratorNext(testListIterator22(A_addToRearB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorPrev_testIterator24", testIteratorPrev(A_addToRearB_AB().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addToRearB_AB");
			e.printStackTrace();
		}


	}

	////////////////////////////////////////////////
	//SCENARIO: [A] -> addAfter(AB) -> [A, B]
	//XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: empty list -> addAfter(AB) -> [A, B] 
	 * @return [A, B] after addAfter(AB)
	 */
	private DoubleLinkedListADT<Integer> A_addAfterAB_AB() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.addAfter(ELEMENT_B, ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> addAfter(AB) -> [A, B] */


	private void test_A_addAfterAB_AB() {
		try {
			// ListADT
			printTest("A_addAfterAB_AB_testRemoveFirst", testRemoveFirst(A_addAfterAB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemoveLast", testRemoveLast(A_addAfterAB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemoveA", testRemoveElement(A_addAfterAB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemoveB", testRemoveElement(A_addAfterAB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemoveC", testRemoveElement(A_addAfterAB_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_addAfterAB_AB_testFirst", testFirst(A_addAfterAB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testLast", testLast(A_addAfterAB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testContainsA", testContains(A_addAfterAB_AB(), ELEMENT_A, Result.True));
			printTest("A_addAfterAB_AB_testContainsB", testContains(A_addAfterAB_AB(), ELEMENT_B, Result.True));
			printTest("A_addAfterAB_AB_testContainsC", testContains(A_addAfterAB_AB(), ELEMENT_C, Result.False));
			printTest("A_addAfterAB_AB_testContainsD", testContains(A_addAfterAB_AB(), ELEMENT_D, Result.False));
			printTest("A_addAfterAB_AB_testIsEmpty", testIsEmpty(A_addAfterAB_AB(), Result.False));
			printTest("A_addAfterAB_AB_testSize", testSize(A_addAfterAB_AB(), 2));
			printTest("A_addAfterAB_AB_testToString", testToString(A_addAfterAB_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addAfterAB_AB_testAddToFrontB", testAddToFront(A_addAfterAB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testAddToRearA", testAddToRear(A_addAfterAB_AB(), ELEMENT_A, Result.NoException));
			printTest(	"A_addAfterAB_AB_testAddAfterDB", testAddAfter(A_addAfterAB_AB(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			printTest(	"A_addAfterAB_AB_testAddAfterAB", testAddAfter(A_addAfterAB_AB(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"A_addAfterAB_AB_testAddAfterDC", testAddAfter(A_addAfterAB_AB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_addAfterAB_AB_testAddAtIndexNeg1B", testAddAtIndex(A_addAfterAB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testAddAtIndex0A", testAddAtIndex(A_addAfterAB_AB(), 0, ELEMENT_A, Result.NoException));
			printTest("A_addAfterAB_AB_testAddAtIndex1B", testAddAtIndex(A_addAfterAB_AB(), 1, ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testSetNeg1B", testSet(A_addAfterAB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testSet0B", testSet(A_addAfterAB_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testAddB", testAdd(A_addAfterAB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testGetNeg1", testGet(A_addAfterAB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testGet0", testGet(A_addAfterAB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testGet1", testGet(A_addAfterAB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testGet2", testGet(A_addAfterAB_AB(), 2, ELEMENT_C, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testIndexOfA", testIndexOf(A_addAfterAB_AB(), ELEMENT_A, 0));
			printTest("A_addAfterAB_AB_testIndexOfB", testIndexOf(A_addAfterAB_AB(), ELEMENT_B, 1));
			printTest("A_addAfterAB_AB_testIndexOfC", testIndexOf(A_addAfterAB_AB(), ELEMENT_C, -1));
			printTest("A_addAfterAB_AB_testIndexOfD", testIndexOf(A_addAfterAB_AB(), ELEMENT_D, -1));
			printTest("A_addAfterAB_AB_testRemoveNeg1", testRemoveIndex(A_addAfterAB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testRemove0", testRemoveIndex(A_addAfterAB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemove1", testRemoveIndex(A_addAfterAB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemove2", testRemoveIndex(A_addAfterAB_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testRemove3", testRemoveIndex(A_addAfterAB_AB(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_addAfterAB_AB_testIterator", testIterator(A_addAfterAB_AB(), Result.NoException));
			printTest("A_addAfterAB_AB_testIteratorHasNext", testIteratorHasNext(A_addAfterAB_AB().iterator(), Result.True));
			printTest("A_addAfterAB_AB_testIteratorNext", testIteratorNext(A_addAfterAB_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testIteratorRemove", testIteratorRemove(A_addAfterAB_AB().iterator(), Result.IllegalState));
			printTest("A_addAfterAB_AB_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_addAfterAB_AB(), 2), Result.False));
			printTest("A_addAfterAB_AB_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_addAfterAB_AB(), 2), null, Result.NoSuchElement));
			printTest("A_addAfterAB_AB_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_addAfterAB_AB(), 2), Result.NoException));
			printTest("A_addAfterAB_AB_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addAfterAB_AB(), 2)), Result.False));
			printTest("A_addAfterAB_AB_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addAfterAB_AB(), 2)), null, Result.NoSuchElement));
			printTest("A_addAfterAB_AB_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_addAfterAB_AB(), 1)), Result.IllegalState));
			printTest("A_addAfterAB_AB_testListIteratorHasPrevious", testIteratorHasPrevious(A_addAfterAB_AB().listIterator() , Result.False));
			printTest("A_addAfterAB_AB_testListIteratorPrevious", testIteratorPrev(A_addAfterAB_AB().listIterator(), null, Result.NoSuchElement));
			printTest("A_addAfterAB_AB_testListIteratorNextIndex", testIteratorNextIndex(A_addAfterAB_AB().listIterator(),0,Result.MatchingValue));
			printTest("A_addAfterAB_AB_testListIteratorPreviousIndex", testIteratorPrevIndex(A_addAfterAB_AB().listIterator(),-1,Result.MatchingValue));
			printTest("A_addAfterAB_AB_testListIteratorAddA", testIteratorAdd(A_addAfterAB_AB().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_addAfterAB_AB_testListIteratorSetB", testIteratorSet(A_addAfterAB_AB().listIterator(), ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_iteratorNext_testIterator1", testIteratorNext(testListIterator1(A_addAfterAB_AB()), null, Result.NoSuchElement));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(A_addAfterAB_AB()), Result.ConcurrentModification));
			printTest("A_addAfterAB_AB_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(A_addAfterAB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(A_addAfterAB_AB()), Result.True));
			printTest("A_addAfterAB_AB_iteratorNext_testIterator13", testIteratorNext(testListIterator13(A_addAfterAB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator14", testIteratorNext(testListIterator14(A_addAfterAB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator15", testIteratorNext(testListIterator15(A_addAfterAB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator16", testIteratorRemove((A_addAfterAB_AB().listIterator()), Result.IllegalState));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator17", testIteratorRemove(testListIterator17(A_addAfterAB_AB()), Result.IllegalState));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator18", testIteratorHasPrevious(A_addAfterAB_AB().listIterator(), Result.False));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator19", testIteratorPrev(A_addAfterAB_AB().listIterator(), null, Result.NoSuchElement));
			printTest("A_addAfterAB_AB_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(A_addAfterAB_AB()), Result.True));
			printTest("A_addAfterAB_AB_iteratorNext_testIterator20", testIteratorNext(testListIterator20(A_addAfterAB_AB()), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(A_addAfterAB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_iteratorNext_testIterator22", testIteratorNext(testListIterator22(A_addAfterAB_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_iteratorPrev_testIterator24", testIteratorPrev(A_addAfterAB_AB().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addAfterAB_AB");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////
	// SCENARIO: [A] -> remove(0) -> []
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: A -> remove(0) -> [] 
	 * @return [] after remove(0)
	 */
	private DoubleLinkedListADT<Integer> A_remove0_emptyList() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.remove(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> remove(0) -> [] */


	private void test_A_remove0_emptyList() {
		try {
			// ListADT
			printTest("A_remove0_emptyList_testRemoveFirst", testRemoveFirst(A_remove0_emptyList(), null, Result.EmptyCollection));
			printTest("A_remove0_emptyList_testRemoveLast", testRemoveLast(A_remove0_emptyList(), null, Result.EmptyCollection));
			printTest("A_remove0_emptyList_testRemoveA", testRemoveElement(A_remove0_emptyList(), null, Result.ElementNotFound));
			printTest("A_remove0_emptyList_testFirst", testFirst(A_remove0_emptyList(), null, Result.EmptyCollection));
			printTest("A_remove0_emptyList_testLast", testLast(A_remove0_emptyList(), null, Result.EmptyCollection));
			printTest("A_remove0_emptyList_testContainsA", testContains(A_remove0_emptyList(), ELEMENT_A, Result.False));
			printTest("A_remove0_emptyList_testIsEmpty", testIsEmpty(A_remove0_emptyList(), Result.True));
			printTest("A_remove0_emptyList_testSize", testSize(A_remove0_emptyList(), 0));
			printTest("A_remove0_emptyList_testToString", testToString(A_remove0_emptyList(), Result.ValidString));
			// UnorderedListADT
			printTest("A_remove0_emptyList_testAddToFrontA", testAddToFront(A_remove0_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_remove0_emptyList_testAddToRearA", testAddToRear(A_remove0_emptyList(), ELEMENT_A, Result.NoException));
			printTest(	"A_remove0_emptyList_testAddAfterBA", testAddAfter(A_remove0_emptyList(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_remove0_emptyList_testAddAtIndexNeg1", testAddAtIndex(A_remove0_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_emptyList_testAddAtIndex0", testAddAtIndex(A_remove0_emptyList(), 0, ELEMENT_A, Result.NoException));
			printTest("A_remove0_emptyList_testAddAtIndex1", testAddAtIndex(A_remove0_emptyList(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_emptyList_testSetNeg1A", testSet(A_remove0_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_emptyList_testSet0A", testSet(A_remove0_emptyList(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_emptyList_testAddA", testAdd(A_remove0_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_remove0_emptyList_testGetNeg1", testGet(A_remove0_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_remove0_emptyList_testGet0", testGet(A_remove0_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_remove0_emptyList_testIndexOfA", testIndexOf(A_remove0_emptyList(), ELEMENT_A, -1));
			printTest("A_remove0_emptyList_testRemoveNeg1", testRemoveIndex(A_remove0_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_remove0_emptyList_testRemove0", testRemoveIndex(A_remove0_emptyList(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_remove0_emptyList_testIterator", testIterator(A_remove0_emptyList(), Result.NoException));
			printTest("A_remove0_emptyList_testIteratorHasNext", testIteratorHasNext(A_remove0_emptyList().iterator(), Result.False));
			printTest("A_remove0_emptyList_testIteratorNext", testIteratorNext(A_remove0_emptyList().iterator(), null, Result.NoSuchElement));
			printTest("A_remove0_emptyList_testIteratorRemove", testIteratorRemove(A_remove0_emptyList().iterator(), Result.IllegalState));
			printTest("A_remove0_emptyList_testListIteratorHasPrevious", testIteratorHasPrevious(A_remove0_emptyList().listIterator() , Result.False));
			printTest("A_remove0_emptyList_testListIteratorPrevious", testIteratorPrev(A_remove0_emptyList().listIterator(), null, Result.NoSuchElement));
			printTest("A_remove0_emptyList_testListIteratorNextIndex", testIteratorNextIndex(A_remove0_emptyList().listIterator(),0,Result.MatchingValue));
			printTest("A_remove0_emptyList_testListIteratorPreviousIndex", testIteratorPrevIndex(A_remove0_emptyList().listIterator(),-1,Result.MatchingValue));
			printTest("A_remove0_emptyList_testListIteratorAddA", testIteratorAdd(A_remove0_emptyList().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_remove0_emptyList_testIteratorHasNext_testListIterator2", testIteratorHasNext(testListIterator2(A_remove0_emptyList()), Result.ConcurrentModification));
			printTest("A_remove0_emptyList_testIteratorHasNext_testListIterator3", testIteratorHasNext(testListIterator3(A_remove0_emptyList()), Result.ConcurrentModification));
			printTest("A_remove0_emptyList_testIteratorHasNext_testListIterator4", testIteratorHasNext(testListIterator4(A_remove0_emptyList()), Result.ConcurrentModification));
			printTest("A_remove0_emptyList_testIteratorHasNext_testListIterator5", testIteratorHasNext(testListIterator5(A_remove0_emptyList()), Result.ConcurrentModification));
			printTest("A_remove0_emptyList_testIteratorHasNext_testListIterator8", testIteratorHasNext(testListIterator8(A_remove0_emptyList()), Result.ConcurrentModification));
			printTest("A_remove0_emptyList_testIteratorHasNext_testListIterator9", testIteratorHasNext(testListIterator9(A_remove0_emptyList()), Result.ConcurrentModification));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_remove0_emptyList");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////
	// SCENARIO: [A] -> removeFirst(A) -> []
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: A -> removeFirst(A) -> [] 
	 * @return [] after removeFirst(A)
	 */
	private DoubleLinkedListADT<Integer> A_removeFirst_emptyList() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.remove(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> removeFirst(A) -> [] */

	private void test_A_removeFirst_emptyList() {

		try {
			// ListADT
			printTest("A_removeFirst_emptyList_testRemoveFirst", testRemoveFirst(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testRemoveLast", testRemoveLast(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testRemoveA", testRemoveElement(A_removeFirst_emptyList(), null, Result.ElementNotFound));
			printTest("A_removeFirst_emptyList_testFirst", testFirst(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testLast", testLast(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testContainsA", testContains(A_removeFirst_emptyList(), ELEMENT_A, Result.False));
			printTest("A_removeFirst_emptyList_testIsEmpty", testIsEmpty(A_removeFirst_emptyList(), Result.True));
			printTest("A_removeFirst_emptyList_testSize", testSize(A_removeFirst_emptyList(), 0));
			printTest("A_removeFirst_emptyList_testToString", testToString(A_removeFirst_emptyList(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeFirst_emptyList_testAddToFrontA", testAddToFront(A_removeFirst_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_emptyList_testAddToRearA", testAddToRear(A_removeFirst_emptyList(), ELEMENT_A, Result.NoException));
			printTest(	"A_removeFirst_emptyList_testAddAfterBA", testAddAfter(A_removeFirst_emptyList(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_removeFirst_emptyList_testAddAtIndexNeg1", testAddAtIndex(A_removeFirst_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testAddAtIndex0", testAddAtIndex(A_removeFirst_emptyList(), 0, ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_emptyList_testAddAtIndex1", testAddAtIndex(A_removeFirst_emptyList(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testSetNeg1A", testSet(A_removeFirst_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testSet0A", testSet(A_removeFirst_emptyList(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testAddA", testAdd(A_removeFirst_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_emptyList_testGetNeg1", testGet(A_removeFirst_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testGet0", testGet(A_removeFirst_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testIndexOfA", testIndexOf(A_removeFirst_emptyList(), ELEMENT_A, -1));
			printTest("A_removeFirst_emptyList_testRemoveNeg1", testRemoveIndex(A_removeFirst_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testRemove0", testRemoveIndex(A_removeFirst_emptyList(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_removeFirst_emptyList_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("A_removeFirst_emptyList_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("A_removeFirst_emptyList_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.NoSuchElement));
			printTest("A_removeFirst_emptyList_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("A_removeFirst_emptyList_testListIteratorHasPrevious", testIteratorHasPrevious(A_removeFirst_emptyList().listIterator() , Result.False));
			printTest("A_removeFirst_emptyList_testListIteratorPrevious", testIteratorPrev(A_removeFirst_emptyList().listIterator(), null, Result.NoSuchElement));
			printTest("A_removeFirst_emptyList_testListIteratorNextIndex", testIteratorNextIndex(A_removeFirst_emptyList().listIterator(),0,Result.MatchingValue));
			printTest("A_removeFirst_emptyList_testListIteratorPreviousIndex", testIteratorPrevIndex(A_removeFirst_emptyList().listIterator(),-1,Result.MatchingValue));
			printTest("A_removeFirst_emptyList_testListIteratorAddA", testIteratorAdd(A_removeFirst_emptyList().listIterator(), ELEMENT_A, Result.NoException));			
			printTest("A_removeFirst_emptyList_testIteratorHasNext_testListIterator2", testIteratorHasNext(testListIterator2(A_removeFirst_emptyList()), Result.ConcurrentModification));
			printTest("A_removeFirst_emptyList_testIteratorHasNext_testListIterator3", testIteratorHasNext(testListIterator3(A_removeFirst_emptyList()), Result.ConcurrentModification));
			printTest("A_removeFirst_emptyList_testIteratorHasNext_testListIterator4", testIteratorHasNext(testListIterator4(A_removeFirst_emptyList()), Result.ConcurrentModification));
			printTest("A_removeFirst_emptyList_testIteratorHasNext_testListIterator5", testIteratorHasNext(testListIterator5(A_removeFirst_emptyList()), Result.ConcurrentModification));
			printTest("A_removeFirst_emptyList_testIteratorHasNext_testListIterator8", testIteratorHasNext(testListIterator8(A_removeFirst_emptyList()), Result.ConcurrentModification));
			printTest("A_removeFirst_emptyList_testIteratorHasNext_testListIterator9", testIteratorHasNext(testListIterator9(A_removeFirst_emptyList()), Result.ConcurrentModification));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_removeFirst_emptyList");
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////
	// SCENARIO: [A] -> removeLast(A) -> []
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: A -> removeLast(A) -> [] 
	 * @return [] after removeLast(A)
	 */
	private DoubleLinkedListADT<Integer> A_removeLast_emptyList() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.remove(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> removeLast(A) -> [] */
	private void test_A_removeLast_emptyList() {
		try {
			// ListADT
			printTest("A_removeLast_emptyList_testRemoveFirst", testRemoveFirst(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testRemoveLast", testRemoveLast(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testRemoveA", testRemoveElement(A_removeLast_emptyList(), null, Result.ElementNotFound));
			printTest("A_removeLast_emptyList_testFirst", testFirst(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testLast", testLast(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testContainsA", testContains(A_removeLast_emptyList(), ELEMENT_A, Result.False));
			printTest("A_removeLast_emptyList_testIsEmpty", testIsEmpty(A_removeLast_emptyList(), Result.True));
			printTest("A_removeLast_emptyList_testSize", testSize(A_removeLast_emptyList(), 0));
			printTest("A_removeLast_emptyList_testToString", testToString(A_removeLast_emptyList(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeLast_emptyList_testAddToFrontA", testAddToFront(A_removeLast_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeLast_emptyList_testAddToRearA", testAddToRear(A_removeLast_emptyList(), ELEMENT_A, Result.NoException));
			printTest(	"A_removeLast_emptyList_testAddAfterBA", testAddAfter(A_removeLast_emptyList(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_removeLast_emptyList_testAddAtIndexNeg1", testAddAtIndex(A_removeLast_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testAddAtIndex0", testAddAtIndex(A_removeLast_emptyList(), 0, ELEMENT_A, Result.NoException));
			printTest("A_removeLast_emptyList_testAddAtIndex1", testAddAtIndex(A_removeLast_emptyList(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testSetNeg1A", testSet(A_removeLast_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testSet0A", testSet(A_removeLast_emptyList(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testAddA", testAdd(A_removeLast_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeLast_emptyList_testGetNeg1", testGet(A_removeLast_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testGet0", testGet(A_removeLast_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testIndexOfA", testIndexOf(A_removeLast_emptyList(), ELEMENT_A, -1));
			printTest("A_removeLast_emptyList_testRemoveNeg1", testRemoveIndex(A_removeLast_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testRemove0", testRemoveIndex(A_removeLast_emptyList(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_removeLast_emptyList_testIterator", testIterator(A_removeLast_emptyList(), Result.NoException));
			printTest("A_removeLast_emptyList_testIteratorHasNext", testIteratorHasNext(A_removeLast_emptyList().iterator(), Result.False));
			printTest("A_removeLast_emptyList_testIteratorNext", testIteratorNext(A_removeLast_emptyList().iterator(), null, Result.NoSuchElement));
			printTest("A_removeLast_emptyList_testIteratorRemove", testIteratorRemove(A_removeLast_emptyList().iterator(), Result.IllegalState));
			printTest("A_removeLast_emptyList_testListIteratorHasPrevious", testIteratorHasPrevious(A_removeLast_emptyList().listIterator() , Result.False));
			printTest("A_removeLast_emptyList_testListIteratorPrevious", testIteratorPrev(A_removeLast_emptyList().listIterator(), null, Result.NoSuchElement));
			printTest("A_removeLast_emptyList_testListIteratorNextIndex", testIteratorNextIndex(A_removeLast_emptyList().listIterator(),0,Result.MatchingValue));
			printTest("A_removeLast_emptyList_testListIteratorPreviousIndex", testIteratorPrevIndex(A_removeLast_emptyList().listIterator(),-1,Result.MatchingValue));
			printTest("A_removeLast_emptyList_testListIteratorAddA", testIteratorAdd(A_removeLast_emptyList().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_removeLast_emptyList_testIteratorHasNext_testListIterator2", testIteratorHasNext(testListIterator2(A_removeLast_emptyList()), Result.ConcurrentModification));
			printTest("A_removeLast_emptyList_testIteratorHasNext_testListIterator3", testIteratorHasNext(testListIterator3(A_removeLast_emptyList()), Result.ConcurrentModification));
			printTest("A_removeLast_emptyList_testIteratorHasNext_testListIterator4", testIteratorHasNext(testListIterator4(A_removeLast_emptyList()), Result.ConcurrentModification));
			printTest("A_removeLast_emptyList_testIteratorHasNext_testListIterator5", testIteratorHasNext(testListIterator5(A_removeLast_emptyList()), Result.ConcurrentModification));
			printTest("A_removeLast_emptyList_testIteratorHasNext_testListIterator8", testIteratorHasNext(testListIterator8(A_removeLast_emptyList()), Result.ConcurrentModification));
			printTest("A_removeLast_emptyList_testIteratorHasNext_testListIterator9", testIteratorHasNext(testListIterator9(A_removeLast_emptyList()), Result.ConcurrentModification));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_removeLast_emptyList");
			e.printStackTrace();
		}

	}
	////////////////////////////////////////////////
	// SCENARIO: [A] -> remove(A) -> []
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: A -> remove(A) -> [] 
	 * @return [] after remove(A)
	 */
	private DoubleLinkedListADT<Integer> A_removeA_emptyList() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.remove(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> remove(A) -> [] */
	private void test_A_removeA_emptyList() {
		try {
			// ListADT
			printTest("A_removeA_emptyList_testRemoveFirst", testRemoveFirst(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testRemoveLast", testRemoveLast(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testRemoveA", testRemoveElement(A_removeA_emptyList(), null, Result.ElementNotFound));
			printTest("A_removeA_emptyList_testFirst", testFirst(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testLast", testLast(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testContainsA", testContains(A_removeA_emptyList(), ELEMENT_A, Result.False));
			printTest("A_removeA_emptyList_testIsEmpty", testIsEmpty(A_removeA_emptyList(), Result.True));
			printTest("A_removeA_emptyList_testSize", testSize(A_removeA_emptyList(), 0));
			printTest("A_removeA_emptyList_testToString", testToString(A_removeA_emptyList(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeA_emptyList_testAddToFrontA", testAddToFront(A_removeA_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeA_emptyList_testAddToRearA", testAddToRear(A_removeA_emptyList(), ELEMENT_A, Result.NoException));
			printTest(	"A_removeA_emptyList_testAddAfterBA", testAddAfter(A_removeA_emptyList(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_removeA_emptyList_testAddAtIndexNeg1", testAddAtIndex(A_removeA_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testAddAtIndex0", testAddAtIndex(A_removeA_emptyList(), 0, ELEMENT_A, Result.NoException));
			printTest("A_removeA_emptyList_testAddAtIndex1", testAddAtIndex(A_removeA_emptyList(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testSetNeg1A", testSet(A_removeA_emptyList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testSet0A", testSet(A_removeA_emptyList(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testAddA", testAdd(A_removeA_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeA_emptyList_testGetNeg1", testGet(A_removeA_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testGet0", testGet(A_removeA_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testIndexOfA", testIndexOf(A_removeA_emptyList(), ELEMENT_A, -1));
			printTest("A_removeA_emptyList_testRemoveNeg1", testRemoveIndex(A_removeA_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testRemove0", testRemoveIndex(A_removeA_emptyList(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_removeA_emptyList_testIterator", testIterator(A_removeA_emptyList(), Result.NoException));
			printTest("A_removeA_emptyList_testIteratorHasNext", testIteratorHasNext(A_removeA_emptyList().iterator(), Result.False));
			printTest("A_removeA_emptyList_testIteratorNext", testIteratorNext(A_removeA_emptyList().iterator(), null, Result.NoSuchElement));
			printTest("A_removeA_emptyList_testIteratorRemove", testIteratorRemove(A_removeA_emptyList().iterator(), Result.IllegalState));
			printTest("A_removeA_emptyList_testListIteratorHasPrevious", testIteratorHasPrevious(A_removeA_emptyList().listIterator() , Result.False));
			printTest("A_removeA_emptyList_testListIteratorPrevious", testIteratorPrev(A_removeA_emptyList().listIterator(), null, Result.NoSuchElement));
			printTest("A_removeA_emptyList_testListIteratorNextIndex", testIteratorNextIndex(A_removeA_emptyList().listIterator(),0,Result.MatchingValue));
			printTest("A_removeA_emptyList_testListIteratorPreviousIndex", testIteratorPrevIndex(A_removeA_emptyList().listIterator(),-1,Result.MatchingValue));
			printTest("A_removeA_emptyList_testListIteratorAddA", testIteratorAdd(A_removeA_emptyList().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_removeA_emptyList_testIteratorHasNext_testListIterator2", testIteratorHasNext(testListIterator2(A_removeA_emptyList()), Result.ConcurrentModification));
			printTest("A_removeA_emptyList_testIteratorHasNext_testListIterator3", testIteratorHasNext(testListIterator3(A_removeA_emptyList()), Result.ConcurrentModification));
			printTest("A_removeA_emptyList_testIteratorHasNext_testListIterator4", testIteratorHasNext(testListIterator4(A_removeA_emptyList()), Result.ConcurrentModification));
			printTest("A_removeA_emptyList_testIteratorHasNext_testListIterator5", testIteratorHasNext(testListIterator5(A_removeA_emptyList()), Result.ConcurrentModification));
			printTest("A_removeA_emptyList_testIteratorHasNext_testListIterator8", testIteratorHasNext(testListIterator8(A_removeA_emptyList()), Result.ConcurrentModification));
			printTest("A_removeA_emptyList_testIteratorHasNext_testListIterator9", testIteratorHasNext(testListIterator9(A_removeA_emptyList()), Result.ConcurrentModification));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_removeA_emptyList");
			e.printStackTrace();
		}


	}

	////////////////////////////////////////////////
	// SCENARIO: [AB] -> addAfter(CA) -> [A,C,B] 
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: AB -> addAfter(CA) -> [] 
	 * @return [A,C,B] after addAfter(CA) 
	 */
	private DoubleLinkedListADT<Integer> AB_addAfterCA_ACB() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.addAfter(ELEMENT_C, ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> addAfter(AB) -> [A,C,B] */ 

	private void test_AB_addAfterCA_ACB() {
		try {
			// ListADT
			printTest("AB_addAfterCA_ACB()_testRemoveFirst", testRemoveFirst(AB_addAfterCA_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemoveLast", testRemoveLast(AB_addAfterCA_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemoveA", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemoveB", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemoveC", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemoveD", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addAfterCA_ACB()_testFirst", testFirst(AB_addAfterCA_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testLast", testLast(AB_addAfterCA_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testContainsA", testContains(AB_addAfterCA_ACB(), ELEMENT_A, Result.True));
			printTest("AB_addAfterCA_ACB()_testContainsB", testContains(AB_addAfterCA_ACB(), ELEMENT_B, Result.True));
			printTest("AB_addAfterCA_ACB()_testContainsC", testContains(AB_addAfterCA_ACB(), ELEMENT_C, Result.True));
			printTest("AB_addAfterCA_ACB()_testContainsD", testContains(AB_addAfterCA_ACB(), ELEMENT_D, Result.False));
			printTest("AB_addAfterCA_ACB()_testIsEmpty", testIsEmpty(AB_addAfterCA_ACB(), Result.False));
			printTest("AB_addAfterCA_ACB()_testSize", testSize(AB_addAfterCA_ACB(), 3));
			printTest("AB_addAfterCA_ACB()_testToString", testToString(AB_addAfterCA_ACB(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addAfterCA_ACB()_testAddToFrontB", testAddToFront(AB_addAfterCA_ACB(), ELEMENT_B, Result.NoException));
			printTest("AB_addAfterCA_ACB()_testAddToRearA", testAddToRear(AB_addAfterCA_ACB(), ELEMENT_A, Result.NoException));
			printTest("AB_addAfterCA_ACB()_testAddToRearC", testAddToRear(AB_addAfterCA_ACB(), ELEMENT_C, Result.NoException));
			printTest(	"AB_addAfterCA_ACB()_testAddAfterDB", testAddAfter(AB_addAfterCA_ACB(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			printTest(	"AB_addAfterCA_ACB()_testAddAfterAB", testAddAfter(AB_addAfterCA_ACB(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"AB_addAfterCA_ACB()_testAddAfterDC", testAddAfter(AB_addAfterCA_ACB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// DoubleListADT
			printTest("AB_addAfterCA_ACB()_testAddAtIndexNeg1B", testAddAtIndex(AB_addAfterCA_ACB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB()_testAddAtIndex0A", testAddAtIndex(AB_addAfterCA_ACB(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_addAfterCA_ACB()_testAddAtIndex2B", testAddAtIndex(AB_addAfterCA_ACB(), 2, ELEMENT_B, Result.NoException));
			printTest("AB_addAfterCA_ACB()_testAddAtIndex1c", testAddAtIndex(AB_addAfterCA_ACB(), 1, ELEMENT_C, Result.NoException));
			printTest("AB_addAfterCA_ACB()_testSetNeg1B", testSet(AB_addAfterCA_ACB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB()_testSet0B", testSet(AB_addAfterCA_ACB(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_addAfterCA_ACB()_testAddB", testAdd(AB_addAfterCA_ACB(), ELEMENT_B, Result.NoException));
			printTest("AB_addAfterCA_ACB()_testGetNeg1", testGet(AB_addAfterCA_ACB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB()_testGet0", testGet(AB_addAfterCA_ACB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testGet2", testGet(AB_addAfterCA_ACB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testGet1", testGet(AB_addAfterCA_ACB(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testIndexOfA", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_A, 0));
			printTest("AB_addAfterCA_ACB()_testIndexOfB", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_B, 2));
			printTest("AB_addAfterCA_ACB()_testIndexOfC", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_C, 1));
			printTest("AB_addAfterCA_ACB()_testIndexOfD", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_D, -1));
			printTest("AB_addAfterCA_ACB()_testRemoveNeg1", testRemoveIndex(AB_addAfterCA_ACB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB()_testRemove0", testRemoveIndex(AB_addAfterCA_ACB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemove1", testRemoveIndex(AB_addAfterCA_ACB(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemove1", testRemoveIndex(AB_addAfterCA_ACB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testRemove2", testRemoveIndex(AB_addAfterCA_ACB(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addAfterCA_ACB()_testIterator", testIterator(AB_addAfterCA_ACB(), Result.NoException));
			printTest("AB_addAfterCA_ACB()_testIteratorHasNext", testIteratorHasNext(AB_addAfterCA_ACB().iterator(), Result.True));
			printTest("AB_addAfterCA_ACB()_testIteratorNext", testIteratorNext(AB_addAfterCA_ACB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testIteratorRemove", testIteratorRemove(AB_addAfterCA_ACB().iterator(), Result.IllegalState));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_addAfterCA_ACB(), 3), Result.False));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_addAfterCA_ACB(), 3), null, Result.NoSuchElement));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_addAfterCA_ACB(), 3), Result.NoException));
			printTest("AB_addAfterCA_ACB()_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(), 3)), Result.False));
			printTest("AB_addAfterCA_ACB()_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(), 3)), null, Result.NoSuchElement));
			printTest("AB_addAfterCA_ACB()_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(), 1)), Result.IllegalState));
			printTest("AB_addAfterCA_ACB()_testListIteratorHasPrevious", testIteratorHasPrevious(AB_addAfterCA_ACB().listIterator() , Result.False));
			printTest("AB_addAfterCA_ACB()_testListIteratorPrevious", testIteratorPrev(AB_addAfterCA_ACB().listIterator(), null, Result.NoSuchElement));
			printTest("AB_addAfterCA_ACB()_testListIteratorNextIndex", testIteratorNextIndex(AB_addAfterCA_ACB().listIterator(),0,Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testListIteratorPreviousIndex", testIteratorPrevIndex(AB_addAfterCA_ACB().listIterator(),-1,Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_testListIteratorAddA", testIteratorAdd(AB_addAfterCA_ACB().listIterator(), ELEMENT_A, Result.NoException));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIterator1", testIteratorNext(testListIterator1(AB_addAfterCA_ACB()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(AB_addAfterCA_ACB()), Result.ConcurrentModification));
			printTest("AB_addAfterCA_ACB()_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(AB_addAfterCA_ACB()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(AB_addAfterCA_ACB()), Result.True));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIterator13", testIteratorNext(testListIterator13(AB_addAfterCA_ACB()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIterator14", testIteratorNext(testListIterator14(AB_addAfterCA_ACB()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIterator15", testIteratorNext(testListIterator15(AB_addAfterCA_ACB()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorRemove_testIterator16", testIteratorRemove((AB_addAfterCA_ACB().listIterator()), Result.IllegalState));
			printTest("AB_addAfterCA_ACB()_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(AB_addAfterCA_ACB()), Result.IllegalState));
			printTest("AB_addAfterCA_ACB()_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(AB_addAfterCA_ACB().listIterator(), Result.False));
			printTest("AB_addAfterCA_ACB()_iteratorPrev_testIterator19", testIteratorPrev(AB_addAfterCA_ACB().listIterator(), null, Result.NoSuchElement));
			printTest("AB_addAfterCA_ACB()_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(AB_addAfterCA_ACB()), Result.True));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIterator20", testIteratorNext(testListIterator20(AB_addAfterCA_ACB()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(AB_addAfterCA_ACB()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorNext_testIterator22", testIteratorNext(testListIterator22(AB_addAfterCA_ACB()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB()_iteratorPrev_testIterator24", testIteratorPrev(AB_addAfterCA_ACB().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addAfterCA_ACB");
			e.printStackTrace();
		}

	}
	////////////////////////////////////////////////
	// SCENARIO: [AB] -> addToFront(C) -> [C,A,B]
	//  XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: AB -> addToFront(C) -> [C,A,B] 
	 * @return [C,A,B] after addToFront(C) 
	 */
	private DoubleLinkedListADT<Integer> AB_addToFrontC_CAB() { 
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.addToFront(ELEMENT_C);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToFront(C) -> [C,A,B] */

	private void test_AB_addToFrontC_CAB() {
		try {
			// ListADT
			printTest("AB_addToFrontC_CAB_testRemoveFirst", testRemoveFirst(AB_addToFrontC_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveLast", testRemoveLast(AB_addToFrontC_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveA", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveB", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveC", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveD", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addToFrontC_CAB_testFirst", testFirst(AB_addToFrontC_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testLast", testLast(AB_addToFrontC_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testContainsA", testContains(AB_addToFrontC_CAB(), ELEMENT_A, Result.True));
			printTest("AB_addToFrontC_CAB_testContainsB", testContains(AB_addToFrontC_CAB(), ELEMENT_B, Result.True));
			printTest("AB_addToFrontC_CAB_testContainsC", testContains(AB_addToFrontC_CAB(), ELEMENT_C, Result.True));
			printTest("AB_addToFrontC_CAB_testContainsD", testContains(AB_addToFrontC_CAB(), ELEMENT_D, Result.False));
			printTest("AB_addToFrontC_CAB_testIsEmpty", testIsEmpty(AB_addToFrontC_CAB(), Result.False));
			printTest("AB_addToFrontC_CAB_testSize", testSize(AB_addToFrontC_CAB(), 3));
			printTest("AB_addToFrontC_CAB_testToString", testToString(AB_addToFrontC_CAB(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addToFrontC_CAB_testAddToFrontB", testAddToFront(AB_addToFrontC_CAB(), ELEMENT_B, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddToRearA", testAddToRear(AB_addToFrontC_CAB(), ELEMENT_A, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddToRearC", testAddToRear(AB_addToFrontC_CAB(), ELEMENT_C, Result.NoException));
			printTest(	"AB_addToFrontC_CAB_testAddAfterDB", testAddAfter(AB_addToFrontC_CAB(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			printTest(	"AB_addToFrontC_CAB_testAddAfterAB", testAddAfter(AB_addToFrontC_CAB(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"AB_addToFrontC_CAB_testAddAfterDC", testAddAfter(AB_addToFrontC_CAB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// DoubleListADT
			printTest("AB_addToFrontC_CAB_testAddAtIndexNeg1B", testAddAtIndex(AB_addToFrontC_CAB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testAddAtIndex0C", testAddAtIndex(AB_addToFrontC_CAB(), 0, ELEMENT_C, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddAtIndex1A", testAddAtIndex(AB_addToFrontC_CAB(), 1, ELEMENT_A, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddAtIndex2B", testAddAtIndex(AB_addToFrontC_CAB(), 2, ELEMENT_B, Result.NoException));
			printTest("AB_addToFrontC_CAB_testSetNeg1B", testSet(AB_addToFrontC_CAB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testSet0B", testSet(AB_addToFrontC_CAB(), 0, ELEMENT_C, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddB", testAdd(AB_addToFrontC_CAB(), ELEMENT_B, Result.NoException));
			printTest("AB_addToFrontC_CAB_testGetNeg1", testGet(AB_addToFrontC_CAB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testGet0", testGet(AB_addToFrontC_CAB(), 0, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testGet1", testGet(AB_addToFrontC_CAB(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testGet2", testGet(AB_addToFrontC_CAB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testIndexOfC", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_C, 0));
			printTest("AB_addToFrontC_CAB_testIndexOfA", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_A, 1));
			printTest("AB_addToFrontC_CAB_testIndexOfB", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_B, 2));
			printTest("AB_addToFrontC_CAB_testIndexOfD", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_D, -1));
			printTest("AB_addToFrontC_CAB_testRemoveNeg1", testRemoveIndex(AB_addToFrontC_CAB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testRemove0", testRemoveIndex(AB_addToFrontC_CAB(), 0, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemove1", testRemoveIndex(AB_addToFrontC_CAB(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemove2", testRemoveIndex(AB_addToFrontC_CAB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemove2", testRemoveIndex(AB_addToFrontC_CAB(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addToFrontC_CAB_testIterator", testIterator(AB_addToFrontC_CAB(), Result.NoException));
			printTest("AB_addToFrontC_CAB_testIteratorHasNext", testIteratorHasNext(AB_addToFrontC_CAB().iterator(), Result.True));
			printTest("AB_addToFrontC_CAB_testIteratorNext", testIteratorNext(AB_addToFrontC_CAB().iterator(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testIteratorRemove", testIteratorRemove(AB_addToFrontC_CAB().iterator(), Result.IllegalState));
			printTest("AB_addToFrontC_CAB_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_addToFrontC_CAB(), 3), Result.False));
			printTest("AB_addToFrontC_CAB_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_addToFrontC_CAB(), 3), null, Result.NoSuchElement));
			printTest("AB_addToFrontC_CAB_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_addToFrontC_CAB(), 3), Result.NoException));
			printTest("AB_addToFrontC_CAB_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(), 3)), Result.False));
			printTest("AB_addToFrontC_CAB_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(), 3)), null, Result.NoSuchElement));
			printTest("AB_addToFrontC_CAB_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(), 1)), Result.IllegalState));
			printTest("AB_addToFrontC_CAB()_testListIteratorHasPrevious", testIteratorHasPrevious(AB_addToFrontC_CAB().listIterator() , Result.False));
			printTest("AB_addToFrontC_CAB()_testListIteratorPrevious", testIteratorPrev(AB_addToFrontC_CAB().listIterator(), null, Result.NoSuchElement));
			printTest("AB_addToFrontC_CAB()_testListIteratorNextIndex", testIteratorNextIndex(AB_addToFrontC_CAB().listIterator(),0,Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_testListIteratorPreviousIndex", testIteratorPrevIndex(AB_addToFrontC_CAB().listIterator(),-1,Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_testListIteratorAddA", testIteratorAdd(AB_addToFrontC_CAB().listIterator(), ELEMENT_A, Result.NoException));
			printTest("AB_addToFrontC_CAB()_iteratorNext_testIterator1", testIteratorNext(testListIterator1(AB_addToFrontC_CAB()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(AB_addToFrontC_CAB()), Result.ConcurrentModification));
			printTest("AB_addToFrontC_CAB()_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(AB_addToFrontC_CAB()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(AB_addToFrontC_CAB()), Result.True));
			printTest("AB_addToFrontC_CAB()_iteratorNext_testIterator13", testIteratorNext(testListIterator13(AB_addToFrontC_CAB()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorNext_testIterator14", testIteratorNext(testListIterator14(AB_addToFrontC_CAB()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorNext_testIterator15", testIteratorNext(testListIterator15(AB_addToFrontC_CAB()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorRemove_testIterator16", testIteratorRemove((AB_addToFrontC_CAB().listIterator()), Result.IllegalState));
			printTest("AB_addToFrontC_CAB()_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(AB_addToFrontC_CAB()), Result.IllegalState));
			printTest("AB_addToFrontC_CAB()_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(AB_addToFrontC_CAB().listIterator(), Result.False));
			printTest("AB_addToFrontC_CAB()_iteratorPrev_testIterator19", testIteratorPrev(AB_addToFrontC_CAB().listIterator(), null, Result.NoSuchElement));
			printTest("AB_addToFrontC_CAB()_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(AB_addToFrontC_CAB()), Result.True));
			printTest("AB_addToFrontC_CAB()_iteratorNext_testIterator20", testIteratorNext(testListIterator20(AB_addToFrontC_CAB()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(AB_addToFrontC_CAB()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorNext_testIterator22", testIteratorNext(testListIterator22(AB_addToFrontC_CAB()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB()_iteratorPrev_testIterator24", testIteratorPrev(AB_addToFrontC_CAB().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addToFrontC_CAB");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////
	//SCENARIO: [AB] -> addToRear(C) -> [A,B,C]
	//XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: AB -> addToRear(C) -> [A,B,C] 
	 * @return [A,B,C] after addToRear(C)
	 */
	private DoubleLinkedListADT<Integer> AB_addToRearC_ABC() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.addToRear(ELEMENT_C);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToRear(C) -> [A,B,C] */


	private void test_AB_addToRearC_ABC() {
		try {
			// ListADT
			printTest("AB_addToRearC_ABC_testRemoveFirst", testRemoveFirst(AB_addToRearC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveLast", testRemoveLast(AB_addToRearC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveA", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveB", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveC", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveD", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addToRearC_ABC_testFirst", testFirst(AB_addToRearC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testLast", testLast(AB_addToRearC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testContainsA", testContains(AB_addToRearC_ABC(), ELEMENT_A, Result.True));
			printTest("AB_addToRearC_ABC_testContainsB", testContains(AB_addToRearC_ABC(), ELEMENT_B, Result.True));
			printTest("AB_addToRearC_ABC_testContainsC", testContains(AB_addToRearC_ABC(), ELEMENT_C, Result.True));
			printTest("AB_addToRearC_ABC_testContainsD", testContains(AB_addToRearC_ABC(), ELEMENT_D, Result.False));
			printTest("AB_addToRearC_ABC_testIsEmpty", testIsEmpty(AB_addToRearC_ABC(), Result.False));
			printTest("AB_addToRearC_ABC_testSize", testSize(AB_addToRearC_ABC(), 3));
			printTest("AB_addToRearC_ABC_testToString", testToString(AB_addToRearC_ABC(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addToRearC_ABC_testAddToFrontB", testAddToFront(AB_addToRearC_ABC(), ELEMENT_B, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddToRearA", testAddToRear(AB_addToRearC_ABC(), ELEMENT_A, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddToRearC", testAddToRear(AB_addToRearC_ABC(), ELEMENT_C, Result.NoException));
			printTest(	"AB_addToRearC_ABC_testAddAfterDB", testAddAfter(AB_addToRearC_ABC(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			printTest(	"AB_addToRearC_ABC_testAddAfterAB", testAddAfter(AB_addToRearC_ABC(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"AB_addToRearC_ABC_testAddAfterDC", testAddAfter(AB_addToRearC_ABC(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// DoubleListADT
			printTest("AB_addToRearC_ABC_testAddAtIndexNeg1B", testAddAtIndex(AB_addToRearC_ABC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testAddAtIndex0C", testAddAtIndex(AB_addToRearC_ABC(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddAtIndex1A", testAddAtIndex(AB_addToRearC_ABC(), 2, ELEMENT_B, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddAtIndex2B", testAddAtIndex(AB_addToRearC_ABC(), 3, ELEMENT_C, Result.NoException));
			printTest("AB_addToRearC_ABC_testSetNeg1B", testSet(AB_addToRearC_ABC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testSet0A", testSet(AB_addToRearC_ABC(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddB", testAdd(AB_addToRearC_ABC(), ELEMENT_B, Result.NoException));
			printTest("AB_addToRearC_ABC_testGetNeg1", testGet(AB_addToRearC_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testGet0", testGet(AB_addToRearC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testGet1", testGet(AB_addToRearC_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testGet2", testGet(AB_addToRearC_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testIndexOfA", testIndexOf(AB_addToRearC_ABC(), ELEMENT_A, 0));
			printTest("AB_addToRearC_ABC_testIndexOfB", testIndexOf(AB_addToRearC_ABC(), ELEMENT_B, 1));
			printTest("AB_addToRearC_ABC_testIndexOfC", testIndexOf(AB_addToRearC_ABC(), ELEMENT_C, 2));
			printTest("AB_addToRearC_ABC_testIndexOfD", testIndexOf(AB_addToRearC_ABC(), ELEMENT_D, -1));
			printTest("AB_addToRearC_ABC_testRemoveNeg1", testRemoveIndex(AB_addToRearC_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testRemove0", testRemoveIndex(AB_addToRearC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemove1", testRemoveIndex(AB_addToRearC_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemove2", testRemoveIndex(AB_addToRearC_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemove2", testRemoveIndex(AB_addToRearC_ABC(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addToRearC_ABC_testIterator", testIterator(AB_addToRearC_ABC(), Result.NoException));
			printTest("AB_addToRearC_ABC_testIteratorHasNext", testIteratorHasNext(AB_addToRearC_ABC().iterator(), Result.True));
			printTest("AB_addToRearC_ABC_testIteratorNext", testIteratorNext(AB_addToRearC_ABC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testIteratorRemove", testIteratorRemove(AB_addToRearC_ABC().iterator(), Result.IllegalState));
			printTest("AB_addToRearC_ABC_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_addToRearC_ABC(), 3), Result.False));
			printTest("AB_addToRearC_ABC_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_addToRearC_ABC(), 3), null, Result.NoSuchElement));
			printTest("AB_addToRearC_ABC_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_addToRearC_ABC(), 3), Result.NoException));
			printTest("AB_addToRearC_ABC_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(), 3)), Result.False));
			printTest("AB_addToRearC_ABC_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(), 3)), null, Result.NoSuchElement));
			printTest("AB_addToRearC_ABC_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(), 1)), Result.IllegalState));
			printTest("AB_addToRearC_ABC_testListIteratorHasPrevious", testIteratorHasPrevious(AB_addToRearC_ABC().listIterator() , Result.False));
			printTest("AB_addToRearC_ABC_testListIteratorPrevious", testIteratorPrev(AB_addToRearC_ABC().listIterator(), null, Result.NoSuchElement));
			printTest("AB_addToRearC_ABC_testListIteratorNextIndex", testIteratorNextIndex(AB_addToRearC_ABC().listIterator(),0,Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testListIteratorPreviousIndex", testIteratorPrevIndex(AB_addToRearC_ABC().listIterator(),-1,Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testListIteratorAddA", testIteratorAdd(AB_addToRearC_ABC().listIterator(), ELEMENT_A, Result.NoException));
			printTest("AB_addToRearC_ABC()_iteratorNext_testIterator1", testIteratorNext(testListIterator1(AB_addToRearC_ABC()), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(AB_addToRearC_ABC()), Result.ConcurrentModification));
			printTest("AB_addToRearC_ABC()_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(AB_addToRearC_ABC()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(AB_addToRearC_ABC()), Result.True));
			printTest("AB_addToRearC_ABC()_iteratorNext_testIterator13", testIteratorNext(testListIterator13(AB_addToRearC_ABC()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorNext_testIterator14", testIteratorNext(testListIterator14(AB_addToRearC_ABC()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorNext_testIterator15", testIteratorNext(testListIterator15(AB_addToRearC_ABC()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorRemove_testIterator16", testIteratorRemove((AB_addToRearC_ABC().listIterator()), Result.IllegalState));
			printTest("AB_addToRearC_ABC()_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(AB_addToRearC_ABC()), Result.IllegalState));
			printTest("AB_addToRearC_ABC()_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(AB_addToRearC_ABC().listIterator(), Result.False));
			printTest("AB_addToRearC_ABC()_iteratorPrev_testIterator19", testIteratorPrev(AB_addToRearC_ABC().listIterator(), null, Result.NoSuchElement));
			printTest("AB_addToRearC_ABC()_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(AB_addToRearC_ABC()), Result.True));
			printTest("AB_addToRearC_ABC()_iteratorNext_testIterator20", testIteratorNext(testListIterator20(AB_addToRearC_ABC()), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(AB_addToRearC_ABC()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorNext_testIterator22", testIteratorNext(testListIterator22(AB_addToRearC_ABC()), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC()_iteratorPrev_testIterator24", testIteratorPrev(AB_addToRearC_ABC().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addToRearC_ABC");
			e.printStackTrace();
		}

	}
	////////////////////////////////////////////////
	//SCENARIO: [ABC] -> removeFirst(A) -> [B,C]
	//XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: ABC -> removeFirst(A) -> [B,C] 
	 * @return [B,C] after removeFirst(A)
	 */
	private DoubleLinkedListADT<Integer> ABC_removeFirstA_BC() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.add(ELEMENT_C);
		list.remove(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> removeFirst(A) -> [B,C] */


	private void test_ABC_removeFirstA_BC() {
		try {
			// ListADT
			printTest("ABC_removeFirstA_BC_testRemoveFirst", testRemoveFirst(ABC_removeFirstA_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testRemoveLast", testRemoveLast(ABC_removeFirstA_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testRemoveB", testRemoveElement(ABC_removeFirstA_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testRemoveC", testRemoveElement(ABC_removeFirstA_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testRemoveD", testRemoveElement(ABC_removeFirstA_BC(), ELEMENT_D, Result.ElementNotFound));
			printTest("ABC_removeFirstA_BC_testFirst", testFirst(ABC_removeFirstA_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testLast", testLast(ABC_removeFirstA_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testContainsA", testContains(ABC_removeFirstA_BC(), ELEMENT_A, Result.False));
			printTest("ABC_removeFirstA_BC_testContainsB", testContains(ABC_removeFirstA_BC(), ELEMENT_B, Result.True));
			printTest("ABC_removeFirstA_BC_testContainsC", testContains(ABC_removeFirstA_BC(), ELEMENT_C, Result.True));
			printTest("ABC_removeFirstA_BC_testContainsD", testContains(ABC_removeFirstA_BC(), ELEMENT_D, Result.False));
			printTest("ABC_removeFirstA_BC_testIsEmpty", testIsEmpty(ABC_removeFirstA_BC(), Result.False));
			printTest("ABC_removeFirstA_BC_testSize", testSize(ABC_removeFirstA_BC(), 2));
			printTest("ABC_removeFirstA_BC_testToString", testToString(ABC_removeFirstA_BC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_removeFirstA_BC_testAddToFrontB", testAddToFront(ABC_removeFirstA_BC(), ELEMENT_B, Result.NoException));
			printTest("ABC_removeFirstA_BC_testAddToRearA", testAddToRear(ABC_removeFirstA_BC(), ELEMENT_A, Result.NoException));
			printTest("ABC_removeFirstA_BC_testAddToRearC", testAddToRear(ABC_removeFirstA_BC(), ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeFirstA_BC_testAddAfterDB", testAddAfter(ABC_removeFirstA_BC(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			printTest(	"ABC_removeFirstA_BC_testAddAfterCB", testAddAfter(ABC_removeFirstA_BC(), ELEMENT_C, ELEMENT_B, Result.NoException));
			printTest(	"ABC_removeFirstA_BC_testAddAfterDC", testAddAfter(ABC_removeFirstA_BC(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// DoubleListADT
			printTest("ABC_removeFirstA_BC_testAddAtIndexNeg1B", testAddAtIndex(ABC_removeFirstA_BC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("ABC_removeFirstA_BC_testAddAtIndex0B", testAddAtIndex(ABC_removeFirstA_BC(), 0, ELEMENT_B, Result.NoException));
			printTest("ABC_removeFirstA_BC_testAddAtIndex1C", testAddAtIndex(ABC_removeFirstA_BC(), 1, ELEMENT_C, Result.NoException));
			printTest("ABC_removeFirstA_BC_testSetNeg1B", testSet(ABC_removeFirstA_BC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("ABC_removeFirstA_BC_testSet0B", testSet(ABC_removeFirstA_BC(), 0, ELEMENT_A, Result.NoException));
			printTest("ABC_removeFirstA_BC_testAddB", testAdd(ABC_removeFirstA_BC(), ELEMENT_B, Result.NoException));
			printTest("ABC_removeFirstA_BC_testGetNeg1", testGet(ABC_removeFirstA_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirstA_BC_testGet0", testGet(ABC_removeFirstA_BC(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testGet1", testGet(ABC_removeFirstA_BC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testGet2", testGet(ABC_removeFirstA_BC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirstA_BC_testIndexOfA", testIndexOf(ABC_removeFirstA_BC(), ELEMENT_A, -1));
			printTest("ABC_removeFirstA_BC_testIndexOfB", testIndexOf(ABC_removeFirstA_BC(), ELEMENT_B, 0));
			printTest("ABC_removeFirstA_BC_testIndexOfC", testIndexOf(ABC_removeFirstA_BC(), ELEMENT_C, 1));
			printTest("ABC_removeFirstA_BC_testIndexOfD", testIndexOf(ABC_removeFirstA_BC(), ELEMENT_D, -1));
			printTest("ABC_removeFirstA_BC_testRemoveNeg1", testRemoveIndex(ABC_removeFirstA_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirstA_BC_testRemove0", testRemoveIndex(ABC_removeFirstA_BC(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testRemove1", testRemoveIndex(ABC_removeFirstA_BC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testRemove2", testRemoveIndex(ABC_removeFirstA_BC(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_removeFirstA_BC_testIterator", testIterator(ABC_removeFirstA_BC(), Result.NoException));
			printTest("ABC_removeFirstA_BC_testIteratorHasNext", testIteratorHasNext(ABC_removeFirstA_BC().iterator(), Result.True));
			printTest("ABC_removeFirstA_BC_testIteratorNext", testIteratorNext(ABC_removeFirstA_BC().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testIteratorRemove", testIteratorRemove(ABC_removeFirstA_BC().iterator(), Result.IllegalState));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(ABC_removeFirstA_BC(), 2), Result.False));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(ABC_removeFirstA_BC(), 2), null, Result.NoSuchElement));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(ABC_removeFirstA_BC(), 2), Result.NoException));
			printTest("ABC_removeFirstA_BC_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeFirstA_BC(), 2)), Result.False));
			printTest("ABC_removeFirstA_BC_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeFirstA_BC(), 2)), null, Result.NoSuchElement));
			printTest("ABC_removeFirstA_BC_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(ABC_removeFirstA_BC(), 1)), Result.IllegalState));
			printTest("ABC_removeFirstA_BC_testListIteratorHasPrevious", testIteratorHasPrevious(ABC_removeFirstA_BC().listIterator() , Result.False));
			printTest("ABC_removeFirstA_BC_testListIteratorPrevious", testIteratorPrev(ABC_removeFirstA_BC().listIterator(), null, Result.NoSuchElement));
			printTest("ABC_removeFirstA_BC_testListIteratorNextIndex", testIteratorNextIndex(ABC_removeFirstA_BC().listIterator(),0,Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testListIteratorPreviousIndex", testIteratorPrevIndex(ABC_removeFirstA_BC().listIterator(),-1,Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_testListIteratorAddA", testIteratorAdd(ABC_removeFirstA_BC().listIterator(), ELEMENT_A, Result.NoException));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIterator1", testIteratorNext(testListIterator1(ABC_removeFirstA_BC()), null, Result.NoSuchElement));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(ABC_removeFirstA_BC()), Result.ConcurrentModification));
			printTest("ABC_removeFirstA_BC_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(ABC_removeFirstA_BC()), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(ABC_removeFirstA_BC()), Result.True));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIterator13", testIteratorNext(testListIterator13(ABC_removeFirstA_BC()), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIterator14", testIteratorNext(testListIterator14(ABC_removeFirstA_BC()), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIterator15", testIteratorNext(testListIterator15(ABC_removeFirstA_BC()), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_iteratorRemove_testIterator16", testIteratorRemove((ABC_removeFirstA_BC().listIterator()), Result.IllegalState));
			printTest("ABC_removeFirstA_BC_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(ABC_removeFirstA_BC()), Result.IllegalState));
			printTest("ABC_removeFirstA_BC_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(ABC_removeFirstA_BC().listIterator(), Result.False));
			printTest("ABC_removeFirstA_BC_iteratorPrev_testIterator19", testIteratorPrev(ABC_removeFirstA_BC().listIterator(), null, Result.NoSuchElement));
			printTest("ABC_removeFirstA_BC_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(ABC_removeFirstA_BC()), Result.True));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIterator20", testIteratorNext(testListIterator20(ABC_removeFirstA_BC()), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(ABC_removeFirstA_BC()), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_iteratorNext_testIterator22", testIteratorNext(testListIterator22(ABC_removeFirstA_BC()), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirstA_BC_iteratorPrev_testIterator24", testIteratorPrev(ABC_removeFirstA_BC().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeFirstA_BC");
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////////
	//SCENARIO: [B] -> set(0B) -> [B]
	//XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: B -> set(0B) -> [B] 
	 * @return [B] after set(0B)
	 */
	private DoubleLinkedListADT<Integer> A_set0B_B() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.set(0, ELEMENT_B);
		return list;
	}

	/** Run all tests on scenario: empty list -> set(0B) -> [B] */

	private void test_A_set0B_B() {
		try {
			// ListADT
			printTest("A_set0B_B_testRemoveFirst", testRemoveFirst(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testRemoveLast", testRemoveLast(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testRemoveB", testRemoveElement(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testFirst", testFirst(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testLast", testLast(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testContainsA", testContains(A_set0B_B(), ELEMENT_A, Result.False));
			printTest("A_set0B_B_testContainsB", testContains(A_set0B_B(), ELEMENT_B, Result.True));
			printTest("A_set0B_B_testIsEmpty", testIsEmpty(A_set0B_B(), Result.False));
			printTest("A_set0B_B_testSize", testSize(A_set0B_B(), 1));
			printTest("A_set0B_B_testToString", testToString(A_set0B_B(), Result.ValidString));
			// UnorderedListADT
			printTest("A_set0B_B_testAddToFrontB", testAddToFront(A_set0B_B(), ELEMENT_B, Result.NoException));
			printTest("A_set0B_B_testAddToRearB", testAddToRear(A_set0B_B(), ELEMENT_B, Result.NoException));
			printTest(	"A_set0B_B_testAddAfterCB", testAddAfter(A_set0B_B(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"A_set0B_B_testAddAfterAB", testAddAfter(A_set0B_B(), ELEMENT_A, ELEMENT_B, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_set0B_B_testAddAtIndexNeg1B", testAddAtIndex(A_set0B_B(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testAddAtIndex0B", testAddAtIndex(A_set0B_B(), 0, ELEMENT_B, Result.NoException));
			printTest("A_set0B_B_testAddAtIndex1B", testAddAtIndex(A_set0B_B(), 1, ELEMENT_B, Result.NoException));
			printTest("A_set0B_B_testSetNeg1B", testSet(A_set0B_B(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testSet0B", testSet(A_set0B_B(), 0, ELEMENT_B, Result.NoException));
			printTest("A_set0B_B_testAddB", testAdd(A_set0B_B(), ELEMENT_B, Result.NoException));
			printTest("A_set0B_B_testGetNeg1", testGet(A_set0B_B(), -1, null, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testGet0", testGet(A_set0B_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testIndexOfA", testIndexOf(A_set0B_B(), ELEMENT_A, -1));
			printTest("A_set0B_B_testIndexOfB", testIndexOf(A_set0B_B(), ELEMENT_B, 0));
			printTest("A_set0B_B_testRemoveNeg1", testRemoveIndex(A_set0B_B(), -1, null, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testRemove0", testRemoveIndex(A_set0B_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testRemove1", testRemoveIndex(A_set0B_B(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_set0B_B_testIterator", testIterator(A_set0B_B(), Result.NoException));
			printTest("A_set0B_B_testIteratorHasNext", testIteratorHasNext(A_set0B_B().iterator(), Result.True));
			printTest("A_set0B_B_testIteratorNext", testIteratorNext(A_set0B_B().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testIteratorRemove", testIteratorRemove(A_set0B_B().iterator(), Result.IllegalState));
			printTest("A_set0B_B_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_set0B_B(), 1), Result.False));
			printTest("A_set0B_B_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_set0B_B(), 1), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_set0B_B(), 1), Result.NoException));
			printTest("A_set0B_B_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_set0B_B(), 1)), Result.False));
			printTest("A_set0B_B_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_set0B_B(), 1)), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_set0B_B(), 1)), Result.IllegalState));
			printTest("A_set0B_B_testListIteratorHasPrevious", testIteratorHasPrevious(A_set0B_B().listIterator() , Result.False));
			printTest("A_set0B_B_testListIteratorPrevious", testIteratorPrev(A_set0B_B().listIterator(), null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIteratorNextIndex", testIteratorNextIndex(A_set0B_B().listIterator(),0,Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorPreviousIndex", testIteratorPrevIndex(A_set0B_B().listIterator(),-1,Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorAddA", testIteratorAdd(A_set0B_B().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_set0B_B_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(A_set0B_B()), Result.ConcurrentModification));
			printTest("A_set0B_B_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(A_set0B_B()), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(A_set0B_B()), Result.False));
			printTest("A_set0B_B_iteratorNext_testIterator13", testIteratorNext(testListIterator13(A_set0B_B()), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorNext_testIterator14", testIteratorNext(testListIterator14(A_set0B_B()), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorNext_testIterator15", testIteratorNext(testListIterator15(A_set0B_B()), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorRemove_testIterator16", testIteratorRemove((A_set0B_B().listIterator()), Result.IllegalState));
			printTest("A_set0B_B_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(A_set0B_B()), Result.IllegalState));
			printTest("A_set0B_B_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(A_set0B_B().listIterator(), Result.False));
			printTest("A_set0B_B_iteratorPrev_testIterator19", testIteratorPrev(A_set0B_B().listIterator(), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(A_set0B_B()), Result.True));
			printTest("A_set0B_B_iteratorNext_testIterator22", testIteratorNext(testListIterator22(A_set0B_B()), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorPrev_testIterator24", testIteratorPrev(A_set0B_B().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_set0B_B");
			e.printStackTrace();
		}

	}



	////////////////////////////////////////////////
	//SCENARIO: [A] -> add(1B) -> [A,B]
	//XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: A -> add(1B) -> [A,B] 
	 * @return [A,B] after add(1B)
	 */
	private DoubleLinkedListADT<Integer> A_add1B_AB() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.add(1, ELEMENT_B);
		return list;
	}
	/** Run all tests on scenario: empty list -> add(1B) -> [B] */

	private void test_A_add1B_AB() {
		try{
			// ListADT
			printTest("A_add1B_AB_testRemoveFirst", testRemoveFirst(A_add1B_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveLast", testRemoveLast(A_add1B_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveA", testRemoveElement(A_add1B_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveB", testRemoveElement(A_add1B_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveC", testRemoveElement(A_add1B_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_add1B_AB_testFirst", testFirst(A_add1B_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testLast", testLast(A_add1B_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testContainsA", testContains(A_add1B_AB(), ELEMENT_A, Result.True));
			printTest("A_add1B_AB_testContainsB", testContains(A_add1B_AB(), ELEMENT_B, Result.True));
			printTest("A_add1B_AB_testContainsC", testContains(A_add1B_AB(), ELEMENT_C, Result.False));
			printTest("A_add1B_AB_testIsEmpty", testIsEmpty(A_add1B_AB(), Result.False));
			printTest("A_add1B_AB_testSize", testSize(A_add1B_AB(), 2));
			printTest("A_add1B_AB_testToString", testToString(A_add1B_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_add1B_AB_testAddToFrontA", testAddToFront(A_add1B_AB(), ELEMENT_A, Result.NoException));
			printTest("A_add1B_AB_testAddToRearB", testAddToRear(A_add1B_AB(), ELEMENT_B, Result.NoException));
			printTest(	"A_add1B_AB_testAddAfterCB", testAddAfter(A_add1B_AB(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"A_add1B_AB_testAddAfterAB", testAddAfter(A_add1B_AB(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"A_add1B_AB_testAddAfterDB", testAddAfter(A_add1B_AB(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_add1B_AB_testAddAtIndexNeg1B", testAddAtIndex(A_add1B_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testAddAtIndex0B", testAddAtIndex(A_add1B_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_add1B_AB_testAddAtIndex1B", testAddAtIndex(A_add1B_AB(), 1, ELEMENT_A, Result.NoException));
			printTest("A_add1B_AB_testSetNeg1B", testSet(A_add1B_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testSet0B", testSet(A_add1B_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_add1B_AB_testAddB", testAdd(A_add1B_AB(), ELEMENT_B, Result.NoException));
			printTest("A_add1B_AB_testGetNeg1", testGet(A_add1B_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testGet0", testGet(A_add1B_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testGet1", testGet(A_add1B_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testIndexOfA", testIndexOf(A_add1B_AB(), ELEMENT_B, 1));
			printTest("A_add1B_AB_testIndexOfB", testIndexOf(A_add1B_AB(), ELEMENT_A, 0));
			printTest("A_add1B_AB_testIndexOfC", testIndexOf(A_add1B_AB(), ELEMENT_C, -1));
			printTest("A_add1B_AB_testRemoveNeg1", testRemoveIndex(A_add1B_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testRemove0", testRemoveIndex(A_add1B_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testRemove1", testRemoveIndex(A_add1B_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testRemove2", testRemoveIndex(A_add1B_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_add1B_AB_testIterator", testIterator(A_add1B_AB(), Result.NoException));
			printTest("A_add1B_AB_testIteratorHasNext", testIteratorHasNext(A_add1B_AB().iterator(), Result.True));
			printTest("A_add1B_AB_testIteratorNext", testIteratorNext(A_add1B_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testIteratorRemove", testIteratorRemove(A_add1B_AB().iterator(), Result.IllegalState));
			printTest("A_add1B_AB_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_add1B_AB(), 2), Result.False));
			printTest("A_add1B_AB_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_add1B_AB(), 2), null, Result.NoSuchElement));
			printTest("A_add1B_AB_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_add1B_AB(), 2), Result.NoException));
			printTest("A_add1B_AB_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_add1B_AB(), 2)), Result.False));
			printTest("A_add1B_AB_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_add1B_AB(), 2)), null, Result.NoSuchElement));
			printTest("A_add1B_AB_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_add1B_AB(), 1)), Result.IllegalState));
			printTest("A_add1B_AB_testListIteratorHasPrevious", testIteratorHasPrevious(A_add1B_AB().listIterator() , Result.False));
			printTest("A_add1B_AB_testListIteratorPrevious", testIteratorPrev(A_add1B_AB().listIterator(), null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIteratorNextIndex", testIteratorNextIndex(A_add1B_AB().listIterator(),0,Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorPreviousIndex", testIteratorPrevIndex(A_add1B_AB().listIterator(),-1,Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorAddA", testIteratorAdd(A_add1B_AB().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_add1B_AB_testListIteratorSetB", testIteratorSet(A_add1B_AB().listIterator(), ELEMENT_B, Result.NoException));
			printTest("A_add1B_AB_iteratorNext_testIterator1", testIteratorNext(testListIterator1(A_add1B_AB()), null, Result.NoSuchElement));
			printTest("A_add1B_AB_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(A_add1B_AB()), Result.ConcurrentModification));
			printTest("A_add1B_AB_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(A_add1B_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_iteratorNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(A_add1B_AB()), Result.True));
			printTest("A_add1B_AB_iteratorNext_testIterator13", testIteratorNext(testListIterator13(A_add1B_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_iteratorHasNext_testIterator14", testIteratorNext(testListIterator14(A_add1B_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_iteratorHasNext_testIterator15", testIteratorNext(testListIterator15(A_add1B_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_iteratorHasNext_testIterator16", testIteratorRemove((A_add1B_AB().listIterator()), Result.IllegalState));
			printTest("A_add1B_AB_iteratorHasNext_testIterator17", testIteratorRemove(testListIterator17(A_add1B_AB()), Result.IllegalState));
			printTest("A_add1B_AB_iteratorHasNext_testIterator18", testIteratorHasPrevious(A_add1B_AB().listIterator(), Result.False));
			printTest("A_add1B_AB_iteratorHasNext_testIterator19", testIteratorPrev(A_add1B_AB().listIterator(), null, Result.NoSuchElement));
			printTest("A_add1B_AB_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(A_add1B_AB()), Result.True));
			printTest("A_add1B_AB_iteratorNext_testIterator20", testIteratorNext(testListIterator20(A_add1B_AB()), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(A_add1B_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_iteratorNext_testIterator22", testIteratorNext(testListIterator22(A_add1B_AB()), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_iteratorPrev_testIterator24", testIteratorPrev(A_add1B_AB().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_add1B_AB");
			e.printStackTrace();
		}


	}

	////////////////////////////////////////////////
	//SCENARIO: [A] -> add(0B) -> [B,A]
	//XXX <- see the box on the right? this tag aids in navigating a long file
	////////////////////////////////////////////////

	/** Scenario: A -> add(0B) -> [B,A] 
	 * @return [B,A] after add(0B)
	 */
	private DoubleLinkedListADT<Integer> A_add0B_BA() {
		DoubleLinkedListADT<Integer> list = newList(); 
		list.add(ELEMENT_A);
		list.add(0, ELEMENT_B);
		return list;
	}

	/** Run all tests on scenario: empty list -> add(0B) -> [B,A] */


	private void test_A_add0B_BA() {

		try {
			// ListADT
			printTest("A_add0B_BA_testRemoveFirst", testRemoveFirst(A_add0B_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveLast", testRemoveLast(A_add0B_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveA", testRemoveElement(A_add0B_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveB", testRemoveElement(A_add0B_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveC", testRemoveElement(A_add0B_BA(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_add0B_BA_testFirst", testFirst(A_add0B_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testLast", testLast(A_add0B_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testContainsA", testContains(A_add0B_BA(), ELEMENT_A, Result.True));
			printTest("A_add0B_BA_testContainsB", testContains(A_add0B_BA(), ELEMENT_B, Result.True));
			printTest("A_add0B_BA_testContainsC", testContains(A_add0B_BA(), ELEMENT_C, Result.False));
			printTest("A_add0B_BA_testIsEmpty", testIsEmpty(A_add0B_BA(), Result.False));
			printTest("A_add0B_BA_testSize", testSize(A_add0B_BA(), 2));
			printTest("A_add0B_BA_testToString", testToString(A_add0B_BA(), Result.ValidString));
			// UnorderedListADT
			printTest("A_add0B_BA_testAddToFrontA", testAddToFront(A_add0B_BA(), ELEMENT_A, Result.NoException));
			printTest("A_add0B_BA_testAddToRearB", testAddToRear(A_add0B_BA(), ELEMENT_B, Result.NoException));
			printTest(	"A_add0B_BA_testAddAfterCB", testAddAfter(A_add0B_BA(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"A_add0B_BA_testAddAfterAB", testAddAfter(A_add0B_BA(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"A_add0B_BA_testAddAfterDB", testAddAfter(A_add0B_BA(), ELEMENT_D, ELEMENT_B, Result.ElementNotFound));
			// DoubleListADT
			printTest("A_add0B_BA_testAddAtIndexNeg1B", testAddAtIndex(A_add0B_BA(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testAddAtIndex0B", testAddAtIndex(A_add0B_BA(), 0, ELEMENT_B, Result.NoException));
			printTest("A_add0B_BA_testAddAtIndex1B", testAddAtIndex(A_add0B_BA(), 1, ELEMENT_B, Result.NoException));
			printTest("A_add0B_BA_testSetNeg1B", testSet(A_add0B_BA(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testSet0B", testSet(A_add0B_BA(), 0, ELEMENT_B, Result.NoException));
			printTest("A_add0B_BA_testAddB", testAdd(A_add0B_BA(), ELEMENT_B, Result.NoException));
			printTest("A_add0B_BA_testGetNeg1", testGet(A_add0B_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testGet0", testGet(A_add0B_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testIndexOfA", testIndexOf(A_add0B_BA(), ELEMENT_A, 1));
			printTest("A_add0B_BA_testIndexOfB", testIndexOf(A_add0B_BA(), ELEMENT_B, 0));
			printTest("A_add0B_BA_testIndexOfC", testIndexOf(A_add0B_BA(), ELEMENT_C, -1));
			printTest("A_add0B_BA_testRemoveNeg1", testRemoveIndex(A_add0B_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testRemove0", testRemoveIndex(A_add0B_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testRemove1", testRemoveIndex(A_add0B_BA(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testRemove2", testRemoveIndex(A_add0B_BA(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_add0B_BA_testIterator", testIterator(A_add0B_BA(), Result.NoException));
			printTest("A_add0B_BA_testIteratorHasNext", testIteratorHasNext(A_add0B_BA().iterator(), Result.True));
			printTest("A_add0B_BA_testIteratorHasNext", testIteratorHasPrevious(A_add0B_BA().listIterator(), Result.False));
			printTest("A_add0B_BA_testIteratorRemove", testIteratorRemove(A_add0B_BA().iterator(), Result.IllegalState));
			printTest("A_add0B_BA_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_add0B_BA(), 2), Result.False));
			printTest("A_add0B_BA_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_add0B_BA(), 2), null, Result.NoSuchElement));
			printTest("A_add0B_BA_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_add0B_BA(), 2), Result.NoException));
			printTest("A_add0B_BA_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_add0B_BA(), 2)), Result.False));
			printTest("A_add0B_BA_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_add0B_BA(), 2)), null, Result.NoSuchElement));
			printTest("A_add0B_BA_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_add0B_BA(), 1)), Result.IllegalState));
			printTest("A_add0B_BA_iteratorNextRemove_testListIteratorAddA", testIteratorAdd(A_add0B_BA().listIterator(), ELEMENT_B,Result.NoException));
			printTest("A_add0B_BA_iteratorNextRemove_testListIteratorAddB", testIteratorAdd(A_add0B_BA().listIterator(), ELEMENT_C,Result.NoException));
			printTest("A_add0B_BA_iteratorNextRemove_testListIteratorSet", testIteratorSet(A_add0B_BA().listIterator(), ELEMENT_C,Result.NoException));
			printTest("A_add0B_BA_iteratorNextRemove_testListIteratorSet", testIteratorSet(A_add0B_BA().listIterator(), ELEMENT_D,Result.NoException));
			printTest("A_add0B_BA_testListIteratorHasPrevious", testIteratorHasPrevious(A_add0B_BA().listIterator() , Result.False));
			printTest("A_add0B_BA_testListIteratorPrevious", testIteratorPrev(A_add0B_BA().listIterator(), null, Result.NoSuchElement));
			printTest("A_add0B_BA_testListIteratorNextIndex", testIteratorNextIndex(A_add0B_BA().listIterator(),0,Result.MatchingValue));
			printTest("A_add0B_BA_testListIteratorPreviousIndex", testIteratorPrevIndex(A_add0B_BA().listIterator(),-1,Result.MatchingValue));
			printTest("A_add0B_BA_testListIteratorAddA", testIteratorAdd(A_add0B_BA().listIterator(), ELEMENT_A, Result.NoException));
			printTest("A_add0B_BA_testListIteratorSetB", testIteratorSet(A_add0B_BA().listIterator(), ELEMENT_B, Result.NoException));
			printTest("A_add0B_BA_iteratorNext_testIterator1", testIteratorNext(testListIterator1(A_add0B_BA()), null, Result.NoSuchElement));
			printTest("A_add0B_BA_iteratorHasNext_testIterator2", testIteratorHasNext(testListIterator2(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorHasNext_testIterator3", testIteratorHasNext(testListIterator3(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorHasNext_testIterator4", testIteratorHasNext(testListIterator4(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorHasNext_testIterator5", testIteratorHasNext(testListIterator5(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorHasNext_testIterator6", testIteratorHasNext(testListIterator6(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorHasNext_testIterator7", testIteratorHasNext(testListIterator7(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorHasNext_testIterator8", testIteratorHasNext(testListIterator8(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorHasNext_testIterator9", testIteratorHasNext(testListIterator9(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_testIteratorNext_testIterator11", testIteratorNext(testListIterator11(A_add0B_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_iteratorHasNext_testIteratorHasN_testIterator12", testIteratorHasNext(testListIterator11(A_add0B_BA()), Result.True));
			printTest("A_add0B_BA_iteratorNext_testIterator13", testIteratorNext(testListIterator13(A_add0B_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_iteratorNext_testIterator14", testIteratorNext(testListIterator14(A_add0B_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_iteratorNext_testIterator15", testIteratorNext(testListIterator15(A_add0B_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_iteratorRemove_testIterator16", testIteratorRemove((A_add0B_BA().listIterator()), Result.IllegalState));
			printTest("A_add0B_BA_iteratorRemove_testIterator17", testIteratorRemove(testListIterator17(A_add0B_BA()), Result.IllegalState));
			printTest("A_add0B_BA_iteratorHasPrevious_testIterator18", testIteratorHasPrevious(A_add0B_BA().listIterator(), Result.False));
			printTest("A_add0B_BA_iteratorPrevious_testIterator19", testIteratorPrev(A_add0B_BA().listIterator(), null, Result.NoSuchElement));
			printTest("A_add0B_BA_iteratorHasNext_testIterator19", testIteratorHasNext(testListIterator19(A_add0B_BA()), Result.True));
			printTest("A_add0B_BA_iteratorNext_testIterator20", testIteratorNext(testListIterator20(A_add0B_BA()), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_iteratorPrev_testIterator21", testIteratorPrev(testListIterator21(A_add0B_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_iteratorHasNext_testIterator21", testIteratorHasNext(testListIterator9(A_add0B_BA()), Result.ConcurrentModification));
			printTest("A_add0B_BA_iteratorNext_testIterator22", testIteratorNext(testListIterator22(A_add0B_BA()), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_iteratorPrev_testIterator23", testIteratorPrev(A_add0B_BA().listIterator(), null, Result.NoSuchElement));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_add0B_BA");
			e.printStackTrace();
		}
	}
	// //////////////////////////
	// LIST_ADT TESTS XXX
	// //////////////////////////

	/**
	 * Runs removeFirst() method on given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveFirst(DoubleLinkedListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.removeFirst();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveFirst", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs removeLast() method on given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveLast(DoubleLinkedListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.removeLast();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveLast", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs removeLast() method on given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element element to remove
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveElement(DoubleLinkedListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.remove(element);
			if (retVal.equals(element)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (ElementNotFoundException e) {
			result = Result.ElementNotFound;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}	catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveElement", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs list's iterator previous() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasPrevious()
	 * @param expectedValue the Integer expected from previous() or null if an exception is expected
	 * @param expectedResult MatchingValue or expected exception
	 * @return test success
	 */
	private boolean testIteratorPrev(ListIterator<Integer> iterator, Integer expectedValue, Result expectedResult) {
		Result result;
		try {
			Integer retVal = iterator.previous();
			if (retVal.equals(expectedValue)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (NoSuchElementException e) {
			result = Result.NoSuchElement;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} catch (NullPointerException npe) {
			result = Result.Fail;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs list's iterator previousIndex() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasPrevious()
	 * @param expectedValue the Integer expected from previousIndex() or null if an exception is expected
	 * @param expectedResult MatchingValue or expected exception
	 * @return test success
	 */
	private boolean testIteratorPrevIndex(ListIterator<Integer> iterator, Integer expectedValue, Result expectedResult){
		Result result;
		try {
			Integer retVal = iterator.previousIndex();
			if (retVal.equals(expectedValue)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (NoSuchElementException e) {
			result = Result.NoSuchElement;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} catch (NullPointerException npe) {
			result = Result.Fail;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	/**
	 * Runs first() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testFirst(DoubleLinkedListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.first();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testFirst", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs last() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testLast(DoubleLinkedListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.last();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testLast", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs contains() method on a given list and element and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testContains(DoubleLinkedListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			if (list.contains(element)) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testContains", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs isEmpty() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIsEmpty(DoubleLinkedListADT<Integer> list, Result expectedResult) {
		Result result;
		try {
			if (list.isEmpty()) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIsEmpty", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs size() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedSize
	 * @return test success
	 */
	private boolean testSize(DoubleLinkedListADT<Integer> list, int expectedSize) {
		try {
			return (list.size() == expectedSize);
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testSize", e.toString());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Runs toString() method on given list and attempts to confirm non-default or empty String
	 * difficult to test - just confirm that default address output has been overridden
	 * @param list a list already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testToString(DoubleLinkedListADT<Integer> list, Result expectedResult) {
		Result result;
		try {
			String str = list.toString();
			System.out.println("toString() output: " + str);
			if (str.length() == 0) {
				result = Result.Fail;
			}
			char lastChar = str.charAt(str.length() - 1);
			if (str.contains("@")
					&& !str.contains(" ")
					&& Character.isLetter(str.charAt(0))
					&& (Character.isDigit(lastChar) || (lastChar >= 'a' && lastChar <= 'f'))) {
				result = Result.Fail; // looks like default toString()
			} else {
				result = Result.ValidString;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testToString", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	// /////////////////////////////////////////
	// UNORDERED_LIST_ADT TESTS XXX
	// /////////////////////////////////////////

	/**
	 * Runs addToFront() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddToFront(DoubleLinkedListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			list.addToFront(element);
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddToFront",  e.toString());
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs addToRear() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddToRear(DoubleLinkedListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			list.addToRear(element);
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddToRear", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs addAfter() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param target
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddAfter(DoubleLinkedListADT<Integer> list, Integer target, Integer element, Result expectedResult) {
		Result result;
		try {
			list.addAfter(element, target);
			result = Result.NoException;
		} catch (ElementNotFoundException e) {
			result = Result.ElementNotFound;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddAfter", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	// /////////////////////////////////////
	// Double_LIST_ADT TESTS XXX
	// /////////////////////////////////////

	/**
	 * Runs add(int, T) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddAtIndex(DoubleLinkedListADT<Integer> list, int index, Integer element, Result expectedResult) {
		Result result;
		try {
			list.add(index, element);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddAtIndex", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs add(T) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAdd(DoubleLinkedListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			list.add(element);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddAtIndex", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs add(T) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	
	private boolean testIteratorAdd(ListIterator<Integer> iterator, Integer element, Result expectedResult) {
		Result result;
		try {
			iterator.add(element);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddAtIndex", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs set(int, T) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testSet(DoubleLinkedListADT<Integer> list, int index, Integer element, Result expectedResult) {
		Result result;
		try {
			list.set(index, element);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testSet", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	

	/**
	 * Runs set(int) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIteratorSet(ListIterator<Integer> iterator, int index, Result expectedResult) {
		Result result;
		try {
			iterator.set(index);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testSet", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs get() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param expectedElement
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testGet(DoubleLinkedListADT<Integer> list, int index, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.get(index);
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} 
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testGet", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs remove(index) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param expectedElement
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveIndex(DoubleLinkedListADT<Integer> list, int index, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.remove(index);
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}
		catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveIndex", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs indexOf() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedIndex
	 * @return test success
	 */
	private boolean testIndexOf(DoubleLinkedListADT<Integer> list, Integer element, int expectedIndex) {
		Result result;
		try {
			return list.indexOf(element) == expectedIndex;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIndexOf", e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Runs indexOf() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedIndex
	 * @return test success
	 */
	private boolean testIteratorIndexOf(ListIterator<Integer> iterator, Integer element, int expectedIndex) {
		Result result;
		try {
			return iterator.nextIndex() == expectedIndex;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIndexOf", e.toString());
			e.printStackTrace();
			return false;
		}
	}

	////////////////////////////
	// ITERATOR TESTS XXX
	////////////////////////////

	/**
	 * Runs iterator() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIterator(DoubleLinkedListADT<Integer> list, Result expectedResult) {
		Result result;
		try {
			Iterator<Integer> it = list.iterator();
			result = Result.NoException;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIterator", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs list's iterator hasNext() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasNext()
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIteratorHasNext(Iterator<Integer> iterator, Result expectedResult) {
		Result result;
		try {
			if (iterator.hasNext()) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} 
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorHasNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs list's iterator hasNext() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasNext()
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIteratorHasPrevious(ListIterator<Integer> iterator, Result expectedResult) {
		Result result;
		try {
			if (iterator.hasPrevious()) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} 
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorHasNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs list's iterator nextIndex() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasNext()
	 * @param expectedValue the Integer expected from nextIndex() or null if an exception is expected
	 * @param expectedResult MatchingValue or expected exception
	 * @return test success
	 */
	private boolean testIteratorNextIndex(ListIterator<Integer> iterator, Integer expectedValue, Result expectedResult){
		Result result;
		try {
			Integer retVal = iterator.nextIndex();
			if (retVal.equals(expectedValue)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (NoSuchElementException e) {
			result = Result.NoSuchElement;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} catch (NullPointerException npe) {
			result = Result.Fail;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs list's iterator next() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasNext()
	 * @param expectedValue the Integer expected from next() or null if an exception is expected
	 * @param expectedResult MatchingValue or expected exception
	 * @return test success
	 */
	private boolean testIteratorNext(Iterator<Integer> iterator, Integer expectedValue, Result expectedResult) {
		Result result;
		try {
			Integer retVal = iterator.next();
			if (retVal.equals(expectedValue)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (NoSuchElementException e) {
			result = Result.NoSuchElement;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	private boolean testListIteratorNext(ListIterator<Integer> iterator, Integer expectedValue, Result expectedResult) {
		Result result;
		try {
			Integer retVal = iterator.next();
			if (retVal.equals(expectedValue)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (NoSuchElementException e) {
			result = Result.NoSuchElement;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		}
		catch(NullPointerException e){
			result = Result.Fail; 
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs list's iterator remove() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to remove()
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIteratorRemove(Iterator<Integer> iterator, Result expectedResult) {
		Result result;
		try {
			iterator.remove();
			result = Result.NoException;
		} catch (IllegalStateException e) {
			result = Result.IllegalState;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} catch(NullPointerException ee){
			result = Result.Fail;
		}
		catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorRemove", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	//////////////////////////////////////////////////////////
	//HELPER METHODS FOR TESTING ITERATORS XXX
	//////////////////////////////////////////////////////////
	/**
	 * Helper for testing iterators. Return an Iterator that has been advanced numCallsToNext times.
	 * @param list
	 * @param numCallsToNext
	 * @return Iterator for given list, after numCallsToNext
	 */
	private Iterator<Integer> iteratorAfterNext(DoubleLinkedListADT<Integer> list, int numCallsToNext) {
		Iterator it = list.iterator();
		try{
			for (int i = 0; i < numCallsToNext; i++) {
				it.next();
			}
		}catch(NullPointerException e){
			//throw new RuntimeException(e);
		}
		return it;
	}

	/**
	 * Helper for testing iterators. Return an Iterator that has had remove() called once.
	 * @param iterator
	 * @return same Iterator following a call to remove()
	 */
	private Iterator<Integer> iteratorAfterRemove(Iterator<Integer> iterator) {
		try{
			iterator.remove();
		}catch(NullPointerException e){


		}
		return iterator;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator1(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		
		lt.hasNext();
		lt.next();
		lt.hasNext();
		lt.next();
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator2(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.add(ELEMENT_D);
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator3(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.addToRear(ELEMENT_D);
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator4(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.addToFront(ELEMENT_D);
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator5(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.addToFront(ELEMENT_C);
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator6(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.removeFirst();
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator7(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.removeLast();
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator8(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.add(ELEMENT_C);
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator9(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		list.addToRear(ELEMENT_C);
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator11(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		lt.next();
		lt.remove();
		lt.hasNext();
		return lt;
	}
	
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator13(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		ListIterator<Integer> lt1 = list.listIterator();
		lt.next();
		lt1.next();
		lt.remove();
		try{
			lt1.hasNext();
		}catch (ConcurrentModificationException e){
			
		}
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator14(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		ListIterator<Integer> lt1 = list.listIterator();
		lt.next();
		lt1.next();
		lt.remove();
		try{
			lt1.next();
		}catch (ConcurrentModificationException e){
			
		}
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator15(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		ListIterator<Integer> lt1 = list.listIterator();
		lt.next();
		lt1.next();
		lt.remove();
		try{
			lt1.remove();
		}catch (ConcurrentModificationException e){
			
		}
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator17(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		lt.next();
		lt.remove();
		return lt;
	}
	
	private ListIterator<Integer> testListIterator19(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator();
		lt.next();
		lt.hasPrevious();
		lt.previous();
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator20(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator(0);
		lt.hasPrevious();
		lt.previousIndex();
		lt.nextIndex();
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator21(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator(1);
		lt.previousIndex();
		lt.hasPrevious();
		return lt;
	}
	/**
	 * Helper for testing iterators. Return an list
	 * @param ListIterator
	 * @return list
	 */
	private ListIterator<Integer> testListIterator22(DoubleLinkedListADT<Integer> list){
		ListIterator<Integer> lt = list.listIterator(1);
		lt.hasNext();
		lt.nextIndex();
		return lt;
	}
}// end class ListTester
