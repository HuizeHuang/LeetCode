class Solution {
    // Approach 1 - HashMap
    // we need to split the string
    // time - O(n^2) - worst case all their names are the same, space - O(n)
    public class Transaction{
        String name;
        int time;
        int amount;
        String city;
        int index;
        
        public Transaction(String name, int time, int amount, String city, int index) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
            this.index = index;
        }
    }
    
    public List<String> invalidTransactions1(String[] transactions) {
        Map<String, List<Transaction>> map = new HashMap();
        Set<String> set = new HashSet();
        
        for (int i = 0; i < transactions.length; i++) {
            
            String[] person = transactions[i].split(",");
            String name = person[0];
            int time = Integer.valueOf(person[1]);
            int amount = Integer.valueOf(person[2]);
            String city = person[3];
            Transaction currTrans = new Transaction(name, time, amount, city, i);
            
            if (amount > 1000) {
                set.add(transactions[i]);
            }
            
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList());
                map.get(name).add(currTrans);
            }
            else {  // have met
                for (Transaction trans : map.get(name)) {
                    if (!city.equals(trans.city) && Math.abs(time - trans.time) <= 60) {
                        set.add(transactions[trans.index]);
                        set.add(transactions[i]);
                    }
                }
                map.get(name).add(currTrans);
            }
        }
        return new ArrayList(set);
    }
    
    // Approach 2 - Sort the time
    public List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        Set<Integer> set = new HashSet();
        
        Transaction[] trans = new Transaction[n];
        for (int i = 0; i < n; i++) {
            String[] curr = transactions[i].split(",");
            trans[i] = new Transaction(curr[0], Integer.valueOf(curr[1]), Integer.valueOf(curr[2]), curr[3], i);
        }
        
        // sort the array according to the time
        Arrays.sort(trans, (a, b) -> (a.time - b.time));
        
        for (int i = 0; i < n; i++) {
            Transaction curr = trans[i];
            if (curr.amount > 1000)
                set.add(curr.index);
            
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(curr.time - trans[j].time) <= 60) {
                    if (!curr.city.equals(trans[j].city) && curr.name.equals(trans[j].name)) {
                        set.add(curr.index);
                        set.add(trans[j].index);
                    }
                }
                else        // time exceeds 60mins
                    break;
            }
        }
        List<String> res = new ArrayList();
        for (int idx : set) {
            res.add(transactions[idx]);
        }
        return res;
    }
}
