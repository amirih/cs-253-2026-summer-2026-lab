package lab07;

import java.util.Arrays;

/**
 * Lab Step 05: KMP preprocessing.
 * Concept: compute the failure function so matched prefix information can be
 * reused.
 */
public class Step05KMPFailureFunction {
    public static int[] buildFailureFunction(String pattern) {
        if (pattern == null)
            throw new IllegalArgumentException("pattern must not be null");
        int[] fail = new int[pattern.length()];
        int j = 1;
        int k = 0;

        while (j < pattern.length()) {
            if (pattern.charAt(j) == pattern.charAt(k)) {
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                fail[j] = 0;
                j++;
            }
        }
        return fail;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(buildFailureFunction("abacab")));
        System.out.println(Arrays.toString(buildFailureFunction("ababaca")));
    }
}
