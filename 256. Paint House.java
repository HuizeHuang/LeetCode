class Solution {
    // Approach 1 - dp
    // dp[i][j] = min(dp[i - 1][y != j]) + costs[i][j]
    // time: O(m * n)
    // space: O(m * n)
    public int minCost1(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0)
            return 0;
        
        int m = costs.length;
        int n = costs[0].length;
        
        int[][] dp = new int[m+1][n];
        Arrays.fill(dp[0], 0);
        
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                
                int min = Integer.MAX_VALUE;
                for (int y = 0; y < n; y++) {
                    if (y == j) continue;
                    min = Math.min(dp[i - 1][y], min);
                }
                dp[i][j] = min + costs[i - 1][j];
            }
        }
        return Math.min(Math.min(dp[m][0], dp[m][1]), dp[m][2]);
    }
    
    // Approach 1 - dp - constant space
    // we can update the value directly in costs array
    public int minCost2(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0)
            return 0;
        
        int m = costs.length;
        int n = costs[0].length;
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                int min = Integer.MAX_VALUE;
                for (int y = 0; y < n; y++) {
                    if (y == j) continue;
                    min = Math.min(costs[i - 1][y], min);
                }
                costs[i][j] = min + costs[i][j];
            }
        }
        return Math.min(Math.min(costs[m-1][0], costs[m-1][1]), costs[m-1][2]);
    }
    
    // Approach 1 - dp - compact space
    // dp[i][j] = min(dp[i - 1][y != j]) + costs[i][j]
    // time: O(n * m)
    // space: O(2 * n)
    public int minCost3(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0)
            return 0;
        
        int m = costs.length;
        int n = costs[0].length;
        
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) 
            dp[i] = costs[0][i];
        
        int[] last = new int[n];
        
        for (int i = 1; i < m; i++) {
            last = dp.clone();
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int y = 0; y < n; y++) {
                    if (y == j) continue;
                    min = Math.min(min, last[y]);
                }
                dp[j] = min + costs[i][j];
            }
        }
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }
    
    // Approach 2 - DFS
    // time limit exceeded
    public int minCost4(int[][] costs) {
        return Math.min(Math.min(dfs(costs, 0, 0), dfs(costs, 0, 1)), dfs(costs, 0, 2));
    }
    
    public int dfs(int[][] costs, int i, int y) {
        if (i == costs.length) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < costs[0].length; j++) {
            if (j == y) continue;
            min = Math.min(min, dfs(costs, i + 1, j) + costs[i][y]);
        }
        return min;
    }
    
    // Approach 2 - DFS + memoization
    public int minCost(int[][] costs) {
        if (costs.length == 0) return 0;
        int[][] memo = new int[costs.length][costs[0].length];
        return Math.min(Math.min(dfs_memo(costs, 0, 0, memo), dfs_memo(costs, 0, 1, memo)), dfs_memo(costs, 0, 2, memo));
    }
    
    public int dfs_memo(int[][] costs, int i, int y, int[][] memo){
        if (i == costs.length) {
            return 0;
        }
        if (memo[i][y] > 0) return memo[i][y];
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < costs[0].length; j++) {
            if (j == y) continue;
            min = Math.min(min, dfs_memo(costs, i + 1, j, memo) + costs[i][y]);
        }
        return memo[i][y] = min;
    }
}
