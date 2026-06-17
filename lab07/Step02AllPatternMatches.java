package lab07;

import java.util.ArrayList;
import java.util.List;

/**
 * Lab Step 02: Find all pattern matches using brute force.
 * Concept: same matching idea as Step 01, but collect every valid start index.
 */
public class Step02AllPatternMatches {
    public static List<Integer> findAll(String text, String pattern) {
        if (text == null || pattern == null) {
            throw new IllegalArgumentException("text and pattern must not be null");
        }
        List<Integer> matches = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if (m == 0) {
            for (int i = 0; i <= n; i++)
                matches.add(i);
            return matches;
        }

        for (int start = 0; start <= n - m; start++) {
            int k = 0;
            while (k < m && text.charAt(start + k) == pattern.charAt(k)) {
                k++;
            }
            if (k == m)
                matches.add(start);
        }
        return matches;
    }

    public static void main(String[] args) {
        System.out.println(findAll("aaaaa", "aa"));
        System.out.println(findAll("banana", "ana"));
    }
}
