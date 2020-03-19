class Solution {
    // Approach 1 - BFS
    // time
    // space
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Integer> queue = new LinkedList();
        int fresh = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == 1) 
                    fresh++;
                if(grid[i][j] == 2)
                    queue.add(i * cols + j);
            }
        }
        
        if (fresh == 0) return 0;
        
        int time = -1;
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int pos = queue.poll();
                int row = pos / cols;
                int col = pos % cols;
                for (int[] dir : dirs) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        queue.add(nr * cols + nc);
                    }
                }
            }
            time++;
        }
        if (fresh > 0)
            return -1;
        return time;
    }
}
