package thumbtackPrepare;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    List<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        if ( !(another instanceof TreeNode) ) {
            return false;
        }
        TreeNode root = (TreeNode) another;
        return sameTree(this, root);
    }

    private boolean sameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val || root1.children.size() != root2.children.size()) {
            return false;
        }
        boolean same = true;
        for (int i = 0; i < root1.children.size(); i++) {
            same = same && sameTree(root1.children.get(i), root2.children.get(i));
        }
        return same;
    }
}

public class SerializationAndDeserialization {

    private static final String SEPARATOR = ",";

    // (val, children number), (val, children number)
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sb.append(cur.val).append(SEPARATOR).append(cur.children.size()).append(SEPARATOR);
                for (TreeNode child : cur.children) {
                    queue.offer(child); // assumption: there's no null in child list
                }
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] datas = data.split(SEPARATOR);
        TreeNode root = new TreeNode(Integer.parseInt(datas[0]));
        Queue<TreeNode> queue = new ArrayDeque<>(); // store the parent nodes in the previous level
        Queue<Integer> childrenNumbers = new ArrayDeque<>(); // store the children number for each node in queue
        queue.offer(root);
        childrenNumbers.offer(Integer.parseInt(datas[1]));

        int i = 2;
        while (i < datas.length) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TreeNode parent = queue.poll();
                int childNum = childrenNumbers.poll();
                while (childNum > 0) {
                    TreeNode child = new TreeNode(Integer.parseInt(datas[i]));
                    parent.children.add(child);
                    queue.offer(child);
                    childrenNumbers.offer(Integer.parseInt(datas[i+1]));
                    childNum--;
                    i += 2;
                }
            }
        }
        return root;
    }
}
