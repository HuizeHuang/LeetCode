class RandomizedSet {
    /*
    !!! HashMap + ArrayList
    In hashmap, store the element as the key, and its index in arraylist as value
    Hashmap provides O(1) in retrieving and removing
    ArrayList provides O(1) in getting random through indexing
    !!! Arraylist has O(n) in removing so we can always remove the last element to achieve the O(1) time complexity, to do so, we swap the element that is going to be removed with the last element in the arraylist, and also change the index in map.
    */

    /** Initialize your data structure here. */
    Map<Integer, Integer> map;
    List<Integer> list;

    public RandomizedSet() {
        this.map = new HashMap();
        this.list = new ArrayList();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        // !!!
        if (map.containsKey(val)) {
            
            // swap the element with the last one
            int idx = map.get(val);
            int last = list.get(list.size() - 1);
            list.set(idx, last);
            
            // update idx of last element
            map.put(last, idx);
            
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (list.size() != 0) {
            int rand = (int)(list.size() * Math.random());
            return list.get(rand);
        }
        return -1;
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
