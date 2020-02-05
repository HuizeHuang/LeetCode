class Solution {
    // Approach 1 - DFS - TLE
    // Try to analyze the problem piece by piece, then we can find that it actually 
    // is very similar to the "climbing stairs"
    // The choices we have at each step are: jump one step or jump two steps
    // but the difference here is that, before jump two steps, there are some constraints
    // that only satisfies, two steps can be jumped.
    // some edge cases: with zeors: "0", "10"
    // time: O(2 ^ n)
    // space: O(n)
    public int numDecodings1(String s) {
        return dfs(s, 0);
    }
    
    public int dfs(String s, int i) {
        // goal
        // it cannot be s.length() - 1, for case "0"
        if (i == s.length()) return 1;
        
        // constraints
        if (s.charAt(i) == '0') return 0;
        
        // single digit
        int res = dfs(s, i + 1);
        
        // if two digits are valid
        if (i < s.length() - 1 && Integer.valueOf(s.substring(i, i + 2)) <= 26)
            res += dfs(s, i + 2);
        
        return res;
    }
    
    // Approach 2 - dfs + memo
    // think what can we store in memo[]
    // we can store the number of res of visited digits
    // time: O()
    // space: O()
    public int numDecodings2(String s) {
        int[] memo = new int[s.length()];
        return dfs(s, 0, memo);
    }
    
    public int dfs(String s, int i, int[] memo) {
        // goal
        if (i == s.length()) return 1;
        // constraint
        if (s.charAt(i) == '0') return 0;
        
        if (memo[i] != 0) return memo[i];
        
        int res = dfs(s, i + 1, memo);
        if (i < s.length() - 1 && Integer.valueOf(s.substring(i, i+2)) <= 26)
            res += dfs(s, i + 2, memo);
        
        return memo[i] = res;
    }
    
    // Approach 3 - dp
    // now we can come up with dp transition function enlightened by above procedure
    // iterate from the end to start
    // dp[i] = dp[i + 1] + dp[i + 2] (if i + 2 to i is valid)
    // time: O(n)
    // space: O(n)
    public int numDecodings3(String s) {
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        // initial value;
        dp[s.length()] = 1;
        
        // to avoid the case "10", we'd better iterate from the end to the beginning,
        // so that we can check the zero first
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') 
                continue;
            dp[i] = dp[i + 1];
            
            if (i + 2 <= s.length() && Integer.valueOf(s.substring(i, i + 2)) <= 26)
                dp[i] += dp[i + 2];
        }
        return dp[0];
    }
    
    // Approach 4 - Compact space
    // next step is to reduce the space complexity by convert 2d array into 1d
    // reduce 1d to constant
    // some edge cases: "100", "10"
    // time: O(n)
    // space: O(1)
    public int numDecodings(String s) {
        if (s.startsWith("0")) return 0;
        
        int dp1 = 1, dp2 = 0;
        int dp = 0;
        for (int i = s.length() - 1; i >= 0; i--) {

            dp = s.charAt(i) == '0' ? 0 : dp1;
            if (i + 2 <= s.length() && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) 
                dp += dp2;
            
            dp2 = dp1;  
            dp1 = dp;   
        }
        return dp;
    }
}
