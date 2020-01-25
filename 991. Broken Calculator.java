class Solution {
    // Approach 1 - BFS - TLE
    // dfs, it's hard to find an ending condition (x>y?)
    // try to use bfs
    // at each step, there are two choices: double or subtract
    // it's like search in a binary tree. level-order
    // time: O(2 ^ (Y - X))
    // space: O(2 ^ (Y - X))
    public int brokenCalc1(int X, int Y) {
        int level = 0;
        if (X > Y) 
            return X - Y;
        
        Queue<Integer> queue = new LinkedList();
        queue.add(X);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int x = queue.poll();
                if (x == Y) {
                    return level;
                }
                queue.add(x * 2);
                queue.add(x - 1);
            }
            level++;
        }
        return level;
    }
    
    // Approach 2 - Working backwards
    // if we start from X to Y, it may be not the minimum steps that we need
    // why?
    // If with subtraction and multiplication, the effect of earlier subtraction will be amplified by later multiplications, 
    // hence, greedily doing multiplication might not reach optimal solution as an additional early subtraction can have a huge effect.
    // Whereas with addition and division, earlier addition will be diminished by later divisions. 
    // It is thus always better to do division wherever possible.
    // We start from Y going backwards to X
    // if Y is smaller than X, then it definitely can be reached by only subtracting
    // if Y is larger than X, then 
    //      if Y is an odd number, Y = (Y + 1)
    //      else Y = Y / 2
    public int brokenCalc(int X, int Y) {
        int res = 0;
        while (Y > X) {
            if (Y % 2 == 0)
                Y /= 2;
            else
                Y++;
            res++;
        }
        return (X - Y) + res;
    }
}
