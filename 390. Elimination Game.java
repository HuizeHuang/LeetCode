class Solution {
    // Approach 1 - break down 
    // time O(n / 2), space - O(1)
    public int lastRemaining(int n) {
        int step = 1, head = 1;
        int remaining = n;
        boolean left = true;
        
        while (remaining > 1) {
            // update the head in two cases:
            if (left || remaining % 2 == 1) {
                head += step;
            }
            remaining /= 2;
            step *= 2;
            left = !left;
 
        }
        return head;
    }
}





