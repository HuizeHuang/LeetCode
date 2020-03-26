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
    // Approach 1 - Recursive in-order traversal
    // time: O(n)
    // space: O(1)
    boolean successor;
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        if (root == null) return null;
        TreeNode res = inorderSuccessor(root.left, p);
        
        // inorder, do something to root here
        if (res != null) return res;
        if (root == p) successor = true;
        if (successor && root.val > p.val) return root;
        
        return inorderSuccessor(root.right, p);    
    }
    
    // imporvement:
    // Since it's a BST, in in-order traversal, it is in increasing order
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) return null;
        
        // search right subtree if it's smaller
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        else{   // search left subtree if it's larger
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null ? root : left;
        }
    }
    
    // Approach 2 - Iterative
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode candidate = null;
        while (root != null) {
            if (root.val <= p.val){
                root = root.right;
            }
            else {
                candidate = root;
                root = root.left;
            }   
        }
        return candidate;
    }
}
