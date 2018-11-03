
package functionaljava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
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
    
    // Iteration with forEach (and Consumers)
    static void displayStudentsPersonalInfo(List<Student> students) {
        // forEach receives a "Consumer", which is a function that receives a 
        // single argument and does not return anything
        
        // A simple one-line Consumer
        students.forEach(Student::display);
        
        System.out.println("-----");
        // A Consumer with multiple lines, declared using a block
        Consumer<Student> c = (Student s) -> 
        {
            s.display();
            System.out.println("  Grade: " + s.getFinalGrade());
        };
        
        students.forEach(c);
    }
    
    // A Demo to Rule them All
    static void demoStudentsInfo() {
        
        List<Student> students = new ArrayList<>();
        
        Student s1 = new Student ("B");
        s1.setTestGrades(10, 10);
        Student s2 = new Student ("M");
        s2.setTestGrades(10, 15);
        Student s3 = new Student ("P");
        s3.setTestGrades(8, 12);
        Student s4 = new Student ("C");
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
        
        System.out.println("--- Sort students by grade (ASC) ---");
        // Sort students by grade (ASC) using Lambda
        Collections.sort(students, (sA, sB) -> sA.getFinalGrade() - sB.getFinalGrade());
        displayStudentsPersonalInfo(students);
        
        System.out.println("---");
        System.out.println("--- Sort students by name (ASC) ---");
        
        // Sort students by name (ASC) using Lambda
        Collections.sort(students, (sA, sB) -> sA.getName().compareTo(sB.getName()));
        displayStudentsPersonalInfo(students);
        
        students.stream()
                .filter(s -> s.isApproved())
                .sorted((sA, sB) -> sA.getName().compareTo(sB.getName()))
                .forEach(s -> System.out.println(s.getName() + " " + s.getFinalGrade()));

     
        // Students that failed although they have the minimum intermediate test grades
        System.out.println("Failed but have min intermediate grades");
        students.stream()
                .filter(s -> !s.isApproved() && s.getGrade1() >= 8 && s.getGrade2() >= 8)
                .forEach(s -> System.out.println(s.toString()));
    }

}
