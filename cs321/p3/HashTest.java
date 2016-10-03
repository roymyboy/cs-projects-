import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author utroy
 */
public class HashTest {
	public static void main(String[]args){
		boolean data1 = false;
		boolean data2 = false;
		boolean data3 = false;
		double loadFactor = 0;
		boolean debugLevel0 = false;
		boolean debugLevel1 = false;
		boolean debugLevel2 = false;
		int num = 0;
		int prog = 0;
				
		if(args.length < 2){
			Usage();
		}
		
		//generate data according to the input at args 0
		if(Integer.parseInt(args[0]) == 1){
			data1 = true; //random data
		} else if(Integer.parseInt(args[0]) == 2) {
			data2 = true; //system time data
		} else if(Integer.parseInt(args[0]) == 3){
			data3 = true; //word list data
		} else {
			Usage();
		}
		
		//load factor at args 1
		if(Double.parseDouble(args[1]) < 3 && (Double.parseDouble(args[1]) > 0)){
			loadFactor = Double.parseDouble(args[1]);
		} else {
			Usage();
		}
		
		//debug level according to the input at args 2
		if(args.length == 2){
			debugLevel0 = true; //start the program
		} else if(args.length == 3){
			if(Integer.parseInt(args[2]) == 0){
				debugLevel0 = true; //print the summary of the experiment
			} else if(Integer.parseInt(args[2]) == 1){
				debugLevel1 = true; //print the hash table at the end
			} else if(Integer.parseInt(args[2]) == 2){
				debugLevel2 = true; //print the number of probes for each new insert
			} else {
				Usage();
			}
		} else {
			Usage();
		}
		
		HashTable<Integer> table = new HashTable<Integer>(95500, 1, loadFactor);
		HashTable<Integer> table1 = new HashTable<Integer>(95500, 2, loadFactor);
		
		HashTable<Long> lTable = new HashTable<Long>(95500, 1, loadFactor);
		HashTable<Long> lTable1 = new HashTable<Long>(95500, 2, loadFactor);
		
		HashTable<String> sTable = new HashTable<String>(95500, 1, loadFactor);
		HashTable<String> sTable1 = new HashTable<String>(95500, 2, loadFactor);
	
		
		//Random Data Program
		if(data1){
			num = (int) (loadFactor*table.getTableSize());
			boolean startProg = false;
			Random rand = new Random();
			
			while(prog < num){
				int number = Math.abs(rand.nextInt());
				if(prog < num){
					startProg = table.insert(number);
					table1.insert(number);
					if(startProg){
						prog++;
					}
				}
			}
		}
		
		//System Time Data Program
		if(data2){
			num = (int) (loadFactor*lTable.getTableSize());
			while(prog < num){
				long number = System.currentTimeMillis();
				if(prog < num){
					lTable.insert(number);
					lTable1.insert(number);
					prog = lTable.getTotalItemsAdded();
				}
			}
		}
		
		//Word List Data Program
		if(data3){
			num = (int) (loadFactor*sTable.getTableSize());
			Scanner scan = null;
			String fileName = "word-list";
			try {
				scan = new Scanner(new File(fileName));
			} catch (FileNotFoundException e){
				System.out.println("File not found");
			}
			
			while(prog < num && scan.hasNext()){
				String nextLine = scan.nextLine();
				if(prog < num){
					sTable.insert(nextLine);
					sTable1.insert(nextLine);
					prog = sTable.getTotalItemsAdded();
				}
			}
		}
		
		//nested Debug levels and data type as passed in by the user 
		if(debugLevel0){
			if(data1){
				  System.out.println("A good table size is found: " + table.getTableSize());
                  System.out.println("Data source type: random number generator");
                  System.out.println();
                  System.out.println("Using Linear Hashing...");
                  System.out.println("Inserted " + table.getTotalItemsAdded() + " elements, of which " + table.getTotalDuplicate() + " duplicates");
                  double averageProbe1 =  (double)table.getTotalProbes()/(double)table.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                  System.out.println();
                  System.out.println("Using Double Hashing...");
                  System.out.println("Inserted " + table1.getTotalItemsAdded() + " elements, of which " + table1.getTotalDuplicate() + " duplicates");
                  double averageProbe2 =  (double) table1.getTotalProbes()/(double)table1.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);
			} else if(data2) {
				  System.out.println("A good table size is found: " + lTable.getTableSize());
                  System.out.println("Data source type: system time generator" );
                  System.out.println();
                  System.out.println("Using Linear Hashing...");
                  System.out.println("Inserted " + lTable.getTotalItemsAdded() + " elements, of which " + lTable.getTotalDuplicate() + " duplicates");
                  double averageProbe1 =  (double)lTable.getTotalProbes()/(double)lTable.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                  System.out.println();
                  System.out.println("Using Double Hashing...");
                  System.out.println("Inserted " + lTable1.getTotalItemsAdded() + " elements, of which " + lTable1.getTotalDuplicate() + " duplicates");
                  double averageProbe2 =  (double) lTable1.getTotalProbes()/(double)lTable1.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);
			} else if (data3){
				  System.out.println("A good table size is found: " + sTable.getTableSize());
                  System.out.println("Data source type:  generated from word list");
                  System.out.println();
                  System.out.println("Using Linear Hashing...");
                  System.out.println("Inserted " + sTable.getTotalItemsAdded() + " elements, of which " + sTable.getTotalDuplicate() + " duplicates");
                  double averageProbe1 =  (double)sTable.getTotalProbes()/(double)sTable.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                  System.out.println();
                  System.out.println("Using Double Hashing...");
                  System.out.println("Inserted " + sTable1.getTotalItemsAdded() + " elements, of which " + sTable1.getTotalDuplicate() + " duplicates");
                  double averageProbe2 =  (double) sTable1.getTotalProbes()/(double)sTable1.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);
			}
		} else if(debugLevel1){
			if(data1){
				  System.out.println("This is the linear table: ");
                  table.printTable1();
                  System.out.println();
                  System.out.println("This is the double hashed table: ");
                  table1.printTable1();
                  System.out.println();
                  System.out.println();

                  System.out.println("A good table size is found: " + table.getTableSize());
                  System.out.println("Data source type: random number generator");
                  System.out.println();
                  System.out.println("Using Linear Hashing...");
                  System.out.println("Inserted " + table.getTotalItemsAdded() + " elements, of which " + table.getTotalDuplicate() + " duplicates");
                  double averageProbe1 =  (double)table.getTotalProbes()/(double)table.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                  System.out.println();
                  System.out.println("Using Double Hashing...");
                  System.out.println("Inserted " + table1.getTotalItemsAdded() + " elements, of which " + table1.getTotalDuplicate() + " duplicates");
                  double averageProbe2 =  (double) table1.getTotalProbes()/(double)table1.getTotalItemsAdded();
                  System.out.println("total probes: " + table1.getTotalProbes());
                  System.out.println("total items added: " + table1.getTotalItemsAdded());
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);
			} else if(data2){
				  System.out.println("This is the linear table: ");
                  lTable.printTable1();
                  System.out.println();
                  System.out.println("This is the double hashed table: ");
                  lTable1.printTable1();
                  System.out.println();
                  System.out.println();

                  System.out.println("A good table size is found: " + lTable.getTableSize());
                  System.out.println("Data source type: system time generator" );
                  System.out.println();
                  System.out.println("Using Linear Hashing...");
                  System.out.println("Inserted " + lTable.getTotalItemsAdded() + " elements, of which " + lTable.getTotalDuplicate() + " duplicates");
                  double averageProbe1 =  (double)lTable.getTotalProbes()/(double)lTable.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                  System.out.println();
                  System.out.println("Using Double Hashing...");
                  System.out.println("Inserted " + lTable1.getTotalItemsAdded() + " elements, of which " + lTable1.getTotalDuplicate() + " duplicates");
                  double averageProbe2 =  (double) lTable1.getTotalProbes()/(double)lTable1.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);
			} else if(data3){
				 System.out.println("This is the linear table: ");
                 sTable.printTable();
                 System.out.println();
                 System.out.println("This is the double hashed table: ");
                 sTable1.printTable1();
                 System.out.println();
                 System.out.println();

                 System.out.println("A good table size is found: " + sTable.getTableSize());
                 System.out.println("Data source type:  generated from word list");
                 System.out.println();
                 System.out.println("Using Linear Hashing...");
                 System.out.println("Inserted " + sTable.getTotalItemsAdded() + " elements, of which " + sTable.getTotalDuplicate() + " duplicates");
                 double averageProbe1 =  (double)sTable.getTotalProbes()/(double)sTable.getTotalItemsAdded();
                 System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                 System.out.println();
                 System.out.println("Using Double Hashing...");
                 System.out.println("Inserted " + sTable1.getTotalItemsAdded() + " elements, of which " + sTable1.getTotalDuplicate() + " duplicates");
                 double averageProbe2 =  (double) sTable1.getTotalProbes()/(double)sTable1.getTotalItemsAdded();
                 System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);

			}
		} else if (debugLevel2){
			if(data1){
				 System.out.println("This is the linear table: ");
                 table.printTable();
                 System.out.println();
                 System.out.println("This is the double hashed table: ");
                 table1.printTable();
                 System.out.println();
                 System.out.println();

                 System.out.println("A good table size is found: " + table.getTableSize());
                 System.out.println("Data source type: random number generator");
                 System.out.println();
                 System.out.println("Using Linear Hashing...");
                 System.out.println("Inserted " + table.getTotalItemsAdded() + " elements, of which " + table.getTotalDuplicate() + " duplicates");
                 double averageProbe1 =  (double)table.getTotalProbes()/(double)table.getTotalItemsAdded();
                 System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                 System.out.println();
                 System.out.println("Using Double Hashing...");
                 System.out.println("Inserted " + table1.getTotalItemsAdded() + " elements, of which " + table1.getTotalDuplicate() + " duplicates");
                 double averageProbe2 =  (double) table1.getTotalProbes()/(double)table1.getTotalItemsAdded();
                 System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);
			} else if (data2){
				  System.out.println("This is the linear table: ");
                  lTable.printTable();
                  System.out.println();
                  System.out.println("This is the double hashed table: ");
                  lTable1.printTable();
                  System.out.println();
                  System.out.println();

                  System.out.println("A good table size is found: " + lTable.getTableSize());
                  System.out.println("Data source type: system time generator" );
                  System.out.println();
                  System.out.println("Using Linear Hashing...");
                  System.out.println("Inserted " + lTable.getTotalItemsAdded() + " elements, of which " + lTable.getTotalDuplicate() + " duplicates");
                  double averageProbe1 =  (double)lTable.getTotalProbes()/(double)lTable.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                  System.out.println();
                  System.out.println("Using Double Hashing...");
                  System.out.println("Inserted " + lTable1.getTotalItemsAdded() + " elements, of which " + lTable1.getTotalDuplicate() + " duplicates");
                  double averageProbe2 =  (double) lTable1.getTotalProbes()/(double)lTable1.getTotalItemsAdded();
                  System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);
			} else if(data3){
				   System.out.println("This is the linear table: ");
                   sTable.printTable();
                   System.out.println();
                   System.out.println("This is the double hashed table: ");
                   sTable1.printTable1();
                   System.out.println();
                   System.out.println();

                   System.out.println("A good table size is found: " + sTable.getTableSize());
                   System.out.println("Data source type:  generated from word list");
                   System.out.println();
                   System.out.println("Using Linear Hashing...");
                   System.out.println("Inserted " + sTable.getTotalItemsAdded() + " elements, of which " + sTable.getTotalDuplicate() + " duplicates");
                   double averageProbe1 =  (double)sTable.getTotalProbes()/(double)sTable.getTotalItemsAdded();
                   System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe1);
                   System.out.println();
                   System.out.println("Using Double Hashing...");
                   System.out.println("Inserted " + sTable1.getTotalItemsAdded() + " elements, of which " + sTable1.getTotalDuplicate() + " duplicates");
                   double averageProbe2 =  (double) sTable1.getTotalProbes()/(double)sTable1.getTotalItemsAdded();
                   System.out.println("total probes: " + sTable1.getTotalProbes());
                   System.out.println("total items added: " + sTable1.getTotalItemsAdded());
                   System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + averageProbe2);

			}
		}
		
	}
	
	//usage message
	public static void Usage(){
		System.out.println("java HashTest <input type> <load factor> [<debug level>]");
		System.exit(1);
	}
}
