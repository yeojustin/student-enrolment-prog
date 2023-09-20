package a2;

/**
 * This class represents a student who is enrolled as a Research student.
 * It extends the Student class and adds a Research object to store information about the Research unit.
 * Research class is an attribute of Student_Research
 */
public class Student_Research extends Student{
    
    /**
     * The Research object that stores information about the research unit.
     */
    private Research researchUnit;

    
    /**
     * Constructor overload that instantiate a new Student_Research object with the given first name, last name, and student ID.
     * The enrolment type is set to 'R' to indicate that the student is enrolled as a Research .
     * <p>
     * Pre-condition: Super class Student constructor overload
     * <p>
     * Post-condtition: 'Student_Research' Object instantiated with Student firstName, lastName and studentID
     *
     * @param firstName as the first name of the student
     * @param lastName as the last name of the student
     * @param studentID as the ID of the student
     */
    public Student_Research(String firstName, String lastName, long studentID) {
        super(firstName, lastName, studentID, 'R');
    }

    
    /**
     * Setter method sets the Research object that stores information about the Research unit.
     * <p>
     * Pre-condition: Research class
     * Post-condition: Sets the Research object
     *
     * @param researchUnit the Research object to set
     */
    public void setResearchUnit(Research researchUnit) {
        this.researchUnit = researchUnit;
    }

    
    /**
     * Getter method returns the Research object that stores information about the research unit.
     * <p>
     * Pre-condition: Research class set and instantiated
     * </p>
     * <p>
     * Post condition: Returns the Research object
     * </p>
     * @return the Research object
     */
    public Research getResearchUnit() {
        return researchUnit;
    }
    
    
    /**
     * Method that overrides the 'reportGrade' method in the Student class to include the displaying of the Research unit marks information.
     * If there is no Research unit information, it displays "No unit information"
     * Pre-condition:
     * <ul>
     * <li>'reportGrade' method in Super Class needs to be used</li>
     * <li>'setResearchUnit' method needs to be called and parsed</li>
     * <li>'displayStudentInfo' method in Super Class needs to be called</li>
     * </ul>
     * <p>
     * Post-condition: Displays the Student information from Super Class and Student marks information from Research class
     * </p>
     */
    @Override
    public void reportGrade(){
        super.displayStudentInfo();
        if (researchUnit != null){
            researchUnit.reportGrade();
        }
        else{
            System.out.println("No unit information");
        }
    }
    
    /**
     * Method that overrides the toString method in the Student class to include the research unit information.
     * Pre-condition:
     * <ul>
     * <li>Student_Class object is initialised at the Constructor</li>
     * <li>Research class needs to be set</li>
     * </ul>
     * Post-condition: A string representation of the Student_Course object of information from Student Class,
     * finalDissertation and proposal marks from the Research Class
     * @return a string representation of the Student_Research object
     */
    @Override
    public String toString() {
        Research r = this.getResearchUnit();
        return super.toString()+","+r.getProposal()+","+r.getFinalDissertation()+"\n";
    }
    
    
    /**
     * Method that runs a test of the Student_Research class.
     * Creates a Student_Research object and instantiate Research object as an attribute
     * and prints out their grade before and after enrolling in a course.
     */
    public static void run(){
        Student_Research sr = new Student_Research("John","Cena",222);
        sr.reportGrade();
        
        System.out.println();
        Research uc = new Research(75,90.5);
        sr.setResearchUnit(uc);
        sr.reportGrade();
        System.out.println("\n"+sr.toString());
    }
    
}
