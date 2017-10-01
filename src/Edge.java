public class Edge implements Comparable{
    int a, b;
    int number;

    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Edge(int a, int b, int number) {
        this.a = a;
        this.b = b;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "number=" + number +
                ", b=" + (b + 1) +
                ", a=" + (a + 1) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return number == edge.number;

    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        Edge edge = (Edge) o;
        return number - ((Edge) o).number;
    }
}
