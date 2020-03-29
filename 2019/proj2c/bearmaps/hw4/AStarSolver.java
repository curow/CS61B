package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end,
                       double timeout) {
        Stopwatch watch = new Stopwatch();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();
        Map<Vertex, Double> distTo = new HashMap<>();
        ArrayHeapMinPQ<Vertex> queue = new ArrayHeapMinPQ<>();
        queue.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, .0);
        while (!queue.isEmpty()) {
            Vertex target = queue.removeSmallest();
            if (target.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                break;
            }
            for (WeightedEdge<Vertex> e : input.neighbors(target)) {
                Vertex p = e.from();
                Vertex q = e.to();
                double w = e.weight();
                double INF = Double.POSITIVE_INFINITY;
                if (distTo.get(p) + w < distTo.getOrDefault(q, INF)) {
                    distTo.put(q, distTo.get(p) + w);
                    edgeTo.put(q, p);
                    double priority = distTo.get(q) + input.estimatedDistanceToGoal(q, end);
                    if (queue.contains(q)) {
                        queue.changePriority(q, priority);
                    } else {
                        queue.add(q, priority);
                    }
                }
            }
            ++numStatesExplored;
            if (watch.elapsedTime() > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
        }
        solution = new LinkedList<>();
        solutionWeight = 0;
        if (outcome == SolverOutcome.SOLVED) {
            solutionWeight = distTo.get(end);
            Vertex v = end;
            while (!v.equals(start)) {
                solution.add(0, v);
                v = edgeTo.get(v);
            }
            solution.add(0, start);
        } else if (outcome != SolverOutcome.TIMEOUT) {
            outcome = SolverOutcome.UNSOLVABLE;
        }
        explorationTime = watch.elapsedTime();
    }


    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return explorationTime;
    }
}
