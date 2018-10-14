package divideAndConquer;

public class QuickSelect {
    public int[] topk(int[] nums, int k) {
        int end = quickSelect(nums, 0, nums.length - 1, k);
        int[] res = new int[k];
        for (int i = 0; i <= end; i++) {
            res[i] = nums[i];
        }
        return res;
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        if (end - start + 1 == k) {
            return end;
        }
        int pivot = nums[ (int) (Math.random() * (end - start + 1) + start) ];
        int left = start;
        int right = end;
        while (left <= right) {
            // [0, left) >= pivot
            while (left < right && nums[left] >= pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        // [start, right] ">="
        // [left, end] "<"
        if (right - start + 1 >= k) {
            return quickSelect(nums, start, right, k);
        } else if (k >= left - start + 1) {
            return quickSelect(nums, left, end, k - (left - start));
        } else {
            return left+1;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSelect quickSelect = new QuickSelect();
        int[] nums = new int[] {3,10,1000,-99,4,100};
        int[] res = quickSelect.topk(nums, 3);
        for (int num: res) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
