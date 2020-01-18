



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Approach 1 - Brute force
    // Traverse all the linked lists and collect the values of the nodes into an array.
    // Sort and iterate over this array to get the proper value of nodes.
    // Create a new sorted linked list and extend it with the new nodes.
    // time - O(nlogn) n is the total nodes in the lists
    // space - O(n)
    
    // Approach 2 - Compare One by One
    // using a vertical sliding windown, each time append the smallest one in the window to the list, for the list who provide the smallest node, move to the left one more.
    // time - O(n * k), each time (k - 1) comparisons where k is the number of lists
    // space - O(n), creating a new linked list
    
    // Approach 3 - Comparing one by one with PriorityQueue 
    // time - O(n * logk), log(k) for priorityQueue add and poll
    // space - O(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        // Define comparator
        Comparator<ListNode> cmp;
        cmp = new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        };
        
        PriorityQueue<ListNode> minheap = new PriorityQueue(cmp);
        
        // fisrt put all head nodes to the PriorityQueue
        for (ListNode head : lists) {
            if (head != null)
                minheap.add(head);
        }
        
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while(!minheap.isEmpty()) {
            curr.next = minheap.poll();
            curr = curr.next;
            if (curr.next != null)
                minheap.add(curr.next);
            
        }
        return head.next;
    }
    
    // Approach 4 - Merge One by one
    // faster than 14%
    // merge every two sorted list and so on
    // time - O(n * k), k is the number of lists, n is the total number of nodes of two lists
    // space - O(1)
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        ListNode merged = lists[0];
        for (int i = 1; i < lists.length; i++) {
            merged = mergeTwoLists1(merged, lists[i]);
        }
        return merged;
    }
    
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (list1 != null && list2 != null) {
            if (list1.val  < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            }
            else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null ? (list2 == null ? null : list2) : list1;
        return head.next;
    }
    
    // Approach 5 - Divide and Conquer with recursion
    // O(n * logk) - we have logk comparisons
    public ListNode mergeKLists2(ListNode[] lists) {
        
        return partition(lists, 0, lists.length - 1);
    }
    
    public ListNode partition(ListNode[] lists, int i, int j) {
        if (i == j) return lists[i];
        if (i > j) return null;
        int mid = (i + j) /2;
        
        ListNode l1 = partition(lists, i, mid);
        ListNode l2 = partition(lists, mid + 1, j);
        return mergeTwo(l1, l2);
    }
    
    public ListNode mergeTwo(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        if (list1.val < list2.val)
            list1.next = mergeTwo(list1.next, list2);
        else 
            list2.next = mergeTwo(list1, list2.next);
        return list1.val < list2.val ? list1 : list2;
    }
}





