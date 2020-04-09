



class Trie {
 
    /** Initialize your data structure here. */
    class TrieNode {
        private final int R = 26;
        private TrieNode[] links;
        private boolean isEnd;
        
        public TrieNode(){
            links = new TrieNode[R];
        }
        
        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }
        
        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }
        
        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }
        
        public void setEnd(){
            isEnd = true;
        }
        public boolean isEnd(){
            return isEnd;
        }
    }
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (!node.containsKey(curr)){
                node.put(curr, new TrieNode());
            }
            node = node.get(curr);
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (node.containsKey(curr)) {
                node = node.get(curr);
            }
            else {
                return false;
            }
        }
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.containsKey(c)) {
                node = node.get(c);
            }
            else
                return false;
        }
        return true;
    }
}
 
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
 
 
// Follow-up: how to do an in-order traversal in Trie tree?
 


