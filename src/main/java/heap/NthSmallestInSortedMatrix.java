package heap;

public class NthSmallestInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0];
        int m = matrix.length;
        int n = matrix[0].length;
        int hi = matrix[m-1][n-1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            int j = n - 1;
            for(int i = 0; i < m; i++) {
                while(j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private int getCount(int[] nums, int target) {
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2; // may overflow
        while (left < right) {
            mid = (left + right + 1) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        count += (left + 1);
        if (nums[left] > target) {
            count--;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5},{6,7,12},{11,14,14}};
        int[] nums = {2,3,5,8,10};
        NthSmallestInSortedMatrix nthSmallestInSortedMatrix = new NthSmallestInSortedMatrix();
        System.out.println(nthSmallestInSortedMatrix.getCount(nums, 7));
    }
}
