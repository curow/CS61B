import com.sun.jdi.connect.spi.TransportService;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> bears;
    private List<Bed> beds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
    }

    private Bear removeRandomBear(List<Bear> bears) {
        int index = (int) (Math.random() * bears.size());
        return bears.remove(index);
    }

    private void partition(Bear bear, List<Bed> beds, List<Bed> less,
                      List<Bed> equal, List<Bed> greater) {
        for (Bed bed : beds) {
            int compare = bear.compareTo(bed);
            if (compare < 0) {
                greater.add(bed);
            } else if (compare > 0) {
                less.add(bed);
            } else {
                equal.add(bed);
            }
        }
    }

    private List<Pair<Bear, Bed>> catenate(List<Pair<Bear, Bed>> l1,
                                           List<Pair<Bear, Bed>> l2) {
        List<Pair<Bear, Bed>> lst = new LinkedList<>();
        lst.addAll(l1);
        lst.addAll(l2);
        return lst;
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return bears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return beds;
    }
}
