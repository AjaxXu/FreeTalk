package com.louis.test.n100.dp;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Louis
 */
public class DpSolution {

    @Test
    public void test1() {
        System.out.println(largestRectangleArea_84(new int[]{2,1,5,6,2,3}));
    }

    public int numDecodings_91(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];

        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int tmp = s.charAt(i - 1) - '0';
            if (tmp != 0) dp[i] = dp[i - 1];

            tmp = (s.charAt(i - 2) - '0') * 10 + tmp;
            if (tmp >= 10 && tmp <= 26) dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }

    public int maximalRectangle_85(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] data = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    data[i][j] = matrix[i][j] == '0' ? 0 : 1;
                } else {
                    if (matrix[i][j] != '0') {
                        data[i][j] = data[i - 1][j] + 1;
                    } else {
                        data[i][j] = 0;
                    }
                }
            }
        }

        int max = 0;
        for (int i = data.length - 1; i >= 0; i--) {
            max = Math.max(max, maxInHistogram(data[i]));
            if (max >= i * data[i].length) break;
        }
        return max;
    }

    private int maxInHistogram(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int i = 0;
        while (i <= heights.length) {
            int val = i < heights.length ? heights[i] : 0;
            if (stack.isEmpty() || val >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int hIndex = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, width * heights[hIndex]);
            }
        }
        return max;
    }

    public int largestRectangleArea_84(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) { // 起点柱子
            int minHeight = 0;
            for (int j = 0; j < n - i; j++) { // 代表柱子间的距离
                if (j == 0) minHeight = heights[i];
                else {
                    minHeight = Math.min(minHeight, heights[i + j]);
                }
                maxArea = Math.max(maxArea, minHeight * (j + 1));
            }
        }
        return maxArea;
    }

    public int minDistance(String word1, String word2) {
        if (word1.isEmpty() && word2.isEmpty()) return 0;
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], 1 + Math.min(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if (i == 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
                else if (j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length <= 0 || obstacleGrid[0].length <= 0) return 0;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else if (i == 0 && j == 0) dp[i][j] = 1;
                else if (i == 0) dp[i][j] = dp[i][j - 1];
                else if (j == 0) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
