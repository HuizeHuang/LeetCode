class Solution {
    /* 1. Can there be negative numbers?
    */
    // Sliding window 
    // -- No, contains negative number and cannot be sorted
    
    // Approach 1 - HashMap
    /*
     1. Hashmap<sum[0,i - 1], frequency>
     2. sum[i, j] = sum[0, j] - sum[0, i - 1]    --> sum[0, i - 1] = sum[0, j] - sum[i, j]
            k           sum      hashmap-key     -->  hashmap-key  =  sum - k
    */
    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
}
