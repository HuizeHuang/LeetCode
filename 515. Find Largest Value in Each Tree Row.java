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
    // Approach 1 - Level-order traversal - BFS - queue
    // time - O(n)
    // faster than 66.84%
    public List<Integer> largestValues1(TreeNode root) { 
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while(!queue.isEmpty()) {
            // level size
            int size = queue.size();
            // max value per level
            int levelMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelMax = Math.max(levelMax, curr.val);
                // add new queue element
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            res.add(levelMax);
        }
        return res;
    }
    
    // Approach 2 - DFS
    // level order recursively
    // time O(n) faster than 100%
    public List<Integer> largestValues(TreeNode root) { 
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }
    
    public void helper(List<Integer> res, TreeNode root, int depth) {
        if (root == null) return;
        
        if (res.size() == depth) 
            res.add(root.val);
        else        // change current level's value
            res.set(depth, Math.max(root.val, res.get(depth)));
        
        helper(res, root.left, depth + 1);
        helper(res, root.right, depth + 1);
    }
}

