package IXL.prepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StickersToSpellWord {
//    public int minStickers(String[] stickers, String target) {
//        int[] min = {Integer.MAX_VALUE};
//        Map<Character, Integer> map = getMap(target);
//        dfs(stickers, 0, 0, map, min);
//        return min[0] == Integer.MAX_VALUE ? -1 : min[0];
//    }
//
//    private Map<Character, Integer> getMap(String target) {
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < target.length(); i++) {
//            int num = map.getOrDefault(target.charAt(i), 0);
//            map.put(target.charAt(i), num + 1);
//        }
//        return map;
//    }
//
//    /*
//    dfs
//    stickers.length levels
//    each level: # stickers[index]
//    Map<Character, Integer>
//    */
//
//    private void dfs(String[] stickers, int index, int num, Map<Character, Integer> map, int[] min) {
//        if (index == stickers.length) {
//            if (coversAll(map)) {
//                min[0] = Math.min(min[0], num);
//            }
//            return;
//        }
//        dfs(stickers, index + 1, num, map, min);
//        int count = 1;
//        boolean progressed = updateMap(map, stickers[index]);
//        num++;
//        while (progressed) {
//            dfs(stickers, index + 1, num, map, min);
//            progressed = updateMap(map, stickers[index]);
//            count++;
//            num++;
//        }
//        for (int i = 0; i < count; i++) {
//            restoreMap(map, stickers[index]);
//        }
//    }
//
//    // return the number of characters that we are making progress on
//    private boolean updateMap(Map<Character, Integer> map, String sticker) {
//        boolean progressed = false;
//        for (int i = 0; i < sticker.length(); i++) {
//            Integer num = map.get(sticker.charAt(i));
//            if (num != null) {
//                map.put(sticker.charAt(i), num - 1);
//                if (num > 0) {
//                    progressed = true;
//                }
//            }
//        }
//        return progressed;
//    }
//
//    private void restoreMap(Map<Character, Integer> map, String sticker) {
//        for (int i = 0; i < sticker.length(); i++) {
//            Integer num = map.get(sticker.charAt(i));
//            if (num != null) {
//                map.put(sticker.charAt(i), num + 1);
//            }
//        }
//    }
//
//    private boolean coversAll(Map<Character, Integer> map) {
//        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//            if (entry.getValue() > 0) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
    public int minStickersII(String[] stickers, String target) {
        int[] charOccurrences = new int[26];
        for (int i = 0; i < target.length(); i++) {
            charOccurrences[target.charAt(i) - 'a']++;
        }
        List<Integer> targetString = new ArrayList<>();
        for (int num: charOccurrences) {
            targetString.add(num);
        }
        Integer res = minStickerHelper(stickers, targetString, new HashMap<>());
        return res == null ? -1 : res;
    }


    // dfs with memorization.
    // stickers: stickers we use
    // index: the index for current sticker

    // target => the min # stickers --> cache
    // dfs with memorization
    // each level: the possible choices for the index-th sticker
    // iterate all the possible choices, and update the target, and do recursion for each choice:
    // what are possible choices? To avoid stack overflow, choose the stickers that can make progress. How to check making progress or not? try update
    // return min + 1
    // base case: 1. when the target <= 0.

    private Integer minStickerHelper(String[] stickers, List<Integer> target, Map<List<Integer>, Integer> cache) {
        if (cache.containsKey(target)) {
            return cache.get(target);
        }

        Integer number = null;
        for (int i = 0; i < stickers.length; i++) {
            if (updateTarget(target, stickers[i])) {
                Integer nextNum = minStickerHelper(stickers, target, cache);
                if (nextNum != null) {
                    if (number == null || nextNum + 1 < number) {
                        number = nextNum + 1;
                    }
                }
            }
            restoreTarget(target, stickers[i]);
        }
        if (isTargetCovered(target)) {
            number = 0;
        }
        cache.put(target, number);
        return number;
    }

    private boolean isTargetCovered(List<Integer> target) {
        for (Integer num : target) {
            if (num > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean updateTarget(List<Integer> target, String sticker) {
        boolean progressed = false;
        for (int i = 0; i < sticker.length(); i++) {
            int index = sticker.charAt(i) - 'a';
            Integer num = target.get(index);
            target.set(index, num - 1);
            if (num > 0) {
                progressed = true;
            }
        }
        return progressed;
    }

    private void restoreTarget(List<Integer> target, String sticker) {
        for (int i = 0; i < sticker.length(); i++) {
            int index = sticker.charAt(i) - 'a';
            Integer num = target.get(index);
            target.set(index, num + 1);
        }
    }

}
