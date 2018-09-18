package OAs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OA {

    List<String> getSubsequence(String s) {
        List<String> results = new ArrayList<>();
        dfs(s, 0, new StringBuilder(), results);
        Collections.sort(results);
        return results;
    }

    private void dfs(String s, int start, StringBuilder path, List<String> results) {
        if (start >= s.length()) {
            return;
        }
        for (int i = start; i < s.length(); i++) {
            path.append(s.charAt(i));
            results.add(path.toString());
            dfs(s, i+1, path, results);
            path.deleteCharAt(path.length()-1);
        }
    }

    // Given a string s, find the subsequence with maximum length that has at most k normal values

    int maxSubSequenceLen(String s, int k, String charValue) {
        int m = s.length();
        int n = k;
        int[][] dp = new int[m+1][n+1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (k = 0; k <= n; k++) {
                for (int j = 0; j < i; j++) {
                    if (isNormal(s.charAt(i-1), charValue)) {
                        if (k == 0) {
                            dp[i][k] = 0;
                        } else {
                            dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + 1);
                        }
                    } else {
                        dp[i][k] = Math.max(dp[i][k], dp[j][k] + 1);
                    }
                }
                System.out.print(dp[i][k] + " ");
                max = Math.max(max, dp[i][k]);
            }
            System.out.println();
        }
        return max;
    }

    // Given a string s, find the longest substring that has at most k normal values

    int maxSubstringLen(String s, int k, String charValue) {
        int max = 0;
        int count = 0;
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            while (count < k && end < s.length()) {
                if (isNormal(s.charAt(end), charValue)) {
                    count++;
                }
                end++;
            }
            int len = end - start;
            max = Math.max(max, len);
            if (isNormal(s.charAt(start), charValue)) {
                count--;
            }
            start++;
        }
        return max;
    }

    private boolean isNormal(char c, String charValue) {
        return charValue.charAt(c - 'a') == '0';
    }

    public static void main(String[] args) {
        OA oa = new OA();
        // System.out.println(oa.getSubsequence("jlda"));
        String s = "giraffe";
        int k = 2;         // abcdefghijklmnopqrstuvwxyz
        String charValue = "01111001111111111011111111";
//        s = "special";
//        k = 1;
//        charValue = "00000000000000000000000000";
        System.out.println(oa.maxSubstringLen(s, k, charValue));
    }
}
