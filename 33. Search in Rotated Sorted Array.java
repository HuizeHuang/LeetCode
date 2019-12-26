class Solution {
    // Binary Search
    // 注意哪里用 >= 哪里用 >
    // time O(logn) space O(1)
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (target < nums[0] && target > nums[nums.length - 1]) return -1;
        
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            
            // !!! Attention to the condition
            if (nums[mid] == target) 
                return mid;
            else if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else {      // if (nums[mid] <= nums[left])
                if (target <= nums[right] && target > nums[mid])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
