class Solution {
    
    // Approach 1 - Backtracking - (Wrong)
    // It's the same as two person both start from (0,0) to (n-1, n-1)
    // time: O(2^(n*m))
    public int cherryPickupTLE(int[][] grid) {
    
        return Math.max(0, dfs2(grid, 0, 0, true));
    }
    
    public int dfs2(int[][] grid, int i, int j, boolean isRound1) {
        if (i >= grid.length || j >= grid[0].length || grid[i][j] == -1){
            return Integer.MIN_VALUE;
        }
        
        // save for later undo the choice
        int restore = grid[i][j];
        grid[i][j] = 0;
        int result;
        
        // first person reach the end, second person starts
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            result = isRound1 ? dfs2(grid, 0, 0, false) : 0;
        }
        else {
            int down = dfs2(grid, i + 1, j, isRound1);
            int right = dfs2(grid, i, j + 1, isRound1);
            result = Math.max(down, right);
        }
        
        grid[i][j] = restore;
        return result + restore;
    }
    
    /*
    It is easy to see:
    Instead of having two paths starting from 0,0 and then other path from N,N. 
    We can have two people starting from 0,0 and find two paths that collects maximum cherries.
    First  person finds the path to collect maximum cherries and mark those cherries collected then
    Second person finds another path to collect maximum cherries. 

    Though here is the case where local maximum is not global maximum. 
    So having cherry pick up by person1 and then person2 won't give the correct result. 
    This approach fails to find the best answer to this case. 

    Reference : https://leetcode.com/problems/cherry-pickup/solution/
    Approach #1: Greedy [Wrong Answer] 
    11100
    00101
    10100
    00100
    00111
    In above example we should be able to pick all cherries. I leave it up to you to figure out two paths that collect all cherries. 
    But, with our approach person1 will collect 9 cherries leaving once that is on the right(1,4) and on the left(2,0). 
    Then person2 won't be able to collect both cherries he can collect only right one or only left one. 
    
    so above solution is not correct when facing this case
    
    Approach #2: 
    Now, we know that we want collectively maximum cherries.
    So, we have to do the traversal of both paths "at the same time" and select maximum global answer. 
    The potential problem of this approach is double counting (if we collect same cherry in 2 paths), but this can be easily avoided in code.
    If both are at the same cell we count cherry only once.
    Following code is backtracking brute force so it is TLE.
    I think Time Complexity is : 4^N*N. As we are calling cherryPickup 4 times recursively with problem size N*N.
*/
    public int cherryPickup1(int[][] grid) {
        // int[][][][] memo = new int[grid.length][grid[0].length][grid.length][grid[0].length];
        return Math.max(0, backtracking(grid, 0, 0, 0, 0));
    }
    
    public int backtracking(int[][] grid, int r1, int c1, int r2, int c2) {
        // since we're only going down and to the right, no need to check for < 0
        // if we went out of the grid or hit a thorn, discourage this path by returning Integer.MIN_VALUE
        if (r1 >= grid.length || r2 >= grid.length || c1 >= grid[0].length || c2 >= grid[0].length || grid[r1][c1] == -1 || grid[r2][c2] == -1) 
            return Integer.MIN_VALUE;
        
        // if person 1 reached the bottom right, return what's in there (could be 1 or 0)
        if (r1 == grid.length - 1 && c1 == grid[0].length - 1)
            return grid[r1][c1];
        
        if (r2 == grid.length - 1 && c2 == grid[0].length - 1)
            return grid[r2][c2];
        
        int res;
        // if two person are both in the same grid, don't double count 
        if (r1 == r2 && c1 == c2)
            res = grid[r1][c1];
        else
            res = grid[r1][c1] + grid[r2][c2];
        
        // since each person of the 2 person can move only to the bottom or to the right, then the total number of cherries
        // equals the max of the following possibilities:
        //    P1     |      P2
        //   DOWN    |     DOWN
        //   DOWN    |     RIGHT
        //   RIGHT   |     DOWN
        //   RIGHT   |     RIGHT
        res += Math.max(
            Math.max(backtracking(grid, r1+1, c1, r2+1, c2), backtracking(grid, r1+1, c1, r2, c2+1)),
            Math.max(backtracking(grid, r1, c1+1, r2+1, c2), backtracking(grid, r1, c1+1, r2, c2+1))
        );
        return res;
    }
    
    /*
    Approach 3: backtracking + memo
    To make this solution use memoization, we have to think what states we have to preserve. 
    Here we want to track r1,c1 and r2,c2 positions. 
    So, if we create Integer[][][][] dp = new Integer[N][N][N][N];  
    and track all these states then it will reduce the time complexity to N^4.

    dp[r1][c1][r2][c2] will identify each state. 
    if dp[r1][c1][r2][c2] is null then that means we haven't computed subanswer for that state. 
    if dp[r1][c1][r2][c2] is NOT null then we just return calculated subanswer. 

    Personally I think if you come up with N^4 solution in the interview then it is awesome. 

    Runtime: 100 ms, faster than 6.59% of Java online submissions for Cherry Pickup.
    Memory Usage: 135.1 MB, less than 5.09% of Java online submissions for Cherry Pickup.
    */
     public int cherryPickup(int[][] grid) {
        int n = grid.length;
        return Math.max(0, backMemo(grid, new Integer[n][n][n][n], n, 0, 0, 0, 0));
    }
    
    
    public int backMemo(int[][] grid, Integer[][][][] memo, int n, int r1, int c1, int r2, int c2) {
        if (r1 >= n || r2 >= n || c1 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) 
            return Integer.MIN_VALUE;
        
        if (memo[r1][c1][r2][c2] != null) return memo[r1][c1][r2][c2];
        
        // if person 1 reached the bottom right, return what's in there (could be 1 or 0)
        if (r1 == n - 1 && c1 == n - 1)
            return grid[r1][c1];
        
        if (r2 == n - 1 && c2 == n - 1)
            return grid[r2][c2];
        
        int res;
        // if two person are both in the same grid, don't double count 
        if (r1 == r2 && c1 == c2)
            res = grid[r1][c1];
        else
            res = grid[r1][c1] + grid[r2][c2];
        
        res += Math.max(
            Math.max(backMemo(grid, memo, n, r1+1, c1, r2+1, c2), backMemo(grid, memo, n, r1+1, c1, r2, c2+1)),
            Math.max(backMemo(grid, memo, n, r1, c1+1, r2+1, c2), backMemo(grid, memo, n, r1, c1+1, r2, c2+1))
        );
        memo[r1][c1][r2][c2] = new Integer(res);
        return res;
    }
    
}
