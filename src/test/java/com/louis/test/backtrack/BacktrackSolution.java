package com.louis.test.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Louis
 */
public class BacktrackSolution {

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
