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
       this.bears = bears;
       this.beds = new ArrayList<>();
       List<Boolean> occupied = new ArrayList<>(beds.size());
       for (int i = 0; i < beds.size(); i++) {
           occupied.add(i, false);
       }
       for (Bear bear : bears) {
           int index = 0;
           for (Bed bed : beds) {
               if (!occupied.get(index) && bear.compareTo(bed) == 0) {
                   this.beds.add(bed);
               }
               ++index;
           }
       }
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
