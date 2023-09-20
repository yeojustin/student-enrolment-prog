package a2;

/**
 * This class represents a Research unit and extends the Unit class.
 * It contains the proposal and final dissertation marks of the student enrolled in the Research unit.
 */
public class Research extends Unit{
    
    /**
     * The proposal marks of the student enrolled in the Research unit.
     */
    private double proposal;
    /**
     * The final dissertation marks of the student enrolled in the Research unit.
     */
    private double finalDissertation;

    
    /**
     * Constructor for the Research class.
     * @param proposal The proposal marks of the student enrolled in the Research unit.
     * @param finalDissertation The final dissertation marks of the student enrolled in the Research unit.
     */
    public Research(double proposal, double finalDissertation){
        super('R');
        this.proposal = proposal;
        this.finalDissertation = finalDissertation;
    }
  
    
    /**
     * Method to calculate the overall marks of the student in the Research unit.
     * 
     * Pre-condition:Research object instantiated to invoke this method to compute overall marks
     * <p>
     * Post-condition: Computes the overall marks
     * </p>
     * 
     * @return The overall marks of the student in the Research unit.
     */
    public double getOverallMarks(){
        return (this.proposal * 0.4) + (this.finalDissertation * 0.6);
    }
    

    /**
     * Method to override the 'reportGrade' method in the Student class to include the displaying of the Research unit marks information.
     * If there is no Research unit information, it displays "No unit information"
     * <p>
     * Pre-condition:
     * </p>
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
        System.out.println("Unit Enrolment Type: " + this.getEnrolmentType());
        System.out.printf("OverallMarks: %.1f\n",this.getOverallMarks());
        System.out.println("Final Grade: " + this.getFinalGrade(this.getOverallMarks()));
    }

    
    /**
     * Getter method to return the proposal marks of the student in the Research unit.
     * @return The proposal marks of the student in the Research unit.
     */
    public double getProposal() {
        return this.proposal;
    }

    
    /**
     * Getter method to return the final dissertation marks of the student in the Research unit.
     * @return The final dissertation marks of the student in the Research unit.
     */   
    public double getFinalDissertation() {
        return this.finalDissertation;
    }
    
}


 