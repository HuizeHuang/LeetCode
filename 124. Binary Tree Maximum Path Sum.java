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
    // Approach 1 - DFS
    // Tree post-order traversal
    // time: O(n)
    // space: O(log(n))
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
            
        int left = Math.max(maxPathSum(root.left), 0);
        int right = Math.max(maxPathSum(root.right), 0);
        // do something after return, update the maximum with the sum of three:
        // current + left + right  --> only this way it's a path
        max = Math.max(max, root.val + left + right);
        
        // but only return one of the path in right and left
        return Math.max(left, right) + root.val;
    }
}


