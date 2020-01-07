class Solution {
    // Approach 1 - DP - Top-Down
    // some edge cases not passed at first place:
    // [[0]], [[1,0]]
    // cannot pass [[0,0],[1,1],[0,0]] why ??？
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        
        int m = obstacleGrid.length;    // rows
        int n = obstacleGrid[0].length; // columns
        if (m == 1 && n == 1)  return 1;
        int[][] memo = new int[m][n];
        memo[0][0] = 1;
        return dp(obstacleGrid, memo, 0, 0);
        
    }
    
    public int dp(int[][] obstacleGrid, int[][] memo, int m, int n) {
        // ending codition
        if (m >= obstacleGrid.length || n >= obstacleGrid[0].length || obstacleGrid[m][n] == 1) return 0;
        // base case
        if (m == 0 && n > 0 && memo[m][n-1] != 0 && obstacleGrid[m][n] != 1)  return 1;
        else if (n == 0 && m > 0 && memo[m-1][n] != 0 && obstacleGrid[m][n] != 1) return 1;
        
        // if visited
        // if (memo[m][n] > 0) return memo[m][n];
        
        memo[m][n] = dp(obstacleGrid, memo, m + 1, n) + dp(obstacleGrid, memo, m, n + 1);
        return memo[m][n];        
        
    }
    
    // Approach 2 - Dp - Bottom-up
    // time - O(n + m + n*m) space - O(n*m)
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;    // rows
        int n = obstacleGrid[0].length; // columns
        int[][] dp = new int[m][n];
        
        if (obstacleGrid[0][0] == 1) return 0;
        else dp[0][0] = 1;
        
        // initialize 0-th row and 0-th column
        for (int i = 1; i < m; i++) 
            dp[i][0] = (dp[i-1][0] == 0 || obstacleGrid[i][0] == 1) ? 0 : 1;
        for (int j = 1; j < n; j++) 
            dp[0][j] = (dp[0][j-1] == 0 || obstacleGrid[0][j] == 1) ? 0 : 1;
            
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    
    // Approach 2 - Optimization - reduce space memory
    // 2d DP -> use 1d dp array
    // since we only need to use value at dp[i - 1][j] and dp[i][j - 1] to update dp[i][j], 
    // we don't need to store the whole 2D table, but instead store value in an 1D array,
    // and update data by using dp[j] = dp[j] + dp[j - 1], (where here dp[j] corresponding 
    // to the dp[i - 1][j]) and dp[j - 1] corresponding to the dp[i][j - 1] in the 2D array)
    // time - O(m * n)  space - O(n)
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < n; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n-1];
    }
}
