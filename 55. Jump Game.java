class Solution {
    // Approach 1 - Backtracking
    // TLE
    // 必须要从前向后遍历，不能从后向前，因为可能出现[2, 0, 0]的情况
    // time - O(x^n), where x is the maximum number in nums, space - O(n)
    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        return backtracking(nums, 0);
    }
    
    public boolean backtracking(int[] nums, int n) {
        // ending condition:
        // !!! this one must be before second condition
        // 1. reached the start index
        if (n == nums.length - 1) return true;
        // 2. stuck at some index
        if (n >= nums.length || nums[n] == 0) return false;

        // iterate over at each step
        for (int step = nums[n]; step >= 1; step--) {
            if (backtracking(nums, n + step))
                return true;   // it will iteratively returns true until it's over
        }
        return false;
    }
    
    
    // Approach 2 - DP Top-Down
    // It's backtracking + memo
    // 386ms - faster than 8.26%
    // time - O(n^2)  ???
    // space - O(n) + O(n) ~ O(n), one is for recursive stack, one is for memo
    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        
        // memo
        int[] memo = new int[nums.length];
        return backtrackingMemo(nums, 0, memo);
    }
    
    public boolean backtrackingMemo(int[] nums, int n, int[] memo) {
        // ending 
        if (n == nums.length - 1) return true;
        if (n >= nums.length || nums[n] == 0) return false;
        
        // check memo
        if (memo[n] > 0) return true;
        else if (memo[n] < 0) return false;
            
        int i = nums[n];
        for (; i >= 1; i--) {
            if (backtrackingMemo(nums, n + i, memo)){
                memo[n + i] = 1;
                return true;
            }
        }
        memo[n + i] = -1;
        return false;
    }
    
    // Approach 3 - DP Bottom-Up
    // transition formula:
    // dp[i] = dp[i - step], step from 1 to nums[i]
    // 221ms
    // time - O(n * x), where x is the maximum number in nums
    // space - O(n)
    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        int[] dp = new int[nums.length];
        
        // initialize the last element's value
        dp[nums.length - 1] = 1;
        
        // start from the right, thus we can have hit the maximum number of stored value
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int step = 1; step <= nums[i]; step++) {
                if (i + step >= nums.length) break;
                // reach the end or has been visited
                // else if (i + step == nums.length - 1 || dp[i + step] > 0){
                else if(dp[i + step] > 0){
                    dp[i] = 1;
                    break;
                }
            }
            // 可以不用这句
            // dp[i] = dp[i] > 0 ? 1 : -1;
        }
        
        return dp[0] == 1;
    }
    
    
    // Approach 4 - Greedy & Compact space
    // use one variable to keep track of the last GOOD position
    // 1ms
    // time - O(n), space - O(1)
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        // iterate from the right
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos)
                lastPos = i;
        }
        return lastPos == 0;
    }
    
    
}
