public class MagicSquareTester {
	
	public static void main(String[] args)
	{
		MagicSquare f; 
		String file;
		
		//reading from the command line
		if (args[0].equals("-check") && args.length == 2)
		{
			file = args[1];
			f = new MagicSquare(file, 0);
			f.checkMatrix();
			
		}
		else if(args[0].equals("-create") && args.length == 3)
		{
			try
			{
				int size = Integer.parseInt(args[2]);
				file = args[1];
				// if else statement to make sure the matrix size is odd
				if (size % 2 == 0)
				{
					throw new RuntimeException("n must be odd");
				}
				else
				{
					f = new MagicSquare(file, 0);
					f.createMagicSquare(size);
				}

			}catch (NumberFormatException e)
			{
				System.out.println("Inuput must be a number");
			}
		}
	
	}
}
