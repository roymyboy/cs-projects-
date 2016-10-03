
/**
 * this class has all the getter and setter 
 * @author utroy
 *
 * @param <T>
 */
public class HashObject<T> {
	private int probeCount;
	private int duplicateCount;
	long hash;
	int tableSize = 0;
	int increaseSize;
	T key;
	
	/**
	 * Constructor 
	 * @param data
	 */
	public HashObject(T data){
		this.key = data;
		hash = data.hashCode();
		duplicateCount = 0;
		probeCount = 0;
		
	}
	
	/**
	 * gets the key value
	 * @return key
	 */
	public T getKey(){
		return key;
	}
	
	/**
	 * gets the count of duplicate
	 * @return duplicateCount
	 */
	public int getDublicateCount(){
		return duplicateCount;
	}
	
	/**
	 * increases the count of duplicate 
	 * @return duplicateCount++
	 */
	public int incrementDuplicateCount(){
		return duplicateCount++;
	}
	
	/**
	 * counts total number of probe 
	 * @param count
	 */
	public void setProbeCount(int count){
		probeCount = count;
	}
	
	/**
	 * 
	 * @return probeCount
	 */
	public int getProbeCount(){
		return probeCount;
	}
	
	public int incrementProbeCount(){
		return probeCount++;
	}
	
	/**
	 * @return
	 */
	private long getHash() {
		return hash;
	}
	
	/**
	 * @param thing
	 * @return
	 */
	public int compareTo(HashObject<T> thing){
		return (int) (this.getHash() - thing.getHash());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		return super.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return super.toString();
	}
}
