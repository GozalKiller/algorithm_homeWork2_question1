import java.util.*;

public class Main {


    enum Color { WHITE, GRAY, BLACK }

    static Map<String, Vertex> graph = new HashMap<>();
    static int time = 0;

    public static void main(String[] args) {
        // Initialize
        String[] names = {"s", "t", "u", "v", "w", "x", "y", "z"};
        for (String name : names) {
            graph.put(name, new Vertex(name));
        }

        // Build the graph from the question
        addEdge("s", "z");
        addEdge("s", "w");
        addEdge("z", "y");
        addEdge("z", "w");
        addEdge("y", "x");
        addEdge("x", "z");
        addEdge("w", "x");
        addEdge("v", "s");
        addEdge("v", "w");
        addEdge("t", "v");
        addEdge("t", "u");
        addEdge("u", "v");

        // Run DFS
        for (Vertex v : graph.values()) {
            if (v.color == Color.WHITE) {
                dfsVisit(v);
            }
        }

        // Output
        System.out.println("\nπ(v), d(v), f(v):");
        for (String name : names) {
            Vertex v = graph.get(name);
            String parentName = (v.parent != null) ? v.parent.name : "nil";
            System.out.printf("%s: π=%s, d=%d, f=%d%n", name, parentName, v.discoveryTime, v.finishTime);
        }
    }

    static void dfsVisit(Vertex u) {
        time++;
        u.discoveryTime = time;
        u.color = Color.GRAY;

        for (Vertex v : u.neighbors) {
            if (v.color == Color.WHITE) {
                System.out.printf("Tree Edge: (%s -> %s)%n", u.name, v.name);
                v.parent = u;
                dfsVisit(v);
            } else if (v.color == Color.GRAY) {
                System.out.printf("Back Edge: (%s -> %s)%n", u.name, v.name);
            } else if (v.discoveryTime > u.discoveryTime) {
                System.out.printf("Forward Edge: (%s -> %s)%n", u.name, v.name);
            } else {
                System.out.printf("Cross Edge: (%s -> %s)%n", u.name, v.name);
            }
        }

        u.color = Color.BLACK;
        time++;
        u.finishTime = time;
    }

    static void addEdge(String from, String to) {
        graph.get(from).neighbors.add(graph.get(to));
    }
}
