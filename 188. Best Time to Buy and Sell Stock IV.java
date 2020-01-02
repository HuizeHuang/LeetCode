class Solution {
    // Approach 1 - DP 
    // similar to lc.123 - memory limit exceeded
    // dp[k][i] = Math.max(dp[k][i-1], prices[i] - Math.min(prices[i] - dp[k-1][i-1]))
    // time - O(n * k), space - O(n + k) 
    public int maxProfit(int k_trans, int[] prices) {
        int n = prices.length;
        if (prices == null || n <= 1) return 0;
        
        // if k >= n/2, which means you can make as many transactions as you want
        // there's no limits. It's the same as the 122. best buy II
        if (k_trans >= n/2) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i-1])
                    res += (prices[i] - prices[i-1]);
            }
            return res;
        }
        
        int[] min = new int[k_trans + 1];
        Arrays.fill(min, prices[0]);
        int[] dp = new int[n];
        
        
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= k_trans; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }
        return dp[k_trans];
    }
    
    // Approach 1 - version 2 - loop for k is outside of loof for prices
    // time space
    public int maxProfit2(int k_trans, int[] prices) {
        int n = prices.length;
        if (prices == null || n <= 1) return 0;
 
        if (k_trans >= n/2) {
            int res = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1])
                    res += (prices[i] - prices[i-1]);
            }
            return res;
        }
        
        int[][] dp = new int[k_trans + 1][n];
        for (int k = 1; k <= k_trans; k++) {
            // int max = -prices[0];
            int min = prices[0];
            for (int i = 1; i < n; i++) {
                // we can also use max to compare
                // dp[k][i] = Math.max(dp[k][i-1], max);
                // max = Math.max(max, dp[k-1][i-1] - prices[i]);
                min = Math.min(min, prices[i] - dp[k-1][i-1]);
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min);
            }
        }
        return dp[k_trans][n-1];
    }
}
