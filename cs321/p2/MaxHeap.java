
/**
 * Max-Heap implementation
 * @author utroy
 */
public class MaxHeap {
		private Process[] array;
		private int countSize;
		private int maxSize;

		/**
		 * constructor
		 * @param maxSize
		 */
		public MaxHeap(int maxSize) {
				this.maxSize = maxSize;
				countSize = 0;
				array = new Process[maxSize];
		}

		/**
		 * insert the value into heap 
		 * @param p
		 */
		public void insert(Process p) {
				if(countSize == maxSize){
						maxSize *= 2;
						Process[] tmp = new Process[maxSize];
						for(int i= 0; i < array.length; i++){
								tmp[i] = array[i];
						}
						array = tmp;
				}
				array[countSize] = p;
				maxHeapUp(countSize);
				countSize++;
		}

		/**
		 * put elements in the current position
		 * @param index
		 */
		private void maxHeapUp(int index) {
				while((index >0) && (array[parent(index)].compareTo(array[index]) < 0)){
						Process tmp = array[parent(index)];
						array[parent(index)] = array[index];
						array[index] = tmp;
						index = parent(index);
				}
		}

		/**
		 * put elements in the current position
		 * @param p
		 * @param index
		 */
		private void maxHeapDown(Process[] p, int index){
				int largeChild;
				int rightChild = 2*index + 2;
				int leftChild = 2*index +1;
				if(leftChild < countSize && (array[leftChild].compareTo(array[index]) > 0)){
						largeChild = leftChild;
				} else {
						largeChild = index;
				}
				if(rightChild < countSize && (array[rightChild].compareTo(array[largeChild]) > 0)){
						largeChild = rightChild;
				}
				if (largeChild != index) {
						Process tmp = array[largeChild];
						array[largeChild] = array[index];
						array[index] = tmp;
						maxHeapDown(array, largeChild);
				}
		}


		/**
		 * retun position for the parent of position
		 * @param pos
		 * @return
		 */
		public int parent(int pos){
				return (pos-1)/2 ;
		}

		/**
		 * return position for the left child of position 
		 * @param pos
		 * @return
		 */
		public int leftChild(int pos){
				return (2*pos+1);
		}

		/**
		 * return position for the right child of position
		 * @param pos
		 * @return
		 */
		public int rightChild(int pos){
				return (2*pos + 2);
		}

		/**
		 * return size of the array
		 * @return
		 */
		public int getCount() {
				return countSize;
		}

		/**
		 * increase the priority of the starving process
		 * assign new process time and level
		 * @param incTime
		 * @param maxLevel
		 */
		public void increasePriority(int incTime, int maxLevel) {
				int val, timeNotProcessed;
				for (int i = 0; i < getCount(); i++){
						val = array[i].getPriority();
						timeNotProcessed = array[i].timeNotProcessed();
						if(val < maxLevel && (timeNotProcessed == incTime)){
								array[i].increasePriorityLevel();
								array[i].resetTimeNotProcessed();
								maxHeapUp(i);
						} else {
								array[i].increaseTimeNotprocessed();
						}
				}
		}

		/**
		 * remove and return maximum value
		 * @return
		 */
		public Process extractMax() {
				Process tmp = array[0];
				countSize--;
				array[0] = array[getCount()];
				maxHeapDown(array, 0);
				return tmp;
		}

}
