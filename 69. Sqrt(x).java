class Solution {
    // Approach 1 - Binary Search
    // 找到平方大于，小于这个数的中间的数
    // 注意edge case!!
    // 1. x = 0, 1
    // 2. x = Integer.MAX_VALUE
    // 3. 注意 left and right boundary
    // 4. return left or right
    // time - O(logn) space - O (1)
    public int mySqrt(int x) {
        if (x < 2) return x;
        int left = 2, right = x / 2;
        long sqr;       // long : 0 ~ 2^64-1
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            sqr = (long) mid * mid;
            if (sqr == x) 
                return mid;
            else if (sqr > x) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        // why return right?
        // Because the loop is stopped when left > right, 
        // and at this moment right * right <= x < left * left
        return right;
    }
}
