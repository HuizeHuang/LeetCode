class Solution {
    // 可以多次买入卖出
    // Approach 1 - Peak Valley Approach
    // Explanation is important!!!
    // Looking for every peak immediately following a valley.
    public int maxProfit(int[] prices) {
        int peak = 0, valley = 0;
        int i = 0, length = prices.length;
        int sum = 0;
        while (i < length-1) {
            // find the valley
            while(i < length-1 && prices[i] >= prices[i+1])
                i++;
            valley = prices[i];
            
            // find the peak
            while(i < length-1 && prices[i] <= prices[i+1])
                i++;
            peak = prices[i];
            
            sum += peak-valley;
        }
        return sum;
    }
    
    // Approach 2 - Adding the difference
    // Keep on adding the difference between the consecutive numbers of the array
    // if the second one is larger than the first one
    public int maxProfit2(int[] prices) {
        int sum = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i+1] > prices[i])
                sum += prices[i+1] - prices[i];
        }
        return sum;
    }
}
