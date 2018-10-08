package VMwareOA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EvenSubarray {
    public Set<List<Integer>> subarrayNum(int[] array, int k) {
        if (array == null || array.length == 0 || k < 0) {
            return new HashSet<>();
        }
        List<Integer> nums = new ArrayList<>();
        for (int num: array) {
            nums.add(num);
        }
        Set<List<Integer>> set = new HashSet<>();
        // [j, i)
        // oddNum: to be updated depending on the num at index j
        for (int i = 1; i <= nums.size(); i++) {
            for (int j = i-1, oddNum = 0; j >= 0; j--) {
                if (nums.get(j) % 2 == 1) {
                    oddNum++;
                }
                if (oddNum <= k) {
                    set.add(nums.subList(j, i));
                } else {
                    break;
                }
            }
        }
        return set;
    }

    public static void main(String[] args) {
        EvenSubarray instance = new EvenSubarray();
        int[] array = {1, 2, 1, 2};
        int k = 1;
        // 1 2 12 21  212
        System.out.println(instance.subarrayNum(array, k));
    }
}
