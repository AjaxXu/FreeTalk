package com.louis.test.n200.numberic;

public class NumbericSolutionN200 {

    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
    public int maxProfit_123(int[] prices) {
        return maxProfitCommon(prices, 2);
    }

    private int maxProfitCommon(int[] prices, int K) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][K + 1];
        for (int k = 1; k <= K; k++) {
            int min = prices[0];
            for (int j = 1; j < prices.length; j++) {
                // 找出第 1 天到第 i 天 prices[buy] - dp[buy][k - 1] 的最小值
                min = Math.min(prices[j] - dp[j][k - 1], min);
                // 比较不操作和选择一天买入的哪个值更大
                dp[j][k] = Math.max(dp[j - 1][k], prices[j] - min);
            }
        }
        return dp[prices.length - 1][K];
    }

    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfit_122(int[] prices) {
        int res = 0;
        if (prices == null || prices.length <= 1) return 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit_121(int[] prices) {
        int gap = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > min) {
                gap = Math.max(gap, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return gap;
    }
}
