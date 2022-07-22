package sandbox;

import java.util.Arrays;

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
                if (goal != 0 && tile != 0 && goal != tile) {
                    k++;
                }
            }
        }
        return k;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
//        for (int i = 0; i < n; i ++) {
//            for (int j = 0; j < n; j ++) {
//                int goal = this.goal[(i * n) + j];
//                int tile = tiles[i][j];
//                if (goal != 0 && tile != 0 && goal != tile) {
//                    k++;
//                }
//            }
//        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
//        printingTheBoard();
//        hammingTest();
    }

    private static void hammingTest() {
        // 6 tiles out of place
        // 0 1 3
        // 4 2 5
        // 7 8 6
        // Goal
        // 1 2 3
        // 4 5 6
        // 7 8 0
        int[][] sixTilesOut = new int[3][3];
        sixTilesOut[0][0] = 0;
        sixTilesOut[0][1] = 1;
        sixTilesOut[0][2] = 3;

        sixTilesOut[1][0] = 4;
        sixTilesOut[1][1] = 2;
        sixTilesOut[1][2] = 5;

        sixTilesOut[2][0] = 7;
        sixTilesOut[2][1] = 8;
        sixTilesOut[2][2] = 6;

        Board sixWrongBoard = new Board(sixTilesOut);
        System.out.println(sixWrongBoard.hamming());

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

        Board twoWrongBoard = new Board(twoTilesOut);
        System.out.println(twoWrongBoard.hamming());
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
