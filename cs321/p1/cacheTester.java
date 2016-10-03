/** 
 * This class is the driver class for cache class. It implements the
 * methods from cache class
 * @author Utsav Roy
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class cacheTester {
	private static int wordCount;
	private static int totalHit, hit1, hit2;

	public static void fail(){
		System.out.println("\nUsage: Java cacheTester 1 <cache size> <input textfile name>"
				+ "\nor\njava cacheTester 2 <1st-level cache size> <2nd-level cache size> "
				+ "<input textfile name>\n");
	}

	/**
	 * reads the file and the tokens from the file 
	 * counts all the object adds to both first level cache. 
	 * Increment the hit count
	 * @param num
	 * @param cachesize
	 * @param fileName
	 */
	private static void cache1(int num,int cacheSize, String fileName){
		Cache<String> cache1 = new Cache<>(cacheSize);
		File file = new File(fileName);
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String word = scan.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(word, " \t");
				while (tokenizer.hasMoreTokens()) {
					String str = tokenizer.nextToken();
					wordCount++;
					cache1.addObject(str);
				}
			}
			scan.close();
			System.out.println("First level cache with " + cacheSize + " entries has been created");
			hit1 = cache1.getHit();
		} catch (FileNotFoundException ee){
			System.out.println("File not found please try again\n");
			System.exit(0);
		}
	}

	/**
	 * reads the file and differenciates the tokens from the file 
	 * counts all the object adds to both level cache. Increment the hit count
	 * @param num
	 * @param cacheSize1
	 * @param cacheSize2
	 * @param fileName
	 */
	private static void cache2(int num, int cacheSize1, int cacheSize2, String fileName){
		Cache<String> cache1 = new Cache<>(cacheSize1);
		Cache<String> cache2 = new Cache<>(cacheSize2);
		File file = new File(fileName);
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String word = scan.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(word, " \t");
				while (tokenizer.hasMoreTokens()) {
					String str = tokenizer.nextToken();
					wordCount++;
					cache1.addObject(str);
					cache2.addObject(str);
				}
			}
			scan.close();
			System.out.println("First level cache with " + cacheSize1 + " entries has been created");
			System.out.println("Second level cache with " + cacheSize2 + " entries has been created");
			hit1 = cache1.getHit();
			totalHit = cache2.getHit();
			hit2 = totalHit-hit1;
		} catch (FileNotFoundException ee){
			System.out.println("File not found please try again\n");
			System.exit(0);
		}
	}

	/**
	 * has the format for the first level cache 
	 */
	private static void results1(){
		System.out.println("......................................\n" +
				"Total number of references: "+wordCount+"\n"+
				"Total number of cache hits: " + hit1 +"\n" +
				"The global hit ratio: " +(double) hit1/wordCount);

	}

	/**
	 * has the format for the second level cache
	 */
	private static void results2(){
		System.out.println(".....................................\n" +
				"Total number of references: "+wordCount +"\n" +
				"Total number of cache hits: " + totalHit+"\n"+
				"The global hit ratio: " +(double) totalHit/wordCount + "\n"+
				"Number of 1st-level cache hits: " + hit1 + "\n" +
				"1st-level cache hit ratio: " + (double) hit1/wordCount +"\n" +
				"Number of 2nd-level cache hits: " +hit2 +"\n"+
				"2nd-level cache hit ratio: " +(double) hit2/(wordCount-hit1));
	}

	/**
	 * the main method to run the program
	 * @param args 
	 */
	public static void main(String args[]){
		if(args.length == 3){
			if(Integer.parseInt(args[0]) ==1) {
				cache1(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
				results1();
			}else{
				fail();		
			}
		}
		else if(args.length == 4){
			if(Integer.parseInt(args[0]) == 2 ){
				if(Integer.parseInt(args[1]) < Integer.parseInt(args[2])){
					cache2(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]), args[3]);
					results2();
				}else{
					System.out.println("\n2nd-level cache size must be bigger than 1st-level cache size. Please try again");
					fail();
				}
			}else{
				fail();
			}
		} else {
			fail();
		}
	}
}
