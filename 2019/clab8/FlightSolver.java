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

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> minStartTimeComparator =
                Comparator.comparingInt(Flight::startTime);
        minStartTimeQueue = new PriorityQueue<>(minStartTimeComparator);
        minStartTimeQueue.addAll(flights);
    }

    public int solve() {
        int maxPassengers = -1;
        while (!minStartTimeQueue.isEmpty()) {
            Flight flight0 = minStartTimeQueue.poll();
            if (flight0.passengers > maxPassengers) {
                maxPassengers = flight0.passengers;
            }
            if (!minStartTimeQueue.isEmpty()) {
                Flight flight1 = minStartTimeQueue.peek();
                if (flight0.endTime >= flight1.startTime) { // overlapping
                    minStartTimeQueue.poll();
                    int endTime = Math.min(flight0.endTime, flight1.endTime);
                    Flight overlappingFlight = new Flight(
                      flight1.startTime,
                      endTime,
                      flight0.passengers + flight1.passengers
                    );
                    minStartTimeQueue.add(overlappingFlight);
                    int startTime = endTime + 1;
                    if (startTime <= flight0.endTime) {
                        Flight remainingFlight0 = new Flight(
                                startTime,
                                flight0.endTime,
                                flight0.passengers
                        );
                        minStartTimeQueue.add(remainingFlight0);
                    } else if (startTime <= flight1.endTime) {
                        Flight remainingFlight1 = new Flight(
                                startTime,
                                flight1.endTime,
                                flight1.passengers
                        );
                        minStartTimeQueue.add(remainingFlight1);
                    }
                }
            }
        }
        return maxPassengers;
    }

}
