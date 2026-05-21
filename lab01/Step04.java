package lab01;

public class Step04 {
    // Step 04: inheritance and method overriding

    static class Animal {
        private String name;

        Animal(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        void speak() {
            System.out.println(name + " makes a sound.");
        }
    }

    static class Dog extends Animal {
        Dog(String name) {
            super(name);
        }

        @Override
        void speak() {
            System.out.println(getName() + " says woof!");
        }
    }

    static class Cat extends Animal {
        Cat(String name) {
            super(name);
        }

        @Override
        void speak() {
            System.out.println(getName() + " says meow!");
        }
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        Cat cat = new Cat("Luna");

        dog.speak();
        cat.speak();
    }
}
