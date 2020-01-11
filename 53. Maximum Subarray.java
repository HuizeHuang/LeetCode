class Solution {
    
    // everytime we see get the maximum or minimum, first we can think greedy, and then dp
    
    // Approach 1 - DP - Kadane's Algorithm - Greedy
    // transformation of best time to buy and sell 
    // two variables: maxCurr, maxSoFar
    // some cases: [-2, -1], [1], [1, 2]
    // time - O(n) space - O(1)
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxCurr = nums[0], maxSoFar = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            maxCurr = Math.max(nums[i], maxCurr + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxCurr);
        }
        return maxSoFar;
    }
    
    // Approach 2 - Divide and Conquer
    // key point is to find base case and subproblems
    // time space
    public int maxSubArray(int[] nums) {
        return divideConquer(nums, 0, nums.length - 1, 0);
    }
    
    public int divideConquer(int[] nums, int left, int right, int max) {
        if (right - left < 0) return 0;
        if (right - left == 0) return nums[left];
        
        int mid = (left + right) / 2;
        int leftSum = divideConquer(nums, left, mid, max);
        int rightSum = divideConquer(nums, mid + 1, right, max);
        int crossSum = crossSum(nums, left, right, mid);
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }
    
    public int crossSum(int[] nums, int left, int right, int mid) {
        int leftMax = Integer.MIN_VALUE, currMax = 0;
        for (int i = mid; i >= left; i--) {
            currMax += nums[i];
            leftMax = Math.max(leftMax, currMax);
        }
        int rightMax = Integer.MIN_VALUE;
        currMax = 0;
        for (int i = mid + 1; i <= right; i++) {
            currMax += nums[i];
            rightMax = Math.max(rightMax, currMax);
        }
        return Math.max(leftMax + rightMax, Math.max(leftMax, rightMax));
    }
}
