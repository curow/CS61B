package bearmaps;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = new LinkedList<>(points);
    }

    public Point nearest(double x, double y) {
        double nearest = Double.MAX_VALUE;
        Point source = new Point(x, y);
        Point nearestPoint = null;
        for (Point p : points) {
            double distance = Point.distance(source, p);
            if (distance < nearest) {
                nearestPoint = p;
                nearest = distance;
            }
        }
        return nearestPoint;
    }

}
