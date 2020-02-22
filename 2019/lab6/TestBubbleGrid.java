import static org.junit.Assert.*;
import org.junit.Test;

public class TestBubbleGrid {
    @Test
    public void test1() {
        int[][] grid = { {1, 1, 0},
                         {1, 0, 0},
                         {1, 1, 0},
                         {1, 1, 1} };
        int[][] darts = { {2, 2}, {2, 0} };
        BubbleGrid bubbleGrid = new BubbleGrid(grid);
        int[] result = bubbleGrid.popBubbles(darts);
        int[] expected = {0, 4};
        assertArrayEquals(expected, result);
    }

    @Test
    public void test2() {
        int[][] grid = { {1, 0, 0, 0}, {1, 1, 1, 0} };
        int[][] darts = { {1, 0} };
        BubbleGrid bubbleGrid = new BubbleGrid(grid);
        int[] result = bubbleGrid.popBubbles(darts);
        int[] expected = {2};
        assertArrayEquals(expected, result);
    }

    @Test
    public void test3() {
        int[][] grid = { {1, 0, 0, 0}, {1, 1, 0, 0} };
        int[][] darts = { {1, 1}, {1, 0} };
        BubbleGrid bubbleGrid = new BubbleGrid(grid);
        int[] result = bubbleGrid.popBubbles(darts);
        int[] expected = {0, 0};
        assertArrayEquals(expected, result);
    }
}
