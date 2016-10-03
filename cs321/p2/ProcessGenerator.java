
import java.util.Random;

/**
 * This class generates process upon the new arrival of the job
 * @author utroy
 */
public class ProcessGenerator {
	private double probability;
	Random rnd = new Random();

	/**
	 * Constructor
	 * @param probability 
	 */
	public ProcessGenerator(double probability) {
		this.probability = probability;
	}

	/**
	 * function to assign level time and current time to the job 
	 * @param currentTime
	 * @param maxProcessTime
	 * @param maxLevel
	 * @return new process
	 */
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) {
		int processTime;
		int priorityLevel;

		processTime = rnd.nextInt(maxProcessTime) + 1;
		priorityLevel = rnd.nextInt(maxLevel) +1;
		return new Process(currentTime, processTime, priorityLevel);
	}

	/**
	 * checks the value of the probability 
	 * @return 
	 */
	public boolean query() {
		if(rnd.nextDouble() <= probability){
			return true;
		} else {
			return false;
		}
	}
}
