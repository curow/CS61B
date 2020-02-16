import org.junit.Test;
import static org.junit.Assert.*;

public class TestExamPrep03 {
    @Test
    public void testFlatten() {
        int[][] input1 = {{1, 2, 3}, {}, {7, 8}};
        int[] expected1 = {1, 2, 3, 7, 8};
        int[] actual1 = ExamPrep03.flatten(input1);
        assertArrayEquals(expected1, actual1);

        int[][] input2 = {{}, {}, {}};
        int[] expected2 = {};
        int[] actual2 = ExamPrep03.flatten(input2);
        assertArrayEquals(expected2, actual2);
    }
}