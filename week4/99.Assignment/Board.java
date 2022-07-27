import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int[][] tiles;
    private final int[] goal;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        int[] tmpGoal = new int[n * n];
        goal = new int[n * n];

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] != 0) {
                    tmpGoal[(i * n) + j] = tiles[i][j];
                }
            }
        }
        Arrays.sort(tmpGoal);
        for (int i = 0; i < tmpGoal.length; i++) {
            if (i != 0) {
                goal[i - 1] = tmpGoal[i];
            }
        }
        goal[goal.length - 1] = 0;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tiles.length).append("\n");
        for (int[] tile : tiles) {
            for (int j = 0; j < tiles.length; j++) {
                sb.append(tile[j]).append(" ");
                if (j == tiles.length - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int k = 0;

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                int goal = this.goal[(i * n) + j];
                int tile = tiles[i][j];
                if (goal != tile) {
                    k++;
                }
            }
        }
        return k;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != (n * i + j + 1)) {
                    int x = (tiles[i][j] / n);
                    int y = (tiles[i][j] - x * n);
                    manhattan += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if ((y == null) || (y.getClass() != this.getClass())) {
            return false;
        }

        Board board = (Board) y;
        if (this.dimension() != ((Board) y).dimension()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != board.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbours = new LinkedList<>();
        int[][] neighboursarray = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                neighboursarray[i][j] = tiles[i][j];
            }
        }

        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0 && i - 1 >= 0) {
                    neighboursarray[i][j] = neighboursarray[i - 1][j];
                    neighboursarray[i - 1][j] = 0;
                    neighbours.add(new Board(neighboursarray));
                    neighboursarray[i][j] = 0;
                    neighboursarray[i - 1][j] = tiles[i - 1][j];
                    c = 1;
                }
                if (tiles[i][j] == 0 && i + 1 < n) {
                    neighboursarray[i][j] = neighboursarray[i + 1][j];
                    neighboursarray[i + 1][j] = 0;
                    neighbours.add(new Board(neighboursarray));
                    neighboursarray[i][j] = 0;
                    neighboursarray[i + 1][j] = tiles[i + 1][j];
                    c = 1;
                }
                if (tiles[i][j] == 0 && j - 1 >= 0) {
                    neighboursarray[i][j] = neighboursarray[i][j - 1];
                    neighboursarray[i][j - 1] = 0;
                    neighbours.add(new Board(neighboursarray));
                    neighboursarray[i][j] = 0;
                    neighboursarray[i][j - 1] = tiles[i][j - 1];
                    c = 1;
                }
                if (tiles[i][j] == 0 && j + 1 < n) {
                    neighboursarray[i][j] = neighboursarray[i][j + 1];
                    neighboursarray[i][j + 1] = 0;
                    neighbours.add(new Board(neighboursarray));
                    neighboursarray[i][j] = 0;
                    neighboursarray[i][j + 1] = tiles[i][j + 1];
                    c = 1;
                }
                if (c == 1) {
                    break;
                }
            }
            if (c == 1) {
                break;
            }
        }

        return neighbours;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinall = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                twinall[i][j] = tiles[i][j];
            }
        }
        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            twinall[0][0] = tiles[0][1];
            twinall[0][1] = tiles[0][0];
        } else {
            twinall[1][0] = tiles[1][1];
            twinall[1][1] = tiles[1][0];
        }
        return new Board(twinall);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        printingTheBoard();
        hammingTest();
        manhattanTest();
    }

    private static void manhattanTest() {
        // 5 tiles out of place
        // manhattan = 1 + 2 + 2 + 2 + 3 = 10
        // 8 1 3
        // 4 0 2
        // 7 6 5
        int[][] fiveTilesOut = new int[3][3];
        fiveTilesOut[0][0] = 1;
        fiveTilesOut[0][1] = 2;
        fiveTilesOut[0][2] = 0;

        fiveTilesOut[1][0] = 6;
        fiveTilesOut[1][1] = 5;
        fiveTilesOut[1][2] = 4;

        fiveTilesOut[2][0] = 7;
        fiveTilesOut[2][1] = 8;
        fiveTilesOut[2][2] = 3;

        System.out.println(new Board(fiveTilesOut).hamming());  // Should be 5
        System.out.println(new Board(fiveTilesOut).manhattan());  // Should be 10
    }
    private static void hammingTest() {
        // 5 tiles out of place
        // 0 1 3
        // 4 2 5
        // 7 8 6
        // Goal
        // 1 2 3
        // 4 5 6
        // 7 8 0
        int[][] fiveTilesOut = new int[3][3];
        fiveTilesOut[0][0] = 0;
        fiveTilesOut[0][1] = 1;
        fiveTilesOut[0][2] = 3;

        fiveTilesOut[1][0] = 4;
        fiveTilesOut[1][1] = 2;
        fiveTilesOut[1][2] = 5;

        fiveTilesOut[2][0] = 7;
        fiveTilesOut[2][1] = 8;
        fiveTilesOut[2][2] = 6;

        System.out.println(new Board(fiveTilesOut).hamming());    // should be 6

        // 2 tiles out of place
        // 1 2 0
        // 6 5 4
        // 7 8 3
        // Goal
        // 1 2 3
        // 4 5 6
        // 7 8 0
        int[][] twoTilesOut = new int[3][3];
        twoTilesOut[0][0] = 1;
        twoTilesOut[0][1] = 2;
        twoTilesOut[0][2] = 0;

        twoTilesOut[1][0] = 6;
        twoTilesOut[1][1] = 5;
        twoTilesOut[1][2] = 4;

        twoTilesOut[2][0] = 7;
        twoTilesOut[2][1] = 8;
        twoTilesOut[2][2] = 3;

        System.out.println(new Board(twoTilesOut).hamming());    // should be 2
    }

    private static void printingTheBoard() {
        // Testing printing the board
        // 0 1 3
        // 4 2 5
        // 7 8 6
        int[][] tiles = new int[3][3];
        tiles[0][0] = 0;
        tiles[0][1] = 1;
        tiles[0][2] = 3;

        tiles[1][0] = 4;
        tiles[1][1] = 2;
        tiles[1][2] = 5;

        tiles[2][0] = 7;
        tiles[2][1] = 8;
        tiles[2][2] = 6;

        Board board = new Board(tiles);
        System.out.println(board);
    }
}
