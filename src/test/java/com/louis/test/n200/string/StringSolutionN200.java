package com.louis.test.n200.string;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StringSolutionN200 {

    @Test
    public void test1() {
        System.out.println(isPalindrome_125("A man, a plan, a canal: Panama"));
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
