package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #76 - Minimum Window Substring
 * Time: O(n), Space: O(k) where k = charset size
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        int left = 0, right = 0, formed = 0, required = need.size();
        int[] ans = {-1, 0, 0};
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right++);
            window.merge(c, 1, Integer::sum);
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) formed++;

            while (formed == required) {
                if (ans[0] == -1 || right - left < ans[0]) {
                    ans[0] = right - left;
                    ans[1] = left;
                    ans[2] = right;
                }
                char lc = s.charAt(left++);
                window.merge(lc, -1, Integer::sum);
                if (need.containsKey(lc) && window.get(lc) < need.get(lc)) formed--;
            }
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2]);
    }
}
