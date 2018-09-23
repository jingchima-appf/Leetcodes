package strings;

import java.util.*;

public class SlidingWindows {
    public int longestSubstring(String s) {
        int left = 0;
        int right = 0;
        Set<Character> characters = new HashSet<>();
        int max = 0;
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (characters.add(cur)) {
                max = Math.max(max, right - left + 1);
            } else {
                while (left < right && s.charAt(left) != cur) {
                    characters.remove(s.charAt(left));
                    left++;
                }
                if (left < right) { // need to check if need to skip current left
                    left++;
                }
            }
            right++;
        }
        return max;
    }

    // source = google, target = go, result = <go, og>
    public List<String> findAnagrams(String source, String target) {

        Map<Character, Integer> map = getMap(target);
        int toMatch = map.size();
        int left = 0;
        int right = 0; // [left, right] to investigate
        List<String> results = new ArrayList<>();
        while (right < source.length()) {
            char cur = source.charAt(right);
            Integer occ = map.get(cur);
            if (occ == null) {
                while (left < right) {
                    toMatch = restore(map, source.charAt(left), toMatch);
                    left++;
                }
                right++;
                left++;
            } else {
                if (occ == 0) {
                    while (left < right && source.charAt(left) != cur) {
                        toMatch = restore(map, source.charAt(left), toMatch);
                        left++;
                    }
                    if (left < right) {
                        toMatch = restore(map, source.charAt(left), toMatch);
                        left++;
                    }
                }

                // note that occ can't be used anymore because the map is updated!!
                occ = map.get(cur);
                map.put(cur, occ - 1);
                right++;
                if (occ == 1) {
                    toMatch--;
                }
                if (toMatch == 0) {
                    results.add(source.substring(left, right));
                }
            }
        }
        return results;
    }

    private int restore(Map<Character, Integer> map, char c, int toMatch) {
        Integer occ = map.get(c);
        if (occ == null) {
            return toMatch;
        } else {
            map.put(c, occ + 1);
            if (occ == 0) {
                toMatch++;
            }
        }
        return toMatch;
    }

    public List<String> findAllAnagrams(String source, String target) {

        // maintain a sliding window of size target.length()
        // state: map => number of each character to match
        // state: toMatch => # characters haven’t matched yet

        Map<Character, Integer> map = getMap(target);
        int toMatch = map.size();
        List<String> results = new ArrayList<>();
        for (int i = 0; i < source.length(); i++) {
            char cur = source.charAt(i);
            Integer occ = map.get(cur);
            // add rightmost character
            if (occ != null) {
                map.put(cur, occ - 1);
                if (occ == 1) {
                    toMatch--;
                } else if (occ == 0) {
                    toMatch++;
                }
            }

            // remove the leftMost character is size exceeds
            if (i >= target.length()) {
                int leftIdx = i - target.length();
                cur = source.charAt(leftIdx);
                occ = map.get(cur);
                if (occ != null) {
                    map.put(cur, occ + 1);
                    if (occ == 0) {
                        toMatch++;
                    } else if (occ == -1) {
                        toMatch--;
                    }
                }
            }
            if (toMatch == 0) {
                results.add(source.substring(i - target.length() + 1, i + 1));
            }
        }
        return results;
    }

    private Map<Character, Integer> getMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        return map;
    }


    private int restoreMapAndCounter(Map<Character, Integer> map, char cur, int counter) {
        Integer occ = map.get(cur);
        if (occ == 0) {
            counter++;
        }
        map.put(cur, occ + 1);
        return counter;
    }


    public static void main(String[] args) {
        SlidingWindows slidingWindows = new SlidingWindows();
        // System.out.println(slidingWindows.longestSubstring("google"));

        System.out.println(slidingWindows.findAnagrams("google", "go"));
    }

}
