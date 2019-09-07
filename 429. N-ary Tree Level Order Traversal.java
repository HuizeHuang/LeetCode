/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    
    // Approach 1 - recursively
    // 在helper函数中加一个记录当前是哪一层的变量
    // recursion 就是dfs
    // time space
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList();
        helper(root, res, 0);
        return res;
    }
    
    public void helper(Node root, List<List<Integer>> res, int level) {
        if (root == null) return;
        
        // 如果dfs到下一层
        if (level >= res.size())
            res.add(new ArrayList<Integer>());
        
        res.get(level).add(root.val);
        
        for (Node child : root.children) {
            helper(child, res, level + 1);    
        }
    }
    
    
    /*
    // Approach 2 - iterative - queue
    // time O(n) 
    // space O(n) depends on the maximum number of nodes in one level
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList();
        Queue<Node> queue = new LinkedList();
        if (root == null) return res;
        queue.add(root);
        int length = 1;
        
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList();
            // 一层的循环
            
            for (int i = 0; i < length; i++) {
                root = queue.poll();
                level.add(root.val);
                
                for (Node child : root.children) 
                    queue.add(child);
            }
            length = queue.size();
            res.add(level);
        }
        return res;
    } 
    */
}




