package IXLOA;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DegreeOfArray {


    // This is a better solution
    static class Window {
        private int occur;
        private int start; // inclusive
        private int end; // inclusive
        Window(int occur, int start, int end) {
            this.occur = occur;
            this.start = start;
            this.end = end;
        }
    }

    public int degreeOfArray(int[] nums) {

        // map stores number => window with maximum occur
        Map<Integer, Window> map = new HashMap<>();
        int maxOccur = 0;
        int minLen = nums.length;
        for (int i = 0; i < nums.length; i++) {
            Window window = map.get(nums[i]);
            if (window == null) {
                window = new Window(1, i, i);
                map.put(nums[i], window);
            } else {
                window.occur++;
                window.end = i;
            }
            if (window.occur > maxOccur) {
                maxOccur = window.occur;
                minLen = window.end - window.start + 1;
            } else if (window.occur == maxOccur && (window.end - window.start + 1) < minLen) {
                minLen = window.end - window.start + 1;
            }
        }
        return minLen;
    }



    public int longestSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int degree = getDegree(nums);
        int left = 0;
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        // numbers with # occurrences == max
        Set<Integer> candidates = new HashSet<>();

        int minLen = nums.length;
        while (right < nums.length) {
            int occurNum = map.getOrDefault(nums[right], 0);
            occurNum++;
            map.put(nums[right], occurNum);
            if (occurNum == max) {
                candidates.add(nums[right]);
            } else if (occurNum > max) {
                max = occurNum;
                candidates.clear();
                candidates.add(nums[right]);
            }
            right++;
            if (max == degree) {
                while (!candidates.contains(nums[left]) || candidates.size() >= 2) {
                    occurNum = map.get(nums[left]);
                    map.put(nums[left], occurNum - 1);
                    candidates.remove(nums[left]);
                    left++;
                }
                // [left, right) subarray with degree == degree
                minLen = Math.min(minLen, right - left);
            }
        }
        return minLen;
    }

    private int getDegree(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num: nums) {
            int occurNum = map.getOrDefault(num, 0);
            occurNum++;
            map.put(num, occurNum);
            max = Math.max(max, occurNum);
        }
        return max;
    }

    public static void main(String[] args) {
        DegreeOfArray instance = new DegreeOfArray();
        int[] nums = {1, 3, 1, 1, 2, 2, 2};
        System.out.println(instance.longestSubarray(nums));
        System.out.println(instance.degreeOfArray(nums));
    }

}
