/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Approach 1 - two pointers
    // time: O(n)
    // space: O(1)
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null) {
            while (fast !=null && slow.val == fast.val) {
                fast = fast.next;
            }
            slow.next = fast;
            slow = fast;
            if (fast != null)
                fast = fast.next;
        }
        return head;
    }
    
    // Approach 2 - one pointer
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            while (curr.next != null && curr.val == curr.next.val) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return head;
    }
}
