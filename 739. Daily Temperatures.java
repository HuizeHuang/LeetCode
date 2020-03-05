class Solution {
    // Approach 1 - Next Array
    // Work backwards
    // The array stores the leftmost index we've seen starting from the right 
    // time: O(71 * n) ~ O(n)
    // space: O(100 + n) ~ O(n)
    public int[] dailyTemperatures1(int[] T) {
        int[] next = new int[101];
        int[] res = new int[T.length];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; i--) {
            next[T[i]] = i; 
            for (int j =  T[i] + 1; j < 101; j++) {
                if (next[j] == 0) continue;
                res[i] = Math.min(res[i], next[j] - i);
            }
            if (res[i] == Integer.MAX_VALUE) res[i] = 0;
        }
        return res;
    }
    
    // Approach 2 - Stack
    // working bakcwards
    // stores temp from small to large from top to bottom
    // if current temp is larger than top, pop out
    // time: O(n)
    // space: O(n)
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack();
        int[] res = new int[T.length];
        
        for (int i = T.length - 1; i >= 0; i--) {

            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
               stack.pop();
            }

            if (stack.isEmpty()) {
                res[i] = 0;
            }
            else
                res[i] = stack.peek() - i;
            stack.add(i);
        }
        return res;
    }
}
