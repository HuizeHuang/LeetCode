/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
class Solution {
    // Approach 1 - DFS
    // pre-order tree
    // pseudo head - sentinel node
    // store leaf node (tail), store current next value (right sub-tree)
    // time - O(N) space - O(N)
    public Node flatten1(Node head) {
        if (head == null) return null;
        
        Node pseudoHead = new Node(0, null, head, null);
        dfs(pseudoHead, head);
        
        pseudoHead.next.prev = null;
        return head;
    }
    
    public Node dfs(Node prev, Node curr) {
        if (curr == null) return prev;
        
        // pre-order
        // do something before recursion
        curr.prev = prev;
        prev.next = curr;
        // we need to store current next value in case it would be altered later
        Node tempNext = curr.next;
        
        // left sub-tree
        // it will return the leaf node
        Node tail = dfs(curr, curr.child);
        curr.child = null;
        
        // right sub-tree
        return dfs(tail, tempNext);
    }
    
    // Approach 2 - DFS - stack
    // pre-order
    // 1. push right child node into stack first
    // 2. pseudo head
    // time - O(n) space - O(n)
    public Node flatten(Node head) {
        if (head == null) return head;
        Stack<Node> stack = new Stack();
        stack.push(head);
        Node prev = new Node(0, null, head, null);
        
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            curr.prev = prev;
            prev.next = curr;
            prev = curr;
