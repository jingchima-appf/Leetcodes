package thumbtackPrepare;

import java.util.HashMap;
import java.util.Map;

public class MinSubstringCoversAll {
    public String minWindow(String s, String t) {
        // [left, right)
        /*
        if not covers all
            then right++
        else
            try left++ until it doesn't covers all
            update global_left and global_right

        A B C
        */
        int left = 0;
        int right = 0;
        int globalLeft = 0;
        int globalRight = 0;
        Map<Character, Integer> toMatch = getMap(t);
        int count = toMatch.size();
        while (right < s.length()) { // invariant: count will never be 0
            count = addToMap(toMatch, s.charAt(right), count);
            right++;
            if (count == 0) {
                while (count == 0) {
                    count = removeFromMap(toMatch, s.charAt(left), count);
                    left++;
                }
                // [left-1, right) will cover all
                if (globalLeft == 0 && globalRight == 0 || right - left + 1 < globalRight - globalLeft) {
                    globalLeft = left - 1;
                    globalRight = right;
                }
            }
        }
        return s.substring(globalLeft, globalRight);
    }

    private int addToMap(Map<Character, Integer> toMatch, char c, int count) {
        Integer num = toMatch.get(c);
        if (num != null) {
            toMatch.put(c, num - 1);
            if (num == 1) {
                count--;
            }
        }
        return count;
    }

    private int removeFromMap(Map<Character, Integer> toMatch, char c, int count) {
        Integer num = toMatch.get(c);
        if (num != null) {
            toMatch.put(c, num + 1);
            if (num == 0) {
                count++;
            }
        }
        return count;
    }

    private Map<Character, Integer> getMap(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int num = map.getOrDefault(t.charAt(i), 0);
            map.put(t.charAt(i), num + 1);
        }
        return map;
    }
}


