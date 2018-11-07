package IXL.prepare;

import java.util.ArrayList;
import java.util.List;

public class RandomFabonaci {


    // n >= 1
    public static int randomFabonaci(int n) {
        if (n == 1) {
            return 1;
        }
        // step 1: generate all the fabonaci numbers <= n
        List<Integer> nums = new ArrayList<>(); // stores fabonaci numbers
        nums.add(1);
        nums.add(1);
        // dp[i] = dp[i-1] + dp[i-2]
        int next = nums.get(nums.size() - 1) + nums.get(nums.size() - 2);
        while (next <= n) {
            nums.add(next);
            next = nums.get(nums.size() - 1) + nums.get(nums.size() - 2);
        }
        // step 2: randomly choose one
        int index = (int) (Math.random() * nums.size());
        return nums.get(index);
    }

    public static void main(String[] args) {
        // 1 1 2 3 5 8 12
        System.out.println(randomFabonaci(10));
    }
}
