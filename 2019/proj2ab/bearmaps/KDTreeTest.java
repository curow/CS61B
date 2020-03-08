package bearmaps;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void nearestTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(3.0, 4.0);

        KDTree kd1 = new KDTree(List.of(p1, p2, p3));
        Point ret = kd1.nearest(3.0, 4.0); // returns p2
        assertEquals(p2, ret);

        KDTree kd2 = new KDTree(List.of(p1));
        ret = kd2.nearest(3.0, 4.0); // returns p2
        assertEquals(p1, ret);

        KDTree kd3 = new KDTree(List.of(p1, p2, p3, p4));
        ret = kd3.nearest(3.0, 4.0); // returns p2
        assertEquals(p4, ret);
    }

    @Test
    public void randomTest() {
        int N = 100;
        Random randomGenerator = new Random(0);
        for (int i = 0; i < N; i++) {
            int M = randomGenerator.nextInt(1000) + 1;
            List<Point> points = new LinkedList<>();
            for (int j = 0; j < M; j++) {
                double x = randomGenerator.nextDouble();
                double y = randomGenerator.nextDouble();
                Point p = new Point(x, y);
                points.add(p);
            }
            NaivePointSet nn = new NaivePointSet(points);
            KDTree kd = new KDTree(points);
            int K = randomGenerator.nextInt(1000) + 1;
            for (int k = 0; k < K; k++) {
                double x = randomGenerator.nextDouble();
                double y = randomGenerator.nextDouble();
                Point source = new Point(x, y);
                Point expected = nn.nearest(x, y);
                Point actual = kd.nearest(x, y);
                double expectedDistance = Point.distance(source, expected);
                double actualDistance = Point.distance(source, actual);
                assertEquals(String.format( "expected distance : %f\nactual " +
                        "distance: %f", expectedDistance, actualDistance),
                        expected, actual);
            }
        }
    }
}
