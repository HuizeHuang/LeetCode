class Solution {
    // Approach 1 - DP
    /*
    we can define 3 states here
    s0: just bought, can sell
    s1: just sold the stock, can only rest(hold, do nothing)
    s2: just rest, can buy
    */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] s0 = new int[prices.length];
        int[] s1 = new int[prices.length];
        int[] s2 = new int[prices.length];
        
        // initial states
        s0[0] = -prices[0];
        s1[0] = Integer.MIN_VALUE;
        s2[0] = 0;
        
        // define the state trasitions
        for (int i = 1; i < prices.length; i++) {
            s0[i] = Math.max(s0[i-1], s2[i-1] - prices[i]);
            s1[i] = s0[i-1] + prices[i];
            s2[i] = Math.max(s2[i-1], s1[i-1]);
        }
        return Math.max(s2[prices.length-1], s1[prices.length-1]);
    }
    
    // Approach 2 - Transition formula
    // dp[i] = Math.max(dp[i-1], prices[i] - prices[j] + dp[j - 2]), j = [1, i-1]
    // time - O(n), space - O(n)
    public int maxProfit2(int[] prices) {
        if (prices.length < 2) return 0;
        int[] dp = new int[prices.length + 1];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] - dp[i - 1]);
            dp[i + 1] = Math.max(dp[i], prices[i] - min);
        }
        return dp[prices.length];
    }
    
    // Approach 3 -Compact space
    public int maxProfit3(int[] prices) {
        if (prices.length < 2) return 0;
        int dp0 = 0, dp1 = 0, dp2 = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] - dp0);
            dp2 = Math.max(dp1, prices[i] - min);
            dp0 = dp1;
            dp1 = dp2;
        }
        return dp2;
    }
    
    // Approach 3 - version 2 - use two variables
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int prev = 0, res = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i] - prev);
            prev = res;
            res = Math.max(res, prices[i] - min);
        }
        return res;
    }
}
