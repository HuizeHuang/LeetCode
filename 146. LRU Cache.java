



// Approach 1: Hashmap + DoubleLinkedList
// hashmap: key - value pair: provide O(1) time searching elements
// doubleLinkedList: provide O(1) time adding and deleting elements
// time space
class LRUCache {
    
    // create a doubly linked list by ourselves
    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        
        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public void addNode(DLinkedNode node) {
        /* always add node after pseudo head */
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    public DLinkedNode removeNode(DLinkedNode node) {
        /* remove current node*/
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        return node;
    }
    
    public void moveToFirst(DLinkedNode node) {
        /* need to move recently used node to head */
        removeNode(node);
        addNode(node);
    }
    
    public DLinkedNode popTail() {
        return removeNode(tail.prev);
    }
    
    Map<Integer, DLinkedNode> map;
    int size;
    int capacity;
    DLinkedNode head;
    DLinkedNode tail;
    
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        map = new HashMap(capacity);
        
        // initialize the doubly linked list
        head = new DLinkedNode(0, 0);
        tail = new DLinkedNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            moveToFirst(map.get(key));
            return map.get(key).value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        
        if (!map.containsKey(key)) {
            // exceeds the capacity
            if (this.size >= this.capacity) {
                DLinkedNode last = popTail();
                // remember to remove the key and node from the map
                map.remove(last.key);
                this.size--;
            }
            
            DLinkedNode node = new DLinkedNode(key, value);
            addNode(node);
            map.put(key, node);
            this.size++;
        }
        else {
            // update value
            map.get(key).value = value;
            moveToFirst(map.get(key));
        }
    }
}
 
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */





