class Solution {
    // Approach 1 - DP
    // transition formula: 
    // dp[i] = max(dp[i - 1], prices[i] - fee - min(prices[j]  + dp[j - 1]) )
    public int maxProfit1(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) return 0;
        
        int[] dp = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] - dp[i - 1]);
            dp[i] = Math.max(dp[i - 1], prices[i] - fee - min);
        }
        return dp[prices.length - 1];
    }
    
    // Approach 1 - version 2 - space compact
    // time O(n), space - O(1)
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) return 0;
        int dp0 = 0;
        
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] - dp0);
            dp0 = Math.max(dp0, prices[i] - fee - min);

        }
        return dp0;
    }
}
