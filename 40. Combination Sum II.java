class Solution {
    // Approach 1 - Backtracking
    // time: O(2^n)
    // space: O(2^n)
    List<List<Integer>> res;
    int[] candidates;
    int target;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.res = new ArrayList();
        this.candidates = candidates;
        this.target = target;
        
        backtracking(new ArrayList(), 0, 0);
        return res;
    }
    
    public boolean backtracking(List<Integer> curr, int sum, int start) {
        if (sum == target) {
            res.add(new ArrayList(curr));
            return true;
        }
        else if (sum > target) {
            return false;
        }
        
        for (int i = start; i < candidates.length; i++) {
            // remove duplicate
            if (i > start && candidates[i] == candidates[i - 1])
                continue;
            curr.add(candidates[i]);
            
            // if larger, than don't need to check elements after current, they must be larger then current
            if (!backtracking(curr, sum + candidates[i], i + 1)) {
                curr.remove(curr.size() - 1);
                break;
            }
            else
                curr.remove(curr.size() - 1);
        }
        return true;
    }
}
