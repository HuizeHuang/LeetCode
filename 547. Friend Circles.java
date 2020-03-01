class Solution {
    // Approach 1 - DFS
    // a visited set, count
    // time: O(n^2) - matrix is completely traversed
    // space: O(n)
    public int findCircleNum1(int[][] M) {
        int n = M.length;
        Set<Integer> visited = new HashSet();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                count++;
                dfs(M, visited, i);
            }
        }
        return count;
    }
    
    public void dfs(int[][] M, Set<Integer> visited, int curr) {
        for (int i = 0; i < M.length; i++) {
            if (!visited.contains(i) && M[curr][i] == 1) {
                visited.add(i);
                dfs(M, visited, i);
            }
        }
    }
    
    // Approach 2 - BFS
    // when the queue is empty, a connected component is found
    // time: O(n^2)
    // space: O(n)
    public int findCircleNum2(int[][] M) {
        Set<Integer> visited = new HashSet();
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        
        for (int i = 0; i < M.length; i++) {
            if (visited.contains(i)) continue;
            
            queue.add(i);
            visited.add(i);
            
            while (!queue.isEmpty()) {
                // NO NEED TO record the size since we are not counting
                // the number of levels here
                // int size = queue.size();
                // for (int k = 0; k < size; k++) {
                int curr = queue.poll();
                
                for (int j = 0; j < M.length; j++) {
                    if (!visited.contains(j) && M[curr][j] == 1) {
                        queue.add(j);
                        visited.add(j);
                    }
                }
            }
            count++;
        }
        return count;
    }
    
    // Approach 3 - Union Find
    // union: make the parents of two sets the same
    // find: find the parent of the set
    // check if they belong to the same set
    // time: O(n^3), union find takes O(n) in the worst case - 6ms
    // space: O(n)
    
    public int find(int[] parents, int i) {
        if (parents[i] == -1)
            return i;
        return find(parents, parents[i]);
    }
    
    public void union(int[] parents, int i, int j) {
        int i_parent = find(parents, i);
        int j_parent = find(parents, j);
        if (i_parent != j_parent) {
            parents[i_parent] = j_parent;
        }
    }
    
    public int findCircleNum3(int[][] M) {
        int n = M.length;
        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1 && i != j)
                    union(parents, i, j);
            }
        }
        
        // check how many different parents are here (how many -1)
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1)
                count++;
        }
        return count;
    }
    
    // Approach 4 - Union Find - Union by Rank + Path Compression 
    // union by rank: the one with the higher rank becomes the parent
    // path compression: when doing the find operation, make all the nodes along the path become the direct children (neighbors) of the parent node of this set
    // time: union find: O(m * a(n)), m is #operations, n is #elements, a(n) is at most 5
    // m is at most (n*(n - 1)) / 2 ~ O(n^2)
    // space: O(2 * n)
    
    public int findPathCompression(int[] parents, int i) {
        if (parents[i] != i){
            parents[i] = findPathCompression(parents, parents[i]);
        }
        return parents[i];
    }

    public void unionByRank(int[] parents, int[] rank, int i, int j) {
        int iroot = findPathCompression(parents, i);
        int jroot = findPathCompression(parents, j);
        if (rank[iroot] < rank[jroot]) {
            rank[jroot]++;
            parents[iroot] = jroot;
        }
        else if (rank[iroot] < rank[jroot]) {
            parents[iroot] = jroot;       
        }
        else {
            parents[jroot] = iroot;
        }
    }
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        int[] rank = new int[n];
        // initialize parents array to the index value
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1 && i != j)
                    unionByRank(parents, rank, i , j);
            }
        }        
        
        int count = 0;
        for (int p = 0; p < n; p++) {
            if (p == parents[p])
                count++;
        }
        return count;
    }
}
