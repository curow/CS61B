package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    @Test
    public void basicTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i++) {
            pq.add(i, i);
            assertTrue(pq.contains(i));
        }
        for (int i = 0; i < 20; i++) {
            assertTrue(pq.contains(i));
        }
        assertEquals(0, (int) pq.getSmallest());
    }

    @Test
    public void basicReverseTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        for (int i = 20; i >= 0; i--) {
            pq.add(i, i);
            assertTrue(pq.contains(i));
        }
        for (int i = 0; i < 20; i++) {
            assertTrue(pq.contains(i));
        }
        assertEquals(0, (int) pq.getSmallest());
    }

    @Test
    public void removeTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        int N = 2000;
        for (int i = 1; i <= N; i++) {
            pq.add(i, i);
        }
        for (int i = 1; i <= N; i++) {
            assertEquals(i, (int) pq.removeSmallest());
        }
    }
}
