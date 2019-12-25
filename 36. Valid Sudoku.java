class Solution {
    // Approach 1 - Brute force 
    // iterates 3 times to check rows, columns, and sub-boxes
    // store values in hash table
    
    // Approach 2 - HashSet
    // use different strings representing the number in the different coordinates
    // board number = 3 * (row / 3) + col / 3
    // time - O(9*9) space - O(9*9)
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) return true;
        
        Set<String> seen = new HashSet();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char num = board[i][j];
                if (num == '.') continue;
                if (!seen.add(num + " in row " + i) || 
                    !seen.add(num + " in col " + j) ||
                    !seen.add(num + " in board" + (3*(i/3) + j/3)))
                    return false;
            }
        }
        return true;
    }
}
