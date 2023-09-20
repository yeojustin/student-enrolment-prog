package a2;

/**
 * The 'Student' class is a Super class for sub classes of Student_Course and Student_Research. 
 * It lays the foundation of the basic information of a Student and the main grounds of the program.
 * This class will be further developed further in its subclasses.<br>
 * <p>NOTE: This class is not meant to be instantiated on its own, but rather as a parent class for its sub classes.</p>
 * @author yeojustin
 */
public class Student {
    
    /**
     * Refers to the first name of a Student
     */
    private String firstName;
    /**
     * Refers to the last name of a Student
     */
    private String lastName;
    /**
     * Refers to the Student ID of a Student
     */
    private long studentID;
    /**
     * Refers to the enrolment type of a Student
     */
    private char enrolmentType;
    
    /**
     * Default constructor for Student class that has no parameter and sets all variable to default.
     * 
     * <p>
     * Precondition: 'Student' Object must be instantiated with no parameters. <br>
     * Post-condition: A 'Student' object is created with default values.
     * </p>
     */
    public Student() {
        this.firstName = "No first name";
        this.lastName = "No last name";
        this.studentID = 0;
        this.enrolmentType = 'N';
    }
    
    /**
     * <p>
     * Constructor overload for Student class that takes in firstName, lastName, studentID and enrolmentType of a Student
     * </p>
     * Precondition - 'Student' Object must be instantiated with no parameters<br>
     * Post-condition - A 'Student' object is created with a input values of first name, last name, student ID and enrolment type.
     * @param firstName, a string of first name of a student
     * @param lastName, a string of last name of a student
     * @param studentID, a long datatype of a student ID
     * @param enrolmentType, a char datatype of student enrolment type
     */
    public Student(String firstName, String lastName, long studentID, char enrolmentType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.enrolmentType = enrolmentType;
    }
    
    /**
     * <p>
     * A default method that reports the grade of a Student. This method will be override in sub classes.
     * </p>
     * Precondition - N.A.<br>
     * Post-condition - Displays a default String message
     */
    public void reportGrade(){
        System.out.println("There is nothing to report here.");
    }
    
    /**
     * <p>
     * A method that displays a Student information in a Student Object.
     * </p>
     * Precondition - N.A.<br>
     * Post-condition - Displays information in a Student Object: Enrolment type, first name, last name and student ID respectively.
     */
    public void displayStudentInfo(){
        System.out.println("Enrolment Type: " + this.enrolmentType);
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last Name: " + this.lastName);
        System.out.println("StudentID: " + this.studentID);
    }
    
    
    /**
     * A method that checks if one Student Object is the same as another Student Object.
     * <p>
     * Compares one Student Object with another Student Object and returns a boolean value.
     * </p>
     * Precondition - N.A.<br>
     * Post-condition - boolean value True is returned if Student Objects equals to another Student Object. Else, return false.
     * @param studentObject, a Student Object to compare
     * @return equals, Boolean value
     */    
    public boolean equals(Student studentObject) { 
        boolean equals = false;
        if (this.studentID == studentObject.getStudentID()) {
            equals = true;
        }
        return equals;
    }

    /**
     * Getter method to get the Student first name from a Student object                      
     * <p>
     * Precondition: firstName is initialised at the Constructor <br>
     * Post-condition: Name of the Student object is returned.
     * </p>   
     * @return firstName Return the Student object first name.      
     */
    public String getFirstName() {
        return firstName;
    }

    
    /**
     * Getter method to get the Student last name from a Student object                      
     * <p>
     * Precondition: lastName is initialised at the Constructor <br>
     * Post-condition: lastName of the Student object is returned.
     * </p>   
     * @return lastName Return the Student object last name.      
     */
    public String getLastName() {
        return lastName;
    }
    
    
    /**
     * Getter method to get the Student ID from a Student object                      
     * <p>
     * Precondition: studentID is initialised at the Constructor <br>
     * Post-condition: studentID of the Student object is returned.
     * </p>   
     * @return studentID Return the Student object student ID.      
     */
    public long getStudentID() {
        return studentID;
    }
    
    
    /**
     * Getter method to get the enrolment type from a Student object                      
     * <p>
     * Precondition: enrolmentType is initialised at the Constructor <br>
     * Post-condition: enrolmentType of the Student object is returned.
     * </p>   
     * @return enrolmentType Return the Student object enrolment type.      
     */
    public char getEnrolmentType() {
        return enrolmentType;
    }
    
    /**
     * Override of toString() method in Object class to convert contents of Student Object to String for display                    
     * <p>
     * Precondition: Student object is initialised at the Constructor <br>
     * Post-condition: Converted to String for Student object firstName, lastName, studentID, enrolmentType
     * </p>   
     * @return this.studentID+","+this.enrolmentType+","+this.getFirstName()+","+this.getLastName() String of Student information      
     */
    @Override
    public String toString(){
        return this.studentID+","+this.enrolmentType+","+this.getFirstName()+","+this.getLastName();
    }
    
}
