/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    // First thought would be do it in 2 pass, one to connect all nodes based
    // on "next", one is to connect all nodes based on random
    // time - O(2 * n), space - O(1)
    
    // Approach 1 - Recursion - DFS + hashMap
    // be careful with the random pointer, don't point to the original node
    // think of it as a graph
    // HashMap to keep track of visited nodes to prevent cycles,
    // keep old node --> new node mapping
    // time - O(n)  space - O(n)
    
    // use a map to record old - new node pair
    Map<Node, Node> map = new HashMap();
    
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // if visited
        if (map.containsKey(head)) return map.get(head);
        
        Node newHead = new Node(head.val);
        
        // if not visited, put before recursion
        map.put(head, newHead);
        
        newHead.next =  copyRandomList(head.next);
        
        // if write like this, it shows that random pointer of node with label 
        // 13 points to a node from the original list.
        // newHead.random = head.random;
        
        newHead.random = copyRandomList(head.random);
        return newHead;
    }
    
    // Apporach 2 - Iterative + HashMap
    // time - O(n), space - O(n)
    
    // Approach 3 - Interweaving - O(1) space
    // If A->B->C is the original linked list,
    // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
    // then we connect random node
    // at last we unweave the list
    // time - O(3 * n) space - O(1)
    
}
