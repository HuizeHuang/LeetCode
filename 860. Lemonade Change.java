class Solution {
    // Approach 1 - Hash Table
    // faster than 8.32%
    // time - O(n) space - O(1)
    public boolean lemonadeChange1(int[] bills) {
        if (bills == null || bills.length == 0) return true;
        Map<Integer, Integer> table = new HashMap();
        for (int bill : bills) {
            if (bill == 5) {
                table.put(bill, table.getOrDefault(bill, 0) + 1);
            }
            else if (bill == 10) {
                table.put(bill, table.getOrDefault(bill, 0) + 1);
                table.put(5, table.getOrDefault(5, 0) - 1);
            }
            else {  // bill == 20
                table.put(bill, table.getOrDefault(bill, 0) + 1);
                if (table.containsKey(10) && table.get(10) > 0){
                    table.put(10, table.getOrDefault(10, 0) - 1);
                    table.put(5, table.getOrDefault(5, 0) - 1);
                }
                else {
                    table.put(5, table.getOrDefault(5, 0) - 3);
                }
            }
            if (table.get(5) < 0) return false;
        }
        return true;
    }
    
    // Approach 2 - Use variable counts
    // faster than 89.69%
    // time - O(n) space - O(1) << space in approach 1
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) return true;
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            }
            else if (bill == 10) {
                // if (five == 0) return false;
                ten++;
                five--;
            }
            else if (ten > 0) {
                // if (five == 0) return false;
                ten--;
                five--;
            }
            else {
                // if (five < 3) return false;
                five -= 3;
            }
            
            if (five < 0) return false;
        }
        return true;
    }
}
