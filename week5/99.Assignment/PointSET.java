import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("null rectangle");
        }
        SET<Point2D> list = new SET<>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                list.add(p);
            }
        }
        return list;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("null point");
        }
        if (points.isEmpty()) {
            return null;
        }
        Point2D n = null;
        for (Point2D q : points) {
            if (n == null) {
                n = q;
                continue;
            }
            if (p.distanceSquaredTo(q) < p.distanceSquaredTo(n)) {
                n = q;
            }
        }
        return n;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
