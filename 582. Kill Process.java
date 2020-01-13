class Solution {
    // Approach 1 - HashMap + DFS
    // first to form a tree, key is the parent node, and the value is child nodes
    // then using dfs to search
    // time - O(2 * n) - traversing all nodes, space - O(n)
    public List<Integer> killProcess1(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList();
        if(pid == null || ppid == null || pid.size() == 0 || ppid.size() == 0 || ppid.size() != pid.size()) return res;
        res.add(kill);
        
        // build the map (tree)
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 0; i < ppid.size(); i++) {
            int child = pid.get(i);
            int parent = ppid.get(i);
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList());
            }
            map.get(parent).add(child);
        }
        dfs(res, map, kill);
        return res;
    }
    
    public void dfs(List<Integer> res, Map<Integer, List<Integer>> map, int kill) {
        if (!map.containsKey(kill)) return;
        
        for (int child : map.get(kill)) {
            res.add(child);
            dfs(res, map, child);
        }
    }
    
    // Approadch 2 - HashMap + BFS
    // time - O(2 * n) space - O(n)
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList();
        if(pid == null || ppid == null || pid.size() == 0 || ppid.size() == 0 || ppid.size() != pid.size()) return res;
        res.add(kill);
        
        // build the map (tree)
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 0; i < ppid.size(); i++) {
            int child = pid.get(i);
            int parent = ppid.get(i);
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList());
            }
            map.get(parent).add(child);
        }
        
        Queue<Integer> queue = new LinkedList();
        queue.add(kill);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> children = map.getOrDefault(curr, new ArrayList());
            for (int child : children) {
                res.add(child);
                queue.add(child);
            }
        }
        return res;
    }
}





