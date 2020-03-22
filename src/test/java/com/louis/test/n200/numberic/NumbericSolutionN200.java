package com.louis.test.n200.numberic;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NumbericSolutionN200 {

    @Test
    public void test() {
        System.out.println(singleNumber_136(new int[]{1,2,2}));
    }


    // https://leetcode-cn.com/problems/max-points-on-a-line/
    public int maxPoints_149(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int i = 0;
        for (; i < points.length - 1; i++) {
            if (points[i][0] != points[i + 1][0] || points[i][1] != points[i + 1][1]) {
                break;
            }

        }
        if (i == points.length - 1) {
            return points.length;
        }
        int max = 0;
        for (i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    continue;
                }
                int tempMax = 0;
                for (int k = 0; k < points.length; k++) {
                    if (k != i && k != j) {
                        if (test(points[i][0], points[i][1], points[j][0], points[j][1], points[k][0], points[k][1])) {
                            tempMax++;
                        }
                    }

                }
                if (tempMax > max) {
                    max = tempMax;
                }
            }
        }
        //加上直线本身的两个点
        return max + 2;
    }
/*private boolean test(int x1, int y1, int x2, int y2, int x, int y) {
	return (long)(y2 - y1) * (x - x2) == (long)(y - y2) * (x2 - x1);
}*/

/*private boolean test(int x1, int y1, int x2, int y2, int x, int y) {
    BigInteger x11 = BigInteger.valueOf(x1);
    BigInteger x22 = BigInteger.valueOf(x2);
    BigInteger y11 = BigInteger.valueOf(y1);
   	BigInteger y22 = BigInteger.valueOf(y2);
    BigInteger x0 = BigInteger.valueOf(x);
    BigInteger y0 = BigInteger.valueOf(y);
    return y22.subtract(y11).multiply(x0.subtract(x22)).equals(y0.subtract(y22).multiply(x22.subtract(x11)));
}*/

    private boolean test(int x1, int y1, int x2, int y2, int x, int y) {
        int g1 = gcd(y2 - y1, x2 - x1);
        if(y == y2 && x == x2){
            return true;
        }
        int g2 = gcd(y - y2, x - x2);
        return (y2 - y1) / g1 == (y - y2) / g2 && (x2 - x1) / g1 == (x - x2) / g2;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // https://leetcode-cn.com/problems/single-number-ii/
    public int singleNumber_137(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int n : nums) {
            twos |= ones & n;
            ones ^= n;
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    // https://leetcode-cn.com/problems/single-number/
    public int singleNumber_136(int[] nums) {
        int t = 0;
        for (int n : nums) {
            t ^= n;
        }
        return t;
    }

    // https://leetcode-cn.com/problems/candy/
    public int candy_135(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
//        System.out.println(Arrays.toString(left));
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
//        System.out.println(Arrays.toString(right));
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.max(left[i], right[i]) + 1;
        }
        return sum;
    }

    // https://leetcode-cn.com/problems/gas-station/
    public int canCompleteCircuit_134(int[] gas, int[] cost) {
        int len = gas.length;
        int total = 0;
        int current = 0;
        int station = 0;
        for (int i = 0; i < len; i++) {
            total += gas[i] - cost[i];
            current += gas[i] - cost[i];
            if (current < 0) {
                current = 0;
                station = i + 1;
            }
        }
        return total >= 0 ? station : -1;
    }

    // https://leetcode-cn.com/problems/surrounded-regions/
    public void solve_130(char[][] board) {
        if (board == null || board.length == 0) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean isEdge = i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs_130(board, i, j);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs_130(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfs_130(board, i - 1, j);
        dfs_130(board, i, j + 1);
        dfs_130(board, i + 1, j);
        dfs_130(board, i, j - 1);
    }

    // https://leetcode-cn.com/problems/longest-consecutive-sequence/
    public int longestConsecutive_128(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        int max = 0;
        for (int n : nums) {
            int low = n - 1;
            while (set.contains(low)) {
                low--;
            }
            int up = n + 1;
            while (set.contains(up)) {
                up++;
            }
            max = Math.max(max, (up - low - 1));
        }
        return max;
    }

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
