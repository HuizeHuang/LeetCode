class MyCircularDeque {
    // Approach 1 - Using an Array
    // FRONT starts from end of array, REAR starts from first element of array.
    
    /** Initialize your data structure here. Set the size of the deque to be k. */
    int front, rear, cnt;
    int[] dq;
    int n;
    public MyCircularDeque(int k) {
        
        dq = new int[k];
        front = k - 1;x
        rear = 0;
        cnt = 0;
        this.k = k;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        
        if (cnt == k) return false;
        dq[front] = value;
        front = (front - 1 + k) % k;
        cnt++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (cnt == k) return false;
        dq[rear] = value;
        rear = (rear + 1) % k;
        cnt++;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (cnt == 0) return false;
        front = (front + 1) % k;
        cnt--;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (cnt == 0) return false;
        rear = (rear - 1 + k) % k;
        cnt--;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if (cnt == 0) return -1;
        return dq[(front + 1) % k];
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if (cnt == 0) return -1;
        return dq[(rear - 1 + k) % k ];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
       return cnt == 0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
         return cnt == k;
    }
    
    
    // Apprroach 2 - Doubly linked list
    /** Initialize your data structure here. Set the size of the deque to be k. */
    int k;
    int cnt;
    DoubleListNode front;
    DoubleListNode rear;
    public MyCircularDeque(int k) {
        front = new DoubleListNode(-1);
        rear = new DoubleListNode(-1);
        this.cnt = 0;
        this.k = k;
        front.pre = rear;
        rear.next = front;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (cnt == k) return false;
        DoubleListNode node = new DoubleListNode(value);
        node.next = front;
        node.pre = front.pre;
        front.pre.next = node;
        front.pre = node;
        
        cnt++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (cnt == k) return false;
        DoubleListNode node = new DoubleListNode(value);
        node.pre = rear;
        node.next = rear.next;
        rear.next.pre = node;
        rear.next = node;
        
        cnt++;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (cnt == 0) return false;
        front.pre.pre.next = front;
        front.pre = front.pre.pre;
        cnt--;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (cnt == 0) return false;
        rear.next.next.pre = rear;
        rear.next = rear.next.next;
        cnt--;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        return front.pre.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        return rear.next.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return cnt == 0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return cnt == k;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
