package VMwareOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinSteps {

    // 1  5  3  2  4  6  2  increasing
// possible values: sorted[]: 1 2 3 4 5 6

//    dp[i][j] represents the min steps to make [0,i] increasing with the max <= sorted[j]
//
//    Observation 1:
//    There is no need to change a number to a number outside the possible values.
//
//    Proof: Suppose there’s a number i in the final increasing array, and i is not in the sorted array. Then we can always replace it with num k, or num l, where k is the largest number in sorted which is smaller than i, and l is the smallest number in sorted which is larger the k. This is because the increasing order is still maintained. And the number of changes must decrease.
//
//    This allows us to use the possible to-change values as dp parameter.
//
//    Observation 2:
//    To minimize the steps required for [0, i], if we can change nums[i] to a number k, s.t. [0,i] becomes increasing, then there’s no need to change nums[i] to a higher number than k.
//
//    The observation 1 and observation 2 provide the foundation for induction rule
//
//    induction rule:
//    dp[i][j] = min { dp[i-1][j] + abs(sorted[j] - nums[i]), dp[i][j-1] }
//
//    case 1: change the nums[i] to sorted[j]. cost = dp[i-1][j] + abs (sorted[j] - nums[i])
//
//    case 2: not change the nums[i] to sorted[j]. Then we should try change it to a even smaller number than sorted[j] (because according to observation 2, there’s no need to change it to a higher value than sorted[j]). cost = dp[i][j-1]
//
//    base case
//    i == 0, j == 0: dp[0][0] = nums[i] - sorted[j]
//    i == 0, j >= 1: dp[0][j] = min { abs(sorted[j] - nums[0]), dp[0][j-1 }
//    i >= 1, j == 0: dp[i][0] = dp[i-1] + abs (sorted[0] - nums[i])

    private List<Integer> results = new ArrayList<>();

    public int minSteps(int[] nums) {

        int[] sorted = getSorted(nums);
        int[][] dp = new int[nums.length][sorted.length];
        dp[0][0] = nums[0] - sorted[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = dp[i-1][0] + Math.abs(sorted[0] - nums[i]);
        }
        for (int j = 1; j < sorted.length; j++) {
            dp[0][j] = Math.min( Math.abs(sorted[j] - nums[0]), dp[0][j-1] );
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < sorted.length; j++) {
                dp[i][j] = Math.min( dp[i-1][j] + Math.abs(sorted[j] - nums[i]), dp[i][j-1] );
            }
        }

        List<Integer> results = new ArrayList<>();
        int i = nums.length - 1;
        int j = sorted.length - 1;
        while (i >= 0 && j >= 0) {
            if (j > 0 && dp[i][j] != dp[i][j-1]) {
                // current j is chosen
                results.add(sorted[j]);
                i--;
            } else if (j == 0) {
                results.add(sorted[j]);
                i--;
            } else {
                j--;
            }
        }
        Collections.reverse(results);
        this.results = results;
        return dp[nums.length - 1][sorted.length - 1];
    }

    private int[] getSorted(int[] nums) {
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        int slow = 0;
        int fast = 0;
        while (fast < copy.length) {
            if (fast == 0 || copy[fast] != copy[fast - 1]) {
                copy[slow] = copy[fast];
                slow++;
            }
            fast++;
        }
        int[] sorted = new int[slow];
        for (int i = 0; i < slow; i++) {
            sorted[i] = copy[i];
        }
        return sorted;
    }

    public static void main(String[] args) {
        MinSteps instance = new MinSteps();
        int[] nums = {1, 7, 4, 3, 8, 10, 42, 10};
        System.out.println(instance.minSteps(nums));
        System.out.println(instance.results);
    }

}
