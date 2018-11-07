package sixSensePrepare;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class TreeNode {
    String val;
    List<TreeNode> children;

    public TreeNode(String val) {
        this.val = val;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preorder(this, new StringBuilder(), sb);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder indent, StringBuilder result) {
        if (root == null) {
            return;
        }
        result.append(indent).append(indent.length() == 0 ? "" : " ").append(root.val).append("\n");
        for (TreeNode child : root.children) {
            indent.append("-");
            preorder(child, indent, result);
            indent.deleteCharAt(indent.length() - 1);
        }
    }
}

public class BuildTree {


    /*
    assumptions:
    (1) input is valid, meaning each open tag is closed, and no intersection for different tags
    (2) <name></name>
     */
    public TreeNode buildTree(String[] input) {
        /*
        Data Structure:
        Stack<TreeNode> stack: stores the TreeNode whose children hasn't been finalized yet

        Algorithm:
        for each string s:
            if s is open tag, then
                nothing
            else if s is a close tag, then
                pop it out, add it to the children list of top node in stack
            else
                create a TreeNode node for it
                store the node into stack
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode dummy = new TreeNode("");
        stack.offerFirst(dummy);

        for (String s : input) {
            if (isOpen(s)) {
                TreeNode cur = new TreeNode(s); // 应该在open的时候建立这个node，因为有可能content是空，所以不能再content处建立
                stack.offerFirst(cur);
            } else if (isClose(s)) {
                TreeNode cur = stack.pollFirst();
                TreeNode parent = stack.peekFirst();
                parent.children.add(cur);
            } else { // content
                stack.peekFirst().val = s;
            }
        }
        return dummy.children.get(0);
    }

    // assumption: s.length() >= 2 i.e. at least <>
    private boolean isOpen(String s) {
        if (s.charAt(0) == '<' && s.charAt(1) != '/') {
            return true;
        }
        return false;
    }

    // assumption: s.length() >= 3, i.e. at least </>
    private boolean isClose(String s) {
        if (s.charAt(0) == '<' && s.charAt(1) == '/') {
            return true;
        }
        return false;
    }
}
