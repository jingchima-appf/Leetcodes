package IXL.prepare;

import java.util.*;

public class StickersToSpellWordII {

    public int minStickers(String[] stickers, String target) {
        // BFS
        Queue<List<Integer>> queue = new ArrayDeque<>();
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> targetList = toList(target);
        queue.offer(targetList);
        set.add(targetList);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<Integer> cur = queue.poll();
                if (isEnd(cur)) {
                    return level;
                }
                for (String sticker: stickers) {
                    List<Integer> next = getUpdatedList(cur, sticker);
                    if (next != null && set.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private List<Integer> toList(String target) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            res.add(0);
        }
        for (int i = 0; i < target.length(); i++) {
            int index = target.charAt(i) - 'a';
            res.set(index, res.get(index) + 1);
        }
        return res;
    }

    private List<Integer> getUpdatedList(List<Integer> original, String sticker) {
        List<Integer> updated = new ArrayList<>(original);
        boolean progressed = false;
        for (int i = 0; i < sticker.length(); i++) {
            int index = sticker.charAt(i) - 'a';
            int value = updated.get(index);
            updated.set(index, value - 1);
            if (value > 0) {
                progressed = true;
            }
        }
        return progressed ? updated : null;
    }

    private boolean isEnd(List<Integer> cur) {
        for (Integer num : cur) {
            if (num > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StickersToSpellWordII instance = new StickersToSpellWordII();
        String[] stickers = {"notice","possible"};
        String target = "basicbasic";
        System.out.println(instance.minStickers(stickers, target));
    }
}