class Solution {
    // Approach 1 - DFS - backtracking
    // time - O(4^(m*n)), spacee - O (m*n)
    
    /*
    1. ending condition
       all 0s have been walked by && grid[i][j] == 2
    2. constraint
       stop when grid[][] == -1
    3. formula
       dfs to 4 directions 
    */
    
    // !!! starting sqaure is counted
    int emptyCount = 1;
    int sr, sc, er, ec;
    int rows, cols;
    int res;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        rows = grid.length;
        cols = grid[0].length;
        
        // iterate over the grid first
        // 1. find the starting and ending position 
        // 2. count the number of empty squares
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int square = grid[i][j];
                if (square == 1){
                    sr = i;
                    sc = j;
                }
                else if (square == 2) {
                    er = i;
                    ec = j;
                }
                if (square == 0){
                    emptyCount++;
                }
            }
        }
        res = 0;
        backtracking(grid, sr, sc, 0);
        return res;
    }
    
    public void backtracking(int[][] grid, int i, int j, int count) {
        // ending condition
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] < 0)
            return;
        
        if (i == er && j == ec && count == emptyCount) {
            res++;
            return;
        }
        
        // turn empty square into an obstacle
        grid[i][j]  = -2;
        count++;
            
        // 4 directions
        for (int d = 0; d < dr.length; d++) {
            int r = i + dr[d];
            int c = j + dc[d];

            backtracking(grid, r, c, count);
        }
        
        // turn empty square back
        // !!! remember to turn count back
        grid[i][j] = 0;
        count--;
    }
}
