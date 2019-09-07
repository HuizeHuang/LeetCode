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
    
    // Approach 1 - Iteratively - stack
    // 按pre order的顺序压入栈导出，最后再翻转一下结果列表
    // time O() space O()
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList();
        Stack<Node> stack = new Stack();
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
            for (Node child : root.children)
                stack.push(child);
        }
        Collections.reverse(res);
        return res;
    }
    
    /*
    // Approach 2 - Recursively
    // time O(n) Because each node will be traversed once
    // space O(n) The deepest calling stack size will reach the deepest path 
    // length in this tree. the worst case is n, in average is logn
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
    }
    
    public void helper(Node root, List<Integer> res) {
        if (root == null) return;
        for (Node child : root.children)
            helper(child, res);
        res.add(root.val);
    }
    */
}
