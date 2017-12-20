
package functionaljava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * These are some demos of Functional Java constructs applied over Lists
 * of Objects.
 * 
 * @author BC
 */
public class FunctionalJavaWithObjects {

    // This will throw an Exception if the List is empty.
    static double averageFinalGrade(List<Student> students) {
        return students.stream().map(s -> s.getFinalGrade()).mapToInt(i -> i).average().getAsDouble();
    }
    
    // This will throw an Exception if the List is empty.
    static double averageFinalGradeOfApprovedStudents(List<Student> students) {
        return students.stream().filter(s -> s.isApproved()).map(s -> s.getFinalGrade()).mapToInt(i -> i).average().getAsDouble();
    }
    
    static long countApprovedStudents(List<Student> students) {
        return students.stream().filter(s -> s.isApproved()).count();
    }
    
    static long countFailedStudents(List<Student> students) {
        return students.stream().filter(s -> !s.isApproved()).count();
    }
    
    static boolean allStudentsApproved(List<Student> students) {
        // Similar to "every" in Common Lisp
        return students.stream().allMatch(s -> s.isApproved());
    }

    // This method will receive a Predicate (function) that says if the Student
    // must be considered
    static int maxFinalGrade(List<Student> students, Predicate<Student> relevantStudent) {
        return students.stream().filter(s -> relevantStudent.test(s)).mapToInt(s -> s.getFinalGrade()).max().getAsInt();
    }
    
    // First usage 
    // (the Predicate is a method from the Student class)
    static int maxFinalGradeOfApprovedStudents(List<Student> students) {
        return maxFinalGrade(students, Student::isApproved);
    }
    
    // Second usage
    // Declare the Predicate "on the fly"
    // We could also assign it to a variable:
    //   static Predicate<Student> isFailedStudent = (Student s) -> !s.isApproved();
    // and then use the variable in the call:
    //   maxFinalGrade(students, isFailedStudent);
    
    static int maxFinalGradeOfFailedStudents(List<Student> students) {
        return maxFinalGrade(students, (Student s) -> !s.isApproved());
    }
    
    // A Demo to Rule them All
    static void demoStudentsInfo() {
        
        List<Student> students = new ArrayList<>();
        
        Student s1 = new Student ();
        s1.setTestGrades(10, 10);
        Student s2 = new Student ();
        s2.setTestGrades(10, 15);
        Student s3 = new Student ();
        s3.setTestGrades(8, 12);
        Student s4 = new Student ();
        s4.setTestGrades(8, 11);
        
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        
        System.out.println(Arrays.toString(students.toArray()));
        
        System.out.println("Average all students: " + averageFinalGrade(students));
        System.out.println("Average approved students: " + averageFinalGradeOfApprovedStudents(students));
        System.out.println("Number approved students: " + countApprovedStudents(students));
        System.out.println("Number failed students: " + countFailedStudents(students));
        System.out.println("All Class Approved: " + allStudentsApproved(students));
        System.out.println("Max final grade (Approved): " + maxFinalGradeOfApprovedStudents(students));
        System.out.println("Max final grade (Failed): " + maxFinalGradeOfFailedStudents(students));
        
    }
   
}
