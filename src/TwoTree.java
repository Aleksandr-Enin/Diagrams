import java.util.HashSet;
import java.util.TreeSet;

public class TwoTree {
    TreeSet<Edge> edges;
    HashSet<Integer> firstVertices, secondVertices;

    private void color(TreeSet<Edge> edges, Edge edge) {
        firstVertices.add(edge.a);
        secondVertices.add(edge.b);
        for (int i = 0; i <= edges.size(); i++) {
            for (Edge treeEdge : edges) {
                if (firstVertices.contains(treeEdge.a)) firstVertices.add(treeEdge.b);
                else if (firstVertices.contains(treeEdge.b)) firstVertices.add(treeEdge.a);
                else if (secondVertices.contains(treeEdge.a)) secondVertices.add(treeEdge.b);
                else if (secondVertices.contains(treeEdge.b)) secondVertices.add(treeEdge.a);
            }
        }
    }

    public TwoTree(Tree tree, Edge bridge) {
        int to = bridge.a;
        int from = bridge.b;
        edges = new TreeSet<>(tree.edges);
        edges.remove(bridge);
        firstVertices = new HashSet<>();
        secondVertices = new HashSet<>();
        color(edges, bridge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwoTree tree = (TwoTree) o;

        return edges != null ? edges.equals(tree.edges) : tree.edges == null;
    }

    @Override
    public int hashCode() {
        return edges != null ? edges.hashCode() : 0;
    }
}
