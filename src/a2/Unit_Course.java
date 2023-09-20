package a2;

/**
 * This class represents a Unit Course and extends the Unit class.
 * It includes information about the unitID, level, assessment 1 mark, assessment 2 mark, and exam mark.
 * It also includes methods to calculate the overall marks and report the final grade for the Unit.
 */
public class Unit_Course extends Unit{
    
    /**
     * The unique identifier or Unit ID for the Unit Course.
     */
    private String unitID; 
    /**
     * The level of the Unit Course.
     */
    private int level;
    /**
     * The mark received for Assessment 1.
     */
    private double a1;
    /**
     * The mark received for Assessment 2.
     */
    private double a2;
    /**
     * The mark received for the final exam.
     */
    private double exam;

    
    /**
     * Constructor overload for the Unit_Course class
     * Pre-condition:
     * <ul>
     * <li>'enrolmentType' set in the Unit Class</li>
     * </ul>
     * <p>
     * Post-condition: Unit_Course Object is instantiated with the information of Enrolment type, UnitID, Unit Level, a1 marks, a2 marks and exam marks
     * </p>
     * @param unitID The unique identifier for the Unit Course.
     * @param level The level of the Unit Course.
     * @param a1 The mark received for Assessment 1.
     * @param a2 The mark received for Assessment 2.
     * @param exam The mark received for the final exam.
     */
    public Unit_Course(String unitID, int level, double a1, double a2, double exam) {
        super('C');
        this.unitID = unitID;
        this.level = level;
        this.a1 = a1;
        this.a2 = a2;
        this.exam = exam;
    }
    
    
    /**
     * Method that calculates the overall marks for the Unit Course.
     * Pre-condition:
     * <ul>
     * <li>Unit_Course object instantiated to invoke this method to compute overall marks</li>
     * </ul>
     * <p>
     * Post-condition: Computes the overall marks
     * </p>
     * 
     * @return The overall marks for the Unit Course.
     */
    public double getOverallMarks(){
        return (this.a1 * 0.3) + (this.a2 * 0.3) + (this.exam * 0.4);
    }
    
    
    /**
     * Method overrides the 'reportGrade' method in the Unit class to report the final grade for the Unit Course.
     * 
     * <p>Pre-condition: Unit_Course object instantiated to invoke this method to display the information</p>
     * 
     * <p>Post-condition: Displays the Unit Enrolment Type, Unit ID, Overall Marks, and Final Grade.</p>
     */
    @Override
    public void reportGrade(){
        System.out.println("Unit Enrolment Type: " + this.getEnrolmentType());
        System.out.println("Unit ID: " + this.unitID);
        System.out.printf("OverallMarks: %.1f\n",this.getOverallMarks());
        System.out.println("Final Grade: " + this.getFinalGrade(this.getOverallMarks()));
    } 

    
    /**
     * Getter method returns the Unit Course's unique identifier.
     * @return unitID : The Unit Course's unique identifier.
     */
    public String getUnitID() {
        return this.unitID;
    }

    
    /**
     * Getter method returns the Unit Course's level.
     * @return level : The Unit Course's level.
     */    
    public int getLevel() {
        return this.level;
    }

    
    /**
     * Getter method returns the mark received for Assessment 1.
     * @return a1 : The mark received for Assessment 1.
     */    
    public double getA1() {
        return this.a1;
    }
    
    
    /**
     * Getter method returns the mark received for Assessment 2.
     * @return a2 : The mark received for Assessment 2.
     */
    public double getA2() {
        return this.a2;
    }

    
    /**
     * Getter method returns the mark received for the final exam.
     * @return exam : The mark received for the final exam.
     */
    public double getExam() {
        return this.exam;
    }
    
    
}
