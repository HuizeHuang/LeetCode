class Solution {
    // Approach 1 - 2 sub DP
    // 1. rob the houses 0 ~ the second-to-last
    // 2. rob the houses 1 ~ the last 
    public int rob(int[] nums) {

        if (nums.length == 1) return nums[0];
        int length = nums.length;
        return Math.max(subRob(nums, 0, length - 2), subRob(nums, 1, length - 1));
    }
    
    public int subRob(int[] nums, int start, int end) {

        int prev = 0, prev2 = 0;
        for (int i = start; i <= end; i++) {
            int curr = Math.max(prev, prev2 + nums[i]);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}
