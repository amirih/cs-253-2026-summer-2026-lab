package lab07;

import java.util.HashMap;
import java.util.Map;

/**
 * Lab Step 04: Boyer-Moore search with looking-glass and character-jump
 * heuristics.
 * Concept: compare pattern from right to left, then shift using last
 * occurrence.
 */
public class Step04BoyerMooreSearch {
    private static Map<Character, Integer> buildLastOccurrence(String pattern) {
        Map<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            last.put(pattern.charAt(i), i);
        }
        return last;
    }

    public static int findFirst(String text, String pattern) {
        if (text == null || pattern == null) {
            throw new IllegalArgumentException("text and pattern must not be null");
        }
        int n = text.length();
        int m = pattern.length();
        if (m == 0)
            return 0;
        if (m > n)
            return -1;

        Map<Character, Integer> last = buildLastOccurrence(pattern);
        int i = m - 1; // index in text aligned with end of pattern
        int k = m - 1; // index in pattern

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(k)) {
                if (k == 0)
                    return i;
                i--;
                k--;
            } else {
                int j = last.getOrDefault(text.charAt(i), -1);
                i += m - Math.min(k, j + 1);
                k = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findFirst("abacaabaccabacabaaabb", "abacab"));
        System.out.println(findFirst("the quick brown fox", "brown"));
    }
}
