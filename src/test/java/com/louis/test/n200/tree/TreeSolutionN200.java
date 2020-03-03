package com.louis.test.n200.tree;

import com.louis.test.base.ListNode;
import com.louis.test.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeSolutionN200 {

    // https://leetcode-cn.com/problems/balanced-binary-tree/submissions/
    public boolean isBalanced_110(TreeNode root) {
        return getHeight_110(root) != -1;
    }

    private int getHeight_110(TreeNode root) {
        if (root == null) return 0;
        int l = getHeight_110(root.left);
        if (l == -1) return -1;
        int r = getHeight_110(root.right);
        if (r == -1 || Math.abs(l - r) > 1) return -1;
        return 1 + Math.max(l, r);
    }

    // https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
    ListNode cur109 = null;
    public TreeNode sortedListToBST_109(ListNode head) {
        int n = 0;
        cur109 = head;
        while (head != null) {
            head = head.next;
            n++;
        }
        return sortedListToBST_109_1(n);
    }

    private TreeNode sortedListToBST_109_1(int size) {
        if (size <= 0) return null;
        TreeNode left = sortedListToBST_109_1(size / 2);
        TreeNode root = new TreeNode(cur109.val);
        cur109 = cur109.next;
        TreeNode right = sortedListToBST_109_1(size - 1 - size / 2);
        root.left = left;
        root.right = right;
        return root;
    }

    // https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
    public TreeNode sortedArrayToBST_108(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBST_108_1(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST_108_1(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST_108_1(nums, start, mid - 1);
        root.right = sortedArrayToBST_108_1(nums, mid + 1, end);
        return root;
    }

    // https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom_107(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<TreeNode> record = new ArrayList<>();
        record.add(root);
        while (!record.isEmpty()) {
            List<Integer> data = new ArrayList<>();
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node: record) {
                data.add(node.val);
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            res.add(0, data);
            record = tmp;
        }
        return res;
    }


    public TreeNode buildTree_106(int[] inorder, int[] postorder) {
        int len = inorder.length - 1;
        return buildTree_106_1(inorder, 0, len, postorder, 0, len);
    }

    private TreeNode buildTree_106_1(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        int n = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == postorder[postEnd]) break;
            n++;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        root.left = buildTree_106_1(inorder, inStart, inStart + n, postorder, postStart, postStart + n);
        root.right = buildTree_106_1(inorder, inStart + n + 2, inEnd, postorder, postStart + n + 1, postEnd - 1);
        return root;
    }

    public TreeNode buildTree_105(int[] preorder, int[] inorder) {
        int len = preorder.length - 1;
        return buildTree_105_1(preorder, 0, len, inorder, 0, len);
    }

    private TreeNode buildTree_105_1(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        int i = inStart;
        for (; i <= inEnd; i++) {
            if (preorder[preStart] == inorder[i]) break;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        root.left = buildTree_105_1(preorder, preStart + 1, preStart + i - inStart, inorder, inStart, i - 1);
        root.right = buildTree_105_1(preorder, preStart + i - inStart + 1, preEnd, inorder, i + 1, inEnd);
        return root;
    }

    public int maxDepth_104(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth_104(root.left), maxDepth_104(root.right));
    }

    public List<List<Integer>> zigzagLevelOrder_103(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<TreeNode> record = new ArrayList<>();
        record.add(root);
        int level = 1;
        while (record.size() > 0) {
            List<TreeNode> tmp = new ArrayList<>();
            List<Integer> tmpRes = new ArrayList<>();
            for (TreeNode node : record) {
                if (level % 2 == 1) {
                    tmpRes.add(node.val);
                } else {
                    tmpRes.add(0, node.val);
                }
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            level++;
            res.add(tmpRes);
            record = tmp;
        }
        return res;
    }

    public List<List<Integer>> levelOrder_102(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<TreeNode> record = new ArrayList<>();
        record.add(root);
        while (record.size() > 0) {
            List<TreeNode> tmp = new ArrayList<>();
            List<Integer> tmpRes = new ArrayList<>();
            for (TreeNode node : record) {
                tmpRes.add(node.val);
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            res.add(tmpRes);
            record = tmp;
        }
        return res;
    }

    public boolean isSymmetric_101(TreeNode root) {
        if (root == null) return true;
        return isSymmetric_101(root.left, root.right);
    }

    private boolean isSymmetric_101(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && isSymmetric_101(left.left, right.right) && isSymmetric_101(left.right, right.left);
    }
}