package IXLOA;

import java.util.*;

public class JungleBook {

    static class Solution {
        public static int jungle(int[] arr) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            Queue<Integer> queue = new LinkedList<Integer>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == -1) {
                    queue.offer(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    if (map.containsKey(arr[i])) {
                        list = map.get(arr[i]);
                    }
                    list.add(i);
                    map.put(arr[i], list);
                }
            }

            int res = 0;
            while (!queue.isEmpty()) {
                res++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    if (map.containsKey(cur)) {
                        for (int l : map.get(cur)) {
                            queue.offer(l);
                        }
                    }
                }

            }
            return res;
        }
    }


    public int minGroupNum(int[] species) {
        if (species == null || species.length == 0) {
            return 0;
        }
        int max = 1;
        for (int i = 0; i < species.length; i++) {
            max = Math.max(max, getPathLength(species, i));
        }
        return max;
    }

    private int getPathLength(int[] nums, int i) {
        int len = 0; // current length
        // i is the current species
        while (i != -1) { // legal species
            len++;
            i = nums[i];
        }
        return len;
    }

    public static void main(String[] args) {
        JungleBook instance = new JungleBook();
        int[] species = {-1, 2 ,4, 2, 3};
        //System.out.println(instance.minGroupNum(species));
        System.out.println(JungleBook.Solution.jungle(species));
    }

}
