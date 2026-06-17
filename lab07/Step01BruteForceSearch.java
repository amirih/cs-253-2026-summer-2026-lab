package lab07;

/**
 * Lab Step 01: Brute-force string matching.
 * Concept: try every possible starting index in the text.
 * Time: O(n * m) in the worst case.
 */
public class Step01BruteForceSearch {
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

        for (int start = 0; start <= n - m; start++) {
            int k = 0;
            while (k < m && text.charAt(start + k) == pattern.charAt(k)) {
                k++;
            }
            if (k == m)
                return start;
        }
        return -1;
    }

    public static void main(String[] args) {
        String text = "abacaabaccabacabaaabb";
        String pattern = "abacab";
        System.out.println(findFirst(text, pattern));
    }
}
