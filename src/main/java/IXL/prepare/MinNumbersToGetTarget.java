package IXL.prepare;

import java.util.*;

public class MinNumbersToGetTarget {

    /*
    dp[i]: the minimum # words to construct target[0: i), where i is the length of substring
    dp[0] = 0
    [0, j) [j, end)
    dp[i] = min {dp[j]} + 1  for any j in [0, i)
     */
    public int wordNum(Set<String> dict, String target) {
        int[] dp = new int[target.length() + 1];
        // dp[0] = 0;
        for (int i = 1; i <= target.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dict.contains(target.substring(j, i))) {
                    continue;
                }
                if (dp[i] == 0 || dp[i] > dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return dp[target.length()] == 0 ? -1 : dp[target.length()];
    }


    // Map<Character, Integer> : to characters to match//   characters in target => # occurrences
    // count: how many characters still needs to be covered.
    // dfs
    // dict.length() levels
    // each level represents if we use the current word or not
    // if use it, then update the Map
    public int minWords(List<String> dict, String target) {
        Map<Character, Integer> map = getMap(target);
        int toMatch = map.size();
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        dfs(dict, 0, 0, map, toMatch, min);
        return min[0];
    }

    private void dfs(List<String> dict, int index, int wordNum,
                     Map<Character, Integer> map, int toMatch, int[] min) {
        if (toMatch <= 0) {
            min[0] = Math.min(min[0], wordNum);
            return;
        }
        if (index == dict.size()) {
            return;
        }
        int matched = updateMap(map, dict.get(index));
        dfs(dict, index + 1, wordNum + 1, map, toMatch - matched, min);
        restoreMap(map, dict.get(index));
        dfs(dict, index + 1, wordNum, map, toMatch, min);
    }

    // return the number of characters being matched by cur
    private int updateMap(Map<Character, Integer> map, String cur) {
        int matched = 0;
        for (int i = 0; i < cur.length(); i++) {
            Integer num = map.get(cur.charAt(i));
            if (num == null) {
                continue;
            }
            map.put(cur.charAt(i), num - 1);
            if (num == 1) {
                matched++;
            }
        }
        return matched;
    }

    private void restoreMap(Map<Character, Integer> map, String cur) {
        for (int i = 0; i < cur.length(); i++) {
            Integer num = map.get(cur.charAt(i));
            if (num == null) {
                continue;
            }
            map.put(cur.charAt(i), num + 1);
        }
    }

    private Map<Character, Integer> getMap(String target) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            int num = map.getOrDefault(target.charAt(i), 0);
            map.put(target.charAt(i), num + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        MinNumbersToGetTarget instance = new MinNumbersToGetTarget();
        List<String> dict = new ArrayList<>();
        dict.add("a");
        dict.add("b");
        System.out.println(instance.minWords(dict, "ab"));
    }
}
