package com.louis.test.n200.dp;

import org.junit.Test;

public class DpSolution {

    @Test
    public void test() {
        System.out.println(numDistinct_115("babgbag", "bag"));
    }

    // https://leetcode-cn.com/problems/distinct-subsequences/
    public int numDistinct_115(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (j == 0) {
                    // 理解成是""字符串
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    if (s.charAt(i - 1) != t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    }
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
