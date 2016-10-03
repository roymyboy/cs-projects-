/**
 * This class has methods for the driver class cacheTester
 * @author Utsav Roy
 */
import java.util.LinkedList;

public class Cache<T> {
	private LinkedList<T> cache;
	private int hit;
	private int maxSize;

	/**
	 * Constructor 
	 * @param maxSize maximum size of cache
	 */	
	public Cache(int maxSize){
		cache = new LinkedList<T>();
		this.maxSize = maxSize;
	}

	/**
	 * Get the specified object from cache 
	 * @return the selected object 
	 */
	public T getObject(T object){
		return cache.get(cache.indexOf(object));
	}

	/**
	 * Adds the new object to the top of the cache 
	 * every time program is executed
	 * @param object 
	 */
	public void addObject(T object){
		if(cache.contains(object)){
			cache.remove(object);
			hit++;
		} else if (cache.size() >= maxSize) {
			cache.removeLast();
		}
		cache.add(0, object);
	}

	/**
	 * remove the specific object from the cache
	 * @param object
	 */
	public void removeObject(T object){
		cache.remove(object);
	}

	/**
	 * Clear the entire cache
	 */
	public void clearCache(){
		cache.clear();
	}

	/**
	 * returns the number of time the program finds the object in cache
	 * @param return 
	 */
	public int getHit(){
		return hit;
	}

	/**
	 * it returns the size of the cache with total number of object
	 * @param return
	 */ 	
	public int size(){
		return cache.size();
	}
}
