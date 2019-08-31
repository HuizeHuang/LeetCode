class Solution {
    // Approach 1 - Brute Force 
    // time O(n^3) space O(1)
    
    // Approach 2
    // sort the array
    // time O(nlongn) + O(n ^ 2) space O(n)
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || n == 0) return res;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] > 0) break;
            int lo = i + 1, hi = n - 1;
            int sum = 0 - nums[i];
            while (lo < hi) {
                if (nums[lo] + nums[hi] == sum){
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    // avoid duplicates
                    while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                    lo++;
                    hi--;
                }
                else if (nums[lo] + nums[hi] < sum){
                    while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                    lo++;
                }
                else{
                    while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                    hi--;
                }
            }
        }
        
        return res;
    }
}
