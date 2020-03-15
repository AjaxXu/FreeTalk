package com.louis.test.n200.graph;

import java.util.*;

public class GraphSolutionN200 {

    // https://leetcode-cn.com/problems/clone-graph/
    private Map<Node, Node> visited_133 = new HashMap<>();
    public Node cloneGraph_133(Node node) {
        // dfs
//        if (node == null) return null;
//        if (visited_133.containsKey(node)) return visited_133.get(node);
//        Node clone = new Node(node.val, new ArrayList<>());
//        visited_133.put(node, clone);
//        for (Node neighbor : node.neighbors) {
//            clone.neighbors.add(cloneGraph_133(neighbor));
//        }
//        return clone;
        // bfs
        if (node == null) return null;
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (Node neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.add(neighbor);
                }

                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }


    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
