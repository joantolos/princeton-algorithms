import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private int size;
    private Point2D point;
    private Node root;

    private static class Node {
        Point2D point;
        RectHV rect;
        Node lb, rt;
        boolean vertical;

        private Node(Point2D point, boolean vertical, Node prev) {
            this.point = point;
            this.vertical = vertical;

            if (prev == null) {
                this.rect = new RectHV(0.0, 0.0, 1.0, 1.0);
            } else {
                double xmin = prev.rect.xmin();
                double xmax = prev.rect.xmax();
                double ymin = prev.rect.ymin();
                double ymax = prev.rect.ymax();

                int comp = prev.compareTo(point);

                if (this.vertical) {
                    if (comp > 0) {
                        ymax = prev.point.y();
                    } else {
                        ymin = prev.point.y();
                    }
                } else {
                    if (comp > 0) {
                        xmax = prev.point.x();
                    } else {
                        xmin = prev.point.x();
                    }
                }

                this.rect = new RectHV(xmin, ymin, xmax, ymax);
            }
        }

        private int compareTo(Point2D that) {
            if (this.vertical) {
                return Double.compare(this.point.x(), that.x());
            } else {
                return Double.compare(this.point.y(), that.y());
            }
        }

        private void draw() {
            StdDraw.setPenRadius(0.007);
            if (this.vertical) {
                StdDraw.line(this.point.x(), this.rect.ymin(), this.point.x(), this.rect.ymax());
            } else {
                StdDraw.line(this.rect.xmin(), this.point.y(), this.rect.xmax(), this.point.y());
            }
            StdDraw.setPenRadius(0.01);
            point.draw();
            if (this.lb != null) {
                this.lb.draw();
            }
            if (this.rt != null) {
                this.rt.draw();
            }
        }

    }

    // construct an empty set of points
    public KdTree() {
        this.size = 0;
        this.point = null;
        this.root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        root = insert(root, p, true, null);
    }

    private Node insert(Node h, Point2D point, boolean isVertical, Node prev) {
        if (h == null) {
            size++;
            return new Node(point, isVertical, prev);
        }
        if (h.point.compareTo(point) == 0) {
            return h;
        }
        int comp = h.compareTo(point);
        if (comp > 0) {
            h.lb = insert(h.lb, point, !isVertical, h);
        } else {
            h.rt = insert(h.rt, point, !isVertical, h);
        }
        return h;
    }

    private Point2D get(Point2D point) {
        return get(root, point);
    }

    private Point2D get(Node h, Point2D point) {
        if (h == null) {
            return null;
        }
        if (h.point.compareTo(point) == 0) {
            return h.point;
        }
        int comp = h.compareTo(point);
        if (comp > 0) {
            return get(h.lb, point);
        } else {
            return get(h.rt, point);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("null point");
        }
        return get(p) != null;
    }

    // draw all points to standard draw
    public void draw() {
        if (root != null) {
            root.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("null rectangle");
        }
        if (isEmpty()) {
            return null;
        }
        List<Point2D> list = new LinkedList<>();
        range(root, rect, list);
        return list;
    }

    private void range(Node h, RectHV rect, List<Point2D> list) {
        if (h == null) {
            return;
        }
        if (rect.intersects(h.rect)) {
            if (rect.contains(h.point)) {
                list.add(h.point);
            }
            range(h.lb, rect, list);
            range(h.rt, rect, list);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("null point");
        }
        if (isEmpty()) {
            return null;
        }
        point = root.point;
        nearest(root, p);
        return point;
    }

    private void nearest(Node h, Point2D p) {
        if (h == null) {
            return;
        }
        if (point == null) {
            point = h.point;
        }
        if (h.rect.distanceSquaredTo(p) < p.distanceSquaredTo(point)) {
            if (p.distanceSquaredTo(h.point) < p.distanceSquaredTo(point)) {
                point = h.point;
            }
            if (h.compareTo(p) > 0) {   // p is less so first traverse the left
                nearest(h.lb, p);
                nearest(h.rt, p);
            } else {                   // p is greater so first traverse the right
                nearest(h.rt, p);
                nearest(h.lb, p);
            }
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
