class Solution {
    // Brute force 
    // generate all possible boards
    // time - O(9^81)
    
    // Backtracking - 1
    // traverse row by row (similar to n queen)
    // time - O(9 ^ m), m is the number of places to fill in, space - O(81 * 3)
    public void solveSudoku(char[][] board) {
        backtracking(board, 0, 0);
    }
    
    public boolean backtracking(char[][] board, int row, int col) {
 
        // iterate over the board cell by cell
        for (int i = row; i < board.length; i++, col = 0) {
            for (int j = col; j < board[0].length; j++) {
                if (board[i][j] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, i, j, c)) {
                        board[i][j] = c;
                        
                        if (backtracking(board, i, j+1))
                            return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    
    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c ||       // check this row
                board[i][col] == c ||       // check this column
                board[(row/3) * 3 + i / 3][(col/3) * 3 + i % 3] == c)   // check this sub-grid
                return false;
        }
        return true;
    }
    
    // Backtracking - 2
    
}
