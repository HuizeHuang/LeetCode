class Solution {
    // First though: Brute Force - O(n^2), loop through every element, in each loop 
    // multiply the other elements
    // then we can reduce to O(2 * n), first to loop the array once to get the total 
     // product, and then iterate from start again and divide the product by current element
    
    // Approach 1 - 3 Pass
    // two arrays, one record the product from left, one record the product from right
    // similar to 42.Trapping Rain Water - approach 2
    // time - O(3 * n) space - O(2 * n)
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        if (nums == null || n == 0) return res;
        int[] fromLeft = new int[n];
        int[] fromRight = new int[n];
        
        // start from left
        fromLeft[0] = nums[0];
        for (int i = 1; i < n; i++) {
            fromLeft[i] = fromLeft[i - 1] * nums[i];
        }
        
        // start from right
        fromRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            fromRight[i] = fromRight[i + 1] * nums[i];
        }
        
        // fill out the result array
        for (int i = 0; i < n; i++) {
            if (i == 0)
                res[i] = fromRight[i + 1];
            else if (i == n - 1)
                res[i] = fromLeft[i - 1];
            else
                res[i] = fromLeft[i - 1] * fromRight[i + 1];
        }
        return res;
    }
    
    // follow-up:
    // Approach 2 - Compact space
    // to achieve O(1) space except answer array, we construct the left array on the fly,
    // and store the value of right array in the answer array
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        if (nums == null || n == 0) return res;
        
        // construct from right directly store to res
        res[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            res[i] = nums[i + 1] * res[i + 1];
        }
        
        // recored the product from left on the fly
        int left = 1;
        for (int i = 0; i < n; i++) {

            res[i] = left * res[i];
            left *= nums[i];
        }
        return res;
    }
}
