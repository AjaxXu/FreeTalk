package com.louis.test.numeric;

import com.louis.test.base.ListNode;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class NumericSolution {
    @Test
    public void test1() {
        System.out.println(multiply("11", "10"));
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = n1 * n2 + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (height[i] > max) {
                max = height[i];
            }
            left[i] = max;
        }
        max = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (height[i] > max) {
                max = height[i];
            }
            right[i] = max;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }
        return res;
    }

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int len = nums.length;
        // 让每个正数i在i - 1的位置上，即nums[i] = i + 1
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        int i = 0;
        for (; i < len; i++) {
            if (nums[i] != i + 1) break;
        }
        return i + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(candidates);
        combinationHelp2(candidates, 0,0, target, tmp, res);
        return new ArrayList<>(res);
    }

    private void combinationHelp2(int[] candidates, int index, int sum, int target, List<Integer> tmp, Set<List<Integer>> res) {
        if (sum == target) {
            List<Integer> t = new ArrayList<>(tmp);
            res.add(t);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            tmp.add(candidates[i]);
            combinationHelp2(candidates, i + 1, sum + candidates[i], target, tmp, res);
            tmp.remove(Integer.valueOf(candidates[i]));
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(candidates);
        combinationHelp(candidates, 0,0, target, tmp, res);
        return res;
    }

    private void combinationHelp(int[] candidates, int index, int sum, int target, List<Integer> tmp, List<List<Integer>> res) {
        if (sum == target) {
            List<Integer> t = new ArrayList<>(tmp);
            res.add(t);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            tmp.add(candidates[i]);
            combinationHelp(candidates, i, sum + candidates[i], target, tmp, res);
            tmp.remove(Integer.valueOf(candidates[i]));
        }
    }

    public String countAndSay(int n) {
        String s = "1";
        while (n-- > 1) {
            StringBuilder sb = new StringBuilder();
            char ch = s.charAt(0);
            int t = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ch) {
                    t++;
                } else {
                    sb.append(t).append(ch);
                    ch = s.charAt(i);
                    t = 1;
                }
            }
            sb.append(t).append(ch);
            s = sb.toString();
        }
        return s;
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
