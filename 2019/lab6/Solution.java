class Solution {
    static class UnionFind {
        private int[] array;

        public UnionFind(int n) {
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = -1;
            }
        }

        public int find(int index) {
            int parent = array[index];
            if (parent < 0) {
                return index;
            } else {
                int root = find(parent);
                array[index] = root;
                return root;
            }
        }

        public int sizeOf(int index) {
            return -array[find(index)];
        }

        public void union(int a, int b) {
            int root1 = find(a);
            int root2 = find(b);
            if (root1 == root2) {
                return;
            }
            int size1 = sizeOf(root1);
            int size2 = sizeOf(root2);
            if (size1 > size2) {
                array[root1] += array[root2];
                array[root2] = root1;
            } else {
                array[root2] += array[root1];
                array[root1] = root2;
            }
        }

    }

    private int rows;
    private int cols;
    private int head;
    private UnionFind uf;

    private int getIndex(int i, int j) {
        return i * cols + j;
    }

    private void unionAdjacent(int[][] grid, int i, int j) {
        int center = getIndex(i, j);
        // connect to head if at top row
        if (i == 0) {
            uf.union(center, head);
        }
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int[] dir : directions) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols
                && grid[ni][nj] == 1) {
                uf.union(center, getIndex(ni, nj));
            }
        }
    }

    public int[] hitBricks(int[][] grid, int[][] hits) {
        rows = grid.length;
        cols = grid[0].length;
        head = rows * cols;
        // one extra space to store head.
        uf = new UnionFind(rows * cols + 1);
        // mark all places that are hit.
        for (int[] hit : hits) {
            int i = hit[0];
            int j = hit[1];
            grid[i][j] -= 1;
        }
        // union top bricks to head
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 1) {
                uf.union(head, getIndex(0, j));
            }
        }
        // union remaining bricks
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    unionAdjacent(grid, i, j);
                }
            }
        }
        int[] numOfFallingBricks = new int[hits.length];
        int oldCount = uf.sizeOf(head);
        // recover hit operation
        for (int k = hits.length - 1; k >= 0; k--) {
            int[] hit = hits[k];
            int i = hit[0];
            int j = hit[1];
            grid[i][j] += 1;
            if (grid[i][j] == 0) {
                numOfFallingBricks[k] = 0;
            } else {
                unionAdjacent(grid, i, j);
                int newCount = uf.sizeOf(head);
                // remove the hit brick if not-falling bricks increases
                numOfFallingBricks[k] = Math.max(newCount - oldCount - 1, 0);
                oldCount = newCount;
            }
        }
        return numOfFallingBricks;
    }
}