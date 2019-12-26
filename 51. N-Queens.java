class Solution {
    // Approach 1 - Backtracking
    // backtrack as row grows, record columns
    // time space
    
    public List<List<String>> solveNQueens(int n) {
        
        List<List<String>> res = new ArrayList();
        if (n <= 0) return res;

        backtracking(n, res, new ArrayList(), 0);
        return res;
    }
    
    public void backtracking(int n, List<List<String>> res, List<Integer> rows, int row) {
        // Once found a solution: 
        // construct the board from char array to list of strings
        if (rows.size() == n) {
            char[][] oneSol = new char[n][n];
            List<String> one = new ArrayList();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == rows.get(i))
                        oneSol[i][j] = 'Q';
                    else
                        oneSol[i][j] = '.';
                }
                String str = new String(oneSol[i]);
                one.add(str);
            }

            res.add(one);
            return;
        }
        
        // backtracking
        // iterate over each possible path
        for (int col = 0; col < n; col++) {
            boolean valid = true;
            // check if each path's valid
            for (int i = 0; i < row; i++) {
                if (col == rows.get(i) || i + rows.get(i) == row + col || i - rows.get(i) == row - col) {
                    valid = false;
                    break;
                }
            }
            
            // if the path is valid, keep tracking down
            // remember to remove the last element 
            if (valid) {
                rows.add(col);
                backtracking(n, res, rows, row + 1);
                rows.remove(rows.size()-1);
            }
        }
    }
}



