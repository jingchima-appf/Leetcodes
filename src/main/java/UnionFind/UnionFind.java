package UnionFind;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    static class Node {
        private String id;
        private Node parent;
        private int rank; // number of levels below this
        private boolean isDeleted;

        Node(String id) {
            this.id = id;
            this.parent = this;
            this.rank = 0;
            this.isDeleted = false;
        }
    }

    Map<String, Node> map;

    public UnionFind() {
        map = new HashMap<>();
    }

    // return if the two nodes are connected by this function call
    public boolean connects(String id1, String id2) {
        if (isConnected(id1, id2)) {
            return false;
        }
        Node node1 = map.get(id1);
        Node node2 = map.get(id2);
        if (node1 == null) {
            node1 = new Node(id1);
            map.put(id1, node1);
        }
        if (node2 == null) {
            node2 = new Node(id2);
            map.put(id2, node2);
        }

// node1 and node2 are not null and not deleted
        Node root1 = getRoot(node1);
        Node root2 = getRoot(node2);
        if (root1.rank >= root2.rank) {
            root2.parent = root1;
            root1.rank = Math.max(root1.rank, root2.rank + 1);
        } else {
            root1.parent = root2;
            root2.rank = Math.max(root2.rank, root1.rank + 1);
        }
        return true;
    }

    public boolean isConnected(String id1, String id2) {
        Node node1 = map.get(id1);
        Node node2 = map.get(id2);
        if (node1 == null || node2 == null) {
            return false; //
        }

        // node1 and node2 are in the graph and not deleted
        Node root1 = getRoot(node1);
        Node root2 = getRoot(node2);
        return root1 == root2;
    }

    private Node getRoot(Node node) {
        Node root = node;
        while (root.parent != root) {
            root = root.parent;
        }
        return root;
    }

    public boolean remove(String id) {
        Node node = map.remove(id);
        if (node == null) {
            return false;
        }
        node.isDeleted = true;
        return true;
    }

    // total number of operations is n
    // each operation time: O(logn)
    // space: O(n)

    public static void main(String[] args) {
        UnionFind instance = new UnionFind();
        instance.connects("1", "2");
        instance.connects("2","3");
        instance.remove("2");
        System.out.println(instance.connects("1", "3"));
    }

}
