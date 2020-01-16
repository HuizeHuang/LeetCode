class Solution {
    // Approach 1 - Brute Force
    // O(n^3) loop through the array and find all possible combinations
    
    // Approach 2 - Binary Search
    // valid triangle: a, b, c --> (a + b) > c and (a - b) < c
    // First sort the array
    // given the property that a < b < c, we can easily satisfy the inequality (a - b) < c
    // time - O(n^2 * logn) space - O(1)
    public int triangleNumber1(int[] nums) {
        int res = 0;
        if (nums == null || nums.length == 0) return res;
        
        // sort the array fisrt
        Arrays.sort(nums);
        
        // loop array for pair (a, b)
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 0) continue;
            int bound = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[j] != 0; j++) {
                bound = binarySearch(bound, nums.length - 1, nums[i] + nums[j], nums);
                res += bound - j ;
            }
        }
        return res;
    }
    
    public int binarySearch(int left, int right, int end, int[] nums) {
        
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            
            if (nums[mid] >= end) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }
    
    // Approach 3 - Linear Scan
    // also sort the array first
    // start from i = 2, loop through the elements before it until left >= right
    // time - O(n^2), space - O(1)
    public int triangleNumber(int[] nums) {
        int res = 0; 
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                
                if (nums[left] + nums[right] > nums[i]) {
                    res += right - left;    // fixing the right, there are (right - left) combinations
                    right--;
                }
                else // if the sum is smaller, we need to move to larger ones
                    left++;
            }
        }
        return res;
    }
}
