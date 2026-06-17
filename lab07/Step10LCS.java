package lab07;

/**
 * Lab Step 10: Longest Common Subsequence.
 * Concept: dynamic programming table for prefixes, then backtrack to
 * reconstruct one LCS.
 */
public class Step10LCS {
    public static int length(String x, String y) {
        return table(x, y)[x.length()][y.length()];
    }

    public static String lcs(String x, String y) {
        int[][] dp = table(x, y);
        StringBuilder result = new StringBuilder();
        int i = x.length();
        int j = y.length();

        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                result.append(x.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return result.reverse().toString();
    }

    private static int[][] table(String x, String y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("strings must not be null");
        }
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String x = "ABCBDAB";
        String y = "BDCABA";
        System.out.println(length(x, y));
        System.out.println(lcs(x, y));

        System.out.println(length("pbcdq", "pcq"));
        System.out.println(lcs("pbcdq", "pcq"));
    }
}
