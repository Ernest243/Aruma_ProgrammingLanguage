import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Lexical 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// Testing if the function is working...
		readSource("test_2");
	}
	
	/*
	 * This function will read the source file input from the user
	 */
	
	public static void readSource(String name) throws FileNotFoundException
	{
		// Initialize the Scanner & File utility to read from the user
		Scanner input = new Scanner(new File(name));
		
		//Initialize variable that will hold each line of the file
		String line = "";
		
		// Looping through the file
		while(input.hasNext()) 
		{
			// Save the first line in the line variable
			line = input.nextLine();
			
			
			//Array that will hold each word after removing white space
			String[] tokens = line.split(" ");
			
			// Looping through the array
			for(String toke : tokens) 
			{
				// Check for String
				if(toke.matches(".*[a-zA-Z]")) 
				{
					System.out.println(toke + " => " + "String");
				}
				
				// Check for Integer
				else if(toke.matches(".*[0-9]")) 
				{
					System.out.println(toke + " => " + "Integer");
				}
				
				// Check for special character
				else if(toke.matches(".*[$&+,:;=\\\\?@#|/'<>.^*\"{}()\\[\\]%!-]")) 
				{
					System.out.println(toke + " => " + "Special Character");
				}
			}
			
		}
		
	}
}
