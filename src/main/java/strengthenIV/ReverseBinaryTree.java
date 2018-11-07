package strengthenIV;

import utils.TreeNode;

public class ReverseBinaryTree {


    // return the new root of reverseTree
    public TreeNode reverseTree(TreeNode root) {
        if (root == null) { // edge case
            return null;
        }
        if (root.left == null && root.right == null) {
            return root; // base case
        }
        TreeNode newRoot = reverseTree(root.left);
        root.left.right = root;
        root.left.left = root.right;
        root.left = null;
        root.right = null;
        return newRoot;
    }

}
