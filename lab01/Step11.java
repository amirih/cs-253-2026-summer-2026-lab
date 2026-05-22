package lab01;

public class Step11 {
    // Step 11: Recursive algorithms
    public static int factorial(int n) {
        if (n == 0) {
            return 1; // Base case
        }
        return n * factorial(n - 1); // Recursive case
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n; // Base cases
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // Recursive case
    }

    public static void main(String[] args) {
        int n = 5;

        System.out.println("Factorial of " + n + " is: " + factorial(n));
        System.out.println("Fibonacci of " + n + " is: " + fibonacci(n));
    }
}
