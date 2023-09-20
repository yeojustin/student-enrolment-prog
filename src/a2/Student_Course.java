package a2;

/**
 * The 'Student_Course' class is a sub class of Student. 
 * It has information of the a Coursework student.
 * A Unit_Course class, a subclass of Unit Class, is an attribute of Student_Course class and contains the Student academic information
 * <p>
 * NOTE: This class is meant to be instantiated and to be used with Unit_Course class as it requires to use the information from the class.
 * </p>
 * 
 * @author yeojustin
 */
public class Student_Course extends Student{
    
    /**
     * This is a Unit_Course object that contains the marks information of a student
     * It serves as an attribute to the Student_Course class.
     * It is declared to be instantiated within the Student_Course class.
     */
    private Unit_Course unit;
    
    
    /**
     * Constructor for a Student_Course object.
     * @param firstName the first name of the student
     * @param lastName the last name of the student
     * @param studentID the student ID of the student
     */
    public Student_Course(String firstName, String lastName, long studentID){
        super(firstName, lastName, studentID, 'C');
    }
    
    
    /**
     * Sets the <code>Unit_Course</code> object representing the course the student is enrolled in.
     * <p>
     * <b>Pre-condition:</b> <code>Unit_Course</code> class
     * </p>
     * <p>
     * <b>Post-condition:</b> Sets the <code>Unit_Course</code> object.
     * </p>
     * @param unit the <code>Unit_Course</code> object representing the Course.
     */
    public void setUnit(Unit_Course unit){
        this.unit = unit;
    }
    

    /**
     * Getter method returns the Unit_Course object representing the course the student is enrolled in.
     * <p>
     * Pre-condition: Unit_Course class set and instantiated
     * </p>
     * <p>
     * Post-condition: Returns the Unit_Course object
     * </p>
     * 
     * @return the Unit_Course object representing the course
     */
    public Unit_Course getUnit() {
        return unit;
    }
    
    
    /**
     * Method display out the student's information and the grade for the course they are enrolled in.
     * If the student is not enrolled in a course, prints out "No unit information."
     * Method is override from Student super class
     * 
     * <p>Pre-condition:
     * <ul>
     * <li>'reportGrade' method in Super Class needs to be used</li>
     * <li>'setUnit' method needs to be called and parsed</li>
     * <li>'displayStudentInfo' method in Super Class needs to be called</li>
     * </ul>
     * 
     * <p>Post-condition: Displays the Student information from Super Class and Student marks information from Unit_Course class
     */
    @Override
    public void reportGrade(){
        super.displayStudentInfo();
        if (unit != null){
            unit.reportGrade();
        }
        else{
            System.out.println("No unit information.");
        } 
    }

    /**
     * Method that overrides toString method from Object class
     * This method is a string representation of the Student_Course object.
     * Includes the student's information and the information for the course they are enrolled in.
     * <b>Precondition:</b>
     * <ul>
     * <li>Student_Class object is initialised at the Constructor</li>
     * <li>Unit_Course class needs to be set</li>
     * </ul>
     * <p>
     * <b>Post-condition:</b> A string representation of the Student_Course object of information from Student Class,
     * UnitID, UnitLevel, Assignment 1 and 2 marks and exam marks from the Unit_Course class
     * </p>
     *
     * @return a string representation of the Student_Course object
     */  
    @Override
    public String toString() {
        Unit_Course uc = this.getUnit();
        return super.toString()+","+ uc.getUnitID()+","+uc.getLevel()+","+uc.getA1()+","+uc.getA2()+","+uc.getExam()+"\n";
    }
    
    
    /**
     * Runs a test of the Student_Course class.
     * Creates a Student_Course object and instantiate Unit_Course object as an attribute
     * and prints out their grade before and after enrolling in a course.
     */
    public static void run(){
        Student_Course sc = new Student_Course("Rin","Takaota",111);
        sc.reportGrade();
        
        System.out.println();
        Unit_Course uc = new Unit_Course("ICT167", 1, 87, 65, 83);
        sc.setUnit(uc);
        sc.reportGrade();
    }
    
}
