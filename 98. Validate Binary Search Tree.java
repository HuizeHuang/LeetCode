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
    // Approach 1 - Recursion
    // 在helper function函数变量中加入记录父结点值的变量
    // time O(n) space O(n)
    public boolean isValidBST(TreeNode root) {
        //use Object Integer and null pointer instead of using Integer.MIN_VALUE to avoid the           // corner cases (when node has value Integer.MIN_VALUE or Integer.MAX_VALUE ).
        return helper(root, null, null);
    }
    public boolean helper(TreeNode root, Integer low, Integer high) {
        if (root == null)
            return true;
        if ((low != null && root.val <= low) || (high != null && root.val >= high))
            return false;
        
        return helper(root.left, low, root.val) && helper(root.right, root.val, high);
    }
    
    */
    
    /*
    // Approach 2 - Inorder Traversal
    // Left -> Node -> Right order of inorder traversal means 
    // for BST that each element should be smaller than the next one.
    // 需要增加一个pre变量记录前一个（即left）的值
    // time O(n), space O(n)
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode pre = null;
        
        while(root != null || !stack.isEmpty()) {
            
            while (root != null) {  //find leftmost node
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val)  // <= means equal value node should be on right side of subtree, < means on left side
                return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
    */
    
    // if we want to control equal value node should be on while side of subtree:
    // we add a boolean variable
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode pre = null;
        boolean right = true;   // boolean to control on which side
        while(root != null || !stack.isEmpty()) {
            
            while (root != null) {  //find leftmost node
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            /**********/
            if (pre != null && (right && root.val <= pre.val) || (!right && root.val < pre.val))  
                return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
    
}
