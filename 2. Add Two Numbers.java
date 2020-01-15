/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Approach 1 - Math - iterative
    // 尽量减少判断，多找规律，利用 sum / 10, sum % 10, sum = x + y + carry
    // Keep track of the carry using a variable
    // dummy head
    // some edge cases: 
    // [8,9,9]
    // [2]
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        if (l1 == null && l2 == null) return dummyHead;
        int sum, x, y;
        
        while (l1 != null || l2 != null) {
            ListNode next = new ListNode(0);
            curr.next = next;
            curr = curr.next;
            
            x = l1 == null ? 0 : l1.val;
            y = l2 == null ? 0 : l2.val;
            sum = x + y + carry;
            
            curr.val = sum % 10;
            carry = sum / 10;
            
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            
        }
           
        if (carry == 1) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
    
    // Approach 2 - Math - Recursively
    // !! ending condition
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return helper(l1, l2, 0);
    }
    
    public ListNode helper(ListNode l1, ListNode l2, int carry) {
        int x, y, sum;
        if (l1 == null && l2 == null && carry == 0) return null;
        if (l1 == null && l2 == null) return new ListNode(carry);
        
        x = l1 == null ? 0 : l1.val;
        y = l2 == null ? 0 : l2.val;
        
        sum = x + y + carry;
        carry = sum / 10;
        
        ListNode curr = new ListNode(sum % 10);
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;
        curr.next = helper(l1, l2, carry);
        return curr;
    }
}
