package a2;

import java.util.*;
import java.io.*;

/**
 * The Client class provides the methods to execute the main 
 * functionality of the Student Enrolment program.
 * It also includes a scanner object to get input from the console.
 * @author yeojustin
 */
public class Client{

    /**
     * The default ArrayList of Student Object to be initialised for usage.
     */
    private ArrayList<Student> studentArrList;
    /**
     * The Scanner class declared to be initialised for reading file
     */
    private Scanner read;
     /**
     * The PrinWriter class declared to be initialised for writing to file
     */
    private PrintWriter write;
    /**
     * The Helper class declared and initialised for general Helper class methods usage
     */
    private final Helper h = new Helper();


    /**
     * Populate ArrayList from CSV file method                         
     * <p>
     * This class is used to populate ArrayList of Student objects from 'student.csv' file 
     * </p>
     * Precondition:
     * <ul>
     * <li>The 'student.csv' file must be in the right format (See limitation in Ext. Docs)</li>
     * <li>The 'student.csv' file must be in the directory</li>
     * </ul>
     * Post-condition:
     * <ul>
     * <li>An ArrayList of student objects are populated into studentArrList ArrayList</li>
     * </ul>
     *
     * @return studentArrList, an ArrayList of Student object
     */
    private ArrayList<Student> populateArrListFromStudentCSV(){

        studentArrList = new ArrayList<Student>();
        String FILENAME = "student.csv";
        File file = new File(FILENAME);

        try{
            read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] fields = line.split(",");

                // parsing information to to different variable for storage in ArrayList
                char enrolment  = fields[0].charAt(0);
                String firstName = fields[1];
                String lastName = fields[2];
                long id = Long.parseLong(fields[3]);

                Student student = new Student(firstName, lastName, id, enrolment);

                studentArrList.add(student);
            }
        } catch (FileNotFoundException ex){
            System.out.println(ex.getLocalizedMessage());
        } finally{
            read.close();
        }
        return studentArrList;
    }


   /**
    * This method is to check if the contents in 'student.csv' is populated into the ArrayList studentArrList                     
    * <p>
    * This method will display the contents in studentArrList ArrayList
    * </p>
    * Precondition:
    * -The method populateArrListFromStudentCSV must be called first and return no error/exception
    * <br>
    * Post-condition:
    * - Displays studentArrList of Student object into meaningful information based First Name, Last Name and StudentID.
    */
    private void printStudentArrayListFromStudentCSV(){
        ArrayList<Student> studentArrList = populateArrListFromStudentCSV();
        for(Student s : studentArrList){
            if(studentArrList != null){
                char enrolment = s.getEnrolmentType();
                String firstName = s.getFirstName();
                String lastName = s.getLastName();
                long id = s.getStudentID();
                System.out.println(enrolment+"\n"+firstName+" "+lastName+"\n"+id);
            }
        }
    }


    /**
     * This method is to populate existing studentArrList ArrayList from 'marks.csv' file                     
     * <p>
     * This method will populate studentArrList ArrayList with marks information from 'marks.csv' file
     * </p>
     * Precondition:
     * <ul>
     * <li>The 'marks.csv' file must be in the right format (See limitation in Ext. Docs)</li>
     * <li>The 'marks.csv' file must be in the directory</li>
     * <li>The method populateArrListFromStudentCSV must be called first and return no error/exception </li>
     * </ul>
     * Post-condition:
     * <ul>
     * <li>An ArrayList of Student_Course and Student_Research subclasses are populated into the existing studentArrList ArrayList</li>
     * </ul>
     * @param fileName a String file name inclusive of file extension
     * @return studentArrList, an ArrayList of Student object
     */
    private ArrayList<Student> addMarksInfoFromAnotherCSV(String fileName){
        studentArrList = populateArrListFromStudentCSV();
        //String FILENAME = h.validateFileName("Enter a file name [no spaces]: ");
        boolean found = false;
        int wrongFileCountForExecuteMenu = 0;
        File file = new File(fileName);
        long id = 0; //declare and init id for comparison

        try {
            read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] fields = line.split(",");

                // parsing information to different variables for storage in studentArrList
                if (!fields[0].isEmpty()){
                    id = Long.parseLong(fields[0]);
                }

                char enrolmentType = (fields.length == 6 ? 'C' : 'R');

                // find student in studentArrList with the ID and enrolment type found in marks.csv file
                for (int i = 0; i < studentArrList.size(); i++) {
                    Student student = studentArrList.get(i);
                    if (student.getStudentID() == id && student.getEnrolmentType() == enrolmentType) {
                        if (enrolmentType == 'C') {
                            String unitID = fields[1];
                            int level = Integer.parseInt(fields[2]);
                            double a1 = Double.parseDouble(fields[3]);
                            double a2 = Double.parseDouble(fields[4]);
                            double exam = Double.parseDouble(fields[5]);

                            Unit_Course uc = new Unit_Course(unitID, level, a1, a2, exam);
                            Student_Course sc = new Student_Course(student.getFirstName(), student.getLastName(), student.getStudentID());
                            sc.setUnit(uc);
                            studentArrList.set(i, sc);
                            //sc.reportGrade();
                            //System.out.println();
                        } 
                        else if (enrolmentType == 'R') {
                            double proposal = Double.parseDouble(fields[1]);
                            double finalDissertation = Double.parseDouble(fields[2]);

                            Research re = new Research(proposal, finalDissertation);
                            Student_Research sr = new Student_Research(student.getFirstName(), student.getLastName(), student.getStudentID());
                            sr.setResearchUnit(re);
                            studentArrList.set(i, sr);
                            //sr.reportGrade();
                            //System.out.println();
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
                ex.getStackTrace();
        } finally {
            if (read != null){
                read.close();
            }
        }

        return studentArrList;
    }


    /**
     * This method is to check if the 'addMarksInfoFromAnotherCSV' method has successfully populated to existing studentArrList.              
     * <p>
     * This method will compare 2 ArrayList of studentArrList, one returned by 'addMarksInfoFromAnotherCSV' and the other returned by 'populateArrListFromStudentCSV' method.
     * </p>
     * Precondition:
     * <ul>
     * <li>The method 'populateArrListFromStudentCSV' must return no error/exception</li>
     * <li>The method 'addMarksInfoFromAnotherCSV' must be called and return no error/exception. Returned ArrayList must be parsed in as a parameter</li>
     * </ul>
     * Post-condition:
     * <ul>
     * <li>Checks and display if Student objects in each index is modified.</li>
     * <li>If modified with subclasses added, prints 'true' at n index.</li>
     * </ul>
     * @param modifiedStudentAL An ArrayList of Student Object that is different from the ArrayLst returned by 'populateArrListFromStudentCSV' method.
     */
    private void checkModifiedArrayListWithOriginal(ArrayList<Student> modifiedStudentAL){

        ArrayList<Student> originalList = populateArrListFromStudentCSV();

        // Check if the modifiedList has any different objects than the originalList
        for (int i = 0; i < originalList.size(); i++) {
            Student originalStudent = originalList.get(i);
            Student modifiedStudent = modifiedStudentAL.get(i);

            if (originalStudent.hashCode() != modifiedStudent.hashCode()) {
                // Objects are different and have been modified
                System.out.println("Object at index " + i + " has been modified.");
            }
        }
    }


    /**
     * This method is to remove a Student Object from an ArrayList of Student object.
     * 
     * <p>
     * This method will check and remove a Student object based on parsed in studentid.
     * </p>
     * 
     * <p>
     * <b>Precondition:</b>
     * </p>
     * <ul>
     * <li>The method 'populateArrListFromStudentCSV' must return no error/exception.</li>
     * <li>Optional: The method 'addMarksInfoFromAnotherCSV' must return no error/exception.</li>
     * <li><b>Note than both ArrayList of Student Object returned by the methods above can be parsed in to run this method</b></li>
     * </ul>
     * 
     * <p>
     * <b>Post-condition:</b>
     * </p>
     * <ul>
     * <li>Checks if StudentID matches any of the Student Objects in the parsed in ArrayList.</li>
     * <li>If there is a match, prompt user to confirm removal.</li>
     * <li>Else, display error message.</li>
     * </ul>
     * 
     * @param studentAL an ArrayList of Student Object
     * @param studentid a long datatype of Student ID
     * @return studentAL a modified ArrayList of Student Object with a removed Student object based on parsed in StudentID
     */
    private ArrayList<Student> removeStudentByStudentID(ArrayList<Student> studentAL,long studentid){

        boolean IDmatch = false;

        for (int i = 0; i < studentAL.size(); i++){
            Student s = studentAL.get(i);
            if (s.getStudentID() == studentid){
                s.displayStudentInfo();
                String choice = h.getString("Are you sure you want to delete Student details? [Y/N]");
                if (choice.equalsIgnoreCase("y")){
                    studentAL.remove(s);
                    System.out.println("...student deleted.\n");
                }
                else if(choice.equalsIgnoreCase("n")){
                    System.out.println("Data not deleted.");
                }
                else{
                    System.out.println("Invalid choice.\nData not deleted.\n");
                }
                IDmatch = true;
            }
        }
        if (!IDmatch){
            System.out.println("Student ID not found...\n");
        }        
        return studentAL;
    }


    /**
     * This is a helper method is to display all unprocessed details in currently in the parsed in ArrayList.         
     * It will be used for output methods. 
     * <p>
     * This method will output the unprocessed information currently held in the ArrayList, separated by commas for each information and separated by rows for each index.
     * </p>
     * 
     * Precondition:
     * <ul>
     * <li>The method 'populateArrListFromStudentCSV' must return no error/exception</li>
     * <li>The method 'addMarksInfoFromAnotherCSV' must return no error/exception</li>
     * <li><b>Note than both ArrayList of Student Object returned by the methods above can be parsed in to run this method</b></li>
     * </ul>
     * 
     * Post-condition:
     * <ul>
     * <li>Displays unprocessed information currently held in the ArrayList of Student Object.</li>
     * </ul>
     * 
     * @param studentAL an ArrayList of Student Object.
     * @return A String of information extracted from the ArrayList of Student object
     */
    private String displayUnprocessedDetailsInArrayList(ArrayList<Student> studentAL){
        String arrayListContents = "";
        for (Student s : studentAL){
            if(s != null){
                if (s instanceof Student_Course){
                    Student_Course sc = (Student_Course) s;
                    arrayListContents += (sc.toString());
                }
                else if (s instanceof Student_Research){
                    Student_Research sr = (Student_Research) s;
                    arrayListContents += (sr.toString());
                }
                else{
                    //not an instance of any of the above (ie. no marks info)
                    //s.displayStudentInfo();
                    arrayListContents += (s.toString() + "\n");
                }
            }
            else{
                System.out.println("Student record is null or empty.");
            }
        }
        return arrayListContents;
    }
    
    
    /**
     * This is a method is to display all details in currently in the parsed in ArrayList.         
     * <p>
     * This method will output the information currently held in the ArrayList and processing them into useful data for user to see.
     * </p>
     * 
     * Precondition:
     * <ul>
     * <li>The method 'populateArrListFromStudentCSV' must return no error/exception</li>
     * <li>The method 'addMarksInfoFromAnotherCSV' must return no error/exception</li>
     * <li><b>Note than both ArrayList of Student Object returned by the methods above can be parsed in to run this method</b></li>
     * </ul>
     * 
     * Post-condition:
     * <ul>
     * <li>Displays information currently held in the ArrayList of Student Object.</li>
     * </ul>
     * 
     * @param studentAL an ArrayList of Student Object.
     */
    private void displayAllDetailsInArrayList(ArrayList<Student> studentAL){
        for (Student s : studentAL){
            if(s != null){
                if (s instanceof Student_Course){
                    Student_Course sc = (Student_Course) s;
                    //s.displayStudentInfo();
                    sc.reportGrade();
                    System.out.println();
                }
                else if (s instanceof Student_Research){
                    Student_Research sr = (Student_Research) s;
                    //s.displayStudentInfo();
                    sr.reportGrade();
                    System.out.println();
                }
                else{
                    //not an instance of any of the above (ie. no marks info)
                    s.displayStudentInfo();
                }
            }
            else{
                System.out.println("Student record is null or empty.");
            }
            System.out.println();
        }    
    }


    /**
     * This is a helper method used to calculate average coursework student marks.
     * 
     * <p>
     * This method will get the computed overall marks from Student_Course sub classes and calculate the average marks.
     * </p>
     * 
     * <p>
     * Precondition:
     * </p>
     * <ul>
     * <li>This method requires the use of helper method to calculate average marks</li>
     * <li>The method 'addMarksInfoFromAnotherCSV' must return no error/exception</li>
     * </ul>
     * 
     * <p>
     * Post-condition:
     * </p>
     * <ul>
     * <li>Average marks is calculated from all the computed overall marks from Student_Course sub class</li>
     * </ul>
     * 
     * @param studentAL an ArrayList of Student Object
     * @return calculated average marks of all coursework student currently held in the parsed in studentAL
     */
    private double calcaulateAverageCourseworkStudentMarks(ArrayList<Student> studentAL){
        double total = 0;
        double counter = 0;
        for (Student s : studentAL){
            if(s instanceof Student_Course && s != null){
                Student_Course sc = (Student_Course) s;
                Unit_Course uc = sc.getUnit();
                double overall = uc.getOverallMarks();
                total += overall;
                counter++;
            }
        }
        double averageMarks = total / counter;

        return averageMarks;
    }


    /**
     * This method displays above and equal average coursework student marks
     * 
     * <p>
     * This method will output the average and above coursework student marks along with Student details, overall marks, average coursework student marks, and total number of students achieving this feat.
     * </p>
     * 
     * <b>Preconditions:</b> 
     * <ul>
     * <li>The method 'populateArrListFromStudentCSV' must return no error/exception.</li>
     * <li>The method 'addMarksInfoFromAnotherCSV' must return no error/exception.</li>
     * <li>The helper method 'calculateAverageCourseworkStudentMarks' will be called within this method.</li>
     * </ul>
     * 
     * <b>Note:</b> 
     * <ul>
     * <li>Both ArrayList of Student Object returned by the methods above can be parsed in to run this method.</li>
     * <li>However, this method will not display marks information if parsed in ArrayList of Student Object does not contain marks information.</li>
     * </ul>
     * 
     * <b>Post-conditions:</b>
     * <ul>
     * <li>Display the Student details: First name, last name, student ID, overall marks.</li>
     * <li>Display the average coursework student marks.</li>
     * <li>Displays the total number of students achieving above or equal average coursework student marks.</li>
     * <li>Displays String of message to inform user if no student achieve above or equal average coursework marks.</li>
     * <li>If no Student_Course sub class (i.e. no marks information) is found in the ArrayList of Student object, a string of error message and suggestion is displayed to the user.</li>
     * </ul>
     * 
     * @param studentAL An ArrayList of Student Object
     */
    private void displayAboveOrEqualAverageCourseworkStudent(ArrayList<Student> studentAL) {
        boolean hasCourseworkStudents = false;
        int count = 0;
        for (Student s : studentAL) {
            if (s instanceof Student_Course && s != null) { // check for correct type casting
                hasCourseworkStudents = true;
                Student_Course sc = (Student_Course) s; // type casting superclass to subclass
                Unit_Course uc = sc.getUnit();
                double uc_overall = uc.getOverallMarks();
                if (uc_overall >= calcaulateAverageCourseworkStudentMarks(studentAL)) {
                    s.displayStudentInfo();
                    System.out.printf("Overall marks: %.1f\n", uc_overall);
                    System.out.println();
                    count++;
                }
            }
        }

        if (hasCourseworkStudents) {
            System.out.printf("Average: %.1f\n\n", calcaulateAverageCourseworkStudentMarks(studentAL));
            if (count == 0) {
                System.out.println("No students obtained overall marks above or equal to the average.\n");
            }
            else {
                System.out.println("Total: " + count + "\n");
            }
        }
        else {
            System.out.println("""
                               No marks information to display.

                               Possible reasons:
                               1) Current ArrayList does not contain marks info (choose option 2 to populate marks info into ArrayList)
                               2) CSV file containing marks info is not in the right format (check csv file)

                               System continue...

                               """);
        }
    }


    /**
     * This method displays Student grade report based on in Student ID
     * <p>
     * This method will output the Student grade report based on parsed in Student ID
     * </p>
     * 
     * Precondition:
     * <ul>
     * <li>The method 'populateArrListFromStudentCSV' must return no error/exception</li>
     * <li>The method 'addMarksInfoFromAnotherCSV' must return no error/exception</li>
     * <li>'reportGrade' method is to be called from the respective sub classes found within the ArrayList of Student objects</li>
     * </ul>
     * 
     * <b>Note: </b>
     * <ul>
     * <li>Both ArrayList of Student Object returned by the methods above: 'populateArrListFromStudentCSV' and 'addMarksInfoFromAnotherCSV' can be parsed in to run this method</li>
     * <li>However, this method will not display marks information if parsed in ArrayList of Student Object does not contain marks information</li>
     * </ul>
     * 
     * Post-condition:
     * <ul>
     * <li>Display the Student details and marks information from subclasses 'reportGrade' methods</li>
     * <li>If no Student sub class (i.e. no marks information) or StudentID is found in the ArrayList of Student object, appropriate string of error message and suggestion is displayed to the user</li>
     * </ul>
     * 
     * @param studentAL An ArrayList of Student Object
     * @param studentid A long datatype of StudentID
     */
    private void reportGradeFromStudentID(ArrayList<Student> studentAL, long studentid){
        boolean foundInstance = false;
        boolean foundID = false;
        for(Student s : studentAL){
            if(studentid == s.getStudentID()){
                foundID = true;
                if (s instanceof Student_Course){
                    Student_Course sc = (Student_Course) s;
                    sc.reportGrade();
                    System.out.println();
                    foundInstance = true;
                } else if (s instanceof Student_Research){
                    Student_Research sr = (Student_Research) s;
                    sr.reportGrade();
                    System.out.println();
                    foundInstance = true;
                }
                else{
                    s.displayStudentInfo();
                    s.reportGrade();
                    break;
                }
            }
        }
        //display approriate messages
        if (!foundID){
            System.out.println("Student ID not found.\n");
            System.out.println("""
                               Possible reason:
                               1) No student ID found, check using option 2 to populate ArrayList
                               2) Check if student ID is correct
                               3) CSV file(s) may not be the right format (check csv file)

                               System continue...
                               """);
        }
        if (!foundInstance && foundID){
            System.out.println("""

                               Possible reasons for the cause:
                               1) Current ArrayList does not contain marks info (choose option 2 to populate marks info into ArrayList)
                               2) CSV file containing marks info may not in the right format (check csv file)

                               System cotinue...

                               """);
        }
    }


    /**
     * This method sorts the Student Object in the ArrayList in ascending order based on StudentID
     * <p>
     * This method will sort the ArrayList of Student Object using a Bubble sort algorithm
     * </p>
     * 
     * <p>Precondition:</p>
     * <ul>
     *  <li>The method 'populateArrListFromStudentCSV' must return no error/exception</li>
     *  <li>The method 'addMarksInfoFromAnotherCSV' must return no error/exception</li>
     *  <li>'reportGrade' method is to be called from the respective sub classes found within the ArrayList of Student objects</li>
     * </ul>
     * 
     * <p><b>Note:</b></p> 
     * <ul>
     *  <li>Both ArrayList of Student Object returned by the methods above: 'populateArrListFromStudentCSV' and 'addMarksInfoFromAnotherCSV' can be parsed in to run this method</li>
     *  <li>However, this method will not display marks information if parsed in ArrayList of Student Object does not contain marks information</li>
     * </ul>
     * 
     * <p>Post-condition:</p>
     * <ul>
     *  <li>Sorted ArrayList of Student Objects based on StduentID in ascending order</li>
     * </ul>
     * 
     * @param studentAL An ArrayList of Student Object
     * @return A new sorted ArrayList of Student Object
     */
    private ArrayList<Student> sortArrayListInAscendingOrderByStudentID(ArrayList<Student> studentAL){
        //bubble sort algo in ascending order by studentID
        boolean swap = true;
        while (swap){
            swap = false;
            for (int i = 1; i<studentAL.size(); i++){
                long id1 = studentAL.get(i-1).getStudentID(); 
                long id2 = studentAL.get(i).getStudentID(); 

                //i-1 comare with i
                if(id1 > id2){
                    Student temp = studentAL.get(i-1);
                    studentAL.set(i-1, studentAL.get(i)); //the element is the Stduent Obj
                    studentAL.set(i, temp);
                    swap = true;
                }
            } //swap = true? continue the loop : break the while-loop   
        }
        //swap is false here
        return studentAL;
    }


    /**
     * This method sorts the Student Object in the ArrayList in ascending order by StudentID and writes it to a csv file
     * <p>
     * This method will write the contents in the sorted ArrayList of Student object to a csv file, indicated by the user
     * </p>
     * <p>
     * Precondition:
     * <ul>
     * <li>The ArrayList of Student Object (studentAL) must not be null</li>
     * <li>The ArrayList of Student Object (studentAL) must contain at least one Student Object</li>
     * <li>The Student Object in the ArrayList must have a valid StudentID (i.e. not null or empty)</li>
     * </ul>
     * Post-condition:
     * <ul>
     * <li>The ArrayList of Student Object (studentAL) will be sorted in ascending order based on StudentID</li>
     * <li>The sorted ArrayList of Student Objects will be written to a csv file, indicated by the user</li>
     * </ul>
     *
     * @param studentAL an ArrayList of Student Object
     */
    private void outputSortedArrayListToCSV(ArrayList<Student> studentAL){
        ArrayList<Student> sortedStudentAL = sortArrayListInAscendingOrderByStudentID(studentAL);
        String OUTPUT_FILENAME = "sorted_arraylist.csv";

        try{
            write = new PrintWriter(OUTPUT_FILENAME);
            write.println("ID,Enrolment,FirstName,LastName"); //header
            write.println(displayUnprocessedDetailsInArrayList(sortedStudentAL));
            write.close();
        } catch (FileNotFoundException ex){
            System.out.println("Error open file to write.");
        }
    }


    /**
     * This method displays the menu options for the user.
     * 
     * Precondition: None. 
     * 
     * Post-condition: None.
     */
    private void displayMenu(){
        System.out.println("""
                           Student Enrolment System
                           ================================================================================
                           1. Quit (exit the program)
                           2. Add course work or research students marks info from another csv file
                           3. Remove a student by Student ID
                           4. Display all student details in current ArrayList
                           5. Display all course work student with above average marks in current ArrayList
                           6. Display student grade information
                           7. Sort and display Student Inforamtion with Student ID in ascending order
                           8. Output and store sorted information into CSV file.
                           ================================================================================
                           """);
    }


    /**
     * <p>
     * This method executes the functions of the Student Enrolment program based on user input. 
     * This method also displays the appropriate messages for user prompts, title and error messages from poor inputs.
     * </p>
     *
     * <strong>Precondition:</strong> 
     * <ul>
     * <li>This method requires the use of <code>populateArrListFromStudentCSV</code> method to return a default ArrayList of Student Object</li>
     * <li>This method must have all the methods that executes the functionality of the program: 
     *   <code>addMarksInfoFromAnotherCSV</code>, <code>removeStudentByStudentID</code>, <code>displayAllDetailsInArrayList</code>,
     *   <code>displayAboveOrEqualAverageCourseworkStudent</code>,<code>reportGradeFromStudentID</code>
     *   <code>sortArrayListInAscendingOrderByStudentID</code>, <code>outputSortedArrayListToCSV</code> </li>
     * </ul>
     * 
     * <strong>Post-condition:</strong>
     * <ul>
     * <li>Runs the appropriate methods based on user input</li>
     * </ul>
     * 
     */
    private void executeMenu() {
        //ArrayList<Student> AL = populateArrListFromStudentCSV();
        ArrayList<Student> currentAL = populateArrListFromStudentCSV(); // initialize currentAL 
        boolean carryOn = true;
        boolean option7Flag = false;
        String currentFilename = ""; // initialize currentFilename
        while (carryOn) {
            displayMenu();
            int choice = h.getInt("Enter choice [1-8]: ");
            switch (choice) {
                case 1:
                    String quit = h.getString("All your inputs be will reset.\nAre you sure? [Y/N]: ");
                    if (quit.equalsIgnoreCase("y")) {
                        System.out.println("System exited.");
                        System.exit(0);
                    } else if (quit.equalsIgnoreCase("n")) {
                        System.out.println("Ok. System continue...\n");
                        break;
                    } else {
                        System.out.println("Invalid choice. System continue...\n");
                    }
                    break;
                case 2:
                    currentFilename = h.validateFileName("Enter a file name: ");
                    File file = new File(currentFilename);
                    if (!file.exists()){
                        System.out.println("Error: File not found in directory. Please enter a valid filename.\n");
                    } else {
                        currentAL = addMarksInfoFromAnotherCSV(currentFilename);
                        System.out.println("...info from " + currentFilename + " successfully populated into ArrayList.\n");
                    }
                    break;
                case 3:
                    long id = h.getLong("\nEnter a StudentID to remove: ");
                    System.out.println("\nQuery result: ");
                    removeStudentByStudentID(currentAL, id); // use currentAL instead of AL
                    break;
                case 4:
                    System.out.println("\nAll current information in ArrayList: \n");
                    displayAllDetailsInArrayList(currentAL); // use currentAL instead of AL
                    break;
                case 5:
                    System.out.println("\nAbove or equal average coursework student:\n");
                    displayAboveOrEqualAverageCourseworkStudent(currentAL); // use currentAL instead of AL
                    break;
                case 6:
                    long idToReport = h.getLong("\nEnter a StudentID to view grade report: ");
                    System.out.println("\nQuery result: \n");
                    reportGradeFromStudentID(currentAL, idToReport); // use currentAL instead of AL
                    break;
                case 7:
                    option7Flag = true;
                    currentAL = sortArrayListInAscendingOrderByStudentID(currentAL); // use currentAL instead of AL
                    displayAllDetailsInArrayList(currentAL); // use currentAL instead of AL
                    break;
                case 8:
                    if (option7Flag) {
                        currentAL = sortArrayListInAscendingOrderByStudentID(currentAL); // use currentAL instead of AL
                        outputSortedArrayListToCSV(currentAL);
                        System.out.println("...output done.\n");
                    } else {
                        System.out.println("Error. Option 7 has to be selected first.\n");
                    }
                    break;
                default:
                    System.out.println("\nError. Enter values from 1 to 8 only.\n");
                    break;
            }
        }
    }


   /**
    * This method is use to run the 'executeMenu' method in the Client Class by instantiating a Client object 'client'.
    * Will be called in the Main Class when program runs
    */ 
    public static void run(){
        Client client = new Client();
        client.executeMenu();

        // Uncomment to test individual functions internally
        //client.test();
    }


    /**
    * This method is simply used for internal testing purposes for the Client Class
    * Note: Test the appropriate functions internally by removing comments // for the chosen function to test
    */ 
    private void test(){
        
        // Uncomment where applicable for individual method testing
        // Please note that some methods has pre-requisites. To test them, please see 3.2 in ext. docs
        Client c = new Client();
        
        //function 2 (WORKING) - limitaions marks.csv and student.csv studentID needs to match corresponding fields
        String file = h.validateFileName("Enter a file name [no spaces]: ");
        ArrayList<Student> StudentAL = c.addMarksInfoFromAnotherCSV("marks.csv");
        
        //check raw info in ArrayList
        //String content = StudentAL.toString();
        //System.out.println(content);

        //function 3 (WORKING)
        //long inputID = h.getLong("Enter StudentID to remove its details: ");
        //ArrayList<Student> newStudentAL = c.removeStudentByStudentID(StudentAL,333);

        //function 4 (WORKING)
        //String content = c.displayUnprocessedDetailsInArrayList(StudentAL);
        //System.out.println(content);
        //c.displayAllDetailsInArrayList(StudentAL);

        //function 5
        //double avg = c.calcaulateAverageCourseworkStudentMarks(StudentAL);
        //System.out.println(avg);
        //c.displayAboveOrEqualAverageCourseworkStudent(newStudentAL);

        //function 6
        //long inputID = h.getLong("Enter StudentID to view its details: ");
        //c.reportGradeFromStudentID(StudentAL,333); // Student ID not found.

        //function 7 (WORKING)
        //ArrayList<Student> sortedStudentAL = c.sortArrayListInAscendingOrderByStudentID(newStudentAL);

        //function 8 (need function 7)
        //c.outputSortedArrayListToCSV(sortedStudentAL);
    }

}
