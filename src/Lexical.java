import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Lexical 
{
	public static void main(String[] args) throws IOException
	{
		// Testing if the function is working...
		readSource("arumaTest");
	}
	
	/*
	 * This function will read the source file input from the user, 
	 * generate tokens & print them out to a new file that will be returned
	 * as the output of this function.
	 */
	
	public static FileWriter readSource(String name) throws IOException
	{	
		/*
		 * Initializing output file containing tokens to be read by the syntax analyzer
		 */
		
		FileWriter tokens = new FileWriter("outputToken.txt");
		PrintWriter writeToFile = new PrintWriter(tokens);
		ArrayList<String> tokensOutput = new ArrayList<String>();
		
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
					// Check for String Data Type
					if(lex.matches(".*[a-zA-Z]")) 
					{
						if(lex.matches("[Litteral]*")) 
						{
							tokensOutput.add("(LITTERAL)");
						}
						
						if(lex.matches("[Number]*")) 
						{
							tokensOutput.add("(NUMBER)");
						}
						
						if(lex.matches("[Real_Number]*")) 
						{
							tokensOutput.add("(REAL_NUMBER)");
						}
						
						if(lex.matches("[Boolean]*")) 
						{
							tokensOutput.add("(BOOLEAN)");
						}
						
						// Check for boolean value
						if(lex.matches("[[Tt]true|[Ff]false]*")) 
						{
							tokensOutput.add("!VALUE_BOOLEAN");
						}
						
					}
					
					// Check for integer_value
					else if(lex.matches("[+-]?[0-9][0-9]*")) 
					{
						tokensOutput.add("!VALUE_NUMBER");
					}
					
					// Check for float_value
					else if(lex.matches("[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?")) 
					{
						tokensOutput.add("!VALUE_REAL_NUMBER");
					}
					
					// Check for special character
					else if(lex.matches(".*[$&+,:;=\\\\?@#|/'<>.^*\"{}()\\[\\]%!-]")) 
					{
						if(lex.matches(".*[\\+]")) 
						{
							tokensOutput.add("[ADD_OP]");
						}
						else if(lex.matches(".*[-]")) 
						{
							tokensOutput.add("[SUB_OP]");
						}
						else if(lex.matches(".*[\\*]")) 
						{
							tokensOutput.add("[MUL_OP]");
						}
						else if(lex.matches(".*[/]")) 
						{
							tokensOutput.add("[DIV_OP]");
						}
						else if(lex.matches(".*[%]")) 
						{
							tokensOutput.add("[MOD_OP]");
						}
						
					}
				}
				
			}
			
			/* These two following lines are just to format the output file 
			*  Except the forEach which used to iterate through the ArrayList 
			*/
			
			writeToFile.println("***************** TOKENS *****************");
			int x = 2;
			
			for(String s : tokensOutput) 
			{
				if(x % 2 == 0) 
				{
					writeToFile.println("-> " + s);
				}
				else
					writeToFile.println(s);
				x++;
			}
			
			// Close after writing process done
			writeToFile.close();
		}
		
		
		catch(FileNotFoundException e) 
		{
			System.out.println("File not found!");
		}
		
		// Return the file with tokens
		return tokens;
	}
}
