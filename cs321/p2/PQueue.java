
/**
 * @author utroy
 */
public class PQueue {
	private MaxHeap heap;
	private int maxSize = 1000000;

	/**
	 * Constructor
	 */
	public PQueue(){
		heap = new MaxHeap(maxSize);
	}

	/**
	 * insert new process in the heap
	 * @param p
	 */
	public void enPQueue(Process p){
		heap.insert(p);
	}

	/**
	 * checks is the heap is empty
	 * @return
	 */
	public boolean isEmpty(){
		return heap.getCount() == 0;
	}

	/**
	 * update if the process is starving 
	 * @param incTime
	 * @param maxLevel
	 */
	public void update(int incTime, int maxLevel){
		heap.increasePriority(incTime, maxLevel);
	}

	/**
	 * removing the process
	 * @return
	 */
	public Process dePQueue() {
		return heap.extractMax();
	}

}
