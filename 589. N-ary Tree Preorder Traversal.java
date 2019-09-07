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
    /*
    // Approach 1 - Recursively
    // time O(n) space O(logn) ~ O(n)
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
    }
    public void helper(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        for (Node child : root.children) 
            helper(child, res);
    }
    */
    
    // Approach 2 - Iteratively
    // time O(n) space O(n) + O(logn)
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList();
        Stack<Node> stack = new Stack();
        if (root == null) return res;
        stack.push(root);
        
        while (!stack.isEmpty()){
            root = stack.pop();
            res.add(root.val);
            
            if (root.children != null)    
                for (int i = root.children.size() - 1; i >= 0; i--)
                    stack.push(root.children.get(i));
        }
        return res;
    }
}
