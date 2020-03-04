import java.util.ArrayList;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FlightSolver {
    private PriorityQueue<Flight> minStartTimeQueue;
    private PriorityQueue<Flight> minEndTimeQueue;

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> minStartTimeComparator =
                Comparator.comparingInt(Flight::startTime);
        minStartTimeQueue = new PriorityQueue<>(minStartTimeComparator);
        minStartTimeQueue.addAll(flights);
        Comparator<Flight> minEndTimeComparator =
                Comparator.comparingInt(Flight::endTime);
        minEndTimeQueue = new PriorityQueue<>(minEndTimeComparator);
        minEndTimeQueue.addAll(flights);
    }

    public int solve() {
        int maxPassengers = -1;
        int totalPassengers = 0;
        while (!minStartTimeQueue.isEmpty()) {
            Flight startFlight = minStartTimeQueue.peek();
            Flight endFlight = minEndTimeQueue.peek();
            if (endFlight == null
                    || startFlight.startTime <= endFlight.endTime) {
                totalPassengers += startFlight.passengers;
                if (totalPassengers > maxPassengers) {
                    maxPassengers = totalPassengers;
                }
                minStartTimeQueue.poll();
            } else {
                totalPassengers -= endFlight.passengers;
                minEndTimeQueue.poll();
            }
        }
        return maxPassengers;
    }

}
