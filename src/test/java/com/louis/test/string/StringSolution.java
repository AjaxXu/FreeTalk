package com.louis.test.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class StringSolution {

    @Test
    public void test() {
        System.out.println(letterCombinations("").toString());
    }


    public int longestValidParentheses(String s) {
        int pos = -1;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    int start = stack.isEmpty() ? pos : stack.peek();
                    max = Math.max(i - start, max);
                } else {
                    pos = i;
                }
            }
        }
        return max;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum <= 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String word: words) {
            Integer value = allWords.getOrDefault(word, 0);
            allWords.put(word, value + 1);
        }
        for (int i = 0; i < s.length() - wordLen * wordNum  + 1; i ++) {
            Map<String, Integer> containWords = new HashMap<>();
            int num = 0;
            while (num < wordNum) {
                String w = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                if (allWords.containsKey(w)) {
                    Integer v = containWords.getOrDefault(w, 0);
                    containWords.put(w, v + 1);
                    if (containWords.get(w) > allWords.get(w)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            if (num == wordNum) res.add(i);
        }
        return res;
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) return 0;
        return haystack.lastIndexOf(needle);
    }

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 == 1) return false;
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (char ch: s.toCharArray()) {
            Character ch2 = map.get(ch);
            if (ch2 == null) {
                stack.add(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!stack.pop().equals(ch2)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public List<String> letterCombinations(String digits) {
        Map<String, String> phone = new HashMap<String, String>() {{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};
        List<String> res = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            String s = digits.substring(i, i + 1);
            List<String> t = new ArrayList<>();
            String s1 = phone.get(s);
            if (res.size() == 0) {
                for (char ch : s1.toCharArray()) {
                    t.add("" + ch);
                }
            } else {
                for (String r : res) {
                    for (char ch : s1.toCharArray()) {
                        t.add(r + ch);
                    }
                }
            }
            res = t;
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || ch != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    public boolean dp(int i, int j, String text, String pattern, Boolean[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j] == true;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern, memo) ||
                        first_match && dp(i+1, j, text, pattern, memo));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern, memo);
            }
        }
        memo[i][j] = ans ? true : false;
        return ans;
    }

    public boolean isPalindrome(int x) {
        String s1 = String.valueOf(x);
        StringBuilder sb = new StringBuilder(s1);
        String s2 = sb.reverse().toString();
        return s1.equals(s2);
    }

    public int myAtoi(String str) {
        str = str.trim();
        int res = 0;
        int flag = 0;
        int i = 0;
        boolean num = false;
        boolean sign = false;
        while (i < str.length()) {
            if (str.charAt(i) == '-' || str.charAt(i) == '+') {
                if (num) break;
                if (sign) return 0;
                flag = str.charAt(i) == '+' ? 1 : -1;
                i++;
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                int temp = str.charAt(i) - '0';
                if ((res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && temp > 7)) && flag != -1) {
                    return Integer.MAX_VALUE;
                } else if (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && temp > 8) {
                    return Integer.MIN_VALUE;
                }
                res = res * 10 + temp;
                i++;
                num = true;
            } else {
                if (res > 0) {
                    break;
                }
                return 0;
            }
            sign = true;
        }
        return flag == -1 ? -res : res;
    }

    /**
     * Z字形变换
     * https://leetcode-cn.com/problems/zigzag-conversion/
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     */
    public String convert(String s, int numRows) {
        if (s == null || s.equals("") || numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numRows; i++) {
            int index = i;
            int k = 1;
            while (index <= len) {
                sb.append(chars[index - 1]);
                if (i == 1 || i == numRows) {
                    index += (numRows - 1) * 2;
                } else {
                    if (k % 2 == 1) {
                        index += (numRows - i) * 2;
                    } else {
                        index += 2 * (i - 1);
                    }
                    k++;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 最长回文子串
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        boolean[][] p = new boolean[length][length];
        int maxLen = 0;
        String maxStr = "";
        for (int len = 1; len<= length; len++) {
            for (int start = 0; start < length; start++) {
                int end = start + len - 1;
                if (end >= length) break;
                p[start][end] = (len == 1 || len == 2 || p[start + 1][end - 1]) && s.charAt(start) == s.charAt(end);
                if (p[start][end] && len > maxLen) {
                    maxLen = len;
                    maxStr = s.substring(start, end + 1);
                }
            }
        }
        return maxStr;
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        int prev = -1;
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i]) && map.get(chars[i]) > prev) {
                prev = map.get(chars[i]);
            } else if (i - prev > len) {
                len = i - prev;
            }
            map.put(chars[i], i);
        }
        return len;
    }
}
