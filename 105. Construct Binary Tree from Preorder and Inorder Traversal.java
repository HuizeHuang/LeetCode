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
    // Approach 1 - recursively
    // Preorder traversing implies that PRE[0] is the root node.
    // Then we can find this PRE[0] in IN, say it's IN[5].
    // Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the     // left side, IN[6] to the end is on the right side.
    // Recursively doing this on subarrays, we can build a tree out of it 
    // time space
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) return null;
        
        return helper(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    public TreeNode helper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        // end condition
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
        // 在inorder中找到preorder的头结点
        // 在inorder中当前位置的左边都为左子树，右边都为右子树
        TreeNode preHead = new TreeNode(preorder[preStart]);
        int index = inStart;
        while (inorder[index] != preHead.val) 
            index++;
        
        preHead.left = helper(preorder, inorder, preStart + 1, inStart, index - 1);
                                                        
        /*
        preorder = [3,9,20,15,7]
        inorder = [9,3,15,20,7]
        3 is inStart, index = 1, left subtree is length of 1 = (1 - 0)
        prestart for right subtree should be 2 = prestart(0) + left subtree length(1) + 1;
        */
                                                     //prestart + length of current left subtree
        preHead.right = helper(preorder, inorder, preStart + (index - inStart) + 1, index + 1, inEnd);
        return preHead;
    }
}






