class Solution {
    // similar to 69.Sqrt()
    // binary search
    // time - O(log n)
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;
        int left = 2, right = num / 2;
        long sqr;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            sqr = (long)mid * mid;
            if (sqr == num) return true;
            else if (sqr > num) 
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }
}
