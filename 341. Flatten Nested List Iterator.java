/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/*
// Approach 1 - Stack - faster than 60%
// flatten a list in hasNext()
public class NestedIterator1 implements Iterator<Integer> {
    private Stack<NestedInteger> stack;
    
    public NestedIterator1(List<NestedInteger> nestedList) {
        stack = new Stack();
        this.flattenList(nestedList);
    }

    @Override
    public Integer next() {
        return hasNext() ? stack.pop().getInteger() : null;
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (stack.peek().isInteger())
                return true;
            else {
                flattenList(stack.pop().getList());
            }
        }
        return false;
    }
    
    private void flattenList(List<NestedInteger> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            this.stack.push(list.get(i));
        }
    }
}
*/

// Approach 2 - Queue - Flatten a list in constructor
// faster than 98%
public class NestedIterator implements Iterator<Integer> {
    private Queue<NestedInteger> queue;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList();
        this.helper(nestedList);
    }

    @Override
    public Integer next() {
        return hasNext() ? queue.poll().getInteger() : null;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    private void helper(List<NestedInteger> list) {
        for (NestedInteger n : list) {
            if (n.isInteger())
                this.queue.offer(n);
            else {
                helper(n.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
