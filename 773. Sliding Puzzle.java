class Solution {
    // Approach 1 - bfs
    // Perform a breadth-first-search, where the nodes are the puzzle boards 
    // and edges are if two puzzle boards can be transformed into one another with one move.
    // time: (m*n)! * (m*n), there are (m*n)! possible board states(A6)
    // space: (m*n)! * (m*n) for queue
    public int slidingPuzzle1(int[][] board) {
        String end = "123450";
        String start = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
            }
        }
        
        // at each index, possibly reachable index for 0 are:
        int[][] adjacent = new int[][] {
            {1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}
        };
        
        Set<String> visited = new HashSet();
        
        // do bfs from the starting point
        Queue<String> queue = new LinkedList();
        queue.offer(start);
        int level = 0;
        while (!queue.isEmpty()){
            
            int size = queue.size();
            
            // iterate through current level
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(end)) 
                    return level;
                
                // get the position of 0, explore the adjacent board state
                int index0 = curr.indexOf('0');
                for (int next : adjacent[index0]) {
                    String neighbor = swap(curr, index0, next);
                    if (visited.add(neighbor))
                        queue.add(neighbor);
                }
            }
            level++;
        }
        
        // if queue is empty and still not find the ending node
        return -1;
    }
    
    public String swap(String str, int index0, int next) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(index0, sb.charAt(next));
        sb.setCharAt(next, '0');
        return sb.toString();
        
    }
    
    // Approach 2 - A* Search
    
}
