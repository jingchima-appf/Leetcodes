package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        dfs(nums, 0, results);
        return results;
    }

    private void dfs(int[] clustered, int index, List<List<Integer>> results) {
        if (index == clustered.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num: clustered) {
                permutation.add(num);
            }
            results.add(permutation);
            return;
        }
        int i = index;
        while (i < clustered.length) {
            swap(clustered, index, i);
            dfs(clustered, index+1, results);
            swap(clustered, index, i);
            i++;
            while (i < clustered.length && clustered[i] == clustered[i-1]) {
                i++;
            }
        }
    }

    public List<String> getPermutation(String set, int end) {
        if (end < 0) {
            List<String> results = new ArrayList<>();
            results.add("");
            return results;
        }
        List<String> results = getPermutation(set, end-1);
        int size = results.size();
        for (int i = 0; i < size; i++) {
            String cur = results.get(i);
            for (int j = 0; j <= cur.length(); j++) {
                results.add(cur.substring(0, j) + set.charAt(end) + cur.substring(j));
            }
        }
        return results;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 0, 9};
        Permutation permutation = new Permutation();
        // System.out.println(permutation.permuteUnique(nums));
        System.out.println(permutation.getPermutation("123", 2));
    }
}
