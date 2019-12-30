class Solution {
    // Approach 1 - DP
    // Maintain two variables to keep track of current maximal and minimal
    // 记录最小值是因为可能当前值是负数，当前最小值也为负，负数和负数相乘会马上变为最大值
    // time - O(n) space - O(1)
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int currMax = nums[0];
        int currMin = nums[0];
        int max = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int nextMax = currMax * nums[i];
            int nextMin = currMin * nums[i];
            currMax = Math.max(nums[i], Math.max(nextMax, nextMin));
            currMin = Math.min(nums[i], Math.min(nextMax, nextMin));
            max = Math.max(max, currMax);
        }
        return max;
    }
}
