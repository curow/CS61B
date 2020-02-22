import static org.junit.Assert.*;
import org.junit.Test;
public class TestUnionFind {
    @Test
    public void simpleTest() {
        UnionFind unions = new UnionFind(10);
        // same size, break tie by connect v1 to v2, so 1 should be root.
        unions.union(0 ,1);
        assertTrue(unions.connected(0, 1));
        assertEquals(1, unions.find(0));
        assertEquals(1, unions.find(1));
        assertEquals(-2, unions.parent(1));
        assertEquals(1, unions.parent(0));
        assertEquals(2, unions.sizeOf(0));
        assertEquals(2, unions.sizeOf(1));

        // connect (2) to (0, 1) set
        unions.union(0, 2);
        assertTrue(unions.connected(0, 2));
        assertTrue(unions.connected(1, 2));
        assertEquals(1, unions.find(0));
        assertEquals(1, unions.find(1));
        assertEquals(1, unions.find(2));
        assertEquals(-3, unions.parent(1));
        assertEquals(1, unions.parent(0));
        assertEquals(1, unions.parent(2));
        assertEquals(3, unions.sizeOf(0));
        assertEquals(3, unions.sizeOf(1));
        assertEquals(3, unions.sizeOf(2));
    }
}
