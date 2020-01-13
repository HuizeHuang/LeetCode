class Solution {
    // Approach 1 - DFS
    // time - O(m^n), m is the maximum number of neighbors of one node
    // space - O(n*m)
    List<List<Integer>> res = new ArrayList();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        
        List<Integer> currPath = new ArrayList();
        currPath.add(0);
        dfs(graph, 0, currPath);
        return res;
    }
    
    public void dfs(int[][] graph, int curr,  List<Integer> currPath) {
        if (curr == graph.length - 1) {
            res.add(new ArrayList(currPath));
            return;
        }
        
        for (int next : graph[curr]) {
            currPath.add(next);
            dfs(graph, next, currPath);
            currPath.remove(currPath.size() - 1);
        }
    }
}
