
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javafx.util.Pair;

public class Graph implements Serializable {
    public ArrayList<Pair<Date,Integer>> points;

    public Graph() {
        this.points = new ArrayList<Pair<Date,Integer>>();
    }

    public ArrayList<Pair<Date, Integer>> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Pair<Date, Integer>> points) {
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
