import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Compiler  {

    public static void main(String[] args) throws IOException {
        parseSource("test");
        generateTokens();
    }
   
    // Declaring variables
    private static ArrayList<String> lex;
    private static FileWriter tokenFile;
    private static Scanner input;
    private static PrintWriter writeToFile;

    /*
    * Function to read from the source code and generate lexemes
    */

    private static ArrayList<String> parseSource(String name) throws IOException {

        // Initializing variables

        // This array will hold lexemes parse from the source code
        lex = new ArrayList<String>();
        // This scanner will read from the source code file
        input = new Scanner(new File(name));
        // This String object will capture each line read from the source
        String line = "";

        // Starting of the parsing
        while(input.hasNext()){
            
            line = input.nextLine();

            String[] lexemes = line.split(" ");

            for(String s : lexemes){

                // Check for STRING LEXEME
                if(s.matches(".*[a-zA-Z]")){
                    lex.add("LITTERAL");
                }

                // Check for INTEGER LEXEME
                else if(s.matches("[+-]?[0-9][0-9]*")){
                    lex.add("NUMBER");
                }

                // Check for FLOAT LEXEME
                else if(s.matches("[+-]?([0-9]*[.])?[0-9]+")) {
                    lex.add("FLOAT");
                }

                // Check for SPECIAL CHARACTTER
                else if(s.matches(".*[$&+,:;`=\\\\\\\\?@#|/'<>.^*\\\"{}()\\\\[\\\\]%!-]")){
                    lex.add("SPECIAL_CHAR");
                }

                else
                    lex.add("UNDEFINED");
            }
        }

        System.out.println(lex.toString());
        return lex;
    }

    /*
    * This function will iterate through the ArrayList and determine which
    * kind of lexeme, then generate tokens
    */

    private static FileWriter generateTokens() throws IOException {

        // Initialize variables
        tokenFile = new FileWriter("Tokens.txt");
        writeToFile = new PrintWriter(tokenFile);

        // Iterate through the array
        for(String s : lex){
            if(s.equals("LITTERAL")){
                writeToFile.println("[DATA_TYPE_LITT]");
            }
            else if(s.equals("NUMBER")){
                writeToFile.println("[DATA_TYPE_NUM]");
            }
            else if(s.equals("FLOAT")){
                writeToFile.println("[DATA_TYPE_FLOAT]");
            }
            else if(s.equals("SPECIAL_CHAR")){
                writeToFile.println("RESERVED_WORD");
            }
            else
                writeToFile.println("UNDEFINED");
        }

        writeToFile.close();
        return tokenFile;
    }
}
