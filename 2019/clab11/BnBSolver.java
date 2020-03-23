import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 *
 * @source https://github.com/zangsy/cs61b_sp19/tree/master/clab11
 * solved it by learning from the above solution
 */
public class BnBSolver {
    private List<Bear> bears;
    private List<Bed> beds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        Pair<List<Bear>, List<Bed>> bbs = quickSort(bears, beds);
        this.bears = bbs.first();
        this.beds = bbs.second();
    }

    private Pair<List<Bear>, List<Bed>> quickSort(List<Bear> bears,
                                                  List<Bed> beds) {
        assert bears.size() == beds.size();
        if (bears.size() <= 1) {
            return new Pair<>(bears, beds);
        }
        Bear bearPivot = getRandomBear(bears);
        List<Bed> lessBeds = new LinkedList<>();
        List<Bed> equalBeds = new LinkedList<>();
        List<Bed> greaterBeds = new LinkedList<>();
        partitionBeds(beds, bearPivot, lessBeds, equalBeds, greaterBeds);
        Bed bedPivot = equalBeds.get(0);
        List<Bear> lessBears = new LinkedList<>();
        List<Bear> equalBears = new LinkedList<>();
        List<Bear> greaterBears = new LinkedList<>();
        partitionBears(bears, bedPivot, lessBears, equalBears, greaterBears);
        Pair<List<Bear>, List<Bed>> lessPair = quickSort(lessBears, lessBeds);
        Pair<List<Bear>, List<Bed>> equalPair = new Pair<>(equalBears, equalBeds);
        Pair<List<Bear>, List<Bed>> greaterPair = quickSort(greaterBears, greaterBeds);
        return catenate(catenate(lessPair, equalPair), greaterPair);
    }


    private Bear getRandomBear(List<Bear> bears) {
        int index = (int) (Math.random() * bears.size());
        return bears.get(index);
    }

    private void partitionBeds(List<Bed> beds, Bear bearPivot, List<Bed> less,
                      List<Bed> equal, List<Bed> greater) {
        for (Bed bed : beds) {
            int compare = bearPivot.compareTo(bed);
            if (compare < 0) {
                greater.add(bed);
            } else if (compare > 0) {
                less.add(bed);
            } else {
                equal.add(bed);
            }
        }
    }

    private void partitionBears(List<Bear> bears, Bed bedPivot, List<Bear> less,
                               List<Bear> equal, List<Bear> greater) {
        for (Bear bear : bears) {
            int compare = bedPivot.compareTo(bear);
            if (compare < 0) {
                greater.add(bear);
            } else if (compare > 0) {
                less.add(bear);
            } else {
                equal.add(bear);
            }
        }
    }

    private Pair<List<Bear>, List<Bed>> catenate(Pair<List<Bear>, List<Bed>> p1,
                                                 Pair<List<Bear>, List<Bed>> p2) {
        List<Bear> bears = new LinkedList<>();
        bears.addAll(p1.first());
        bears.addAll(p2.first());
        List<Bed> beds = new LinkedList<>();
        beds.addAll(p1.second());
        beds.addAll(p2.second());
        return new Pair<>(bears, beds);
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
