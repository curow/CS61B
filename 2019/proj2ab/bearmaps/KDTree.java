package bearmaps;

import java.util.List;

public class KDTree {
    private KDNode root;

    private static class KDNode {
        public Point point;
        public int dimension;
        public KDNode left;
        public KDNode right;

        public KDNode(Point point, int dimension) {
            this.point = point;
            this.dimension = dimension % 2;
        }

        public boolean lessOrEqualTo(Point p) {
            switch (dimension) {
                case 0:
                    return point.getX() <= p.getX();
                case 1:
                    return point.getY() <= p.getY();
                default:
                    throw new IllegalStateException("Illegal Dimension");
            }
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            add(p);
        }
    }

    private KDNode add(KDNode root, int dimension, Point p) {
        if (root == null) {
            return new KDNode(p, dimension);
        } else if (root.point.equals(p)) {
            return root;
        } else if (root.lessOrEqualTo(p)) {
            root.right = add(root.right, (dimension + 1) % 2, p);
        } else {
            root.left = add(root.left, (dimension + 1) % 2, p);
        }
        return root;
    }

    public void add(Point p) {
        root = add(root, 0, p);
    }

    public Point nearest(double x, double y) {
        Point source = new Point(x, y);
        return nearest(root, source, null, Double.MAX_VALUE).point;
    }

    private KDNode nearest(KDNode p, Point source,
                           KDNode nearestNode, double nearestDistance) {
        if (p == null) {
            return nearestNode;
        }
        double distance = Point.distance(p.point, source);
        if (distance < nearestDistance) {
            nearestNode = p;
            nearestDistance = distance;
        }
        nearestNode = nearest(p.left, source, nearestNode, nearestDistance);
        if (nearestNode != p) {
            nearestDistance = Point.distance(nearestNode.point, source);
        }
        nearestNode = nearest(p.right, source, nearestNode, nearestDistance);
        return nearestNode;
    }
}
