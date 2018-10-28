package forusallOA;

public class ThreeParts {
    public static boolean threeParts(int[] nums) {
        if (nums.length < 5) {
            return false;
        }
        // 1 2 3 4 5
        //   L   R
        int left = 1;
        int right = nums.length - 2;
        int leftSum = nums[0];
        int rightSum = nums[nums.length - 1];
        int totalSum = 0;
        for (int num: nums) {
            totalSum += num;
        }
        while (left < right - 1) {
            int sum = totalSum - nums[left] - nums[right];
            int middleSum = sum - leftSum - rightSum;
            if (leftSum == rightSum && leftSum == middleSum) {
                return true;
            }
            if (middleSum < leftSum || middleSum < rightSum) {
                return false;
            }
            if (leftSum < rightSum) {
                leftSum += nums[left];
                left++;
            } else if (leftSum > rightSum) {
                rightSum += nums[right];
                right--;
            } else {
                leftSum += nums[left];
                rightSum += nums[right];
                left++;
                right--;
            }
        }
        return false;
    }

}
