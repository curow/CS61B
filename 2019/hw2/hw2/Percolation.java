package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF antibackwash;
    private int top;
    private int bottom;
    private int N;
    private int numOfOpenSites;

    private int getIndex(int i, int j) {
        return i * N + j;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N can't be less "
                    + "than zero!");
        }
        this.N = N;
        numOfOpenSites = 0;
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        int numOfSites = N * N;
        // two more to store top and bottom head.
        uf = new WeightedQuickUnionUF(numOfSites + 2);
        antibackwash = new WeightedQuickUnionUF(numOfSites + 1);
        // index in uf to store top grid head.
        top = numOfSites;
        // index in uf to store bottom grid head.
        bottom = numOfSites + 1;
    }

    private void  validate(int row, int col) {
        if (row > N || col > N || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("illegal arguments");
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOfOpenSites++;
            int center = getIndex(row, col);
            if (row == 0) {
                uf.union(top, center);
                antibackwash.union(top, center);
            }
            if (row == N - 1) {
                uf.union(bottom, center);
            }
            int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
            for (int[] direction : directions) {
                int i = row + direction[0];
                int j = col + direction[1];
                int other = getIndex(i, j);
                if (i >= 0 && j >= 0 && i < N && j < N && isOpen(i, j)) {
                    uf.union(center, other);
                    antibackwash.union(center, other);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = getIndex(row, col);
        return antibackwash.connected(top, index);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    // use for unit testing
    // (not required, but keep this here for the autograder)
    public static void main(String[] args) {
    }
}
