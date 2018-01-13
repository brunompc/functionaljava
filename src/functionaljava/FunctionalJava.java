
package functionaljava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
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
    
    // Find the max() element of a List<Integer>
    static Integer max(List<Integer> numbers) {
        return numbers.stream().max((n1, n2) -> Integer.compare(n1, n2)).get();
    }
    
    // Find the min() element
    static Integer min(List<Integer> numbers) {
        return numbers.stream().min((n1, n2) -> Integer.compare(n1, n2)).get();
    }
    
    // Sum the elements of a List<Integer>
    static Integer sumAll(List<Integer> numbers) {
        return numbers.stream().mapToInt(n -> n).sum();
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
        
        // max and min
        List<Integer> grades = new ArrayList<>();
        grades.add(10);
        grades.add(12);
        grades.add(13);
        grades.add(15);
        grades.add(10);
        grades.add(19);
        grades.add(10);
        System.out.println("Grades:" + Arrays.toString(grades.toArray()));
        int max = max(grades);
        int min = min(grades);
        System.out.println("Max grade: " + max);
        System.out.println("Min grade: " + min);
        
        // sumAll
        List<Integer> numbers3 = new ArrayList<>();
        numbers3.add(1);
        numbers3.add(5);
        numbers3.add(11);
        int sum = sumAll(numbers3);
        System.out.println("Contents to Sum:" + Arrays.toString(numbers3.toArray()));
        System.out.println("Sum: " + sum);
        
        // Functional Java with Objects demos
        FunctionalJavaWithObjects.demoStudentsInfo();
        
        // Call getRandomNumbers to get numbers with some properties
        // use a Lambda to indicate if the number is "valid"

        // Lambda will return true for Even numbers
        List<Integer> evenNumbers = getRandomNumbers(3, 0, 15, n -> (n % 2 == 0));
        System.out.println("Even: " + Arrays.toString(evenNumbers.toArray()));
        
        // Lambda will return true for numbers that are not multiples of 3 or 5
        // (i.e. excludes 3, 5, 6, 9, 10, 12, 15, etc.)
        List<Integer> otherNumbers = getRandomNumbers(3, 0, 15, n -> (n % 3 != 0 && n % 5 != 0));
        System.out.println("Exclude multiples of 3 and 5: " + Arrays.toString(otherNumbers.toArray()));
        
        // Storing a Lambda in a variable
        Function<Integer, Boolean> isEvenNumber = (n) -> n % 2 == 0;
        getNumbers(2, isEvenNumber);
        
        // Enter the BiFunction
        int res1 = performIntCalculation(10, 20, (n1, n2) -> n1 + n2);
        int res2 = performIntCalculation(10, 20, (n1, n2) -> n1 - n2);
        int res3 = performIntCalculation(10, 20, (n1, n2) -> n1 * n2);
        int res4 = performIntCalculation(10, 20, (n1, n2) -> n1 / n2);
        System.out.println("Res1: " + res1);
        System.out.println("Res2: " + res2);
        System.out.println("Res3: " + res3);
        System.out.println("Res4: " + res4);
    }
 
    public static List<Integer> getRandomNumbers(int count, int min, int max, Function<Integer, Boolean> validateNumber) {
        List<Integer> result = new ArrayList<>();
        int nr = 0;
        Random generator = new Random();
        while(nr < count) {
            int temp = min + generator.nextInt(max);
            if(validateNumber.apply(temp)) {
                result.add(temp);
                nr++;
            }
        }
        return result;
    }
    
    // getNumbers(3, (n) -> n % 2)
    // getNumbers(3, (n) -> n >= 18);
    public static List<Integer> getNumbers(int amount, Function<Integer, Boolean> validateNumber) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        List<Integer> result = new ArrayList<>();
        while(count < amount) {
            System.out.println("Insert an number:");
            int nr = sc.nextInt();
            if(validateNumber.apply(nr)) {
                result.add(nr);
                count++;
            }
            else {
                System.out.println("Invalid number. Try again...");
            }
        }
        return result;
    }
    
    static Integer performIntCalculation(int a, int b, BiFunction<Integer, Integer, Integer> operator) {
        return operator.apply(a, b);
    }
}
