import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        Queue<String> sortedTas = QuickSort.quickSort(tas);
        assertTrue(isSorted(sortedTas));

        int N = 100;
        Queue<Integer> queue = new Queue<>();
        Random random = new Random(1);
        for (int i = 0; i < N; i++) {
            queue.enqueue(random.nextInt());
        }
        Queue<Integer> sorted = QuickSort.quickSort(queue);
        assertTrue(isSorted(sorted));
    }

    @Test
    public void testMergeSort() {
        int N = 10;
        Queue<Integer> queue = new Queue<>();
        Random random = new Random(1);
        for (int i = 0; i < N; i++) {
            queue.enqueue(random.nextInt());
        }
        Queue<Integer> sorted = MergeSort.mergeSort(queue);
        assertTrue(isSorted(sorted));

        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        Queue<String> sortedTas = MergeSort.mergeSort(tas);
        assertTrue(isSorted(sortedTas));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable<Item>> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
