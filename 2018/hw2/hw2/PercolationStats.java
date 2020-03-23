package hw2;

import static edu.princeton.cs.introcs.StdRandom.uniform;

public class PercolationStats {

    private double[] recorder;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.T = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException(
                    "N or T should not less than or equal to zero");
        }
        recorder = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int count = 0;
            while (!p.percolates()) {
                int row = uniform(N);
                int col = uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count++;
                }
            }
            recorder[i] = ((double) count) / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return edu.princeton.cs.introcs.StdStats.mean(recorder);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return edu.princeton.cs.introcs.StdStats.stddev(recorder);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
