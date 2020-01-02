class Solution {
    // DP
    // transition function:
    // dp[k][i] = Math.max(dp[k][i-1], prices[i]-prices[j]+dp[k-1][j-1]), j=[0,i-1]
    
    // Version 1 - 3 for loops 
    // loop all over i, j, k
    // dp[k][i] = Math.max(dp[k][i-1], prices[i]- Math.min(prices[j]-dp[k-1][j-1])), j=[0,i-1]
    // time - O(k * n * n) (faster than 13%) space - O(k*n)
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[3][prices.length];
        for (int k = 1; k <= 2; k++) {
            for(int i = 1; i < prices.length; i++) {
                int min = prices[0];
                for(int j = 1; j <= i; j++) {
                    min = Math.min(min, prices[j] - dp[k-1][j-1]);
                }
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min);
            }
        }
        return dp[2][prices.length-1];
    }
    
    // Version 2 - 2 for loops
    // loop over all k, i  --> remove the loop for j, it's the same to get minimum value before current i
    // dp[k][i] = Math.max(dp[k][i-1], prices[i]- Math.min(prices[i]-dp[k-1][i-1]))
    // time  - O(k * n), faster than 99.69%, space - O(n * k)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[3][prices.length];
        for (int k = 1; k<=2; k++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
            }
        }
        return dp[2][prices.length-1];
    }
    
    // Version 3 - swap 2 loops
    // move the loop that iterates k inside that for i
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[3][prices.length];
        int[] min = new int[3];
        Arrays.fill(min, prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= 2; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k-1][i-1]);
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min[k]);
            }
        }
        return dp[2][prices.length-1];
    }
    
    // Version 4 - reduce the dp dimension
    // time - O(n*k), space - O(k)
     public int maxProfit4(int[] prices) {
         if (prices == null || prices.length == 0) return 0;
         int[] dp = new int[3];
         int[] min = new int[3];
         Arrays.fill(min, prices[0]);
         for (int i = 1; i < prices.length; i++) {
             for (int k = 1; k <= 2; k++) {
                 min[k] = Math.min(min[k], prices[i] - dp[k-1]);
                 dp[k] = Math.max(dp[k], prices[i] - min[k]);
             }
         }
         return dp[2];
     }
    
    // Version 5 - use variables
    // time - O(n), space - O(1)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int min1 = prices[0], min2 = prices[0], dp0 = 0, dp1 = 0, dp2 = 0;
        for (int i = 1; i < prices.length; i++) {
            min1 = Math.min(min1, prices[i] - dp0);
            dp1 = Math.max(dp1, prices[i] - min1);
            min2 = Math.min(min2, prices[i] - dp1);
            dp2 = Math.max(dp2, prices[i] - min2);
        }
        return dp2;
    }
}
