import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FlightSolver {
    private static class Flight {
        int startTime;
        int endTime;
        int numOfPassengers;

        public Flight(int start, int end, int num) {
            startTime = start;
            endTime = end;
            numOfPassengers = num;
        }
    }

    private PriorityQueue<Flight> PQ;

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> flightComparator =
                Comparator.comparingInt(a -> a.numOfPassengers);
        PQ = new PriorityQueue<>(flightComparator);
        PQ.addAll(flights);
    }

    public int solve() {
        assert PQ.peek() != null;
        return PQ.peek().numOfPassengers;
    }
}
