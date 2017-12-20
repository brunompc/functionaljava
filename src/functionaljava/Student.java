
package functionaljava;

/**
 *
 * @author BC
 */
public class Student {
    private String name;
    private int grade1;
    private int grade2;
    
    public Student(String name) {
        this.name = name;
    }
    public void setTestGrades(int grade1, int grade2) {
        this.grade1 = grade1;
        this.grade2 = grade2;
    }
    
    // 
    // Passing rules:
    // - At least 8 in each test
    // - At least 10 in the final grade (non-rounded average)
    //  (Which means that 9.5 is not enough :p)
    //
    public boolean isApproved() {
        return grade1 >= 8 && grade2 >= 8 && getFinalGrade() >= 10;
    }
    
    // 
    // Final grades are integers [0; 20]
    //
    public int getFinalGrade() {
        return (grade1 + grade2) / 2;
    }
    
    @Override
    public String toString() {
        return grade1 + " " + grade2 + " " + getFinalGrade() + " " + isApproved();
    }
    
    public void display() {
        System.out.println("Name: " + name);
    }
    
    
}
