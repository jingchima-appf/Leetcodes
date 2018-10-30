package thumbtackPrepare;

import java.util.*;

public class MaxKPairs {

    static class Pair implements Comparable<Pair> {
        int num;
        int index;
        public Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }
        @Override
        public int compareTo(Pair another) {
            return this.num - another.num;
        }

        @Override
        public String toString() {
            return "(" + num + "," + index + ")";
        }
    }

    public static List<Integer> maxKSums(int[] nums, int l, int k) {
        // step 1: sort the array -> storing original index
        // step 2: do bfs for index
        /*
        starting point: <0,1>
        expand / generate rule:
            <i,j> -> <i+1, j> <i, j+1> // indices in pairs array
            check if <i, j> --> j - i + 1 >= l
        termination condition: k == 0

        forgot to deduplicate!!!
         */

        //
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        Arrays.sort(pairs, Collections.reverseOrder());
        for (Pair p: pairs) {
            System.out.print(p);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() { // use PriorityQueue instead of Queue!!!!!
            @Override
            public int compare(int[] a, int[] b) {
                int sum1 = pairs[a[0]].num + pairs[a[1]].num;
                int sum2 = pairs[b[0]].num + pairs[b[1]].num;
                return sum2 - sum1;
            }
        });
        queue.offer(new int[] {0, 1});
        boolean[][] visited = new boolean[pairs.length][pairs.length];
        visited[0][1] = true;
        List<Integer> results = new ArrayList<>();
        while (!queue.isEmpty() && k > 0) { // forgot to deduplicate!
            int[] cur = queue.poll();
            int index1 = pairs[cur[0]].index;
            int index2 = pairs[cur[1]].index;
            if (Math.abs(index1 - index2) >= l) {
                k--;
                results.add(pairs[cur[0]].num + pairs[cur[1]].num);
            }
            if (cur[0] + 1 < pairs.length && !visited[cur[0] + 1][cur[1]]) {
                queue.offer(new int[] {cur[0] + 1, cur[1]});
                visited[cur[0] + 1][cur[1]] = true;
            }
            if (cur[1] + 1 < pairs.length && !visited[cur[0]][cur[1] + 1]) {
                queue.offer(new int[] {cur[0], cur[1] + 1});
                visited[cur[0]][cur[1] + 1] = true;
            }
        }
        return results;
    }
}
