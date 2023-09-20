package a2;

/**
 * The Unit class represents a unit in a university course.
 * Each unit has an enrolment type (either 'C' for core or 'E' for elective)
 * and a method to calculate the final grade based on overall marks.
 */
public class Unit{
    
    /**
     * Refers to the enrolment type of the Unit Class Object.
     */
    private char enrolmentType;

    /**
     * Constructor overload for Unit class.
     * <p>
     * Precondition: N.A<br>
     * Post-condition: A 'Unit' object is instantiated.
     * </p>
     * @param enrolmentType The enrolment type of the unit.
     */
    public Unit(char enrolmentType) {
        this.enrolmentType = enrolmentType;
    }
    
    
    /**
     * Method to calculate the final grade based on overall marks.
     * 
     * <p>Pre-condition: Overall marks computed in its sub classes Unit_Course Class or Research Class</p>
     * <p>Post-condition: Determines the final grade</p>
     * 
     * @param overallMarks The overall marks achieved by the student in the unit.
     * @return A string representing the final grade (either HD, D, C, P or N).
     */
    public String getFinalGrade(double overallMarks) {
        if (overallMarks >= 80) {
            return "HD";
        }
        else if (overallMarks >= 70) {
            return "D";
        }
        else if (overallMarks >= 60) {
            return "C";
        }
        else if (overallMarks >= 50) {
            return "P";
        } 
        else {
            return "N";
        }
    }

    
    /**
     * <p>
     * A default method that reports the grade of a Student. This method will be override in sub classes.
     * </p>
     * Precondition - N.A.<br>
     * Post-condition - Displays a default String message
     */
    public void reportGrade(){
        System.out.println("N.A.");
    }

    
    /**
     * Getter method to get the enrolment type from a Unit Class object                      
     * <p>
     * Precondition: enrolmentType is initialised at the Constructor <br>
     * Post-condition: enrolmentType of the Unit Class object is returned.
     * </p>   
     * @return enrolmentType Return the Student object enrolment type.      
     */
    public char getEnrolmentType() {
        return this.enrolmentType;
    } 
}
