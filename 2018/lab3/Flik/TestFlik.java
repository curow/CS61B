import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlik {
    @Test
    public void testIsSameNumber() {
        int a1 = 10;
        int b1= 10;
        assertTrue(Flik.isSameNumber(a1, b1));

        int a2 = 0;
        int b2= 0;
        assertTrue(Flik.isSameNumber(a2, b2));

        int a3 = -10;
        int b3= -10;
        assertTrue(Flik.isSameNumber(a3, b3));

        int a4 = 10;
        int b4= -10;
        assertFalse(Flik.isSameNumber(a4, b4));

        int a5 = 0;
        int b5= 10;
        assertFalse(Flik.isSameNumber(a5, b5));

        int a6 = -10;
        int b6= 0;
        assertFalse(Flik.isSameNumber(a6, b6));

        int a7 = 129;
        int b7= 129;
        assertTrue(Flik.isSameNumber(a7, b7));

        int a8 = 10000;
        int b8= 10000;
        assertTrue(Flik.isSameNumber(a8, b8));
    }
}