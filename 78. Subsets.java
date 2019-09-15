class Solution {
    /*
    // 详见pad backtracking
    // Approach 1 - Recursion, TYPICAL Backtracking
    // Time - O(2^n) - there are 2^n subsets generated
    // space - O(n + 1), the largest depth of recursion is n+1
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> curr = new ArrayList();
        helper(nums, res, curr, 0);
        return res;
    }
    
    public void helper(int[] nums, List<List<Integer>> res, List<Integer> curr, int index) {
        res.add(new ArrayList<Integer>(curr));
        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);
            helper(nums, res, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
    */
    
    // Approach 2 - 二分递归
    // Time O(2 ^ (n + 1)) Space O(n + 1)
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        List<List<Integer>> res = new ArrayList();
        helper(nums, res, 0, new ArrayList());
        return res;
    }
    
    public void helper(int[] nums, List<List<Integer>> res, int index, List<Integer> curr) {
        if (index >= nums.length) {
            res.add(new ArrayList<Integer>(curr));
            return;
        }
        helper(nums, res, index + 1, curr);
        curr.add(nums[index]);
        helper(nums, res, index + 1, curr);
        curr.remove(curr.size() - 1);
    }
}
