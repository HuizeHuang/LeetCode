class Solution {
    // Approach 1 - DFS
    // similar to 200. Number of Islands
    // 'E' -> 'B': keep searching
    // 'E' -> '1'-'8': stop searching
    // 'M' -> 'X'
    // Find the number of mines first!!!
    // You can use an array representing the directions or use two for loops
    public char[][] updateBoard1(char[][] board, int[] click) {
        if (board == null || board.length == 0) return board;
        int i = click[0];
        int j = click[1];
        if (board[i][j] == 'M') 
            board[i][j] = 'X';
        else 
            DFS(board, i, j);
        return board;
    }
    
    public void DFS(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'E') 
            return;
        int count = findMines(board, i, j);
        
        if (count <= 0) {       // blank, keep searching
            board[i][j] = 'B';
            for (int h = -1; h <= 1; h++) {
                for (int v = -1; v <= 1; v++) {
                    if (h == 0 && v == 0) continue;
                    DFS(board, i + h, j + v);
                }
            }
        }
        else {      // has mines
            board[i][j] = (char)('0' + count);
        }
    }
    
    public int findMines(char[][] board, int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (row + i < 0 || row + i >= board.length || col + j < 0 || col + j >= board[0].length)
                    continue;
                if (board[row + i][col + j] == 'M' || board[row + i][col + j] == 'X') 
                    count++;    
            }
        }
        return count;    
    }
    
    // Approach 2 - BFS
    // similar to number of islands
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) return board;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> queue = new LinkedList();
        queue.add(click);
        while(!queue.isEmpty()) {
            int[] coord = queue.poll();
            int x = coord[0], y = coord[1];
            if (board[x][y] == 'E'){
                int count = findMines(board, x, y);
                if (count > 0) 
                    board[x][y] = (char)('0' + count);
                else{
                    board[x][y] = 'B';
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (i == 0 && j == 0) continue;
                            if (x + i < 0 || x + i >= board.length || y + j < 0 || y + j >= board[0].length)
                                continue;
                            queue.add(new int[] {x+i, y+j});
                        }
                    }
                }
            }
        }
        return board;
    }
    
}



