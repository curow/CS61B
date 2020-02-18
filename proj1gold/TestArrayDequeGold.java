import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testRandom() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            int N = StdRandom.uniform(0, 4);
            Integer output;
            Integer expected;
            switch (N) {
                case 0:
                    sad.addFirst(i);
                    ads.addFirst(i);
                    message.append(String.format("addFirst(%d)\n", i));
                    break;
                case 1:
                    sad.addLast(i);
                    ads.addLast(i);
                    message.append(String.format("addLast(%d)\n", i));
                    break;
                case 2:
                    if (ads.isEmpty()) {
                        assertTrue(message.toString(), sad.isEmpty());
                        break;
                    }
                    output = sad.removeFirst();
                    expected = ads.removeFirst();
                    message.append("removeFirst()\n");
                    assertEquals(message.toString(), expected, output);
                    break;
                case 3:
                    if (ads.isEmpty()) {
                        assertTrue(message.toString(), sad.isEmpty());
                        break;
                    }
                    output = sad.removeLast();
                    expected = ads.removeLast();
                    message.append("removeLast()\n");
                    assertEquals(message.toString(), expected, output);
                    break;
            }
        }
    }
}
