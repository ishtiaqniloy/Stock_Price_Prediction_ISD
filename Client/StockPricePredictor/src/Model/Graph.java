
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javafx.util.Pair;

public class Graph implements Serializable {
    private ArrayList<Pair<Integer,Integer>> points;

    public ArrayList<Pair<Integer, Integer>> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Pair<Integer, Integer>> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(points, graph.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "points=" + points +
                '}';
    }
}
