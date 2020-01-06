class Solution {
    // Approach 1 - DP - Top Down
    // transition formula:
    // dp[i] = nums[left] * nums[i] * nums[right] + dp[left] + dp[right]
    // we will choose to insert the element into the array
    // time - O(n^3), space - O(n^2)
    public int maxCoins1(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        int[] new_nums = new int[n + 2];
        for (int i = 0; i < n; i++) {
            new_nums[i + 1] = nums[i];
        }
        new_nums[0] = new_nums[n + 1] = 1;
        int[][] memo = new int[n + 2][n + 2];
        return helper(0, n + 1, new_nums, memo);
    }
    
    public int helper(int left, int right, int[] new_nums, int[][] memo) {
        // when it reaches the boundaries
        if (left + 1 == right) return 0;
        
        // it has been visited before
        if (memo[left][right] > 0) return memo[left][right];
        
        int tempMax = Integer.MIN_VALUE;
        for (int i = left + 1; i < right; i++) {
            tempMax = Math.max(tempMax, new_nums[left] * new_nums[i] * new_nums[right] + helper(left, i, new_nums, memo) + helper(i, right, new_nums, memo));
        }
        memo[left][right] = tempMax;
        return tempMax;
    }
    
    
    // Approach 2 - DP - Bottom-Up
    // time - O(n^3), space - O(n^2)
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] newNums = new int[n + 2];
        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }
        newNums[0] = newNums[n + 1] = 1;
        
        // iterate over dp and incrementally build up to dp[0][n-1]
        // end condition is left + 1 == right
        for (int left = n; left >= 0; left--) {
            for (int right = left + 2; right < n + 2; right++) {
                for (int i = left + 1; i < right; i++) {
                    dp[left][right] = Math.max(dp[left][right], newNums[left] * newNums[i] * newNums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n + 1];
    }
}
