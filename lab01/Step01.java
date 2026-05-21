package lab01;

public class Step01 {
    // Step 01: variables and methods

    static void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int age = 20;
        double price = 19.99;
        boolean isStudent = true;
        String name = "Ava";

        sayHello(name);
        System.out.println("Age: " + age);
        System.out.println("Price: " + price);
        System.out.println("Student: " + isStudent);
        System.out.println("2 + 3 = " + add(2, 3));
    }
}
