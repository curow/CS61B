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

        public boolean lessThanOrEqualTo(Point p) {
            switch (dimension) {
                case 0:
                    return point.getX() <= p.getX();
                case 1:
                    return point.getY() <= p.getY();
                default:
                    throw new IllegalStateException("Illegal Dimension");
            }
        }

        public double boundaryDistanceTo(Point p) {
            switch (dimension) {
                case 0:
                    return Math.pow(point.getX() - p.getX(), 2);
                case 1:
                    return Math.pow(point.getY() - p.getY(), 2);
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
        } else if (root.lessThanOrEqualTo(p)) {
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
        Point goal = new Point(x, y);
        return nearest(root, goal, root).point;
    }

    private KDNode nearest(KDNode p, Point goal, KDNode bestNode) {
        if (p == null) {
            return bestNode;
        }
        double currentDistance = Point.distance(p.point, goal);
        double bestDistance = Point.distance(bestNode.point, goal);
        if (currentDistance < bestDistance) {
            bestNode = p;
        }
        KDNode goodSide;
        KDNode badSide;
        if (p.lessThanOrEqualTo(goal)) {
            goodSide = p.right;
            badSide = p.left;
        } else {
            goodSide = p.left;
            badSide = p.right;
        }
        bestNode = nearest(goodSide, goal, bestNode);
        bestDistance = Point.distance(bestNode.point, goal);
        if (p.boundaryDistanceTo(goal) < bestDistance) {
            bestNode = nearest(badSide, goal, bestNode);
        }
        return bestNode;
    }
}
