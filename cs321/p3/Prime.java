import java.math.*;

/**
 * @author utroy
 *
 */
public class Prime{
    private int prime;
    private int size;

    /**
     * @param lowerNum
     * @param higherNum
     */
    public Prime(int lowerNum, int higherNum){
        prime = find(lowerNum, higherNum);
        size = prime + 2;
    }

    /**
     * @param lowerNum
     * @param higherNum
     * @return
     */
    public int find(int lowerNum, int higherNum){
        int val;
        BigInteger testInt;

        for(int i = lowerNum; i <= higherNum; i++){
            testInt = new BigInteger(String.valueOf(i));
            boolean primeNum = testInt.isProbablePrime(20);
            if(primeNum){
            	int prime2 = i +2;
            	testInt = new BigInteger(String.valueOf(prime2));
            	primeNum = testInt.isProbablePrime(25);
            	if(primeNum){
            		val = i;
            		return val;
            	}
            	
            }
        }
        System.out.println("No prime value found in the given range");
        return 0;
    }
    
    /**
     * @return
     */
    public int getPrime(){
    	return prime;
    }
    
    /**
     * @return
     */
    public int getTableSize(){
    	return size;
    }
    


}
