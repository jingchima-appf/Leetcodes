package recursionII;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}

class TreeNodeP {
    int val;
    TreeNodeP left;
    TreeNodeP right;
    TreeNodeP parent;
    public TreeNodeP(int val) {
        this.val = val;
    }
}

public class LCA {

    // Question 1: Without Parent Pointer
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode node1, TreeNode node2) {

        // node1 -> return, node1 is either the LCA, or node2 is not in the subtree
        TreeNode lca = findLCA(root, node1, node2);
        if (lca == null || (lca != node1 && lca != node2) ) {
            return lca;
        }
        if (lca == node1) {
            if (findLCA(node1, node2, node2) == node2) {
                // then node2 is in the tree
                return node1;
            } else {
                return null;
            }
        }
        if (findLCA(node2, node1, node1) == node1) {
            return node2;
        } else {
            return null;
        }
        // != node1 && != node2 => this IS the LCA

        // == node1
        // (1) node1 is LCA (2) node2 is not in the LCA
        // if  LCA(node1, node2, node2) == node2 => (1)
        //
    }

    private TreeNode findLCA(TreeNode root, TreeNode node1, TreeNode node2) {

        if (root == null || root == node1 || root == node2) {
            return root;
        }
        TreeNode left = findLCA(root.left, node1, node2);
        TreeNode right = findLCA(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left: right;
    }

    // Question2 : with Parent pointer

    public TreeNodeP lowestCommonAncestor(TreeNodeP node1, TreeNodeP node2) {


        // step1: get the depth for each node
        int depth1 = getDepth(node1);
        int depth2 = getDepth(node2);

        if (depth1 >= depth2) {
            return goUp(node1, node2, depth1 - depth2);
        } else {
            return goUp(node2, node1, depth2 - depth1);
        }

        // step2: continue to go until they go to the same node

        //            1
        //         /     \
        //       2        4
        //      / \      /  \
        //        node1  5   node2
        //              /      \
        //            node1'
    }

    // diff >= 0
    private TreeNodeP goUp(TreeNodeP deeper, TreeNodeP higher, int diff) {
        while (diff > 0) {
            deeper = deeper.parent;
            diff--;
        }

        while (deeper != higher) { // 因为会同时达到null，所以不需要检查null
            deeper = deeper.parent;
            higher = higher.parent;
        }

        return deeper;
    }

    private int getDepth(TreeNodeP node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }

}
