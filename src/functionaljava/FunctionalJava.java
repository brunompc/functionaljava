
package functionaljava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author BC
 */
public class FunctionalJava {

    // Destructive "filtering" operation
    static void removeNegatives(List<Integer> numbers) {
        numbers.removeIf(n -> n < 0);
    }
    
    // Destructive "mapping" operation
    static void addSuffix(List<String> words, String suffix) {
        words.replaceAll(w -> w + suffix);
    }
    
    // Destructive "mapping" operation
    static void multiplyBy(List<Integer> numbers, int factor) {
        numbers.replaceAll(n -> n * factor);
    }
    
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(33);
        numbers.add(42);
        System.out.println("Before:" + Arrays.toString(numbers.toArray()));
        multiplyBy(numbers, 3);
        System.out.println("After:" + Arrays.toString(numbers.toArray()));
        
        List<Integer> moreNumbers = new ArrayList<>();
        for(int i=-3; i<=3; i++) {
            moreNumbers.add(i);
        }
        System.out.println("Before:" + Arrays.toString(moreNumbers.toArray()));
        removeNegatives(moreNumbers);
        System.out.println("After:" + Arrays.toString(moreNumbers.toArray()));
        
        List<String> words = new ArrayList<>();
        words.add("Peace");
        words.add("Love");
        words.add("Music");
        
        System.out.println("Before:" + Arrays.toString(words.toArray()));
        addSuffix(words, " Lover");
        System.out.println("After:" + Arrays.toString(words.toArray()));
        
    }
    
}
