class Solution {
    // if don't use memo, will exceed time limit:
    /*
    //recursive + memo : the fastest!
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return rob(nums, nums.length - 1);
    }
    
    //use memo: 
    public int rob(int[] nums, int i){
        if(i < 0)
            return 0;
        if(memo[i] >= 0)
            return memo[i];
        int result = Math.max(rob(nums, i - 1), nums[i] + rob(nums, i - 2));
        memo[i] = result;
        return result;
    }
   
    
    //iterative:
    //iterative + memo[]
    public int rob(int[] nums){
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for(int i = 1; i < nums.length; i++){
            memo[i + 1] = Math.max(memo[i], nums[i] + memo[i - 1]);
        }
        return memo[nums.length];
    }
    */
    //iterative + two variables
    public int rob(int[] nums){
        if (nums.length == 0) return 0;
        int prev1 = 0, prev2 = 0;
        for(int i = 0; i < nums.length; i++){
            int tmp = prev1;
            prev1 = Math.max(prev1, nums[i] + prev2);
            prev2 = tmp;
        }
        return prev1;
    }
}
