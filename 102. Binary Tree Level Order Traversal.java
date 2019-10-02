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
    // Approach 1 - BFS - queue
    // time O(n), space O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        List<List<Integer>> result = new ArrayList();
        if (root == null) return result;
        queue.add(root);
        while (!queue.isEmpty()) {
            int level_size = queue.size();
            List<Integer> level = new ArrayList();
            for (int i = 0; i < level_size; i++) {
                TreeNode curr = queue.poll();
                level.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            result.add(level);
        }
        return result;
    }
    */
    
    // Approach 2 - DFS - recursion
    // time O(n) space O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        helper(root, result, 0);
        return result;
    }
    public void helper(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null) 
            return;
        if (level == result.size())
            result.add(new ArrayList<Integer>());
        result.get(level).add(root.val);
        helper(root.left, result, level + 1);
        helper(root.right, result, level + 1);
    }
}
