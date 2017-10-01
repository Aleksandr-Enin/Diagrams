import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Tree {
    TreeSet<Edge> edges;

    public Tree(TreeSet<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        String result = "";
        for (Edge edge: edges) {
            result += "(" + edge.a + 1 + " " + edge.b +1 + " " + edge.number + ") ";
        }
        return result;
    }

    public Tree add(Edge edge) {
        TreeSet<Edge> newEdges = new TreeSet<>(edges);
        newEdges.add(edge);
        return new Tree(newEdges);
    }

    public Tree add(List<Edge> e) {
        TreeSet<Edge> newEdges = new TreeSet<>(edges);
        newEdges.addAll(e);
        return new Tree(newEdges);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree tree = (Tree) o;

        return edges != null ? edges.equals(tree.edges) : tree.edges == null;
    }

    @Override
    public int hashCode() {
        return edges != null ? edges.hashCode() : 0;
    }
}
