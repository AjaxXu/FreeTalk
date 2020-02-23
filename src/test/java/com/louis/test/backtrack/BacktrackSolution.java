package com.louis.test.backtrack;

import java.util.Arrays;

/**
 * @author Louis
 */
public class BacktrackSolution {
    public void solveSudoku(char[][] board) {
        help(board, 0);
    }

    private boolean help(char[][] board, int n) {
        if (n >= 9 * 9) return true;
        int row = n / 9, col = n % 9;
        if (board[row][col] != '.') {
            return help(board, n + 1);
        }
        for (int i = 1; i <= 9; i++) {
            board[row][col] = (char) (i + '0');
            if (isValidSudoku(board) && help(board, n + 1)) {
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
