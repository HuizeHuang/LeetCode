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
    // Approach 1 - recursion
    // time O(n) space O(n)
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 注意返回值的条件！！
        // 为了解决[1, 2]这种情况
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
    */
    
    // Approach 2 - BFS - iteratively - level order traversal
    // 从左到右找到的第一个左右子节点为空的就返回当前级数(level)
    // 在每一层level中，如果有左右节点都为空 就返回当前层数
    // 也可以用两个queue，一个存节点，一个记录每个节点当前的层数
    // time O(n) space O(n)
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int level = queue.size();
            // In each level
            for (int i = 0; i < level; i++) {
                root = queue.poll();
                if (root.left == null && root.right == null)
                    return depth;
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
            }
            depth++;
        }
        return depth;
    }  
}
