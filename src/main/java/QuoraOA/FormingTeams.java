package QuoraOA;

import java.util.Arrays;

public class FormingTeams {
    public long threeStudents(int[] nums, int k) {
        long count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            count += twoSum(nums, i+1, k - nums[i]);
        }
        return count;
    }


    // nums is sorted, and start is inclusive
// nums is sorted, and start is inclusive
    private long twoSum(int[] nums, int start, int target) {
        int left = start;
        int right = nums.length - 1;
        long count = 0;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                if (nums[right] == nums[left]) {
                    long curCount = right - left + 1;
                    count += (curCount * (curCount - 1)) / 2;
                    break;
                } else {
                    int nextLeft = left;
                    while (nums[nextLeft] == nums[left]) {
                        nextLeft++;
                    }
                    int nextRight = right;
                    while (nums[nextRight] == nums[right]) {
                        nextRight--;
                    }
                    long leftNum = nextLeft - left;
                    long rightNum = right - nextRight;
                    count += (leftNum * rightNum);
                    left = nextLeft;
                    right = nextRight;
                }
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        FormingTeams instance = new FormingTeams();
        int[] students = {2, 2, 2, 2, 2};
        int k = 3;
        System.out.println(instance.threeStudents(students, k));
    }

}
