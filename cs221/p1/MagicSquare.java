import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class file reads the magic square and checks the validity
 * or create the magic square in a separate text file
 * @author utroy
 */
public class MagicSquare 
{

	private int n = 0;
	private int [][] square;
	private int row;
	private int col;
	private int formula;
	private File file = null;
	private boolean checkRow = false, checkCol = false, checkDiag1 = false, checkDiag2 = false;
	
	/**
	 * constructor that creates magic square in a file of size n
	 * @param filename
	 * @param n
	 */

	public MagicSquare(String filename, int n)
	{
		file = new File(filename);
		this.n = n;
	}

	/**
	 * method to use private readMatrix
	 */

	public void checkMatrix()
	{
		readMatrix(file);

	}
	
	/**
	 * method  to read and check the validity of the matrix
	 * @param inputFile
	 */
	
	private void readMatrix(File inputFile) 
	{
		try {

			Scanner fileScan = new Scanner(inputFile);

			n = fileScan.nextInt();
			square = new int[n][n];
			while (fileScan.hasNext())
			{
				String line = fileScan.nextLine(); 
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						square[i][j] = fileScan.nextInt();
					}
				}
			}

		} catch (FileNotFoundException ffe) {

			System.out.println("File not found");
			ffe.printStackTrace();
			System.exit(1);
		} catch (InputMismatchException ime) {

            System.out.println("File does not contain integers.");
            ime.printStackTrace();
            System.exit(1);
        }


		//formula to calculate the magic number
		formula = (n*((n*n)+1))/2;
		//square = new int[n][n];
		printMatrix(square);
		
		// check the sum of each row
		
		for(int i = 0; i < n; i++)
		{
			int sumRow = 0;
			for(int j = 0; j < square[i].length; j++)
			{
				sumRow += square[i][j];
			}
			if(sumRow != formula)
			{
				checkRow= false;
			}
			else
			{
				checkRow = true;
			}
		}

		//check the sum of the each column
		for(int j = 0; j < square[0].length; j++)
		{
			int sumCol = 0;
			for(int i = 0; i < n; i++)
			{
				sumCol += square[i][j];
			}
			if(sumCol != formula)
			{
				checkCol= false;
			}
			else
			{
				checkCol = true;
			}
		}
		//check the diagonal from top left to right bottom corner
		int sumDiag1 = 0;
		for (int i = 0; i < n; i++) 
		{ 
			sumDiag1 += square[i][i];	
		}
		if (sumDiag1 != formula) 
		{
			checkDiag1 = false;
		}
		else
		{
			checkDiag1 = true;
		}

		//Check the diagonal from right top to left down corner
		int sumDiag2 = 0;
		for (int i = 0; i < n; i++) 
		{ 
			sumDiag2 += square[i][n-i-1];
		}

		if (sumDiag2 != formula) 
		{
			checkDiag2 = false;
		} 
		else
		{
			checkDiag2 = true;
		}
		

		if (checkRow && checkCol && checkDiag1 && checkDiag2 )
		{
			System.out.println("is a magic square");
		}
		else 
		{
			System.out.println("is not a magic square");
		}
	}


	/**
	 * method to create magic square which takes integer as the size
	 * @param size
	 */
	
	public void createMagicSquare(int size)
	{
		this.n = size;
		square = new int[n][n];
		for(int i=0; i < n; i++)
		{
			for (int j =0; j < n; j++)
			{
				square[i][j] = 0;
			}
		}
		row = n-1;
		col = n/2;
		for(int i = 1; i <= n*n; i++)
		{
			square[row][col] = i;

			if (square[(row + 1)% n][(col +1) % n] == 0)
			{
				row = (row + 1) % n;
				col = (col + 1) % n;
			}
			else 
			{
				row = (row - 1 + n) % n;
			}
		}
		writeMatrix(file, square);

	}

	/**
	 * method to write and the magic square in a file 
	 * @param filename
	 * @param square
	 */

	private void writeMatrix(File filename, int square[][])
	{
		try
		{
			PrintWriter outFile = new PrintWriter(filename);
			outFile.printf("%2d\n", square.length);

			//print magic square
			for(int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					outFile.printf("%2d ", square[i][j]);
				}
				outFile.println();
			}
			outFile.close();
		} catch (IOException e) 
		{
			System.out.println("IOException found");
			e.printStackTrace();
		}
	}
	
	/**
	 * method to write the matrix on the console
	 * @param square
	 */
	
	private void printMatrix(int square[][])
	{
		System.out.println("The matrix: ");
		for( int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				System.out.print(square[i][j] + " ");
			}
			System.out.println();
		}
	}

}
