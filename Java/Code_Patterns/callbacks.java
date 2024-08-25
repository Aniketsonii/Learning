package Learning.Java.Code_Patterns;
import java.util.function.Function;

/**
 * callbacks
 */
public class callbacks {


    public static void main(String[] args) {
        Function<Integer, Integer> squareFunction = x -> x * x;

        // Pass the square function as an argument to the map() method
        int[] numbers = {1, 2, 3, 4, 5};
        int[] squares = map(numbers, squareFunction);

        // Print the squares
        for (int square : squares) {
            System.out.println(square);
        }
    }

    public static int[] map(int[] numbers, Function<Integer, Integer> function) {
        int[] results = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            results[i] = function.apply(numbers[i]);
        }
        return results;
    }
}