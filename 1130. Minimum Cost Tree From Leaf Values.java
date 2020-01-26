



class Solution {
    // Intuition:
    // Try to make the larger one being multiplied later
    // After each comparison, the smaller one is removed and we won't use it 
    // anymore, and the bigger one actually stays.
    /*
    The problem can translated as following:
    Given an array A, choose two neighbors in the array a and b,
    we can remove the smaller one min(a,b) and the cost is a * b.
    What is the minimum cost to remove the whole array until only one left?
    
    To remove a number a, it needs a cost a * b, where b >= a.
    So a has to be removed by a bigger number.
    We want minimize this cost, so we need to minimize b.
 
    b has two candidates, the first bigger number on the left,
    the first bigger number on the right.
 
    The cost to remove a is a * min(left, right).
    */
    // time: O(n)
    // space: O(n)
    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> stack = new Stack();
        
        // push the largest one that the last one can be multiplied
        stack.push(Integer.MAX_VALUE);
        int res = 0;
        for (int num : arr) {
            // keep comparing until the current element is smaller than the top
            while (num > stack.peek()) {
                int top = stack.pop();
                res += top * Math.min(num, stack.peek());
            }
            stack.push(num);
        }
        while(stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}
