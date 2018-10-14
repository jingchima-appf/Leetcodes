package DPI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DPI {

    // Question 1: longest increasing subarray
    public int longestIncreasingSubarray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        // 1 5 3 2 4 5
        // [left, right) increasing
        int left = 0;
        int right = 1;
        int max = 1;
        while (right < array.length) {
            if (array[right] < array[right - 1]) {
                left = right;
            }
            right++;
            max = Math.max(max, right - left);
        }
        return max;
    }

    // Question 2: maximal product when cutting ropes
    int max = 0;
    public int cutRopes(int length) {
        dfs(length, 0, 1);
        return max;
    }
    private void dfs(int length, int start, int prev) {
        if (start >= length - 1) { // no need to cut anymore
            return;
        }
        for (int i = start + 1; i < length; i++) { // i is the cut idx
            max = Math.max(max, prev * (i - start) * (length - i));
            dfs(length, i, prev * (i - start));
        }
    }

    /*
    Method II: DP
     */
    // dp[i]: the maximum product with length = i and at least one cut
    // _ _ _ _
    // cut index = j, then [0, j) [j, length)
    // dp[i] = Max { max(dp[j], j) * (lenth - j) } over j, j = 1: length-1

    public int cutRopesDP(int length) {
        if (length < 2) {
            return -1; // invalid
        }
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= length; i++) {
            for (int j = 1; j < i; j++) { // j is the length of first part
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * (i - j));
            }
        }
        return dp[length];
    }



    // Follow-up: Word-Break
    public boolean wordBreak(String s, List<String> dict) {
        Set<String> set = getSet(dict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                dp[i] |= ( dp[j] && set.contains(s.substring(j, i)) );
            }
        }
        return dp[s.length()];
    }
    private Set<String> getSet(List<String> dict) {
        Set<String> set = new HashSet<>();
        set.addAll(dict);
        return set;
    }
    
    // Question 3: Frog Jump
    // can jump at most A[i] steps => can directly use greedy
    public boolean canReachEnd(int[] array) {
        int farthest = 0;
        for (int i = 0; i < array.length; i++) {
            if (farthest < i) {
                return false; // can’t jump to index i
            }
            farthest = Math.max(farthest, i + array[i]);
        }
        return farthest >= array.length - 1;
    }


    public static void main(String[] args) {
        DPI dpi = new DPI();
        assert dpi.longestIncreasingSubarray(new int[] {1, 5, 3, 2, 4, 5}) == 3;
        assert dpi.longestIncreasingSubarray(new int[] {1, 5, 3, 3, 3, 5}) == 4;

//        System.out.println(dpi.cutRopes(25));
//        System.out.println(dpi.cutRopesDP(25));

    }
}
