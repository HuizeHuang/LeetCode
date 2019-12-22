class Solution {
    // Almost the same as the word ladder
    // BFS
    public int minMutation(String start, String end, String[] bank) {
        Set<String> dict = new HashSet(), visited = new HashSet();;
        for (String s : bank) dict.add(s);
        if (!dict.contains(end)) return -1;
        char[] genes = new char[]{'A', 'C', 'G', 'T'};
        
        Queue<String> queue = new LinkedList();
        queue.add(start);
        for (int level = 1; !queue.isEmpty(); level++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                char[] chars = curr.toCharArray();
                for(int j = 0; j < chars.length; j++) {
                    char old = chars[j];
                    for (char c : genes) {
                        if (chars[j] == c) continue;
                        chars[j] = c;
                        String str = String.valueOf(chars);
                        if (str.equals(end)) return level;
                        if (dict.contains(str) && visited.add(str)) queue.add(str);
                    }
                    chars[j] = old;
                }
            }
        }
        return -1;
    }
    
    // Almost the same as the word ladder
    // Bidirectional BFS
    // time O (nl)
    public int minMutation2(String start, String end, String[] bank) {
        Set<String> staSet = new HashSet(), endSet = new HashSet(), visited = new HashSet();
        Set<String> dict = new HashSet();
        for (String s : bank) dict.add(s);
        staSet.add(start);
        if (dict.contains(end)) endSet.add(end);
        char[] genes = new char[]{'A', 'C', 'G', 'T'};
        
        Map<String, Integer> map = new HashMap();
        map.put(start, -1);
        map.put(end, -1);
        
        for (int level = 1; !staSet.isEmpty(); level++) {
            if (staSet.size() > endSet.size()) {
                Set<String> set = staSet;
                staSet = endSet;
                endSet = set;
            }
            Set<String> temp = new HashSet();
            for (String currGene : staSet) {
                char[] chars = currGene.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (map.get(currGene) == i) continue;
                    char old = chars[i];
                    for (char c : genes) {
                        if (chars[i] == c) continue;
                        chars[i] = c;
                        String newGene = String.valueOf(chars);
                        if (endSet.contains(newGene)) return level;
                        if (dict.contains(newGene) && visited.add(newGene)){
                            temp.add(newGene);
                            map.put(newGene, i);
                        }
                    }
                    chars[i] = old;
                }
            }
            staSet = temp;
        }
        return -1;
    }
}
