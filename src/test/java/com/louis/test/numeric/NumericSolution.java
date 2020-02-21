package com.louis.test.numeric;

import com.louis.test.base.ListNode;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class NumericSolution {
    @Test
    public void test1() {
        nextPermutation(new int[]{1});
    }

    public void nextPermutation(int[] nums) {
        int k = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) k = i;
        }
        // 没有找到，则全部翻转
        if (k == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int l = k + 1;
        for (int i = k + 1; i < nums.length; i++) {
            if (nums[i] > nums[k]) l = i;
        }
        int tmp = nums[k];
        nums[k] = nums[l];
        nums[l] = tmp;
        reverse(nums, k + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    public int divide(int dividend, int divisor) {
        int ans = -1;
        int sign = 1;
        if (dividend > 0) {
            sign = -sign;
            dividend = -dividend;
        }
        if (divisor > 0) {
            sign = -sign;
            divisor = -divisor;
        }

        int originDividend = dividend;
        int originDivisor = divisor;

        if (dividend > divisor) return 0;

        dividend -= divisor;
        while (dividend <= divisor) {
            ans = ans + ans;
            divisor += divisor;
            dividend -= divisor;
        }

        ans = ans - divide(originDividend - divisor, originDivisor);
        if (ans == Integer.MIN_VALUE) {
            return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            return sign > 0 ? -ans : ans;
        }
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            String w = s.substring(i, i <= s.length() - 2 ? i + 2 : i + 1);
            if (map.containsKey(w)) {
                res += map.get(w);
                if (w.length() == 2) i++;
            } else {
                res += map.get(s.substring(i, i + 1));
            }
        }
        return res;
    }

    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};  // 罗马数字
        int[] arab = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};  // 阿拉伯数字
        for (int i = 0; num != 0; i++) {
            while (num >= arab[i]) {
                num -= arab[i];
                ans.append(roman[i]);
            }
        }
        return ans.toString();
    }

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int i = 0, j = height.length - 1;
        int m = 0;
        while (i < j) {
            m = Math.max(0, (j - i) * Math.min(height[i], height[j]));
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return m;
    }

    public int reverse(int x) {
        boolean sign = x > 0;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            if (res > Integer.MAX_VALUE / 10) return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return sign ? res : -res;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                res[0] = map.get(nums[i]);
                res[1] = i;
                return res;
            } else {
                map.put(target - nums[i], i);
            }
        }
        return res;
    }
}
