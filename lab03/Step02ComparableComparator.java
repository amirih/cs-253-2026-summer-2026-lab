package lab03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Comparable vs Comparator
 * Concept: natural ordering uses compareTo; custom ordering uses Comparator.compare.
 */
public class Step02ComparableComparator {
    static class Student implements Comparable<Student> {
        private final String name;
        private final double gpa;

        Student(String name, double gpa) {
            this.name = name;
            this.gpa = gpa;
        }

        public int compareTo(Student other) {
            return this.name.compareTo(other.name); // natural ordering by name
        }

        public String toString() {
            return name + "(" + gpa + ")";
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Mike", 3.7));
        students.add(new Student("Alice", 3.5));
        students.add(new Student("Sarah", 3.9));

        students.sort(null);
        System.out.println("Natural order by name: " + students);

        Comparator<Student> byGpaDescending = new Comparator<Student>() {
            public int compare(Student a, Student b) {
                return Double.compare(b.gpa, a.gpa);
            }
        };
        students.sort(byGpaDescending);
        System.out.println("Comparator order by GPA descending: " + students);

        List<String> words = new ArrayList<>(List.of("heap", "priority", "map", "hash"));
        words.sort(Comparator.comparingInt(String::length));
        System.out.println("Strings by length: " + words);
    }
}
