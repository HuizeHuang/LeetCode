class Solution {
    // Approach 1 - Stack simulation
    // pop from the stack if top element is equal to the current popped element
    // time: O(n)
    // space: O(n)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int j = 0;
        for(int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            
            while(!stack.isEmpty() && stack.peek() == popped[j] && j < popped.length) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
