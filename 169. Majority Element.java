import javafx.util.Pair;

class Solution {
    /*
    // Approach 1 - Brute Froce - Hashset
    // reduce two iterations to one: we can break if >(n/2) is satisfied
    // time O(n) >8.73% 
    // space O(n) ~= n-[n/2]
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (!map.containsKey(num))
                map.put(num, 0);
            map.put(num, map.get(num) + 1);
            if (map.get(num) > (nums.length) / 2)
                return num;
        }
        return 0;
    }
    */
    
    /*
    // Approach 2 - Sorting
    // If the elements are sorted in monotonically increasing (or decreasing) order,
    // the majority element can be found at index ⌊ n/2 ⌋
    // time O(nlogn) space O(1)
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
    */
    
    /*
    // Approach 3 - Divide & Conquer
    // If we know the majority element in the left and right halves of an array, 
    // we can determine which is the global majority element in linear time.
    // 分成子问题：如果左半部分的majority和右半部分的majority是一样的，则说明是当前majorrity是
    // 整个数组的majority，否则计算两个majority谁大，更大的那个为整个数组的majority
    // time O(nlogn) >99.95%  space O(logn)
    
    public int majorityElement(int[] nums) {
        return helper(nums, 0, nums.length - 1, 0);
    }
    
    public int helper(int[] nums, int lo, int hi, int num) {
        if (lo == hi) 
            return nums[lo];
            
        int mid = (hi - lo) / 2 + lo;
        int left = helper(nums, lo, mid, num);
        int right = helper(nums, mid + 1, hi, num);
        
        if (left == right) return left;
        return count(nums, lo, hi, left) > count(nums, lo, hi, right) ? left : right;
    }
    
    public int count(int[] nums, int lo, int hi, int num) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num)
                count++;
        }
        return count;
    }
    */
    
    /*
    // Approach 4 - Divide & Conquer with counting
    // time O(n/4 logn) > 44.2%  space O(logn)
    public int majorityElement(int[] nums) {
        return helper(nums, 0, nums.length - 1, 0).getKey();
    }
    
    public Pair<Integer, Integer> helper(int[] nums, int lo, int hi, int num) {
        if (lo == hi)
            return new Pair(nums[lo], 1);
        
        int mid = (hi - lo) / 2 + lo;
        Pair<Integer, Integer> left = helper(nums, lo, mid, num);
        Pair<Integer, Integer> right = helper(nums, mid + 1, hi, num);
        
        if (left.getValue() > right.getValue()) {
            num = left.getKey();
            return new Pair(num, left.getValue() + count(nums, mid + 1, hi, num));
        }
        else{
            num = right.getKey();   
            return new Pair(num, right.getValue() + count(nums, lo, mid, num));
        }
    }
    
    public int count(int[] nums, int lo, int hi, int num) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num)
                count++;
        }
    return count;
    }
    */
    
    /*
    // Apprroach 5 - Boyer-Moore Voting
    // [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    // majority is larger than n/2, so we can always get the majority with count larger than 1
    // time O(n) >99.95%  space O(1)
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0){
                candidate = num;
            }
            if (num == candidate)
                count++;
            else
                count--;         
        }
        return candidate;
    }
    */
    
    // Approach 6 - Bit Voting
    // 对integer每一位bit都比较并存储当前出现次数较多的 1or0，最后用和合并
    // time O(32 * n) >56.02%  space O(1)
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int result = 0, mask;
        for (int i = 0; i < 32; i++) {
            mask = 1 << i;
            int count = 0;
            for (int num : nums) {
                if ((num & mask) != 0)
                    count++;
            }
            
            mask = (count > nums.length / 2) ? mask : 0;
            result = result | mask;
            
        }
        return result;
    }
}
