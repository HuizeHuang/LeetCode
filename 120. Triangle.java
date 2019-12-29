class Solution {
    // Approach 1 - DFS
    // Using DFS find all the sum and keep track of the minimal
    // space would be O(n^2) since we need a memo[] to store the seen value
    
    // Approach - DP
    // 不能用greedy，因为局部最优解无法推出全局最优解
    // How to approach DP?
    // bottom-up
    // 最优解：current value + minimum value to the last row
    // time - O() space - O()
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int rows = triangle.size();
        // store minimum value until previous row 
        int[] lastRow = new int[triangle.size() + 1];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                lastRow[j] = Math.min(lastRow[j], lastRow[j + 1]) + triangle.get(i).get(j);
            }
        }
        return lastRow[0];
    }
}
