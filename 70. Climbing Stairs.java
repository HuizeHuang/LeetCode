class Solution {
    
    // 1. recursion
    // time limit exceeded
    // time O(2^n) space O(log(2^n)) ~ O(n)
    public int climbStairs1(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    
    
    // 2.recursion with memoization
    // we can store the result at each step in memo array and directly returning the result from the memo array whenever that function is called again.
    // In this way we are pruning recursion tree with the help of memomemo array and reducing the size of recursion tree upto n.
    // using recursion tree
    // time - O(n) space - O(n)
    public int climbStairs2(int n) {
        int[] memo = new int[n+1];
        return helper(memo, n);
    }
    
    public int helper(int[] memo, int n) {
        if (n <= 2) return n;
        if (memo[n] > 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
        
    }
   
    // 3. Dynamic Programming - Array
    // dp[i] = dp[i - 1] + dp[i - 2]
    // time - O(n) space - O(n)
    public int climbStairs3(int n) {
        if (n <= 1) return n;       // !!!
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {  // !!! <=
            dp[i] = dp[i - 1] + dp[i - 2];
        }
            
        return dp[n];
    }
    
    
    // 4. DP - Variables
    // instead of using an array to remember all the states
    // we can use two variables
    public int climbStairs4(int n) {
        if (n <= 1) return n;       // !!!
        int dp, dp1 = 2, dp2 = 1;
        for (int i = 3; i <= n; i++) {  // !!! <=
            dp = dp1 + dp2;
            dp2 = dp1;
            dp1 = dp;
        }
            
        return dp1;
    }
    
    // print all the possible paths
    public int climbStairs(int n) {
        List<String> res = new ArrayList();
        helper(n, res, "");
        System.out.println(res);
        return 0;
    }
    public void helper(int n, List<String> res, String curr) {

        if (n <= 0) {
            if (n == 0)
                res.add(curr);
            return;
        }
        
        // 这里不需要remove最后的 因为本来就没有记住 返回道上一层时会自动返回为上一层的值
        helper(n - 1, res, curr + "1");
        // curr = curr.substring(0, curr.length() - 1);
        helper(n - 2, res, curr + "2");
        // curr = curr.substring(0, curr.length() - 1);

    }
 
}
