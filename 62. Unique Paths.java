class Solution {
    // Approach 1 - dp - bottom-up
    // base case is important!!
    // transition formula: dp[i][j] = dp[i-1][j] + dp[i][j-1]
    // time - O(m * n), space - o(m * n)
    public int uniquePaths1(int m, int n) {
        if (m <= 0|| n <= 0) return 0;
        
        int[][] dp= new int[n + 1][m + 1];
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                // base case
                // we need to make sure i-1 and j-1 are valid
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n-1][m-1];
    }
    
    // Approach 2 - dp - top-down
    // time - O(m * n), space - O(m * n + max(m,n))
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 && n == 1) return 1;
        int[][] memo = new int[m][n];
        return dp(m-1, n-1, memo);
    }
    
    public int dp(int m, int n, int[][] memo) {
        if (m <= 0 && n <= 0) return 0;
        if (m == 0 || n == 0) return 1;
        if (memo[m][n] > 0) return memo[m][n];
        memo[m][n] = dp(m - 1, n, memo) + dp(m, n - 1, memo);
        return memo[m][n];
    }
}
