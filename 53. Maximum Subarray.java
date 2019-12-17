class Solution {
    
    // Approach 1 - Kadane's algorithm
    // similar to <best time to buy and sell stock>
    // time O(n) space O(1)
    public int maxSubArray(int[] nums) {
        int maxCurr = nums[0], maxSoFar = maxCurr;
        for (int i = 1; i < nums.length; i++) {
            // !!!
            maxCurr = maxCurr > 0 ? maxCurr += nums[i] : nums[i];
            // in best to buy and sell, we use:
            // maxCurr = Math.max(0,  maxCurr += nums[i]);
            maxSoFar = Math.max(maxCurr, maxSoFar);
        }
        return maxSoFar;
    }
    
    // Approach 2 - divide and conquer
}
