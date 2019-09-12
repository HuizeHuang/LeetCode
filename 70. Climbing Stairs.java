class Solution {
    /*
    //Approach 1 recursion 
    //time O(2^n) space O(n) (the depth of recursion tree goes up to n)
    public int climbStairs(int n) {
        // this recursion is to count how many different ways there are
        if(n == 1 || n == 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    
    public int climbStairs(int n){
        return climb_stairs(0, n);
    }
    
    public int climb_stairs(int i, int n){
        if (i > n) return 0;
        if (i == n) return 1;
        return climb_stairs(i + 1, n) + climb_stairs(i + 2, n);
    }
    */
    //Approach 2 recursion with memoization
    // time O(n), space O(n) 
     public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_stairs_with_memo(memo, 0, n);
    }
    
    public int climb_stairs_with_memo(int memo[], int i, int n){
        if (i > n) return 0;
        if (i == n) return 1;
        if (memo[i] > 0) return memo[i];
        memo[i] = climb_stairs_with_memo(memo, i+1, n) + climb_stairs_with_memo(memo, i+2,n);
        return memo[i];
    }
    //or
    
    // 2.recursion with memoization
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return helper(n, memo);
    }
     public int helper(int n, int[] memo) {
         if (n <= 2) return n;
         if (memo[n] > 0) return memo[n];
         memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
         return memo[n];
     }
    
    /********************************/
    // print all the possible paths
    public int climbStairs(int n) {
        
        helper(n, "");
        
        return 0;
    }
    public void helper(int n, String curr) {
        if (n < 0) return;
        
        if (n == 0) 
            System.out.println(curr);
        
        helper(n - 1, curr + "1" );
        helper(n - 2, curr + "2");
    }
    
    // other approaches left to DP exercise
    
     // if you were asked to print all different ways
     public void printStairs(int n){
         
     }
}
