import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DiagramTest {
    @Test
    public void dfs() throws Exception {

    }

    @Test
    public void isBridge() throws Exception {

    }

    @Test
    public void det() throws Exception {
        System.out.println(test.det());
    }

    @Test
    public void getCompletion() throws Exception {
        ArrayList<ArrayList<Edge>> result = test.getCompletion(test.generateTrees(test.n, test.edges, test.findSpanningTree()));
        for (ArrayList<Edge> item: result) {
            for (Edge edge: item) {
                System.out.print(edge.number);
            }
            System.out.print(" + ");
        }
    }

    @Test
    public void generateTrees() throws Exception {
        ArrayList<Tree> t = test.generateTrees(test.n, test.edges, test.findSpanningTree());
        System.out.println(t.size());
        for (Tree x: t) {
            System.out.println(x);
        }
    }

    @Test
    public void shrinkGraph() throws Exception {
        ArrayList<Edge> edges = test.edges;
        test.printEdges(edges);
        ArrayList<Edge> removed = new ArrayList<>();
        System.out.println("Removing edge " + edges.get(0).a + " " + edges.get(0).b);
        test.shrinkGraph(edges, removed, edges.get(0));
        test.printEdges(edges);
    }

    @Test
    public void findSpanningTree() throws Exception {
        test.findSpanningTree();
    }

    Diagram test;
    @Before
    public void initialize() {
        test = new Diagram();
    }

    @Test
    public void printEdges() throws Exception {
        test.printEdges(test.edges);
    }

    //@Before
    //Diagram test = new Diagram();
    @Test
    public void diagramTest() {
        ArrayList<Integer> t = Stream.of(1,2,3,4,5,6).collect(Collectors.toCollection(ArrayList<Integer>::new));
        ArrayList<Integer> x = new ArrayList<>(t.subList(3,5));
        t.remove(3);
    }
}