class Solution {
    // Approach 1 - Simulation - Linear Scan
    // Use an extra matrix to record the element that has been visited,
    // it can be used as boundary
    // time: O(m * n)
    // space: O(m * n)
    public List<Integer> spiralOrder1(int[][] matrix) {
        
        List<Integer> res = new ArrayList();
        if (matrix == null || matrix.length == 0) return res;
        
        int R = matrix.length;
        int C = matrix[0].length;
        
        // four directions
        int[] dirR = {0, 1, 0, -1};
        int[] dirC = {1, 0, -1, 0};
        int i = 0;
        
        // Used as boundary
        boolean[][] seen = new boolean[R][C];
        int r = 0, c = 0;
        
        for (int count = 0; count < R * C; count++) {
            res.add(matrix[r][c]);
            seen[r][c] = true;
            int nextR = r + dirR[i];
            int nextC = c + dirC[i];
            
            // meet a boundary
            if (nextR >= R || nextR < 0 || nextC >= C || nextC < 0 || seen[nextR][nextC]) {
                i = (i + 1) % 4;
                r += dirR[i];
                c += dirC[i];
            }
            // safe to move
            else {
                r = nextR;
                c = nextC;
            }
        }
        return res;
    }
    
    // Approach 2 - Reduce space
    // time: O(m * n)
    // space: O(1)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        if (matrix == null || matrix.length == 0) return res;
        int R = matrix.length;
        int C = matrix[0].length;
        
        // define the boundary
        int left = 0, right = C - 1;
        int up = 0, down = R - 1;
        
        while (res.size() < R * C) {
            for (int i = left; i <= right && res.size() < R * C; i++) 
                res.add(matrix[up][i]);
            up++;
            
            for (int i = up; i <= down && res.size() < R * C; i++)
                res.add(matrix[i][right]);
            right--;
            
            for (int i = right; i >= left && res.size() < R * C; i--)
                res.add(matrix[down][i]);
            down--;
            
            for (int i = down; i >= up && res.size() < R * C; i--)
                res.add(matrix[i][left]);
            left++; 
        }
        return res;
    }
}
