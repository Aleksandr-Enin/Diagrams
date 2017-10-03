import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class TwoTree {
    TreeSet<Edge> edges;
    HashSet<Integer> firstVertices, secondVertices;
    ArrayList<Integer> impulses;

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
        impulses = new ArrayList<>();
    }

    public void findImpulse(int[] p) {
        HashSet<Integer> vertices = new HashSet<>();
        for (int i =0; i < p.length; i++) {
            if (p[i] == -1) {
                if (firstVertices.contains(i)) vertices = secondVertices;
                else vertices = firstVertices;
            }
        }
        for (int vertice: vertices) {
            if (p[vertice] != 0) {
                impulses.add(p[vertice]);
            }
        }
    }

    public boolean isZero() {
        return impulses.isEmpty();
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
