package a2;

import java.io.*;
import java.util.*;

/**
 * The Main class executes the Student Enrolment program.
 * @author yeojustin
 */
public class Main {

        /**
         * This is the main method to run the actual Student Enrolment program
         * @param args
         */
        public static void main(String[] args) {
            //testArrayList();
            Client.run();
        }

        /**
         * This is a test method to:
         * Set up a student ArrayList of 10 Student objects and use ArrayList pre-written methods and
         * run a loop to test for instances of the Student objects in ArrayList
         */
        public static void testArrayList(){
            // Testing the program with 10 Student objects in the ArrayList

            // Declare and initialise 6 Student_Course and 4 Student_Research students 
            Student_Course sc1 = new Student_Course("Tom","Doe",555);
            Unit_Course uc1 = new Unit_Course("ICT189",1,65,72,58);
            sc1.setUnit(uc1);

            Student_Course sc2 = new Student_Course("Okuhara","Hirai",333);
            Unit_Course uc2 = new Unit_Course("ICT213",2,70,56,92);
            sc2.setUnit(uc2);

            Student_Course sc3 = new Student_Course("Yuta","Watanabe",111);
            Unit_Course uc3 = new Unit_Course("ICT123",2,70,80,76);
            sc3.setUnit(uc3);

            Student_Course sc4 = new Student_Course("Ame","Nozomi",777);
            Unit_Course uc4 = new Unit_Course("ICT201",2,90,66,94);
            sc4.setUnit(uc4);

            Student_Course sc5 = new Student_Course("Aaron","Yeoh",147);
            Unit_Course uc5 = new Unit_Course("ICT301",3,78,81,72);
            sc5.setUnit(uc5);

            Student_Research sr1 = new Student_Research("Shin","Lee",444);
            Research re1 = new Research(80,88);
            sr1.setResearchUnit(re1);

            Student_Research sr2 = new Student_Research("Eric","Jay",666);
            Research re2 = new Research(85,72);
            sr2.setResearchUnit(re2);

            Student_Research sr3 = new Student_Research("Gary","See",222);
            Research re3 = new Research(70,67);
            sr3.setResearchUnit(re3);

            Student_Research sr4 = new Student_Research("Ken","Tran",999);
            Research re4 = new Research(45,97);
            sr4.setResearchUnit(re4);

            Student_Research sr5 = new Student_Research("Ken","Tran",999);
            Research re5 = new Research(89,97);
            sr5.setResearchUnit(re5);

            // Add the 10 students into ArrayList in random order
            ArrayList<Student> stdAL = new ArrayList<Student>();
            stdAL.add(sc1); //student 1
            stdAL.add(sr3); //student 2
            stdAL.add(sc4); //student 3
            stdAL.add(sr4); //student 4
            stdAL.add(sr2); //student 5
            stdAL.add(sr5); //student 6
            stdAL.add(sc5); //student 7
            stdAL.add(sr1); //student 8
            stdAL.add(sc3); //student 9
            stdAL.add(sc2); //student 10

            // Check populated ArrayList size
            int n = stdAL.size();
            System.out.println("ArrayList size: "+n+"\n");

            // check for Student sub-classes in each element in ArrayList
            int scCount = 0;
            int srCount = 0;
            for(int i=0; i<stdAL.size(); i++){
                Student student = stdAL.get(i);
                if(student instanceof Student_Course){
                    System.out.println("Student in index "+i+" in ArrayList is a Coursework stduent ");
                    scCount++;
                }else if(student instanceof Student_Research){
                    System.out.println("Student in index "+i+" in ArrayList is a Research stduent ");
                    srCount++;
                }else{
                    System.out.println("No student found");
                }
                System.out.println();   
            }
            System.out.println("There are "+scCount+" Coursework students and "+srCount+" Research students");
            System.out.println("\nTEST COMPLETED");
        }
  
}
