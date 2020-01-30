class Solution {
    // Approach 1 - 2 pass - HashMap
    // First pass to store indices into the map as values, occurrences would the size of list
    // Second pass to fine the minimum length substring
    // edge case: there could be multiple numbers with maximum degree!!
    // time: O(2 * n)
    // space: O(n)
    public int findShortestSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, List<Integer>> map = new HashMap();
        int maxCount = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i]))
                map.put(nums[i], new ArrayList());
            map.get(nums[i]).add(i);
            if (map.get(nums[i]).size() > maxCount)
                maxCount = map.get(nums[i]).size();
        }
        
        // second pass
        for (int num : map.keySet()) {
            List<Integer> list = map.get(num);
            if (list.size() == maxCount) {
                int len = (list.get(list.size() - 1) - list.get(0)) + 1;
                if (len < res)
                    res = len;
            }
        }
        return res;
    }
    
    // Approach 2 - HashMap - 1 pass
    // Two hashmaps, one stores first index, the other stores the count
    // time: O(n)
    // space: O(2 * n)
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> first = new HashMap(), count = new HashMap();
        int res = Integer.MAX_VALUE;
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            count.put(num, count.getOrDefault(num, 0) + 1);
            if (!first.containsKey(num)) {
                first.put(num, i);
            }
            
            if (count.get(num) > maxCount) {
                maxCount = count.get(num);
                res = i - first.get(nums[i]) + 1;
            }
            else if (count.get(num) == maxCount) {
                res = Math.min(res, i - first.get(nums[i]) + 1);
            }
            
        }
        return res;
    }
}
