class Solution {
    // Approach 1 - DP Top-Down - Backtracking + memo
    // TLE - passes all tests, but took too long
    // time - O(n^2) space - O(n)
    public int jump1(int[] nums) {
        int[] memo = new int[nums.length];
        return dp(nums, 0, memo);
    }
    
    public int dp(int[] nums, int n, int[] memo) {
        if (n >= nums.length - 1) return 0;
        // check memo
        if (memo[n] > 0) return memo[n];
        
        int minCount = nums.length - 1;
        for (int step = 1; step <= nums[n]; step++) {
            minCount = Math.min(minCount, dp(nums, n + step, memo) + 1);
        }
        memo[n] = minCount;
        return memo[n];
    }
    
    // Approach 2 - DP - Bottom-Up
    // transition formula:
    // dp[i] = min(dp[i - step]), step from 1 to nums[i]
    // 246ms - faster than 17.74%
    // time - O(n * x), where x is the maximum number in nums
    // space - O(n)
     public int jump2(int[] nums) {
         // initialize dp
         int[] dp = new int[nums.length];
         
         // iterate from right to left
         for (int i = nums.length - 2; i >= 0; i--) {
             // maximum value is the length - 1
             int minCount = nums.length - 1;
             for (int step = 1; step <= nums[i]; step++) {
                 if (i + step >= nums.length) break;
                 minCount = Math.min(minCount, dp[i + step] + 1);
             }
             dp[i] = minCount;
         }
         return dp[0];
     }
    
    
    // Approach 3 - BFS
    // can think of as a BFS problem, 
    // where nodes in level i are all the nodes that can be reached in i-1th jump
    // or build a graph, indices can jump from one to another are nodes connected
    // but here we don't use queue, and we only check the largest step we can jump at each level
    // faster than 100%
    // time - O(n) space - O(1)
    public int jump3(int[] nums) {
        if (nums.length <= 1) return 0;
        int currEnd = 0, level = 0, i = 0;
        
        // check current nodes count is not zero
        // it's like while (!queue.isEmpty())
        while (currEnd - i + 1 > 0) {
            // To store furthest connected node of current node
            int nextEnd = 0;
            // currEnd is like the current level size
            for (; i <= currEnd; i++) {
                nextEnd = Math.max(nums[i] + i, nextEnd);
                
                // reach exactly or beyond the last element
                if (nextEnd >= nums.length - 1) return level + 1;
            }
            // finish looping through current level
            level++;
            // assign next level's queue size
            currEnd = nextEnd;
        }
        return level;
    }
    
    // Approach 4 - Greedy 
    // every time we search for the largest step we can jump
    // so that it can contribute to the minimum number of steps
    // time step
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        
        int currEnd = 0, nextEnd = 0, jumps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // next level end would be the farthest node current node can reach
            nextEnd = Math.max(nextEnd, nums[i] + i);
            
            // This is like the end of current level
            // jumps++ means increment level
            // currEnd = nextEnd means assign current queue size
            if (i == currEnd) {
                jumps++;
                currEnd = nextEnd;
            }
        }
        return jumps;
    }
}
