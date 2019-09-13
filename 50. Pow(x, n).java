class Solution {
    
    // Approach 1 - iterative 
    // two conditions: n>0 or n<0
    // time O(n) space O(1)
    
    // Approach 2 - Recursion
    // time O(x ^ n) space O(x ^ n)
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        
        if (n == 0) return 1;
        
        if (n < 0) {
            // if n == Integer.MIN_VALUE, -n will overflow (outside the range of integer)
            // so we need to check that separately, n with the range [−2^31, 2^31 − 1]
            if (n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
                x = 1 / x;
                // here two x is because first, MAX_VALUE is one less than - MIN_VALUE
                // second is for MAX_VALUE is an odd number
                return x * x * myPow( x * x, n / 2 );
            }
            n = -n;
            x = 1 / x;
        }
        return n % 2 == 0 ? myPow(x*x, n/2) : x * myPow(x*x, n/2);
    }
}
