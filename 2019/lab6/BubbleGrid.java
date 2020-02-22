public class BubbleGrid {
    private int[][] grid;
    private int height;
    private int width;

    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        height = grid.length;
        width = grid[0].length;
    }

    private UnionFind buildUnions() {
        UnionFind unions = new UnionFind(height * width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 1) {
                    int center = i * width + j;
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        // top = (i - 1) * width + j;
                        int top = center - width;
                        unions.union(center, top);
                    }
                    if (i + 1 < height && grid[i + 1][j] == 1) {
                        // down = (i + 1) * width + j;
                        int down = center + width;
                        unions.union(center, down);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        // left = i * width + j - 1;
                        int left = center - 1;
                        unions.union(center, left);
                    }
                    if (j + 1 < width && grid[i][j + 1] == 1) {
                        // right = i * width + j + 1;
                        int right = center + 1;
                        unions.union(center, right);
                    }
                }
            }
        }
        return unions;
    }

    private boolean stuckWithTop (UnionFind uf, int index) {
        for (int i = 0; i < width; i++) {
            if (uf.connected(i, index)) {
                return true;
            }
        }
        return false;
    }

    private int countFall() {
        int count = 0;
        UnionFind uf = buildUnions();
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int index = i * width + j;
                if (grid[i][j] == 1 && !stuckWithTop(uf, index)) {
                    count++;
                    grid[i][j] = 0;
                }
            }
        }
        return count;
    }

    public int[] popBubbles(int[][] darts) {
        int[] ans = new int[darts.length];
        for (int k = 0; k < darts.length; k++) {
            int[] dart = darts[k];
            int i = dart[0];
            int j = dart[1];
            if (grid[i][j] == 0) {
                ans[k] = 0;
            } else {
                grid[i][j] = 0;
                ans[k] = countFall();
            }
        }
        return ans;
    }
}
