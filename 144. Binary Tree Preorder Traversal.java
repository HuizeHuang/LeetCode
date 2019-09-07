/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /*
    // Approach 1 Iteratively - stack
    // 若当前节点不为空则加入result，若有右节点则把当前节点压入栈, 一直到左节点底部，压出右结点
    // time O(n) space O(n)
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            
            while (root != null) {
                res.add (root.val);
                if (root.right != null)
                    stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()){
                root = stack.pop();
                root = root.right;
            }
        }
        return res;
    }
    */
    
    // Approach 2 Recursively
    // time O(n) space O(n)
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res); 
    }
}
