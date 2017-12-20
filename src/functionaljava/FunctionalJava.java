
package functionaljava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Some experiments with Java 8's Functional Programming constructs.
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
    
    // Non-Destructive (using Stream) "mapping" operation
    static List<Integer> divideBy(List<Integer> numbers, int d) {
        return numbers.stream().map(n -> n / d).collect(Collectors.toList());
    }
    
    public static void main(String[] args) {

        // multiplyBy
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(33);
        numbers.add(42);
        System.out.println("Before:" + Arrays.toString(numbers.toArray()));
        multiplyBy(numbers, 3);
        System.out.println("After:" + Arrays.toString(numbers.toArray()));
        
        // removeNegatives
        List<Integer> moreNumbers = new ArrayList<>();
        for(int i=-3; i<=3; i++) {
            moreNumbers.add(i);
        }
        System.out.println("Before:" + Arrays.toString(moreNumbers.toArray()));
        removeNegatives(moreNumbers);
        System.out.println("After:" + Arrays.toString(moreNumbers.toArray()));
        
        // addSuffix
        List<String> words = new ArrayList<>();
        words.add("Peace");
        words.add("Love");
        words.add("Music");
        System.out.println("Before:" + Arrays.toString(words.toArray()));
        addSuffix(words, " Lover");
        System.out.println("After:" + Arrays.toString(words.toArray()));
        
        // divideBy
        List<Integer> numbers1 = new ArrayList<>();
        numbers1.add(10);
        numbers1.add(20);
        numbers1.add(30);
        List<Integer> numbers2 = divideBy(numbers1, 2);
        System.out.println("Before:" + Arrays.toString(numbers1.toArray()));
        addSuffix(words, " Lover");
        System.out.println("After:" + Arrays.toString(numbers2.toArray()));
        
    }
    
}
