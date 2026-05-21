package lab01;

public class Step02 {
    // Step 02: classes and objects

    static class Student {
        String name;
        int age;

        void introduce() {
            System.out.println("I am " + name + " and I am " + age + " years old.");
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ava";
        s1.age = 20;

        Student s2 = new Student();
        s2.name = "Noah";
        s2.age = 22;

        s1.introduce();
        s2.introduce();
    }
}
