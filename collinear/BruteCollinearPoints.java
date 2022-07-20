import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        WeightedQuickUnionUF union = new WeightedQuickUnionUF(10);

//        To check whether the 4 points p, q, r, and s are collinear,
//        check whether the three slopes between p and q, between p and r, and between p and s are all equal.
        Arrays.sort(points);
        List<LineSegment> segmentsList = new ArrayList<>();
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
                                points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                            segmentsList.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }

        segments = segmentsList.toArray(new LineSegment[0]);
    }

    // Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null,
    // if any point in the array is null, or if the argument to the constructor contains a repeated point.
    private void validate(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Points array can't be null");
        for (Point point : points) if (point == null) throw new IllegalArgumentException("Null point found");
        for (int i = 0; i < points.length - 1; i++)
            if (points[i].compareTo(points[i + 1]) == 0 || points[i].compareTo(points[points.length - 1]) == 0)
                throw new IllegalArgumentException("Duplicate(s) found.");
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }
}
