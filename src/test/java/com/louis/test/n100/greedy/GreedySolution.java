package com.louis.test.n100.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Louis
 */
public class GreedySolution {

    @Test
    public void test() {
        System.out.println(jump(new int[]{0}));
    }

    List<List<Integer>> listRes = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        permuteUnique_(nums, 0, nums.length - 1);
        return listRes;
    }

    private void permuteUnique_(int[] nums, int start, int end) {
        if (start == end) {
            listRes.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = start; i <= end; i++) {
            if (allowSwap(nums, start, i)) {
                swap(nums, start, i);
                permute_(nums, start + 1, end);
                swap(nums, i, start);
            }
        }
    }

    private boolean allowSwap(int[] nums, int start, int end) {
        int val = nums[end];
        for (int i = start; i < end; i++) {
            if (val == nums[i]) return false;
        }
        return true;
    }


    public List<List<Integer>> permute(int[] nums) {
        permute_(nums, 0, nums.length - 1);
        return listRes;
    }

    private void permute_(int[] nums, int start, int end) {
        if (start == end) {
            listRes.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(nums, start, i);
            permute_(nums, start + 1, end);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public boolean canJump(int[] nums) {
        int end = 0, maxEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxEnd = Math.max(maxEnd, i + nums[i]);
            if (maxEnd <= i) return false;
            if (i == end) {
                end = maxEnd;
            }
        }
        return true;
    }

    public int jump(int[] nums) {
        if (nums.length == 0) return 0;
        int end = 0;
        int step = 0;
        int maxEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxEnd = Math.max(maxEnd, i + nums[i]);
            if (i == end) {
                step++;
                end = maxEnd;
            }
        }
        return step;
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
