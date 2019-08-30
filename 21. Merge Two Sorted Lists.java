/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /*
    // Approach 1 - iteratively
    // use a dummy head
    // time O(n) space O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;
        while (l1 != null && l2 != null) {
            
            if (l1.val > l2.val) {
                current.next = l2;
                l2 = l2.next;
            }
            else {
                current.next = l1;
                l1 = l1.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        return dummyHead.next;   
    }
    */
    
    // Approach 2 - recursively
    // time O(n) space O(n)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
