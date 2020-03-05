class Solution {
    // Approach 1 - HashMap 
    // time: O(2 * n)
    // space: O(n)
    public int findPairs1(int[] nums, int k) {
        if (k < 0 || nums.length == 0) return 0;
        
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (k == 0){
                if (entry.getValue() >= 2)
                    count++;
            }
            // To avoid repetition, we only check addition
            else if (map.containsKey(entry.getKey() + k)){
                count++;
            }
        }
        return count;
    }
    
    // Approach 2 - Sort + two pointers
    public int findPairs(int[] nums, int k) {
        if (k < 0 || nums.length <= 1) return 0;
        
        Arrays.sort(nums);
        
        int left = 0, right = 1, count = 0;
        while (right < nums.length && left < right) {
            if (nums[right] - nums[left] == k) {
                if (left == 0 || nums[left] != nums[left - 1])
                    count++;
                left++;
                right++;
            }
            else if (nums[right] - nums[left] < k || right - left == 1)
                right++;
            else 
                left++;
        }
        return count;
    }
}
