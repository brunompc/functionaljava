
package functionaljava;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * In this demo, Java's functional constructs are used to get the contents
 * from a text file.
 * 
 * @author BC
 */
public class FunctionalJavaWithFiles {
    
    static List<Student> readStudentsFileToList() {

        List<Student> students = null;
        
        String filename = "students.txt";
        
        try {
        
            BufferedReader reader = new BufferedReader(new FileReader(filename));
        
            students = reader.lines()
                .map((line) -> line.split(":"))
                .filter((String[] parts) -> parts.length == 3)
                .map((String[] parts) -> {
                    String name = parts[0];
                    int grade1 = Integer.parseInt(parts[1].trim());
                    int grade2 = Integer.parseInt(parts[2].trim());
                    if(grade1 < 0 || grade1 > 20 || grade2 < 0 || grade2 > 20) {
                        return null;
                    }
                    return new Student(name, grade1, grade2);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
            
        }
        catch(FileNotFoundException e) {
            System.out.println("File " + filename + " was no found.");
        }
        
        return students;
    }
    
    public static void demoReadFile() {
        List<Student> students = readStudentsFileToList();
        if (students != null) {
            System.out.println("Nr of Students: " + students.size());
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    
}
