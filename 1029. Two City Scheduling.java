



class Solution {
    // Approach 1 - Brute Force
    // time - O(n^2)
    
    // Approach 2 Greedy
    // A lot of times we know we can try with greedy, but the to apply with it,
    // we need to make sure that local optimal can lead to the global optimal
    // so we can try to alter the given cases and check if it can leads to the global optimal
    // Often, this alteration would be "sort"
    // how to prove that: proof by contradiction
    // how to sort? sort by priceA - priceB
    // time space
    public int twoCitySchedCost(int[][] costs) {
        // sort by priceA - priceB
        Arrays.sort(costs, (a, b) -> ((a[0] - a[1]) - (b[0] - b[1])));
        
        int n = costs.length;
        int res= 0;
        for (int i = 0; i < n; i++) {
            res += i < n / 2 ? costs[i][0] : costs[i][1];
        }
        return res;
    }
}
