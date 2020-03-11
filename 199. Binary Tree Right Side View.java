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
    // Approach 1 - level-order traversal
    // time: O(n)
    // space: O(n)
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        List<Integer> res = new ArrayList();
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    res.add(node.val);
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }
}
