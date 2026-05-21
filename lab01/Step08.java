package lab01;

import java.util.ArrayList;
import java.util.List;

public class Step08 {
    // Step 08: generics

    static class Box<T> {
        private T value;

        void put(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }

    static class Printer {
        static <T> void printBox(Box<T> item) {
            System.out.println(item.get());
        }

        static <T> void printList(List<T> list) {
            for (T item : list) {
                System.out.println(item);
            }
        }
    }

    public static void main(String[] args) {
        Box<String> textBox = new Box<>();
        textBox.put("Hello Generics");
        Printer.printBox(textBox);

        Box<Integer> number = new Box<>();
        number.put(30);
        Printer.printBox(number);

        // Using generics with collections
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Generics");
        list.add("Java");
        Printer.printList(list);

        //
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        Printer.printList(numbers);
    }
}
