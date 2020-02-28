package com.louis.test.list;

import com.louis.test.base.ListNode;
import org.junit.Test;

import java.util.*;

public class ListSolution {

    @Test
    public void test() {
        System.out.println(search(new int[]{1}, 1));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> res = new ArrayList<>();
        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            res.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }
        return res;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right - left == 0) return padResult(words[left], maxWidth);

        boolean isLastLine = right == words.length - 1;
        int numSpace = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);

        String space = isLastLine ? " " : blankResult(totalSpace / numSpace);
        int remain = isLastLine ? 0 : totalSpace % numSpace;
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(words[i]).append(space)
                    .append(remain-- > 0 ? " " : "");
        }
        return padResult(sb.toString().trim(), maxWidth);
    }

    private int wordsLength(int left, int right, String[] words) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += words[i].length();
        }
        return sum;
    }

    private String padResult(String word, int maxWidth) {
        return word + blankResult(maxWidth - word.length());
    }

    private String blankResult(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }

    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();
        while (right < words.length && sum + 1 + words[right].length() <= maxWidth) {
            sum += 1 + words[right++].length();
        }
        return right - 1;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int n = 0;
        ListNode l = head;
        while (l.next != null) {
            n++;
            l = l.next;
        }
        k = k % (n + 1);
        k = n + 1 - k;
        l.next = head;
        while (k-- > 0) {
            l = l.next;
        }
        head = l.next;
        l.next = null;
        return head;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int p1 = 0, q1 = 0;
        int p2 = n - 1, q2 = n - 1;
        int num = 1;
        while (true) {
            for (int i = q1; i <= q1; i++) {
                res[p1][i] = num++;
            }
            if (++p1 > p2) break;
            for (int i = p1; i <= p2; i++) {
                res[i][q2] = num++;
            }
            if (--q2 < q1) break;;
            for (int i = q2; i >= q1; i--) {
                res[p2][i] = num++;
            }
            if (--p2 < p1) break;
            for (int i = p2; i >= p1; i--) {
                res[i][q1] = num++;
            }
            if (++q1 > q2) break;
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return res;
        int p1 = 0, q1 = 0;
        int p2 = matrix.length - 1, q2 = matrix[0].length - 1;
        while (true) {
            for (int i = q1; i <= q2; i++) {
                res.add(matrix[p1][i]);
            }
            if (++p1 > p2) break;
            for (int i = p1; i <= p2; i++) {
                res.add(matrix[i][q2]);
            }
            if (--q2 < q1) break;
            for (int i = q2; i >= q1; i--) {
                res.add(matrix[p2][i]);
            }
            if (--p2 < p1) break;
            for (int i = p2; i >= p1; i--) {
                res.add(matrix[i][q1]);
            }
            if (++q1 > q2) break;
        }
        return res;
    }

    public void rotate(int[][] matrix) {
        // 先反对角线，后上下翻转
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - i - 1; j++) {
                swap(matrix, i, j,  matrix[i].length - 1 - j, matrix.length - 1 - i);
            }
        }

        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                swap(matrix, i, j, matrix.length - 1 - i, j);
            }
        }
    }

    private void swap(int[][] matrix, int i, int j, int m, int n) {
        int t = matrix[m][n];
        matrix[m][n] = matrix[i][j];
        matrix[i][j] = t;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                int i = mid;
                while (i >= 0 && nums[i--] == nums[mid]) res[0] = i + 1;
                i = mid;
                while (i < nums.length && nums[i++] == nums[mid]) res[1] = i - 1;
                break;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            // 左边升序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) { // 在左边
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右边升序
                if (nums[mid] <= target && target <= nums[right]) { // 在右边
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        boolean flag = true;
        while (head != null) {
            ListNode temp = head;
            cur.next = temp;
            for (int j = 0; j < k; j++) {
                if (temp == null) {
                    flag = false;
                    break;
                }
                temp = temp.next;
            }
            if (!flag) {
                break;
            }
            temp = head;
            for (int i = 0; i < k; i++) {
                ListNode t = head.next;
                head.next = cur.next;
                cur.next = head;
                head = t;
                temp.next = t;
            }
            cur = temp;
        }
        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head != null && head.next != null) {
            ListNode t = head.next.next;
            cur.next = head.next;
            cur.next.next = head;
            cur = head;
            head = t;
            cur.next = head;
        }
        cur.next = head;
        return dummy.next;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt((ListNode l) -> l.val));
        for (ListNode l1: lists) {
            if (l1 != null) {
                heap.offer(l1);
            }
        }
        ListNode head = null;
        ListNode tail = null;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = tail.next;
            }
            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return head;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode dummy = new ListNode(0);
        dummy.next = lists[0];
        for(int i = 1; i < lists.length; i++) {
            dummy.next = mergeTwoLists(dummy.next, lists[i]);
        }
        return dummy.next;
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        gen("", n, n, res);
        return res;
    }

    private void gen(String s, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left < 0 || right < 0 || left > right) return;
        gen(s + "(", left - 1, right, res);
        gen(s + ")", left, right - 1, res);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode fast = pre;
        ListNode low = pre;
        while (n-- > 0 && fast.next != null) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        return pre.next;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                int l = j + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        List<Integer> t = new ArrayList<>();
                        t.add(nums[i]);
                        t.add(nums[j]);
                        t.add(nums[l]);
                        t.add(nums[r]);
                        res.add(t);
                        l++;
                        r--;
                        while (l < r && nums[l - 1] == nums[l]) l++;
                        while (l < r && nums[r] == nums[r + 1]) r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        int mininal = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) return target;
                else if (sum > target) {
                    if ((sum - target) < mininal) {
                        mininal = sum - target;
                        res = sum;
                    }
                    r--;
                } else {
                    if ((target - sum) < mininal) {
                        mininal = target - sum;
                        res = sum;
                    }
                    l++;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> t = new ArrayList<>();
                    t.add(nums[i]);
                    t.add(nums[l]);
                    t.add(nums[r]);
                    res.add(t);
                    l++;
                    r--;
                    while (l < r && nums[l - 1] == nums[l]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] temp = new int[len];
        int n1 = 0, n2 = 0, n = 0;
        while (n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] <= nums2[n2]) {
                temp[n++] = nums1[n1++];
            } else {
                temp[n++] = nums2[n2++];
            }
        }
        if (n1 < nums1.length) {
            System.arraycopy(nums1, n1, temp, n, nums1.length - n1);
        } else if (n2 < nums2.length) {
            System.arraycopy(nums2, n2, temp, n, nums2.length - n2);
        }
        if (len % 2 == 0) {
            return (temp[(len - 1) / 2] + temp[len / 2]) / 2.0;
        }
        return temp[len / 2];
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x1 = l1 == null ? 0 : l1.val;
            int x2 = l2 == null ? 0 : l2.val;
            temp.next = new ListNode((x1 + x2 + carry) % 10);
            carry = (x1 + x2 + carry) / 10;
            temp = temp.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
}
