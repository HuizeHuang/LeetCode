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
    // Approach 1 - Iteratively - Using stack
    // stack: push left node first
    // time O(n) space O(n)
    public List<Integer> inorderTraversal(TreeNode root) {
        
        List<Integer> res = new ArrayList();       
        Stack<TreeNode> stack = new Stack();
        
        // 注意root != null 这个条件，因为可能有栈是空的（左边节点已经pop出）
        // 但是当前节点有右结点，不是空的情况
        while (root != null || !stack.isEmpty()) {
            // 永远先压入左边的节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            
            root = root.right;
        }
        return res;
    }
    */
    
    /*
    // Approach 2 - Recursively
    // time O(n) space worst case O(n) in average O(logn)
    public List<Integer> inorderTraversal(TreeNode root) {
        
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
        
    }
    
    public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            helper(root.left, res);     
            res.add(root.val);
            helper(root.right, res);
        }
    }
    */
    
    // Approach 3 - Morris Traversal
    // Always make the current as the right node of left subtrees's rightmost node
    // time O(n) space O(n)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        TreeNode curr = root;
        TreeNode rightmost;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;          // move to next right node
            }
            else {                          // has a left subtree
                rightmost = curr.left;
                while (rightmost.right != null)   // find rightmost
                    rightmost = rightmost.right;   
                
                rightmost.right = curr;     // put cur after the rightmost node
                TreeNode temp = curr;
                curr = curr.left;           // move cur to the top of the new tree
                temp.left = null;           // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
