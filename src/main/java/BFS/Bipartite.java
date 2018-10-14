package BFS;


import java.util.*;

class GraphNode {
    public int key;
    public List<GraphNode> neighbors;
    public GraphNode(int key) {
        this.key = key;
        this.neighbors = new ArrayList<GraphNode>();
    }
}


public class Bipartite {
    // BFS: label each level of nodes with 0 or 1.
    // if we meet a node which has been labelled as the opposite, then not bipartite
    // use HashMap to label the nodes

    private static final Boolean LABEL_1 = true;
    private static final Boolean LABEL_2 = false;
    private static final Boolean UNVISITED = null;

    public boolean isBipartite(List<GraphNode> graph) {
        Map<GraphNode, Boolean> labels = new HashMap<>();
        for (GraphNode node: graph) { // 因为图可能不是连在一起的，所以必须每个node都traverse！
            if (!bfs(node, labels)) {
                return false;
            }
        }
        return true;
    }
    // return false if a node has been labelled as the opposite
    public boolean bfs(GraphNode node, Map<GraphNode, Boolean> labels) {
        Boolean label = labels.get(node);
        if (label != UNVISITED) {
            return true;
        }
        label = LABEL_1;
        labels.put(node, label);
        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.offer(node);
        boolean correctLabel = !label;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { // need to traverse based on the level!!
                GraphNode cur = queue.poll();
                for (GraphNode neighbor: cur.neighbors) {
                    label = labels.get(neighbor);
                    if (label == UNVISITED) {
                        labels.put(neighbor, correctLabel);
                        queue.offer(neighbor);
                    } else if (label != correctLabel) {
                        return false;
                    }
                }
            }
            correctLabel = !correctLabel;
        }
        return true;
    }
}
