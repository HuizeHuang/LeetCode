/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import javafx.util.*;
class Solution {
    /*
    // Approach 1 - Recursion
    // 先向下找到p，q，若找到则返回当前结点，没找到返回null
    // 最后比较根结点的左右返回值
    // time O(n), space max-O(N)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        
        if (root.val == p.val || root.val == q.val) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (right != null && left != null) return root;
        if (right == null && left == null) return null;
        return left == null ? right : left;
    }
    */
    
    /*
    // Approach 2 - Iterative with parent pointers
    // backtracking
    // 遍历整个tree 找到从root到对应p，q的所有parent nodes，
    // 然后比较两个parent nodes，找到第一个相同的地方
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
    }
    */
    
    // Approach 3 - Iterative without parent pointers
    // 用一个stack存储（root，state），用一个index记录最低触及的stack的位置
    // post-order traversal, get rid of backtracking
    // time space
    
    private static int BOTH_PENDING = 2;
    private static int LEFT_DONE = 1;
    private static int BOTH_DONE = 0;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<Pair<TreeNode, Integer>> stack = new Stack();
        stack.push(new Pair<TreeNode, Integer>(root, Solution.BOTH_PENDING));
        boolean one_node_found = false;
        TreeNode LCA = null;
        TreeNode child_node = null;
        
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> top = stack.peek();
            TreeNode parent_node = top.getKey();
            int parent_state = top.getValue();
            
            if (parent_state != Solution.BOTH_DONE) {
                if (parent_state == Solution.BOTH_PENDING) {
                    
                    if (parent_node == p || parent_node == q) {
                        if (one_node_found)
                            return LCA;
                        one_node_found = true;
                        LCA = parent_node;
                    }
                    child_node = parent_node.left;
                }
                else        //not both pending
                    child_node = parent_node.right;
                
                stack.pop();
                stack.push(new Pair<TreeNode, Integer>(parent_node, parent_state - 1));
                
                if (child_node != null)
                    stack.push(new Pair<TreeNode, Integer>(child_node, Solution.BOTH_PENDING));
            }
            else {  // BOTH_DONE
                if (LCA == stack.pop().getKey() && one_node_found)
                    LCA = stack.peek().getKey();
            }
        }
        return null;
    }
}
