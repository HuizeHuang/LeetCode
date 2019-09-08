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
    // Approach 1 - Recursively
    // time O(n) space O(n)
    public int maxDepth(TreeNode root) {
        
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
    */
    
    /*
    // Approach 2 - BFS - queue
    // 相当于是level order traversal 只不过每层增加计一次数
    // time O(n) space O(n) largest number of nodes in one level
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList(); 
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++){
                root = queue.poll();
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            depth++; 
        }
        return depth;
    }
    */
    
    // Approach 3 - DFS - Stack
    // Use two stacks, one to store nodes, one to store current counting levels
    // time space
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack();
        Stack<Integer> counter = new Stack();
        int depth = 1;
        stack.add(root);
        counter.add(depth);
        while (!stack.isEmpty()) {
            root = stack.pop();
            int count = counter.pop();
            depth = Math.max(depth, count);
            
            if (root.left != null){
                stack.push(root.left);
                counter.push(count + 1);
            }
            if (root.right != null) {
                stack.push(root.right);
                counter.push(count + 1);
            }
        }
        return depth;
    }
}
