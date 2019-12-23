import javafx.util.*;
class Solution {
    // Approach 1 - DFS 
    // time O(h*w) space worst - O(h*w)
    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    helper(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void helper(char[][] grid, int i, int j) {
        // out of boundary or reached a 0
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;
        // reached 1
        grid[i][j] = '0';
        // keep searching
        helper(grid, i + 1, j);
        helper(grid, i - 1, j);
        helper(grid, i, j + 1);
        helper(grid, i, j - 1);
    }

    
    // Approach 2 - BFS
    // time O(row * col) 
    // space O(min(row, col)), in worst case, where the grid is filled with islands
    // the maximum size of queue can grow up to min(row, col)
    public int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0) return count;
        
        Queue<Pair<Integer, Integer>> queue = new LinkedList();
        
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    queue.add(new Pair(i,j));
                    
                    while (!queue.isEmpty()) {
                        int curri = queue.peek().getKey();
                        int currj = queue.poll().getValue();
                        if (curri+1 < row && grid[curri + 1][currj] == '1'){
                            queue.add(new Pair(curri+1, currj));
                            grid[curri + 1][currj] = '0';
                        }   
                        if (curri-1 >= 0 && grid[curri - 1][currj] == '1'){
                            queue.add(new Pair(curri-1, currj));
                            grid[curri - 1][currj] = '0';
                        }
                        if (currj+1 < col && grid[curri][currj + 1] == '1') {
                            queue.add(new Pair(curri, currj+1));
                            grid[curri][currj + 1] = '0';
                        }
                        if (currj-1 >= 0 && grid[curri][currj - 1] == '1') {
                            queue.add(new Pair(curri, currj-1));
                            grid[curri][currj - 1] = '0';
                        }
                    }
                }
            }
        }
        return count;
    }
    
}
