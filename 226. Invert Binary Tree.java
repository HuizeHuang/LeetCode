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
    // Approach 1 - recursive
    // 每一步的共同公式就是child nodes俩俩翻转
    // time O(n) every node is visited
    // space O(logn) ~ O(n)
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
    */
    
    // Approach 2 - iteratively
    // 相当于level order traversal
    // we create a queue to store nodes whose left and right child have not been swapped yet
    // time space
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode curr = root;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(curr);
        while(!queue.isEmpty()) {
            curr = queue.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
        return root;
    }
}
