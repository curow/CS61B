package bearmaps.hw4.integerhoppuzzle;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * The Integer Hop puzzle implemented as a graph.
 * Created by hug.
 */
public class IntegerHopGraph implements AStarGraph<Integer> {

    @Override
    public List<WeightedEdge<Integer>> neighbors(Integer v) {
        ArrayList<WeightedEdge<Integer>> neighbors = new ArrayList<>();
        neighbors.add(new WeightedEdge<>(v, v * v, 10));
        neighbors.add(new WeightedEdge<>(v, v * 2, 5));
        neighbors.add(new WeightedEdge<>(v, v / 2, 5));
        neighbors.add(new WeightedEdge<>(v, v - 1, 1));
        neighbors.add(new WeightedEdge<>(v, v + 1, 1));
        return neighbors;
    }

    @Override
    public double estimatedDistanceToGoal(Integer s, Integer goal) {
        // possibly fun challenge: Try to find an admissible heuristic that
        // speeds up your search. This is tough!
        double estimate;
        int addOrSubtractStep = Math.abs(s - goal);
        int powerStep = 0;
        int x = s;
        while (Math.abs(x - goal) > 5 && powerStep < 2) {
            if (x > goal) {
                x *= 2;
            } else {
                x /= 2;
            }
            powerStep += 1;
        }
        if (addOrSubtractStep < 5) {
            estimate = addOrSubtractStep;
        } else if (powerStep < 2) {
            estimate = 5;
        } else {
            estimate = 10;
        }
        return estimate;
    }
}
