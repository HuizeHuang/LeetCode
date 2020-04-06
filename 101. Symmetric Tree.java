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
    // Approach 1 - Recursion
    // time: O(n): 2 ^ log_2(n)
    // space: O(n): The number of recursive calls is bound by the height of the tree. In worst case, the tree is linear and the height O(n).
    public boolean isSymmetric1(TreeNode root) {
        return isMirror(root, root);
        
    }
    public boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2 == null;
        if (root2 == null) return root1 == null;
        
        if (root1.val != root2.val) return false;
        
        return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }
    
    // Approach 2 - Iteration - queue
    // time: O(n)
    // space: O(n)
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        queue.add(root);
        
        while(!queue.isEmpty()){
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;
            
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }
}
