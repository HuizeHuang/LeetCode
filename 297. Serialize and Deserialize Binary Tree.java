/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Approach 1 - preorder traversal
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "x,";
        
        String left = serialize(root.left);
        String right = serialize(root.right);
        // 在string前面加上递归上一层的
        return root.val + "," + left + right;
    }
    
    // Create a queue to represent the strring flow
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList();
        queue.addAll(Arrays.asList(data.split(",")));
        return deHelper(queue);
    }
    public TreeNode deHelper(Queue<String> queue) {
        String value = queue.poll();
        if ("x".equals(value)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = deHelper(queue);
        root.right = deHelper(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
