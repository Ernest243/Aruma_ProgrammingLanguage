import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
		// Initialize the HashMap
		HashMap<Integer, String> dataType = new HashMap<Integer, String>();
		
		// Registering different code OP in the hash
		dataType.put(1, "add_OP");
		dataType.put(2, "sub_OP");
		dataType.put(3, "div_OP");
		dataType.put(4, "mul_OP");
		dataType.put(5, "mod_OP"); 
		
		try 
		{
			// Initialize the Scanner & File utility to read from the user
			Scanner input = new Scanner(new File(name));
			
			//Initialize variable that will hold each line of the file
			String line = "";
			
			// Looping through the file
			while(input.hasNext()) 
			{
				// Save the line in the line variable
				line = input.nextLine();
				
				
				//Array that will hold each word after removing white space
				String[] lexemes = line.split(" ");
				
				// Looping through the array
				for(String lex : lexemes) 
				{
					// Check for String
					if(lex.matches(".*[a-zA-Z]")) 
					{
						System.out.println(lex + " => " + "String");
					}
					
					// Check for Integer
					else if(lex.matches(".*[0-9]")) 
					{
						System.out.println(lex + " => " + "Integer");
					}
					
					// Check for special character
					else if(lex.matches(".*[$&+,:;=\\\\?@#|/'<>.^*\"{}()\\[\\]%!-]")) 
					{
						if(lex.matches(".*[\\+]")) 
						{
							System.out.println(lex + " => " + "ADD_OP");
						}
						else if(lex.matches(".*[-]")) 
						{
							System.out.println(lex + " => " + "SUB_OP");
						}
						else if(lex.matches(".*[\\*]")) 
						{
							System.out.println(lex + " => " + "MUL_OP");
						}
						else if(lex.matches(".*[/]")) 
						{
							System.out.println(lex + " => " + "DIV_OP");
						}
						else if(lex.matches(".*[%]")) 
						{
							System.out.println(lex + " => " + "MOD_OP");
						}
						else
							System.out.println(lex + " => " + "Special Character");
					}
				}
				
			}
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println("File not found!");
		}
	}
}