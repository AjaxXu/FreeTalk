package com.louis.test.n100.numeric;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class NumericSolution {
    @Test
    public void test1() {
        System.out.println(grayCode_89(2));
    }

    public List<Integer> grayCode_89(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        if (n <= 0) return list;
        int i = 0;
        while (i < n) {
            int b = 1 << i++;
            for (int k = list.size() - 1; k >= 0; k--) {
                list.add(b | list.get(k));
            }
        }
        return list;
    }

    public void merge_88(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[--len] = nums1[--m];
            } else {
                nums1[--len] = nums2[--n];
            }
        }
        while (m > 0) {
            nums1[--len] = nums1[--m];
        }
        while (n > 0) {
            nums1[--len] = nums2[--n];
        }
    }

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int v = nums[mid];
            if (v == target) return true;
            if (nums[left] < v) {
                if (nums[left] <= target && target < v) {
                    right = mid -1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > v) {
                if (v < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public int removeDuplicates_1(int[] nums) {
        int index = -1;
        boolean hasTwo = false;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[index] != nums[i]) {
                hasTwo = false;
                nums[++index] = nums[i];
            } else if (nums[index] == nums[i]) {
                if (!hasTwo) {
                    nums[++index] = nums[i];
                    hasTwo = true;
                }
            }
        }
        return index + 1;
    }

    public List<List<Integer>> subsets_78(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> tmp = new ArrayList<>(res);
            for(List<Integer> list : res) {
                ArrayList<Integer> list1 = new ArrayList<>(list);
                list1.add(nums[i]);
                tmp.add(list1);
            }
            res = tmp;
        }
        return res;
    }

    public void sortColors(int[] nums) {
        int p = -1;
        int q = nums.length - 1;
        int k = p + 1;
        while (k < q) {
            switch (nums[k]) {
                case 0:
                    swap(nums, ++p, k++);
                    break;
                case 1:
                    k++;
                    break;
                case 2:
                    swap(nums, k, q--);
            }
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int element = matrix[mid / n][mid % n];
            if (element == target) return true;
            else {
                if (element > target) right = mid - 1;
                else left = mid + 1;
            }
        }
        return false;
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        boolean firstColumnHasZero = false;
        boolean firstRowHasZero = false;
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                firstColumnHasZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstColumnHasZero) {
            for (int[] ints : matrix) {
                ints[0] = 0;
            }
        }
        if (firstRowHasZero) {
            Arrays.fill(matrix[0], 0);
        }
    }

    public int climbStairs(int n) {
        int s0 = 1;
        int s1 = 1;
        int s = 1;
        while (n-- > 1) {
            s = s0 + s1;
            s0 = s1;
            s1 = s;
        }
        return s;
    }

    public int mySqrt(int x) {
        if (x < 2) return x;
        int left = 2, right = x / 2;
        while (left <= right) {
            int mid = (left + right) / 2;
            long num = (long)mid * mid;
            if (num == x) return mid;
            if (num > x) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            carry = digits[i] + carry;
            digits[i] = carry % 10;
            carry = carry / 10;
        }
        if (carry == 0) return digits;
        int[] res = new int[digits.length + 1];
        res[0] = carry;
        System.arraycopy(digits, 0, res, 1, digits.length);
        return res;
    }

    public boolean isNumber(String s) {
        s = s.trim();
        boolean hasE = false;
        boolean hasPoint = false;
        boolean hasNumber = false;
        boolean numberAfterE = false;
        for (int i = 0; i < s.length(); i++) {
            if (isNumber(s.charAt(i))) {
                hasNumber = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {
                if (hasE || hasPoint) return false;
                hasPoint = true;
            } else if (s.charAt(i) == 'e') {
                if (hasE || !hasNumber) {
                    return false;
                }
                hasE = true;
                numberAfterE = false;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return hasNumber && numberAfterE;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public String getPermutation(int n, int k) {
        boolean[] visited = new boolean[n];
        return getPermutation_(n, factorial(n - 1), k, visited);
    }

    /**
     * @param n 剩余的数字个数，递减
     * @param f 每组的排列个数
     */
    private String getPermutation_(int n, int f, int k, boolean[] visited) {
        int off = k % f; //
        int groupId = k / f + (off > 0 ? 1 : 0); // 第几组
        // 在没被访问的数字里查找第groupId个
        int i = 0;
        for (; i < visited.length && groupId > 0; i++) {
            if (!visited[i]) groupId--;
        }
        visited[i - 1] = true;
        if (n > 1) {
            // offset = 0 时，则取第 i 组的第 f 个排列 (全部降序)，否则取第 i 组的第 offset 个排列
            return i + getPermutation_(n - 1, f / (n - 1), off == 0 ? f : off, visited);
        }
        // 最后一数字
        return "" + i;
    }

    private int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int[] tmp = newInterval;
        for(int[] interval : intervals) {
            // interval在newInterval左边或右边
            if (tmp == null || interval[1] < tmp[0]) {
                res.add(interval);
            } else if (tmp[1] < interval[0]) {
                res.add(tmp);
                tmp = null;
                res.add(interval);
            } else {
                // 交界
                tmp[0] = Math.min(tmp[0], interval[0]);
                tmp[1] = Math.max(tmp[1], interval[1]);
            }
        }
        if (tmp != null) {
            res.add(tmp);
        }
        return res.toArray(new int[0][]);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int[] tmp = null;
        for(int[] interval : intervals) {
            if (tmp == null) {
                tmp = interval;
            } else if (tmp[1] < interval[0]) {
                res.add(tmp);
                tmp = interval;
            } else {
                tmp[0] = Math.min(tmp[0], interval[0]);
                tmp[1] = Math.max(tmp[1], interval[1]);
            }
        }
        if (tmp != null) {
            res.add(tmp);
        }
        return res.toArray(new int[0][]);
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > max) max = sum;
            if (sum < 0) sum = 0;
        }
        return max;
    }

    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / myPow_(x, -n);
        } else {
            return myPow_(x, n);
        }
    }

    private double myPow_(double x, int n) {
        if (n == 0) return 1;
        double v = myPow_(x, n / 2);
        if (x % 2 == 0) {
            return v * v;
        } else {
            return x * v * v;
        }
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
