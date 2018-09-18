import java.util.ArrayDeque;
import java.util.Deque;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class BST {
    private TreeNode root;

    public void insert(int target) {
        if (this.root == null) {
            this.root = new TreeNode(target);
        } else {
            insertHelper(this.root, target);
        }
    }

    public void delete(int target) {
        this.root = deleteHelper(this.root, target);
    }

    // return the new root node after inserting new node into this tree(root)
    private TreeNode insertHelper(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode(target);
        }
        if (target < root.val) {
            root.left = insertHelper(root.left, target);
        } else if (target > root.val) {
            root.right = insertHelper(root.right, target);
        }
        return root;
    }

    // return the new root node after deleting target node
    private TreeNode deleteHelper(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        if (target < root.val) {
            root.left = deleteHelper(root.left, target);
            return root;
        } else if (target > root.val) {
            root.right = deleteHelper(root.right, target);
            return root;
        }

        // root is the node to delete

        if (root.right == null) {
            return root.left;
        }
        // if there's right child, use the nextLarger element to replace current node
        TreeNode nextLarger = removeNextLarger(root); // note we should search in this subtree
        nextLarger.left = root.left;
        nextLarger.right = root.right;
        return nextLarger;
    }

    private static TreeNode removeNextLarger(TreeNode root) {
        if (root == null || root.right == null) {
            return null;
        }
        TreeNode iter = root.right;
        TreeNode prev = root;
        while (iter.left != null) {
            prev = iter;
            iter = iter.left;
        }
        if (prev == root) {
            prev.right = iter.right;
        } else {
            prev.left = iter.right;
        }
        return iter;
    }

    public String inorder() {
        if (this.root == null) {
            return "[]";
        }
        StringBuilder res = new StringBuilder();
        res.append("[ ");
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode toVisit = this.root;
        while (toVisit != null || !stack.isEmpty()) {
            if (toVisit != null) {
                stack.offerFirst(toVisit);
                toVisit = toVisit.left;
            } else {
                TreeNode cur = stack.pollFirst();
                res.append(cur.val).append(" ");
                toVisit = cur.right;
            }
        }
        res.append("]");
        return res.toString();
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode toVisit = root;
        boolean isFound = false;
        while (toVisit != null || !stack.isEmpty()) {
            if (toVisit != null) {
                stack.offerFirst(toVisit);
                toVisit = toVisit.left;
            } else {
                TreeNode cur = stack.pollFirst();
                if (isFound) {
                    return cur;
                } else if (p == cur) {
                    isFound = true;
                }
                toVisit = cur.right;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BST bst = new BST();
//        final int n = 10;
//        int[] nums = {18,4,25,6,7,3,7,8,3,6,8};
//        for (int num: nums) {
//            bst.insert(num);
//        }
//        System.out.println(bst.inorder());
//
//        for (int num: nums) {
//            bst.delete(num);
//            System.out.println(num + ": " + bst.inorder());
//        }
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        System.out.println(bst.inorderSuccessor(root, root.left).val);
    }

}


