import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Diagram {
    ArrayList<Edge> edges;
    ArrayList<Tree> trees;
    HashSet<TwoTree> doubleTrees;
    String[] impulses;
    int n;

    public Diagram() {
        readDiagram();
        trees = generateTrees(n, edges, findSpanningTree());
        doubleTrees = new HashSet<>();
        for (Tree tree: trees) {
            for (Edge edge: tree.edges) {
                doubleTrees.add(new TwoTree(tree, edge));
            }
        }
    }

    public void printEdges(ArrayList<Edge> edges) {
        for(Edge edge: edges) {
            System.out.println(edge.a + " " + edge.b);
        }
    }

    public ArrayList<Edge> findSpanningTree() {
        ArrayList<Edge> tree = new ArrayList<>();
        boolean visited[] = new boolean[n];
        for (Edge edge: edges) {
            if (visited[edge.a] && visited[edge.b]) continue;
            tree.add(edge);
            visited[edge.a] = true;
            visited[edge.b] = true;
        }
        return tree;
    }

    public boolean dfs(ArrayList<Edge> edges, int from, int to) {
        boolean visited[] = new boolean[n];
        visited[from] = true;
        for (int i = 0; i < n; i++) {
            for (Edge edge: edges) {
                if (visited[edge.a] || visited[edge.b]) {
                    visited[edge.a] = true;
                    visited[edge.b] = true;
                }
            }
        }
        return visited[to];
    }

    public boolean isBridge(ArrayList<Edge> edges, Edge edge) {
        edges.remove(edge);
        boolean result = dfs(edges, edge.a, edge.b);
        edges.add(edge);
        return !result;
    }

    public ArrayList<Tree> generateTrees(int n, ArrayList<Edge> edges, List<Edge> nearTree) {
        ArrayList <Tree> result = new ArrayList<>();

        if (edges.size() == n-1) {
            result.add(new Tree(new TreeSet<>(edges)));
            return result;
        }
        if (n == 2) {
            for (Edge edge: edges) {
                TreeSet<Edge> treeEdges = new TreeSet<>();
                treeEdges.add(edge);
                result.add(new Tree(treeEdges));
            }
            return result;
        }

        //merge vertices;
        ArrayList<Edge> removed = new ArrayList<>();
        Edge treeEdge = nearTree.get(0);
        ArrayList<Edge> changed = shrinkGraph(edges, removed, treeEdge);

        //find spanning trees for subgraphs;
        ArrayList<Tree> subTrees = generateTrees(n - 1, edges, nearTree.subList(1, nearTree.size()));
        ArrayList<Edge> newTree = new ArrayList<>(subTrees.get(subTrees.size()-1).edges);
        for (Tree tree: subTrees) {
            result.add(tree.add(treeEdge));
        }
        for (Edge removedEdge: removed) {
            edges.add(removedEdge);
        }

        for (Edge edge: changed) {
            if (edge.a == treeEdge.b) {
                edge.a = treeEdge.a;
            }
            if (edge.b == treeEdge.b) {
                edge.b = treeEdge.a;
            }
        }

        /*ArrayList<Edge> bridgeEdges = new ArrayList<>();
        while (!nearTree.isEmpty() && isBridge(edges, nearTree.get(0))) {
            bridgeEdges.add(nearTree.get(0));
            edges.remove(nearTree.get(0));
            nearTree = nearTree.subList(1, nearTree.size());
        }
        if (nearTree.isEmpty())
        {
            edges.addAll(bridgeEdges);
            return result;
        }*/
        //ArrayList<Edge> treeEdges = result.get(result.size()-1).edges;
        //nearTree = result.get(0).edges;
        //treeEdge = treeEdges.get(0);
        if (!isBridge(edges, treeEdge)) {
            edges.remove(treeEdge);
            subTrees = generateTrees(n, edges, newTree);
            result.addAll(subTrees);
            edges.add(treeEdge);
        }
        return result;
    }

    public ArrayList<Edge> shrinkGraph(ArrayList<Edge> edges, ArrayList<Edge> removed, Edge removedEdge) {
        ArrayList<Edge> changed = new ArrayList<>();
        for (Edge edge: edges) {
            if (edge.a == removedEdge.a) {
                if (edge.b == removedEdge.b) {
                    removed.add(edge);
                    continue;
                }
                changed.add(edge);
                edge.a = removedEdge.b;
            }

            else if (edge.b == removedEdge.a) {
                if (edge.a == removedEdge.b) {
                    removed.add(edge);
                    continue;
                }
                changed.add(edge);
                edge.b = removedEdge.b;
            }
        }
        edges.removeAll(removed);
        return changed;
    }

    public String V() {
        System.out.println("Trees found: " + doubleTrees.size());
        String result = "";
        for (TwoTree tree: doubleTrees) {
            //result += tree;
            for (Edge edge: getCompletion(tree.edges)) {
                System.out.print(edge.number);
            }
            System.out.println("\nFirst component");
            for (int vertice: tree.firstVertices) {
                System.out.print(impulses[vertice] + ", ");
            }
            System.out.println("\nSecond Component:");
            for (int vertice: tree.secondVertices) {
                System.out.print(impulses[vertice] + ", ");
            }
            System.out.println();
        }
        return result;
    }

    public String det() {
        ArrayList<ArrayList<Edge>> det = getCompletion(trees);
        String result = "";
        for (ArrayList<Edge> item: det) {
            for (Edge edge : item) {
                result += edge.number;
            }
            result += " + ";
        }
        return result;
    }

    private ArrayList<Edge> getCompletion(Collection<Edge> edges) {
        ArrayList<Edge> complementEdges = new ArrayList<>(this.edges);
        complementEdges.removeAll(edges);
        return complementEdges;
    }

    public ArrayList<ArrayList<Edge>> getCompletion(ArrayList<Tree> trees) {
        ArrayList<ArrayList<Edge>> result = new ArrayList<>();
        for (Tree tree:trees) {
            result.add(getCompletion(tree.edges));
        }
        return result;
    }

    private void readDiagram() {
        try {
            Scanner scanner = new Scanner(new File("Diagram.dat"));
            n = scanner.nextInt();
            edges = new ArrayList<>();
            impulses = new String[4];
            int number = 1;
            scanner.nextLine();
            for (int i = 0; i < n; i++)
                impulses[i] = scanner.nextLine();
            while (scanner.hasNextInt()) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                edges.add(new Edge(a-1, b-1, number++));
            }
        }
        catch (IOException ex) {
            System.out.println("Can't open the file");
        }

    }
}
