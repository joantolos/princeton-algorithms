import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        validate(points);
//        To check whether the 4 points p, q, r, and s are collinear,
//        check whether the three slopes between p and q, between p and r, and between p and s are all equal.
        Arrays.sort(points);
        List<LineSegment> segmentsList = new ArrayList<>();
        int d;

        for (Point point : points) {
            Point[] all = points.clone();
            Arrays.sort(all, point.slopeOrder());
            d = 1;
            double previous = Double.NEGATIVE_INFINITY;
            int start = 0;

            for (int j = 0; j < all.length; j++) {
                double current = point.slopeTo(all[j]);
                if (Double.compare(current, previous) != 0) {
                    if (d >= 4 && point.compareTo(all[start]) <= 0) {
                        segmentsList.add(new LineSegment(point, all[j - 1]));
                    }
                    d = 1;
                    start = j;
                } else if (j == all.length - 1) {
                    if (d >= 3 && point.compareTo(all[start]) <= 0) {
                        segmentsList.add(new LineSegment(point, all[j]));
                    }
                    d = 1;
                }
                d++;
                previous = current;
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
