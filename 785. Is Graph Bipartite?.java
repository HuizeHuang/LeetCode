class Solution {
    // Approach 1 - BFS + color array
    // undirected graph often needs a visited set
    // beware of unconnected nodes
    // time: O(V + E)
    // space: O(V)
    public boolean isBipartite1(int[][] graph) {
        int len = graph.length;
        // 0 - unseen, 1 - red, -1 - blue
        int[] colors = new int[graph.length];
        
        for (int i = 0; i < len; i++) {
             if (colors[i] != 0) continue;
            
            // if there are unconnected components, 
            // we need to construct a queue for each of the component
            Queue<Integer> queue = new LinkedList();
            queue.offer(i);
            colors[i] = -1;
                
            while(!queue.isEmpty()) {
                int node = queue.poll();
                int color = colors[node];

                for (int next : graph[node]) {
                    if (colors[next] == color)
                        return false;
                    else if (colors[next] == 0) {
                        queue.offer(next);
                        colors[next] = -1 * color;
                    }
                }
            }
        }
        return true;
    }
    
    // Approach 2 - DFS + color array
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] colors = new int[len];
        
        for (int i = 0; i < len; i++) {
            if (colors[i] != 0) continue;
            colors[i] = -1;
            if (!dfs(graph, colors, i))
                return false;
        }
        return true;
    }
    
    public boolean dfs(int[][] graph, int[] colors, int node) {

        for (int next : graph[node]) {
            if (colors[next] == colors[node]) return false;
            if (colors[next] != 0) continue;
            colors[next] = -colors[node];
            
            if (!dfs(graph, colors, next))
                return false;
            
        }
        return true;
    }
}
