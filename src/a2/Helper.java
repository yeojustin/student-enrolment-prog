package a2;

import java.util.*;
import java.io.*;

/**
 * The Helper class provides various helper methods for console input and output. 
 * It also includes a scanner object to get input from the console.
 * @author yeojustin
 */
public class Helper {
    
        /**
         * Scanner class used to get input from console/user.            
         */
        public Scanner kb = new Scanner(System.in);

        
        /**
         * Helper method to get user to input integer value with error validation.                      
         * <p>
         * This class is a used to get a valid integer input with InputMismatchError validation. The user is prompted to input an integer indefinitely until an integer is received.
         * </p>
         * <p>Precondition- There needs to be a valid String parsed into this method as a prompt statement or message in the console.</p>
         * <p>Post-condition - An integer in returned.</p>
         * @param msg A prompt statement or message to be displayed in the console.
         * @return n A user input integer is returned.
         */
        public int getInt(String msg) {
            int n = 0;
            boolean carryOn = true;
            while (carryOn) {
                try {
                    System.out.println(msg);
                    n = kb.nextInt();
                    kb.nextLine(); // Consume newline character
                    carryOn = false;
                }
                catch (InputMismatchException e) {
                    System.out.println("Please enter only menu option numbers.\n");
                    kb.nextLine();
                }
            }
            return n;
        }
        
        
        /**
         * Helper method to get user to input datatype long value with error validation.                      
         * <p>
         * This class is a used to get a valid long input with InputMismatchError validation. The user is prompted to input an integer indefinitely until a long datatype is received.
         * </p>
         * <p>Precondition - There needs to be a valid String parsed into this method as a prompt statement or message in the console.</p>
         * <p>Post-condition - A datatype long is returned.</p>
         * @param msg A prompt statement or message to be displayed in the console.
         * @return n A user input long value is returned.
         */
        public long getLong(String msg) {
            long n = 0;
            boolean carryOn = true;
            while (carryOn) {
                try {
                    System.out.println(msg);
                    n = kb.nextLong();
                    kb.nextLine(); // Consume newline character
                    carryOn = false;
                }
                catch (InputMismatchException e) {
                    System.out.println("Please enter only numbers.\n");
                    kb.nextLine();
                }
            }
            return n;
        }
        
        
        /**
         * Helper method to get user to input String value.                     
         * <p>
         * This class is a used to get a String input from the user.
         * </p>
         * Precondition - There needs to be a valid String parsed into this method as a prompt statement or message in the console.. <br>
         * Post-condition - A String is returned.
         * @param msg A prompt statement or message to be displayed in the console.
         * @return s A String of message input from the user.
         */
        public String getString(String msg) {
            System.out.println(msg);
            String s = kb.nextLine();
            return s;
        }

        /**
         * Helper method to validate one word String input for file name.                     
         * <p>
         * This class is a used to get a one word String input from the user as name using another getString method. This method uses a loop and a String class function .contains() to get user to input a one word string. When a one word String is input, the loop stops.
         * </p>
         * <p>Precondition- N.A.</p>
         * <p>Post-condition - String is returned with no spaces.</p>
         * @return fileName One word String of name from user input.
         * @param msg String message to be parsed in as user prompt
         */
        public String validateFileName(String msg){
            String fileName = ""; //declare and initialise working storage
            boolean carryOn = true;
            while (carryOn){
                fileName = getString(msg);
                if (fileName.contains(" ")){
                    System.out.println("Enter only one word file names only [no spaces].");
                }
                else{
                    carryOn = false;
                }
            }
            return fileName;
        }
        
        
        /**
         * Checks if the file exists in the current directory. Used for internal testing in Client Class if needed
         * <p>
         * This method throws a FileNotFoundException if the file does not exist.
         * </p>
         * <p>Precondition - The file name should be a valid file name in the current directory.</p>
         * <p>Post-condition - A scanner object is returned that can be used to read the file.</p>
         * @param FILENAME The name of the file to be checked for.
         * @return A scanner object that can be used to read the file.
         * @throws FileNotFoundException if the file does not exist
         */
        public Scanner checkFileFromDirectory(String FILENAME) throws FileNotFoundException {
            File file = new File(FILENAME);
            if (!file.exists()){
                throw new FileNotFoundException("File " + FILENAME + " not found in directory");
            }
            return new Scanner(file);
        }

       /**
        * Runs internal testing on the 'Helper' class by instantiating an object 'helper'.
        * <p>
        * Pre-condition: N.A. </p>
        * <p>Post-condition: Depends on the intention of the user testing the methods in Helper class.</p>
        */ 
        public static void run(){
            Helper helper = new Helper();
           
            // Test user input integer function with error validaton
            int num = helper.getInt("Please enter a num: ");
            System.out.println(num);
            
            // Test user input String function
            String name1 = helper.getString("Please enter a name: ");
            System.out.println(name1);
            
            // Other test methods here
        }
        
}
