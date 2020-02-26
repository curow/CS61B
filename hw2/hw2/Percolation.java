package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    // https://drkbl.com/posts/quote-avoid-backwash-in-percolation/
    // implemented solution 3 of the above link
    private boolean[] connectedToBottom;
    private int top;
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
        uf = new WeightedQuickUnionUF(numOfSites + 1);
        connectedToBottom = new boolean[numOfSites + 1];
        top = numOfSites;
    }

    private void  validate(int row, int col) {
        if (row > N || col > N || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("illegal arguments");
        }
    }

    // accumulate whether or not a set has connection to bottom.
    private void unionWithCheckBottom(int p, int q) {
        boolean toBottom =
                connectedToBottom[uf.find(p)] || connectedToBottom[uf.find(q)];
        uf.union(p, q);
        connectedToBottom[uf.find(p)] |= toBottom;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOfOpenSites++;
            int center = getIndex(row, col);
            // first check if node is connected to bottom.
            if (row == N - 1) {
                connectedToBottom[center] = true;
            }
            // union components while keeping the connected to bottom info.
            if (row == 0) {
                unionWithCheckBottom(top, center);
            }
            int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
            for (int[] direction : directions) {
                int i = row + direction[0];
                int j = col + direction[1];
                int other = getIndex(i, j);
                if (i >= 0 && j >= 0 && i < N && j < N && isOpen(i, j)) {
                    unionWithCheckBottom(center, other);
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
        return grid[row][col] && uf.connected(top, index);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connectedToBottom[uf.find(top)];
    }

    // use for unit testing
    // (not required, but keep this here for the autograder)
    public static void main(String[] args) {
    }
}
