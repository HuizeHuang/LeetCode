class Solution {
    // Approach 1 - Brute Force
    // 循环两遍找到最大的差值
    // time O(n(n-1)/2) ~ O(n^2)
    
    // Approach 2 - Maintaining min and max
    // maintain two variables keeping min and max price
    // time O(n)
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            
            if (prices[i] < minPrice) 
                minPrice = prices[i];
            
            else if ((prices[i] - minPrice) > maxProfit) 
                maxProfit = prices[i] - minPrice;
            
        }
        return maxProfit;
    }
    
    // Approach 3 - Kadane's Algorithm
    // Calculate the difference between two adjacent elements
    // time O(n) space O(1)
    public int maxProfit(int[] prices) {
        int maxCurr = 0, maxSoFar = 0;
        // [0, -6, 4, -2, 3,-2]
        for (int i = 1; i < prices.length; i++) {
            maxCurr = Math.max(0, maxCurr += (prices[i] - prices[i-1]));
            maxSoFar = Math.max(maxSoFar, maxCurr);
        }
        return maxSoFar;
    }
}
