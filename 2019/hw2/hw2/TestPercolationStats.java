package hw2;

import org.junit.Test;

public class TestPercolationStats {
    @Test
    public void simpleTest() {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 3, pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLow()
                + " : " + ps.confidenceHigh());
    }
}
