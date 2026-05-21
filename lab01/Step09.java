package lab01;

import java.util.HashMap;
import java.util.Map;

public class Step09 {
    // Step 09: exceptions and collections

    static class GradeBook {
        private final Map<String, Integer> grades = new HashMap<>();

        void addGrade(String student, int grade) {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("Grade must be between 0 and 100.");
            }
            grades.put(student, grade);
        }

        int getGrade(String student) {
            if (!grades.containsKey(student)) {
                throw new IllegalArgumentException("No grade found for " + student);
            }
            return grades.get(student);
        }
    }

    public static void main(String[] args) {
        GradeBook book = new GradeBook();
        book.addGrade("Ava", 95);
        book.addGrade("Noah", 88);

        try {
            System.out.println("Ava: " + book.getGrade("Ava"));
            System.out.println("Mia: " + book.getGrade("Mia"));
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
