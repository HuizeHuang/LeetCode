class Solution {
    // Approach 1 - Backtracking - Time Limit Exceeded
    // generate all combinations with constraints
    // time - O(c^amount) c is the number of coins, space - O(n)
    public int coinChange1(int[] coins, int amount) {
        if(coins == null || coins.length == 0) return -1;
        return backtracking(coins, amount, 0, -1);
        
    }
    
    public int backtracking(int[] coins, int amount, int count, int minCount) {
        if (amount == 0) return count;
        if (amount < 0) return -1;
        
        for (int coin : coins) {
            int cnt = backtracking(coins, amount - coin, count+1, minCount);
            // if either cnt or minCount is -1
            if (cnt * minCount < 0)
                minCount = Math.max(cnt, minCount);
            else
                minCount = Math.min(cnt, minCount);
            // 不需要这个remove，当返回到上级时，variable自动回到上级的值
            // 如果是arraylist等，需要remove？
            // amount += coin;
        }
        return minCount;
    }
    
    // Approach 2 - Backtracking + Memoization - Top Down
    // TLE
    public int coinChangeTLE(int[] coins, int amount) {
        if (amount < 1) return 0;
        if (coins == null || coins.length == 0) return -1;
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        return helper(coins, amount, memo);
    }
    
    public int helperTLE(int[] coins, int amount, int[] memo) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        
        // if this amount has been recorded
        if (memo[amount] >= 0) return memo[amount];
        
        for (int coin : coins) {
            int curr = helper(coins, amount - coin, memo);
            if (curr >= 0)
                memo[amount] = (memo[amount] == -1) ? (curr + 1) : (Math.min(memo[amount], curr + 1));
        }
        
        return memo[amount];
    }
    
    
    // Approach 3 - Backtracking + Memoization - Top Down
    // time O(c * n) where c is the denomination count, n is the total amount
    // space O(2 * n) -- the worst case the tree depth is amount and
    // we use a memo array of length n 
    public int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        if (coins == null || coins.length == 0) return -1;
        
        return helper(coins, amount, new int[amount + 1]);
    }
    
    public int helper(int[] coins, int amount, int[] memo) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        
        // if this amount has been recorded
        if (memo[amount] != 0) return memo[amount];
        
        // 与上一种不同的是：在当前层，循环所有可能性，记录最小值，最后再一次性赋最小值
        // 当前层之前的最小值会被保留的
        // 上一种是每次都比较一下
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int curr = helper(coins, amount - coin, memo);
            if (curr >= 0 && curr < min)
                min = curr + 1;
        }
        memo[amount] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[amount];
    }
    
    // Approach 4 - DP - Bottom up
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        if (coins == null || coins.length == 0) return -1;
        
        // initialize dp
        int[] dp = new int[amount + 1];
        
        // A small trick: we don't need to assign largest integer value here
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        // sort the coins array
        // Arrays.sort(coins);
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
                // else
                //     break;
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
