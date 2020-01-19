class Solution {
    // thinking process: dfs --> dfs + memo (top-down) --> dp (bottom-up)
    
    // Approach 1 - DP
    // dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j -1]) + 1
    // time: O(n * m)
    // space: O(n * m)
    public int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        // dp array
        int[][] dp = new int[n + 1][m + 1];
        
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i - 1][j - 1] != '0') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    if (dp[i][j] > max)
                        max = dp[i][j];
                }
                
            }
        }
        return max * max;
    }
    
    // Approach 2 - DP - Compact Space
    // 2d dp array --> 1D dp array
    // time: O(n * m)
    // space: O(n)
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[] dp = new int[m + 1];
        // used as [i - 1][j -1]
        int prev = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                if (matrix[i][j - 1] != '0') {
                    // dp[j]: dp[i - 1][j]
                    // dp[j - 1]: dp[i][j - 1]
                    // prev: dp[i - 1][j - 1]
                    dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), prev) + 1;
                    if (dp[j] > max)
                        max = dp[j];
                }
                else
                    dp[j] = 0;
                prev = temp;
            }
        }
        return max * max;
    }
}
