class Solution {
    // Approach 1 - stack
    // We keep pushing elements into stack if it's smaller than the top element in the stack
    // otherwise we keep pop elements until it's larget than current element
    // and record the smallest one and comparing with curr (since it should be in right subtree)
    // time: O(n)
    // space: O(n)
    public boolean verifyPreorder1(int[] preorder) {
        if (preorder.length == 0) return true;
        Stack<Integer> stack = new Stack();
        stack.push(Integer.MAX_VALUE);
        int leftMax = Integer.MIN_VALUE;
        for (int node : preorder) {
            // if current value is smaller than left subtree,
            // it's not a valid node
            if (node < leftMax)
                return false;
            // keep popping until it's smaller
            while (node > stack.peek()) {
                leftMax = stack.pop();
            }
            stack.push(node);
        }
        return true;
    }

    // Approach 2 - Constant space
    // Make use of preorder array
    // time: O(n)
    // space: O(1)
    public boolean verifyPreorder(int[] preorder) {
        int top = -1;
        int leftMax = Integer.MIN_VALUE;
        for (int node : preorder) {
            if (node < leftMax)
                return false;
            while (top >= 0 && node > preorder[top])
                leftMax = preorder[top--];
            preorder[++top] = node;
            
        }
        return true;
    }
    
    // this is actually verify if it's a BST
    public boolean dfs(int curr, int[] preorder) {
        if (curr >= preorder.length) return true;
        // check the property of BST: left child < root, right child > root
        if ((curr * 2 + 1 < preorder.length && preorder[curr * 2 + 1] > preorder[curr]) || (curr * 2 + 2 < preorder.length && preorder[curr * 2 + 2] < preorder[curr]))
            return false;
        return dfs(curr * 2 + 1, preorder) && dfs(curr * 2 + 2, preorder);
    }
}
