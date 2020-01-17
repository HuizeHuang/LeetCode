// Approach 1 - two stacks
// put current minimum into minstack
class MinStack1 {
    
    /** initialize your data structure here. */
    Stack<Integer> stack;
    Stack<Integer> minstack;
    
    public MinStack1() {
        minstack = new Stack();
        minstack.push(Integer.MAX_VALUE);
        stack = new Stack();
    }
    
    public void push(int x) {
        stack.push(x);
        if (x <= minstack.peek()) 
            minstack.push(x);
    }
    
    public void pop() {
        int top = stack.pop();
        if (top == minstack.peek())
            minstack.pop(); 
    }
    
    public int top() {
        if (stack.isEmpty())
            return -1;
        return stack.peek();
    }
    
    public int getMin() {
        if (stack.isEmpty())
            return -1;
        return minstack.peek();
    }
}

// Approach 2 - One stack
// use a variable to record current minimum value
// if new element is smaller than current min, then push the old min to stack
// otherwise only push the new element
class MinStack2 {
    
    /** initialize your data structure here. */
    Stack<Integer> stack;
    int min;
    
    public MinStack2() {
        stack = new Stack();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if (stack.pop() == min)
            min = stack.pop();
    }
    
    public int top() {
        if (stack.isEmpty())
            return -1;
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

// Approach 3 - Linked List
// keep min in the node structure
class MinStack {
    
    /** initialize your data structure here. */
    class Node{
        int val;
        Node next;
        int min;
        
        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
    Node head;
    
    public MinStack() {
        head = new Node(0, Integer.MAX_VALUE, null);
    }
    
    public void push(int x) {
        Node curr = new Node(x, Math.min(x, head.min), head);
        head = curr;
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
