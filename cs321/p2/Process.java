

/**
 * Class to implement process to the job 
 * @author utroy
 */
public class Process implements Comparable<Process>{
	private int priorityLevel, time, processTime, timeNotProcessed;

	/**
	 * Constructor
	 * @param currTime
	 * @param maxProcessTime
	 * @param maxLevel
	 */
	public Process(int currTime, int maxProcessTime, int maxLevel){
		time = currTime;
		processTime = maxProcessTime;
		priorityLevel = maxLevel;
		timeNotProcessed = 1;
	}

	/* 
	 * compare the process level and time
	 * @param p
	 * @return 1 or -1
	 */
	public int compareTo(Process p) {
		if (this.priorityLevel == p.priorityLevel){
			if(this.time < p.time){
				return 1;
			} else { 
				return -1;
			}
		} else { 
			if(this.priorityLevel > p.priorityLevel){
				return 1;
			}	else {
				return -1;
			}
		}
	}

	/**
	 * returns time remaining 
	 * @return processTime
	 */
	public int getTimeRemaining(){
		return processTime;
	}

	/**
	 * returns the priority level
	 * @return priorityLevel
	 */
	public int getPriority(){
		return priorityLevel;
	}

	/**
	 * increments the priority level of the process
	 */
	public void increasePriorityLevel(){
		priorityLevel++;
	}

	/**
	 * decrements the time remaining for the process 
	 * @return processTime
	 */
	public int reduceTimeRemaining(){
		return processTime--;
	}

	/**
	 * returns the time not processed 
	 * @return timeNotProcessed
	 */
	public int timeNotProcessed(){
		return timeNotProcessed;
	}

	/**
	 * increments the time not processed 
	 */
	public void increaseTimeNotprocessed(){
		timeNotProcessed++;
	} 

	/**
	 * resets the old time
	 */
	public void resetTimeNotProcessed() {
		timeNotProcessed = 1;

	}

	/**
	 * check's if the process is completed or not 
	 * @return
	 */
	public boolean finish() {
		if(processTime == 0){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns the arrival time of the process
	 * @return time
	 */
	public int getArrivalTime(){	
		return time;
	}

}
/**
 * Class to implement process to the job 
 * @author utroy
 */
public class Process implements Comparable<Process>{
	private int priorityLevel, time, processTime, timeNotProcessed;

	/**
	 * Constructor
	 * @param currTime
	 * @param maxProcessTime
	 * @param maxLevel
	 */
	public Process(int currTime, int maxProcessTime, int maxLevel){
		time = currTime;
		processTime = maxProcessTime;
		priorityLevel = maxLevel;
		timeNotProcessed = 1;
	}

	/* 
	 * compare the process level and time
	 * @param p
	 * @return 1 or -1
	 */
	public int compareTo(Process p) {
		if (this.priorityLevel == p.priorityLevel){
			if(this.time < p.time){
				return 1;
			} else { 
				return -1;
			}
		} else { 
			if(this.priorityLevel > p.priorityLevel){
				return 1;
			}	else {
				return -1;
			}
		}
	}

	/**
	 * returns time remaining 
	 * @return processTime
	 */
	public int getTimeRemaining(){
		return processTime;
	}

	/**
	 * returns the priority level
	 * @return priorityLevel
	 */
	public int getPriority(){
		return priorityLevel;
	}

	/**
	 * increments the priority level of the process
	 */
	public void increasePriorityLevel(){
		priorityLevel++;
	}

	/**
	 * decrements the time remaining for the process 
	 * @return processTime
	 */
	public int reduceTimeRemaining(){
		return processTime--;
	}

	/**
	 * returns the time not processed 
	 * @return timeNotProcessed
	 */
	public int timeNotProcessed(){
		return timeNotProcessed;
	}

	/**
	 * increments the time not processed 
	 */
	public void increaseTimeNotprocessed(){
		timeNotProcessed++;
	} 

	/**
	 * resets the old time
	 */
	public void resetTimeNotProcessed() {
		timeNotProcessed = 1;

	}

	/**
	 * check's if the process is completed or not 
	 * @return
	 */
	public boolean finish() {
		if(processTime == 0){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns the arrival time of the process
	 * @return time
	 */
	public int getArrivalTime(){	
		return time;
	}

}
