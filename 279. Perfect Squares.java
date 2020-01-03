class Solution {
    // similar to coin change
    
    // Approach 1 - Brute Force Enumeration - backtracking
    // TLE when n = 49
    // time - O(s ^ (n/2)) s is the number of sqrt numbers for n
    // space - O(n)
    public int numSquares1(int n) {
        
        List<Integer> squareNums = new ArrayList();
        for (int i = 1; i <= (int)Math.sqrt(n); i++) {
            squareNums.add(i);
        }
        return backtracking(n, squareNums, 0);
    }

    public int backtracking(int n, List<Integer> squares, int count) {
        if (n < 0) return -1;
        if (n == 0) {
            return count;
        }
        // minCount would stay in current level
        int minCount = Integer.MAX_VALUE;
        
        for (int square : squares) {
            int cnt = backtracking(n - square * square, squares, count + 1);
            minCount = cnt == -1 ? minCount : Math.min(minCount, cnt);
        }
        return minCount;
    }  
    
    // Approach 2 - DP - bottom up
    // 15ms - faster than 93.42%
    // transition formula:
    // dp[n] = min(dp[n - i*i] + 1), for i > 0 and i*i <= n
    // time - O(n * sqrt(n)) space - O(n)
    public int numSquares2(int num) {
        int[] dp = new int[num + 1];
        for (int n = 1; n <= num; n++) {
            // loop over for all sqrt to get the min value
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= (int)Math.sqrt(n); i++) {
                min = Math.min(min, dp[n - i*i]);
            }
            dp[n] = min + 1;
        }
        return dp[num];
    }
    
    // Approach 2 - DP - version 2
    // using an arraylist
    // 2ms - faster then 97.75%
    static List<Integer> result = new ArrayList<>();
    public int numSquares22(int n) {
        if (result.size() == 0) {
            result.add(0);
        }
        while (result.size() <= n) {
            int m = result.size();
            int tempMin = Integer.MAX_VALUE;
            for (int j = 1; j * j <= m; j++) {
                tempMin = Math.min(tempMin, result.get(m - j * j) + 1);
            }
            result.add(tempMin);
        }
        return result.get(n);
    }
    
    // Approach 3 - BFS
    // 82ms - faster than 17.83%
    /* 
    Consider a graph which consists of number 0, 1,...,n as its nodes. 
    Node j is connected to node i via an edge if and only if either 
    j = i + (a perfect square number) or i = j + (a perfect square number). 
    Starting from node 0, do the breadth-first search. If we reach node n at step m, 
    then the least number of perfect square numbers which sum to n is m. 
    */
    // time -  - O(n * sqrt(n)) space - O(n)
    public int numSquares3(int num) {
        // store all square numbers smaller than num
        List<Integer> squares = new ArrayList();
        for (int i = 1; i * i <= num; i++) {
            squares.add(i * i);
        }
        
        Queue<Integer> queue = new LinkedList();
        // we should start from 0 because if the number itself is a perfect square number
        queue.add(0);
        
        // we can also add a hashset to check if the number has been visited before
        Set<Integer> visited = new HashSet();
        
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                for (int square : squares) {
                    int next = curr + square; 
                    if (next == num) return count;
                    else if (next > num) break;
                    
                    else if (!visited.contains(next)){
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        return count;
    }
    
    // Approach 4 - Mathematics
}
