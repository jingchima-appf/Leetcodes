package DPII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Question 2: word break
public class DictionaryWordBreak {

    // DP[i]: if it is possible to break the substring[0,i)
    // base case: DP[0] = 0;
    // induction rule: DP[i] = OR { DP[j] && dict contains substring[j, i) } for j = 0 to i-1
    // cut at j => [0,j) and [j, i)
    //
    // int[i] to record the cutting location for each dp[i]
    // int[i] = the cutting index if substring[0,i] can be cut, -1 otherwise
    public List<String> breakWords(String input, Set<String> dict) {

        int[] dp = new int[input.length() + 1]; // i should be the length instead of the index
        dp[0] = 0;
        for (int i = 1; i <= input.length(); i++) {
            dp[i] = -1;
            for (int j = 0; j < i; j++) {
                if (dp[j] >= 0 && dict.contains(input.substring(j, i))) {
                    dp[i] = j;
                    break;
                }
            }
        }
        if (dp[input.length()] == -1) {
            return new ArrayList<>(); // can't be cut
        }
        int idx = input.length();
        List<String> results = new ArrayList<>();
        // idx: the length of substring [0, idx)
        // cutIdx: the cut idx for substring [0,i] cutIdx = dp[idx]
        // |
        while (idx > 0) { // if idx = 0, then cut index is 0, no left sides left so we can stop there
            int cutIdx = dp[idx];
            results.add(input.substring(cutIdx, idx));
            idx = cutIdx;
        }
        return results;
    }

    public static void main(String[] args) {
        DictionaryWordBreak instance = new DictionaryWordBreak();
        Set<String> set = new HashSet<>();
        set.add("good");
        set.add("bad");
        set.add("goodb");
        System.out.println(instance.breakWords("goodbad", set));
    }
}
