package com.louis.test.n200.string;

import org.junit.Test;

import java.util.*;

public class StringSolutionN200 {

    @Test
    public void test1() {
        System.out.println(reverseWords_151("  hello world!  "));
    }


    // https://leetcode-cn.com/problems/reverse-words-in-a-string/
    public String reverseWords_151(String s) {
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            String trim = array[i].trim();
            if (!trim.isEmpty()) {
                sb.append(trim).append(" ");
            }
        }
        if (sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
    public int evalRPN_150(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int n1, n2;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n1 + n2);
                    break;
                case "-":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 - n2);
                    break;
                case "*":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n1 * n2);
                    break;
                case "/":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 / n2);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
                    break;
            }
        }
        return stack.pop();
    }

    // https://leetcode-cn.com/problems/word-break-ii/
    public List<String> wordBreak_140(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len];
        for (int r = 0; r < len; r++) {
            if (set.contains(s.substring(0, r + 1))) {
                dp[r] = true;
            } else {
                for (int l = 0; l < r; l++) {
                    if (dp[l] && set.contains(s.substring(l + 1, r + 1))) {
                        dp[r] = true;
                        break;
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        if (dp[len - 1]) {
            LinkedList<String> queue = new LinkedList<>();
            dfs_140(s, len - 1, set, res, queue, dp);
        }
        return res;
    }

    private void dfs_140(String s, int end, Set<String> set, List<String> res, LinkedList<String> queue, boolean[] dp) {
        String prefix = s.substring(0, end + 1);
        if (set.contains(prefix)) {
            queue.addFirst(prefix);
            StringBuilder sb = new StringBuilder();
            for (String word : queue) {
                sb.append(word).append(" ");
            }
            res.add(sb.substring(0, sb.length() - 1));
            queue.removeFirst();
        }
        for (int i = 0; i < end; i++) {
            if (dp[i]) {
                String suffix = s.substring(i + 1, end + 1);
                if (set.contains(suffix)) {
                    queue.addFirst(suffix);
                    dfs_140(s, i, set, res, queue, dp);
                    queue.removeFirst();
                }
            }
        }
    }

    // https://leetcode-cn.com/problems/word-break/
    public boolean wordBreak_139(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // https://leetcode-cn.com/problems/palindrome-partitioning-ii/
    public int minCut_132(String s) {
        int len = s.length();
        if (len < 2) return 0;

        boolean[][] checkPalindrome = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || checkPalindrome[left + 1][right - 1])) {
                    checkPalindrome[left][right] = true;
                }
            }
        }

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            if (checkPalindrome[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (checkPalindrome[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }

    // https://leetcode-cn.com/problems/palindrome-partitioning/
    public List<List<String>> partition_131(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) return res;
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        Deque<String> path = new ArrayDeque<>();
        backTrap_131(s, 0, len, dp, path, res);
        return res;
    }

    private void backTrap_131(String s, int start, int len, boolean[][] dp, Deque<String> path, List<List<String>> res) {
        if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {
            if (!dp[start][i]) {
                continue;
            }
            path.add(s.substring(start, i + 1));
            backTrap_131(s, i + 1, len, dp, path, res);
            path.removeLast();
        }
    }

    // https://leetcode-cn.com/problems/valid-palindrome/
    public boolean isPalindrome_125(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
                left++;
                right--;
            }
        }
        return true;
    }
}
