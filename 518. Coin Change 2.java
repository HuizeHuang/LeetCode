



class Solution {
    // Approach 1 - Backtracking - Top-Down
    // 可能有重复的情况，avoid repeating counting
    // time space
    int count = 0;
    public int change1(int amount, int[] coins) {
        if (amount == 0) return 1;
        if (coins == null || coins.length <= 0) return 0;
        int[] memo = new int[coins.length];
        Set<String> set = new HashSet();
        
        dfs(coins, amount, memo, set);
        
        return count;
    }
    
    public void dfs(int[] coins, int amount, int[] memo, Set<String> set) {
        if (amount < 0) return;
        
        String str = "";
        for (int i : memo) {
            str += i;
        }
        
        if (set.contains(str)) return;
        
        if (amount == 0) {
            count++;
            set.add(str);
            return;
        }
        
        for (int i = 0; i < coins.length; i++) {
            memo[i]++;
            dfs(coins, amount - coins[i], memo, set);
            memo[i]--;
        }
    }
    
    // Approach 2 - DP - Bottom-Up
    // !!!! dp[i] = dp[i] + dp[i - coin]
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // !!!!
        dp[0] = 1;
        for (int c : coins) {
            for (int i = c; i <= amount; i++) {
                dp[i] = dp[i] + dp[i - c];
            }
        }
        return dp[amount];
    }
}





