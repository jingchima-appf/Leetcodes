package IXL.prepare;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MaxStringLevelOccurrences {
    public static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;
        public TreeNode(String val) {
            this.val = val;
        }
    }
    public String maxString(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        String maxString = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                TreeNode cur = queue.poll();
                int count = map.getOrDefault(cur.val, 0);
                map.put(cur.val, count + 1);

                if (count + 1 > max) {
                    max = count + 1;
                    maxString = cur.val;
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return maxString;
    }

}
