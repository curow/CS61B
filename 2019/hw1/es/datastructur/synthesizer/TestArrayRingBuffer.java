package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertEquals(10, arb.capacity());
        assertEquals(0, arb.fillCount());
        assertTrue(arb.isEmpty());
        arb.enqueue(30);
        assertEquals(30, (int) arb.peek());
        assertEquals(1, arb.fillCount());
        int output = arb.dequeue();
        assertEquals(30, output);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        assertEquals(10, arb.fillCount());
        assertTrue(arb.isFull());
    }

    @Test
    public void cornerCaseTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(1);
        assertTrue(arb.isEmpty());
        arb.enqueue(23);
        assertTrue(arb.isFull());
        assertEquals(23, (int) arb.peek());
        int output = arb.dequeue();
        assertTrue(arb.isEmpty());
        assertEquals(23, output);
    }

    @Test
    public void iteratorTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arb.capacity(); i++) {
            arb.enqueue(i);
        }
        for (int i : arb) {
            System.out.println(i);
        }
    }

    @Test
    public void equalTest() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arb1.capacity(); i++) {
            arb1.enqueue(i);
        }

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arb2.capacity(); i++) {
            arb2.enqueue(i);
        }

        assertEquals(arb1, arb2);
    }
}
