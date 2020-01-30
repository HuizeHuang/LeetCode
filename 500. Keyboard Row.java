import java.util.regex.Pattern;
import java.util.regex.Matcher;
class Solution {
    // Approach 1 - Array
    public String[] findWords1(String[] words) {
        String r1 = "qwertyuiop", r2 = "asdfghjkl", r3 = "zxcvbnm";
        int[] rows = new int[26];
        helper(rows, r1, 1);
        helper(rows, r2, 2);
        helper(rows, r3, 3);
        
        List<String> res = new ArrayList<String>();
        
        for (String word : words) {
            boolean valid = true;
            String curr = word.toLowerCase();
            for (int i = 0; i < curr.length() - 1; i++) {
                if (rows[curr.charAt(i) - 'a'] != rows[curr.charAt(i + 1) - 'a']) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                res.add(word);
        }
        String[] strs = new String[res.size()];
        return res.toArray(strs);
    }
    
    public void helper(int[] rows, String str, int row) {
        for (char c : str.toCharArray()) {
            rows[c - 'a'] = row;
        }
    }
    
    // Approach 2 - Regex 
    public String[] findWords(String[] words) {
        List<String> res = new ArrayList();
        
        Pattern pattern = Pattern.compile("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*");

        Matcher matcher;
        
        for (String word : words) {
            String lower = word.toLowerCase();
            
            matcher = pattern.matcher(lower);
            if (matcher.matches())
                res.add(word);
        }
        
        String[] strs = new String[res.size()];
        return res.toArray(strs);
    }
}
