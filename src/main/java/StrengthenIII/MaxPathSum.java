package StrengthenIII;

public class MaxPathSum {

    private int globalMax = Integer.MIN_VALUE;
    // return the maxSum of all paths starting from root

    public int maxSumIII(TreeNode root) {
        maxSumIIIHelper(root);
        return globalMax;
    }

    private int maxSumIIIHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int left = maxSumIIIHelper(root.left);
        int right = maxSumIIIHelper(root.right);
        if (root.left != null && root.right != null) {
            globalMax = Math.max(globalMax, Math.max(left, right) + root.val);
        } else if (root.left == null) {
            globalMax = Math.max(globalMax, right + root.val);
        } else {
            globalMax = Math.max(globalMax, left + root.val);
        }
        return Math.max(0, Math.max(left, right)) + root.val;
    }
}
