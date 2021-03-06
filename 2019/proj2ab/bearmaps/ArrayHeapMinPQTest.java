package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

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
            assertFalse(pq.contains(i - 1));
            assertEquals(i, (int) pq.removeSmallest());
        }
    }

    @Test
    public void changPriorityTest() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        int N = 20;
        for (int i = 1; i <= N; i++) {
            pq.add(i, i);
        }
        pq.changePriority(N, 0);
        assertEquals(N, (int) pq.removeSmallest());
        for (int i = 1; i < N; i++) {
            assertEquals(i, (int) pq.removeSmallest());
        }
    }

    @Test
    public void randomTest() {
        Random randomNumberGenerator = new Random(0);
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        int N = 10000;
        int size = 0;
        ArrayList<Integer> allItems = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int code = randomNumberGenerator.nextInt(7);
            int index;
            int item;
            double priority;
            switch (code) {
                case 0: // add items to MinPQ
                    item = randomNumberGenerator.nextInt();
                    priority = randomNumberGenerator.nextDouble();
                    naiveMinPQ.add(item, priority);
                    arrayHeapMinPQ.add(item, priority);
                    allItems.add(item);
                    System.out.println(String.format("add(%d, %f)", item,
                            priority));
                    size++;
                    break;
                case 1: // test whether MinPQ contains some item added
                    if (size != 0) {
                        index = randomNumberGenerator.nextInt(allItems.size());
                        item = allItems.get(index);
                        assertEquals(naiveMinPQ.contains(item),
                                arrayHeapMinPQ.contains(item));
                        System.out.println(String.format("contains(%d)", item));
                    }
                    break;
                case 2: // test whether MinPQ contains some random item
                    item = randomNumberGenerator.nextInt();
                    assertEquals(naiveMinPQ.contains(item),
                            arrayHeapMinPQ.contains(item));
                    System.out.println(String.format("contains(%d)", item));
                    break;
                case 3: // test correct behavior of getSmallest
                    if (size != 0) {
                        assertEquals(naiveMinPQ.getSmallest(),
                                arrayHeapMinPQ.getSmallest());
                        System.out.println("getSmallest()");
                    }
                    break;
                case 4: // test correct behavior of removeSmallest
                    if (size != 0) {
                        item = arrayHeapMinPQ.removeSmallest();
                        assertEquals(naiveMinPQ.removeSmallest(),
                                (Integer) item);
                        allItems.remove(Integer.valueOf(item));
                        System.out.println("removeSmallest()");
                        size--;
                    }
                    break;
                case 5: // test size
                    assertEquals(size, arrayHeapMinPQ.size());
                    assertEquals(naiveMinPQ.size(), arrayHeapMinPQ.size());
                    System.out.println("size()");
                    break;
                case 6: // change priority of random item in MinPQ
                    if (size != 0) {
                        index = randomNumberGenerator.nextInt(allItems.size());
                        item = allItems.get(index);
                        priority = randomNumberGenerator.nextDouble();
                        naiveMinPQ.changePriority(item, priority);
                        arrayHeapMinPQ.changePriority(item, priority);
                        System.out.println(String.format("changePriority(%d, " +
                                "%f)", item, priority));
                    }
                    break;
            }
        }
    }

    @Test
    public void addTimingTest() {
        Random randomNumberGeneratorA = new Random(0);
        Random randomNumberGeneratorB = new Random(0);
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        int N = 10000;

        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < N; i += 1) {
            int item = randomNumberGeneratorA.nextInt();
            double priority = randomNumberGeneratorA.nextDouble();
            naiveMinPQ.add(item, priority);
        }
        System.out.println("NaiveMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");

        sw = new Stopwatch();
        for (int i = 0; i < N; i += 1) {
            int item = randomNumberGeneratorB.nextInt();
            double priority = randomNumberGeneratorB.nextDouble();
            arrayHeapMinPQ.add(item, priority);
        }
        System.out.println("ArrayHeapMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");
    }

    @Test
    public void containsTimingTest() {
        Random randomNumberGenerator = new Random(0);
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        int N = 10000;
        ArrayList<Integer> allItems = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            int item = randomNumberGenerator.nextInt();
            double priority = randomNumberGenerator.nextDouble();
            naiveMinPQ.add(item, priority);
            arrayHeapMinPQ.add(item, priority);
            allItems.add(item);
        }

        Random randomNumberGeneratorA = new Random(0);
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < N; i++) {
            int index = randomNumberGeneratorA.nextInt(allItems.size());
            int item = allItems.get(index);
            naiveMinPQ.contains(item);
        }
        System.out.println("NaiveMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");

        Random randomNumberGeneratorB = new Random(0);
        sw = new Stopwatch();
        for (int i = 0; i < N; i += 1) {
            int index = randomNumberGeneratorB.nextInt(allItems.size());
            int item = allItems.get(index);
            arrayHeapMinPQ.contains(item);
        }
        System.out.println("ArrayHeapMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");
    }

    @Test
    public void getAndRemoveSmallestTimingTest() {
        Random randomNumberGenerator = new Random(0);
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int item = randomNumberGenerator.nextInt();
            double priority = randomNumberGenerator.nextDouble();
            naiveMinPQ.add(item, priority);
            arrayHeapMinPQ.add(item, priority);
        }

        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < N; i++) {
            naiveMinPQ.getSmallest();
            naiveMinPQ.removeSmallest();
        }
        System.out.println("NaiveMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");

        sw = new Stopwatch();
        for (int i = 0; i < N; i += 1) {
            arrayHeapMinPQ.getSmallest();
            arrayHeapMinPQ.removeSmallest();
        }
        System.out.println("ArrayHeapMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");
    }

    @Test
    public void changePriorityTimingTest() {
        Random randomNumberGenerator = new Random(0);
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        int N = 10000;
        ArrayList<Integer> allItems = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            int item = randomNumberGenerator.nextInt();
            double priority = randomNumberGenerator.nextDouble();
            naiveMinPQ.add(item, priority);
            arrayHeapMinPQ.add(item, priority);
            allItems.add(item);
        }

        Random randomNumberGeneratorA = new Random(0);
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < N; i++) {
            int index = randomNumberGeneratorA.nextInt(allItems.size());
            int item = allItems.get(index);
            double priority = randomNumberGeneratorA.nextDouble();
            naiveMinPQ.changePriority(item, priority);
        }
        System.out.println("NaiveMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");

        Random randomNumberGeneratorB = new Random(0);
        sw = new Stopwatch();
        for (int i = 0; i < N; i += 1) {
            int index = randomNumberGeneratorB.nextInt(allItems.size());
            int item = allItems.get(index);
            double priority = randomNumberGeneratorB.nextDouble();
            arrayHeapMinPQ.changePriority(item, priority);
       }
        System.out.println("ArrayHeapMinPQ Total time elapsed: "
                + sw.elapsedTime() + " seconds.");
    }
}
