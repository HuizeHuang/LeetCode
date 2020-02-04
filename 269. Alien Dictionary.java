class Solution {
    // Build graph
    Map<Character, Set<Character>> map = new HashMap();
    public int[] buildGraph(String[] words) {
        int[] indegree = new int[26];   // record how many incoming edges
        
        // save all characters which is used in the hash set
        // avoid all letters are the same, we store all letters in the map fisrt
        for(String word: words) {
            for(char c: word.toCharArray()) {
                map.put(c, new HashSet());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            // compare every two words
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            
            // compare each letter
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    // if (!map.containsKey(c1))
                    //     map.put(c1, new HashSet());
                
                    // only for bfs
                    // only increase indegree when two incoming edges are different
                    if (map.get(c1).add(c2))
                        indegree[c2 - 'a']++;
                    
                    // !!!
                    break;
                }
            }
        }
        return indegree;
    }
        
    // Topological sorting
    // Approach 1 - BFS (kahn's algorithm)
    // edge cases: ["z","z"]
    public String alienOrder1(String[] words) {
        int[] indegree = buildGraph(words);
        Queue<Character> queue = new LinkedList();
        String res = "";
        
        // find all characters with 0 incoming edges
        for (char c : map.keySet()) {
            if (indegree[c - 'a'] == 0)
                queue.add(c);
        }
        
        // if we only store letters that are differenct, 
        // when map's empty, either there is a cycle, or all letters are the same
        // we need to deal with that, so first we need to store all letters in map
        
        // bfs
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            res += curr;
            // if not last one
            if (map.containsKey(curr)){
                // iterate all next nodes
                for (char c : map.get(curr)) {
                    indegree[c - 'a']--;
                    if (indegree[c - 'a'] == 0)
                        queue.add(c);
                }
            }
        }
        
        if (res.length() != map.size()) return "";
        return res;
    }
    
    // Approach 2 - DFS
    // when backtrack returns, add the character at the end of string 
    // to prevent cycles, 
    // visited[i] = 1: visiting
    // visited[i] = 2: visited
    // i records current index in order array
    // i == -2: there's a cycle
    
    public String alienOrder(String[] words) {
        // we can use the same function for bfs
        int[] unuse = buildGraph(words);
        int n = map.size();
        char[] order = new char[n];
        int i = n - 1; // record current position of last iteration
        int[] visited = new int[26];
        Arrays.fill(visited, -1);
        
        for (char c : map.keySet()) {
            if (visited[c - 'a'] == -1) {
                i = dfs(visited, order, i, c);
                if (i == -2) return "";
            }
        }
        String res = new String(order);
        return res;
    }
    
    public int dfs(int[] visited, char[] order, int i, char curr) {
        // if a node is being visiting, and it is currently visited again
        // then there's a cycle
        if (visited[curr - 'a'] == 1) 
            return -2;
        
        // if a node is visited
        // ["z", "x"], first visiting "x", and second call visiting z -> x
        if (visited[curr - 'a'] == 2)
            return i;
        visited[curr - 'a'] = 1;  // visiting
        
        for (char next : map.get(curr)) {
            i = dfs(visited, order, i, next);
        }
        visited[curr - 'a'] = 2;  // visited
        if (i >= 0) order[i] = curr;
        
        return i < 0 ? -2 : i - 1;
    }
}
