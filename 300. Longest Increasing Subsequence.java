class Solution {
    // Approach 1 - DP
    // dp[i] = max(dp[j] + 1), where j \in (0, i) && nums[j] < nums[i]
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
