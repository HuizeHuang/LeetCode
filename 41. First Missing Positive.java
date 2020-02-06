class Solution {
    // Approach 1 - Index as a hash key
    // the max possible first missing number is smaller or equal to n + 1
    // To achieve O(n) we can think if we can replace some elements or modify
    // So, each
    public int firstMissingPositive(int[] nums) {
        // move element to the right index
        for (int i = 0; i < nums.length; i++) {
            // keep swapping until the number is at the right place
            while (nums[i] <= nums.length && nums[i] > 0 && nums[i] != i + 1) {
                int temp = nums[nums[i] - 1];
                // [1,1] prevent cycling
                if (nums[i] == temp) break;
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        int i;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return i + 1;
    }
}
