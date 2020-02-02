class Solution {
    // Approach 1 - Brute Force
    // we first get the factorial, and then get then count how many zeros in the result
    // time complexity would be O(n), not logarithmic
    
    // Approach 2 - Counting 5s
    // Counting trailig zeors is the same as counting how many 5s and multiple of 5 
    // time: O(log_5(n))
    
    // iteratively
    public int trailingZeroes1(int n) {
        int res = 0;
        while (n > 1) {
            n = n / 5;
            res += n;
        }
        return res;
    }
    
    // recursively
    public int trailingZeroes(int n) {
        if (n <= 4) return 0;
        return n / 5 + trailingZeroes(n / 5);
    }
}
