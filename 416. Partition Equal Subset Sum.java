class Solution {
    // Approach 1 - 2d DP
    // This problem is essentially let us to find whether there are several numbers in a set which are able to sum to a specific value (in this problem, the value is sum/2).
    // transition function: dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
    // time - O(n * sum)
    // space - O(n * sum)
    public boolean canPartition1(int[] nums) {
        if (nums.length <= 1) return false;
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // sum is an odd number, there will never be equal partition
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        
        // we have dp[i-1][0] to be true when j == nums[i - 1]
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
            
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < nums[i - 1]) 
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i- 1]];
            }
        }
        return dp[nums.length][sum];
    }
    
    // Approach 2 - 1D dp
    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) return false;
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // sum is an odd number, there will never be equal partition
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        
        boolean[] dp = new boolean[sum + 1];
        
        // we have dp[i-1][0] to be true when j == nums[i - 1]
        dp[0] = true;
            
        for (int num : nums) {
            // we start from the back, since we use dp[j - num], 
            // if we start from the beginning, if num = 1, j - num will be true if dp[1]is true
            for (int j = sum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[sum];
    }
}
