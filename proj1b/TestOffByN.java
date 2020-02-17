import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffByN() {
        OffByN offByFive = new OffByN(5);

        CharacterComparator offByOne = new OffByN(1);
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'a'));

        CharacterComparator offByTwo = new OffByN(2);
        assertTrue(offByTwo.equalChars('a', 'c'));
        assertFalse(offByTwo.equalChars('a', 'b'));
        assertFalse(offByTwo.equalChars('a', 'a'));
    }
}
