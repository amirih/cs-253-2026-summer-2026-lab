package lab07;

/**
 * Lab Step 06: Knuth-Morris-Pratt string search.
 * Concept: use the failure function to avoid rechecking characters after a
 * mismatch.
 * Time: O(n + m).
 */
public class Step06KMPSearch {
    private static int[] buildFailureFunction(String pattern) {
        int[] fail = new int[pattern.length()];
        int j = 1;
        int k = 0;
        while (j < pattern.length()) {
            if (pattern.charAt(j) == pattern.charAt(k)) {
                fail[j++] = ++k;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                fail[j++] = 0;
            }
        }
        return fail;
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

        int[] fail = buildFailureFunction(pattern);
        int i = 0;
        int k = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(k)) {
                if (k == m - 1)
                    return i - m + 1;
                i++;
                k++;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                i++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findFirst("abacaabaccabacabaaabb", "abacab"));
        System.out.println(findFirst("aaaaab", "aaab"));
    }
}
