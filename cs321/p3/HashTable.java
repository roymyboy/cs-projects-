
/**
 * This class creates the hash table and has functionality to
 * add insert in the table using linear probing or double hashing 
 * @author utroy
 *
 * @param <T>
 */
public class HashTable<T> {
	private HashObject<T>[] array;
	private boolean linearProbing = false;
	private boolean doubleHashing = false;
	private int tableSize = 0;
	private double loadFactor = 0.0;
	private int totalProbes = 0;
	private int duplicate;
	private int totalItems = 0;
	
	
	/**
	 * Constructor
	 * @param size
	 * @param probeType
	 * @param alpha
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size, int probeType, double alpha){
			tableSize = size;
			Prime prime = new Prime(size, 96000);
			tableSize = prime.getTableSize();
			if(probeType == 1){
				linearProbing = true;
			} else {
				doubleHashing = true;
			}
			 loadFactor = alpha;
			 
			 array = new HashObject[tableSize];
	}
	
	
	/**
	 * inserts the item in the table 
	 * keeps track of the duplicate items
	 * @param item
	 * @return
	 */
	public boolean insert(T item){
		HashObject<T> newItem = new HashObject<T>(item);
		String object = String.valueOf(item);
		
		int i = 0;
		int j = 0;
		while(i< this.getTableSize()){
			j = (int)(HashFunction(Math.abs(item.hashCode()), i));
			totalProbes++;
			if (array[j] == null) {
                array[j] = newItem;
                array[j].setProbeCount(i+1);
                totalItems++;
                return true;

            }else if (newItem.compareTo(array[j]) == 0 && object.equals(String.valueOf(array[j].getKey()))) {
                array[j].incrementDuplicateCount();
                totalProbes = totalProbes - (i+1);
                duplicate++;
                return false;
            } else{
            	i++;}
		}
		System.out.println("error");
		return false;
	}
	
	/**
	 * adds object to the table 
	 * keeps tack of number of added items
	 * @param i
	 * @param item
	 */
	public void add(int i, T item){
		HashObject<T> table = new HashObject<T>(item);
		array[i] = table;
		totalItems++;
	} 
	
	/**
	 * @return tableSize
	 */
	protected int getTableSize(){
		return tableSize;
	}
	
	/**
	 * @return loadFactor
	 */
	protected double getLoadFactor(){
		return loadFactor;
	}
	
	/**
	 * @return totalProbes total number of probes 
	 */
	protected int getTotalProbes(){
		return totalProbes;
	}
	
	/**
	 * @return duplicate
	 */
	protected int getTotalDuplicate(){
		return duplicate;
	}
	
	/**
	 * @return totalItems
	 */
	protected int getTotalItemsAdded(){
		return totalItems;
	}
	
	/**
	 * @param k key
	 * @return table index to insert the item through linear probing
	 */
	public long h1(long k){
		return k % this.getTableSize();
	}
	
	/**
	 * @param k
	 * @return table index to insert the item through double hashing
	 */
	public long h2(long k){
		int prime = tableSize - 2;
		long index = (k%prime)+1;
		return index;
	}
	
	/**
	 * @param k
	 * @param t
	 * @return index of table through linear probing or double hashing 
	 */
	public long HashFunction(long k, int t){
		if(linearProbing){
			return (h1(k) + t)%this.getTableSize();
		} else if (doubleHashing){
			return (h1(k) + t*h2(k))%this.getTableSize();
		} else {
			System.out.println("error");
			return 0;
		}
	}
	
	/**
	 * prints table
	 */
	public void printTable(){
		for(int i= 0; i<array.length; i++){
			if(array[i] != null)
			System.out.println("table[" +i + "]:" + array[i].getKey() + " " + array[i].getDublicateCount() +
					" " + array[i].getProbeCount());
		}
	}
	
	/**
	 * prints table
	 */
	public void printTable1(){
		for(int i= 0; i<array.length; i++){
			if(array[i] != null)
			System.out.println("table[" +i + "]:" + array[i].getKey() + " " + array[i].getDublicateCount());
		}
	}
}
