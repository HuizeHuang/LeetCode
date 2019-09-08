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
    // Approach 1 - iteration - In-order traversal
    // inorder对于bst来说永远是按照从小到大排列的
    // time O(H + k), H is the depth of tree, average case(balanced tree) O(logN + k),
    // worst case O(N + k)
    // space O(H + k) the same as time
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack();
        int count = 0;
        while (root != null || !stack.isEmpty()) {
            
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (++count == k) return root.val;
            root = root.right;
        }
        return 0;
    }
    */
    
    // Approach 2 - Recursive
    // add some global variables or function parameters
    // time O(N) space O(N)
    private int count = 0;
    private int result = 0;
    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return result;
    }
    public void helper(TreeNode root, int k) {
        if (root == null) return;
        helper(root.left, k);
        if (++count == k) result = root.val;
        helper(root.right, k);
    }
    
    // Follow up
    //What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
}
