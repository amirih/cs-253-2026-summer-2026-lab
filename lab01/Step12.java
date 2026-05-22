package lab01;
// import sleep() and current timestamp for timing the algorithms

public class Step12 {
    // Step 11: Big O notation and algorithm analysis
    static int[] array = getArray(1000);

    public static int[] getArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static int method1(int n) {
        return array[0]; // O(1)
    }

    public static int method2(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count++; // O(n)
        }
        return count;
    }

    public static int method3(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count++; // O(n^2)
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 1000;

        // O(1) - constant time
        long startTime = System.nanoTime();
        int constantTime = method1(n);
        long endTime = System.nanoTime();
        System.out.println("O(1): " + constantTime + " (Time: " + (endTime - startTime) + " ns)");

        // O(n) - linear time
        startTime = System.nanoTime();
        int linearTime = method2(n);
        endTime = System.nanoTime();
        System.out.println("O(n): " + linearTime + " (Time: " + (endTime - startTime) + " ns)");

        // O(n^2) - quadratic time
        startTime = System.nanoTime();
        int quadraticTime = method3(n);
        endTime = System.nanoTime();
        System.out.println("O(n^2): " + quadraticTime + " (Time: " + (endTime - startTime) + " ns)");
    }

}
