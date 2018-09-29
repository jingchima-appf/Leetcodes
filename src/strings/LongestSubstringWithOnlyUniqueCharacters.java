package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithOnlyUniqueCharacters {
    public int longest(String input) {
        // abdad => abd

        // [left, right)
        // hashSet
        int left = 0;
        int right = 0;
        Set<Character> set = new HashSet<>();
        int max = 0;
        while (right < input.length()) {
            if ( !set.add(input.charAt(right)) ) { // make the substring legal
                while (input.charAt(left) != input.charAt(right) ) {
                    set.remove(input.charAt(left));
                    left++;
                }
                // set.remove(input.charAt(left)); no need to remove this! because you have to add it back for right !!
                left++; // just skip that one!
            }
            right++;
            max = Math.max(max, right - left);
        }
        return max;
    }



    // FOLLOW-UP: Leetcode 340
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k <= 0) {
            return 0;
        }
        // [left, right)
        // Set size <= k
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>(); // characters and its # occurrences
        int distinctNum = 0; // # distinct characters
        int max = 0;
        while (right < s.length()) {
            char cur = s.charAt(right);
            int num = map.getOrDefault(cur, 0); // NOTE HERE, unshown characters can have a value null or 0 so you can't use Integer num = map.get(cur)
            map.put(cur, num + 1);
            right++;
            if (num == 0) {
                distinctNum++;
            }
            while (distinctNum > k) {
                num = map.get(s.charAt(left));
                if (num == 1) {
                    distinctNum--;
                }
                map.put(s.charAt(left), num - 1);
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
