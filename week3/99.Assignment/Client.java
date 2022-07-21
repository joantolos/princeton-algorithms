package sandbox;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Client {

    public static void main(String[] args) {

        // Six points example
        // (19000 10000),(18000 10000),(32000 10000),(21000 10000),(1234 5678),(14000 10000)
        Point[] sixPoints = new Point[6];
        sixPoints[0] = new Point(19000, 10000);
        sixPoints[1] = new Point(18000, 10000);
        sixPoints[2] = new Point(32000, 10000);
        sixPoints[3] = new Point(21000, 10000);
        sixPoints[4] = new Point(1234, 5678);
        sixPoints[5] = new Point(14000, 10000);

        // Eight points example
        // (10000 0),(0 10000),(3000 7000),(7000 3000),(20000 21000),(3000 4000),(14000 15000),(6000 7000)
        Point[] eightPoints = new Point[8];
        eightPoints[0] = new Point(10000, 0);
        eightPoints[1] = new Point(0, 10000);
        eightPoints[2] = new Point(3000, 7000);
        eightPoints[3] = new Point(7000, 3000);
        eightPoints[4] = new Point(20000, 21000);
        eightPoints[5] = new Point(3000, 4000);
        eightPoints[6] = new Point(14000, 15000);
        eightPoints[7] = new Point(6000, 7000);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : eightPoints) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
        BruteCollinearPoints collinear = new BruteCollinearPoints(eightPoints);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
