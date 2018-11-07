package utils;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(this);
        StringBuilder res = new StringBuilder();
        final TreeNode emptyNode = new TreeNode(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == emptyNode) {
                    res.append("null ");
                } else {
                    res.append(cur.val).append(" ");
                    queue.offer(cur.left == null ? emptyNode : cur.left);
                    queue.offer(cur.right == null ? emptyNode : cur.right);
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
}
