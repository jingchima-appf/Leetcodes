package QuoraOA;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Test {

    private static long threeStudents(int[] nums, int k) {
        long count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            count += twoSum(nums, i+1, k - nums[i]);
        }
        return count;
    }

    private static long twoSum(int[] nums, int start, int target) {
        int left = start;
        int right = nums.length - 1;
        long count = 0;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                count += getCombinationNum(nums, left, right);
                break;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    // start < end inclusive
    private static long getCombinationNum(int[] nums, int start, int end) {
        if (nums[start] == nums[end]) {
            long count = end - start + 1;
            return count * (count - 1) / 2;
        }
        int left = start;
        while (nums[left] == nums[start]) {
            left++;
        }
        int right = end;
        while (nums[right] == nums[end]) {
            right--;
        }
        long leftCount = left - start;
        long rightCount = end - right;
        return leftCount * rightCount;
    }


    public static void main(String args[] ) throws Exception {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine());
//        String[] skillsStrings = scanner.nextLine().split("\\s+");
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = Integer.parseInt(skillsStrings[i]);
//        }
//        int k = Integer.parseInt(scanner.nextLine());
//        System.out.println(threeStudents(nums, k));
        int[] students = {2, 2, 2, 2, 2};
        int k = 3;
        System.out.println(threeStudents(students, k));
    }
}