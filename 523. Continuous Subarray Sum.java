



class Solution {
    // Approach 1 - Brute Force
    // time: O(n^2)
    // space: O(1)
    
    // Approach 2 - HashMap
    /*
    (a+(n*x))%x is same as (a%x)
 
    For e.g. in case of the array [23,2,6,4,7] the running sum is
    [23,25,31,35,42] and the remainders are [5,1,1,5,0]. We got 
    remainder 5 at index 0 and at index 3. That means, in between 
    these two indexes we must have added a number which is multiple of the k.
    */
    // edge cases: 
    // ** k = 0
    // time: O(n)
    // space: O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) 
                sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            }
            else
                map.put(sum, i);
            
        }
        return false;
    }
}

