



class Solution {
    // Approach 1 - Brute force
    // all cells in the board are updated at once
    // so we need to save a copy of original board of all the value before updated
    // and directly change the value in the board
    // time: O(m * n)
    // space: O(m * n)
    int[] dx = {0, 1, 0, -1, -1, -1, 1, 1};
    int[] dy = {1, 0, -1, 0, -1, 1, -1, 1};
    public void gameOfLife1(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] temp = new int[n][m];
        copyBoard(board, temp);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                checkForEachCell(i, j, temp, board);
            }
        }
    }
    
    public void copyBoard(int[][] from, int[][] to) {
        int n = from.length;
        int m = from[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
    
    public void checkForEachCell(int x, int y, int[][] temp, int[][] board) {
        int liveCount = 0;
        for (int i = 0; i < 8; i++) {
            if ((x + dx[i] < 0) || (x + dx[i] >= temp.length) || (y + dy[i] < 0) || (y + dy[i] >= temp[0].length))
                continue;
            if (temp[x + dx[i]][y + dy[i]] == 1)
                liveCount++;
        }
        if (temp[x][y] == 0 && liveCount == 3)
            board[x][y] = 1;
        else if (temp[x][y] == 1 && (liveCount < 2 || liveCount > 3))
            board[x][y] = 0;
    }
    
    
    // Approach 2
    // how to solve it in-place?
    // We can use some dummy cell value to signify previous state of the cell
    // along with the new changed value.
    // time: O(m * n) 
    // space: O(1)
    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                checkForEachCell(i, j, board);
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2)
                    board[i][j] = 0;
                else if (board[i][j] == -1)
                    board[i][j] = 1;
            }
        }
        
    }
    
    public void checkForEachCell(int x, int y, int[][] board) {
        int liveCount = 0;
        for (int i = 0; i < 8; i++){
            if ((x + dx[i] < 0) || (x + dx[i] >= board.length) || (y + dy[i] < 0) || (y + dy[i] >= board[0].length))
                continue;
            if (board[x + dx[i]][y + dy[i]] >= 1)
                liveCount++;
        }
        if (board[x][y] == 0 && liveCount == 3)
            board[x][y] = -1;
        else if (board[x][y] == 1 && (liveCount < 2 || liveCount > 3))
            board[x][y] = 2;
    }
}






]
