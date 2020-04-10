/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    // Approach 1 - Recursion
    // time: O(N * logN) 
    // find the middle point needs (N/2 + 2*(N/4) + 4(N/8) +...)
    // this is done logN times - logN * (n/2)
    // space: O(logN)
    public TreeNode sortedListToBST1(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        // 1. find the middle point in the linked list
        // since it's a singly linked list, need another pointer 
        // points to the node before middle point
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        if(prev != null) prev.next = null;
        TreeNode root = new TreeNode(slow.val);
        
        // 2. recursively find the middle point of left list and right list
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
    
    // Approach 2 - Convert linked list to an array + two pointers
    // You can get the time complexity down by using more space.
    // array has O(1) time of finding the middle point
    // time: O(N) - 1 + 2 + 4 + 8 + ... + N -- logN 个 2^(i-1)
    // space: O(N + logN)
    List<Integer> list = new ArrayList<>();
    public TreeNode sortedListToBST2(ListNode head) {
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        int left = 0, right = list.size() - 1;
        return helper(left, right);
    }
    
    public TreeNode helper(int left, int right) {
        if (left > right) return null;
            
        int mid = (right - left) / 2 + left;
        TreeNode root = new TreeNode(list.get(mid));
        if(left == right) return root;
        
        root.left = helper(left, mid - 1);
        root.right = helper(mid + 1, right);
        return root;
    }
    
    // Approach 3 - In-order Traversal
    // Elements processed in the inorder fashion on a binary search tree turn out to be sorted in ascending order.
    // reduce space complexity 
    // time: O(n + )
    // space: O()
    ListNode head;
    public TreeNode sortedListToBST(ListNode head) {
        
        int len = findSize(head);
        this.head = head;
        return inorder(0, len - 1);
    }
    
    public int findSize(ListNode head){
        ListNode node = head;
        int size = 0;
        while(node != null) {
            size++;
            node = node.next;
        }
        return size;
    }
    
    public TreeNode inorder(int start, int end){
        if (start > end) return null;
        
        int mid = (start + end) / 2;
        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = inorder(start, mid - 1);
        
        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(head.val);
        node.left = left;
        
        // Maintain the invariance: head in the algorithm
        this.head = this.head.next;
        
        // Recurse on the right hand side and form BST out of them
        node.right = inorder(mid + 1, end);
        return node;
    }
}
