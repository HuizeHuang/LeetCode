class Solution {
    // Approach 1 - DFS - Bridges Algorithm
    // 1. Build graph -> form adjacency list
    // 2. Bridge Algorithm
    // time - O(n + edges) space - O(n)
    
    // result
    List<List<Integer>> res = new ArrayList();
    int id = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        // build graph, use adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
        }
        
        for (int i = 0; i < connections.size(); i++) {
            
            int node1 = connections.get(i).get(0);
            int node2 = connections.get(i).get(1);
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        // visited
        boolean[] visited = new boolean[n];
        // low link
        int[] low = new int[n];
        // id
        int[] ids = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                dfs(i, -1, graph, visited, low, ids);
        }
        return res;
    }
    
    public void dfs(int curr, int parent, List<Integer>[] graph, boolean[] visited, int[] low, int[] ids) {
        visited[curr] = true;
        ids[curr] = id;
        low[curr] = id++;
        
        // iterate over all neighbor nodes of current node
        for (int neighbor : graph[curr]) {
            // !!!
            if (neighbor == parent) continue;
            
            if (!visited[neighbor]) {
                dfs(neighbor, curr, graph, visited, low, ids);
                low[curr] = Math.min(low[curr], low[neighbor]);
                // !!!
                if (ids[curr] < low[neighbor]) {
                    res.add(new ArrayList(Arrays.asList(curr, neighbor)));
                }
            }
            
            else {
                low[curr] = Math.min(low[curr], ids[neighbor]);
            }
        }
    }
}
