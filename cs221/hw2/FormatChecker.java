//package a2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
public class FormatChecker
{
	public static void main(String[] args)throws IOException 
	{
		if(args.length==0) 
		{
			System.out.println("Empty command line arguments!");
		}
		else 
		{
			for (int i = 0; i < args.length; i++) 
			{
				BufferedReader br = null;// Making flag to true at the beginning of loop
				boolean isValid = true;
				try 
				{
					String sCurrentLine;
					br = new BufferedReader(new FileReader(args[i]));
					double rows = 0.0, columns = 0.0, line = 0.0, parsedRows = 0.0;
					while ((sCurrentLine = br.readLine()) != null) 
					{
						// Extract the number of rows and columns

						if (line == 0) 
						{
							String ins[] = sCurrentLine.split(" ");
							if (ins.length != 2)
							{
								isValid = false;
								break;
							}
							rows = Integer.parseInt(ins[0]);
							columns = Integer.parseInt(ins[1]);
						} else 
						{
							String insVal[] = sCurrentLine.split(" ");
							// check for each row validity
							if (insVal.length != columns) 
							{
								isValid = false;
								break;}
							for (int j = 0; j < insVal.length; j++) 
							{
								Double.parseDouble(insVal[j]);
							}
							parsedRows++;
						}
						line++;
					}
					// check for each columns validity
					if (rows != parsedRows)
					{
						isValid = false;
					} 
				}
				catch (FileNotFoundException e) 
				{
					System.out.println("File not found");
					isValid = false;
				}
				catch (NumberFormatException n)
				{
					System.out.println(n);
					isValid = false;
				}
				catch(InputMismatchException t)
				{
					System.out.println(t);
					isValid = false;
				}
				finally 
				{
					try 
					{
						if (br != null)
							br.close();
					} 
					catch (IOException ex) 
					{
						isValid = false;
					}
				}
				System.out.println(args[i]);
				if (isValid == true)
				{
					System.out.println("VALID");
					System.out.println();
				} 
				else 
				{
					System.out.println("INVALID");
					System.out.println("Out of format");
					System.out.println();
				}
			}
		}
	}
}
