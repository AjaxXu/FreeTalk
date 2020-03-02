package com.louis.test.n100.backtrack;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Louis
 */
public class BacktrackSolution {

    @Test
    public void test1() {
        System.out.println(restoreIpAddresses_93("0000"));
    }

    public List<String> restoreIpAddresses_93(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) return res;
        List<Integer> cur = new ArrayList<>();
        restoreIpAddresses_93_1(s, 0, res, cur);
        return res;
    }

    private void restoreIpAddresses_93_1(String s, int start, List<String> res, List<Integer> cur) {
        if (cur.size() == 4) {
            if (s.length() != start) return;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(cur.get(i)).append(".");
            }
            sb.append(cur.get(3));
            res.add(sb.toString());
            return;
        }
        for (int i = start + 1; i <= start + 3 && i <= s.length(); i++) {
            String s1 = s.substring(start, i);
            if (isValid_93(s1)) {
                cur.add(Integer.valueOf(s1));
                restoreIpAddresses_93_1(s, i, res, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isValid_93(String s1) {
        if (s1.startsWith("0")) return s1.equals("0");
        int n = Integer.parseInt(s1);
        return n >= 0 && n < 256;
    }

    public List<List<Integer>> subsetsWithDup_90(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        List<Integer> cur = new ArrayList<>();
        subsetsWithDup_90_(nums, res, 0, cur);
        return res;
    }

    private void subsetsWithDup_90_(int[] nums, List<List<Integer>> res, int start, List<Integer> cur) {
        res.add(new ArrayList<>(cur));
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            cur.add(nums[i]);
            subsetsWithDup_90_(nums, res, i + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }

    public boolean isScramble_87(String s1, String s2) {
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty() || s1.length() != s2.length()) {
            return false;
        }
        Map<String, Boolean> cache = new HashMap<>();
        return isScramble_(s1, s2, cache);
    }

    private boolean isScramble_(String s1, String s2, Map<String, Boolean> cache) {
        if (s1.equals(s2)) {
            return setScramble(s1, s2, true, cache);
        }
        String k = getScrambleKey(s1, s2);
        if (cache.containsKey(k)) return cache.get(k);

        int len = s1.length();
        for (int i = 1; i < len; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, len);

            // s2从i开始分隔
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, len);
            if (isScramble_(s11, s21, cache) && isScramble_(s12, s22, cache)) {
                return setScramble(s1, s2, true, cache);
            }

            // s2 从len - i开始分隔
            s21 = s2.substring(len - i, len);
            s22 = s2.substring(0, len - i);
            if (isScramble_(s11, s21, cache) && isScramble_(s12, s22, cache)) {
                return setScramble(s1, s2, true, cache);
            }
        }
        // 任意位置分割都没有找到匹配成功的，则不是扰乱字符串
        return setScramble(s1, s2, false, cache);
    }

    private boolean setScramble(String s1, String s2, boolean res, Map<String, Boolean> cache) {
        String key = getScrambleKey(s1, s2);
        cache.put(key, res);
        return res;
    }

    private String getScrambleKey(String s1, String s2) {
        return s1 + "-" + s2;
    }

    int[][] direct = new int[][] {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (exist_(board, i, j, m, n, visited, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean exist_(char[][] board, int i, int j, int m, int n, boolean[][] visited, String word, int index) {
        if (index == word.length()) return true;
        if (i >= m || i < 0 || j >= n || j < 0 || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            if (exist_(board, i + direct[k][0], j + direct[k][1], m, n, visited, word, index + 1)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        combine_(n, 1, k, res, tmp);
        return res;
    }

    private void combine_(int n, int start, int k, List<List<Integer>> res, List<Integer> tmp) {
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (Integer i = start; i <= n; i++) {
            tmp.add(i);
            combine_(n, i + 1, k, res, tmp);
            tmp.remove(i);
        }
    }

    public int totalNQueens(int n) {
        return solveNQueens(n).size();
    }

    List<List<String>> listListStr = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] pan = new char[n][n];
        for (char[] p : pan) {
            Arrays.fill(p, '.');
        }
        solveNQueens_(pan, 0, n);
        return listListStr;
    }

    private void solveNQueens_(char[][] pan, int row, int n) {
        if (row == n) {
            listListStr.add(Arrays.stream(pan).map(String::valueOf).collect(Collectors.toList()));
            return;
        }
        for (int i = 0; i < n; i++) {
            pan[row][i] = 'Q';
            if (isValidNQueens(pan, row, i, n)) {
                solveNQueens_(pan, row + 1, n);
            }
            pan[row][i] = '.';
        }
    }

    private boolean isValidNQueens(char[][] pan, int row, int col, int n) {
        // 列
        for (int i = 0; i < n; i++) {
            if (i != row && pan[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = 1; i < n; i++) {
            // 正对角线
            if (row - i >= 0 && col - i >= 0 && pan[row - i][col- i] == 'Q') {
                return false;
            }
            if (row + i < n && col + i < n && pan[row + i][col + i] == 'Q') {
                return false;
            }
            // 反对角线
            if (row - i >= 0 && col + i < n && pan[row - i][col + i] == 'Q') {
                return false;
            }
            if (row + i < n && col - i >= 0 && pan[row + i][col - i] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        solveSudoku_(board, 0);
    }


    private boolean solveSudoku_(char[][] board, int n) {
        if (n >= 9 * 9) return true;
        int row = n / 9, col = n % 9;
        if (board[row][col] != '.') {
            return solveSudoku_(board, n + 1);
        }
        for (int i = 1; i <= 9; i++) {
            board[row][col] = (char) (i + '0');
            if (isValidSudoku(board) && solveSudoku_(board, n + 1)) {
                return true;
            }
        }
        board[row][col] = '.';
        return false;
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[] flag = new boolean[10];
        // 查看每行
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(flag, false);
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.' ) {
                    int n = board[i][j] - '0';
                    if (flag[n])
                        return false;
                    else flag[n] = true;
                }
            }
        }

        // 查看每列
        for (int i = 0; i < board[0].length; i++) {
            Arrays.fill(flag, false);
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != '.' ) {
                    int n = board[j][i] - '0';
                    if (flag[n])
                        return false;
                    else flag[n] = true;
                }
            }
        }
        // 3X3

        for (int i = 0; i < board.length; i+= 3) {
            for (int j = 0; j < board[0].length; j+= 3) {
                Arrays.fill(flag, false);
                for (int k = i; k < i + 3; k++) {
                    for (int t = j; t < j + 3; t++) {
                        if (board[k][t] != '.' ) {
                            int n = board[k][t] - '0';
                            if (flag[n])
                                return false;
                            else flag[n] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
