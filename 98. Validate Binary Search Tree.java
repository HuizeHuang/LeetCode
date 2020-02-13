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
    // It's a pre-order traversal
    // 1. Not only the left and right child should satisfy the condition, all the elements
    // in the subtree should also satisfy the condition.
    // [10,5,15,null,null,6,20]
    // 2. equal elements are not allowed in any of the subtrees
    // 3. compare to value above
    
    // Approach 1 - Pre-order Traversal - recursively
    // for left subtree, its upper bound is the root value, root's lower bound
    // for right subtree, its lower bound is the root value, root's upper bound
    // time: O(n)
    // space:O(logn)
    public boolean isValidBST1(TreeNode root) {
       return dfs(root, null, null);
    }
    
    public boolean dfs(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        
        if (lower != null && root.val <= lower) return false;
        if (upper != null && root.val >= upper) return false;
        
        return dfs(root.left, lower, root.val) && dfs(root.right, root.val, upper);
    }
    
    
    // Approach 1 - Pre-order - iteratively - 3 stacks
    Stack<TreeNode> stack = new Stack();
    Stack<Integer> lowers = new Stack();
    Stack<Integer> uppers = new Stack();
    
    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.push(root);
        lowers.push(lower);
        uppers.push(upper);
    }
    
    public boolean isValidBST2(TreeNode root) {
        Integer lower = null, upper = null;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            lower = lowers.pop();
            upper = uppers.pop();
            
            if (node == null) continue;
            
            if (lower != null && node.val <= lower) return false;
            if (upper != null && node.val >= upper) return false;
            update(node.right, node.val, upper);
            update(node.left, lower, node.val);
        }
        return true;
    }
    
    // Approach 2 - In-order - Recursively
    // Left -> Node -> Right order of inorder traversal means for BST that each element should be smaller than the next one.
    public boolean isValidBST(TreeNode root) {
        
        return inorder(root, true);
    }
    
    
    TreeNode prev = null;
    public boolean inorder(TreeNode root, boolean flag) {
        if (root == null) return true;
        
        flag = inorder(root.left, flag);
        
        if (prev != null && root.val <= prev.val)
            return false;
        
        prev = root;
        
        return flag && inorder(root.right, flag);
    }

    
    // Approach 2 - In-order - Iteratively
    // Double.MIN_VALUE is the least positive value 
    public boolean isValidBST4(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        // double value is symmetric
        double prev = -Double.MAX_VALUE;
        
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            
            if (root.val <= prev)
                return false;
            
            prev = root.val;
            root = root.right; 
        }
        return true;
    }
}
