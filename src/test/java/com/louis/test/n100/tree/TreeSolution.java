package com.louis.test.n100.tree;

import com.louis.test.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeSolution {

    public boolean isSameTree_100(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null) return false;
        if (q == null) return false;
        return p.val == q.val && isSameTree_100(p.left, q.left) && isSameTree_100(p.right, q.right);
    }

    TreeNode prev = null, first = null, second = null;
    public void recoverTree_99(TreeNode root) {
        findSwap_99(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void findSwap_99(TreeNode root) {
        if (root == null) return;
        findSwap_99(root.left);
        if (prev != null) {
            if (first == null && prev.val > root.val) {
                first = prev;
            }
            if (first != null && prev.val > root.val) {
                second = root;
            }
        }
        prev = root;
        findSwap_99(root.right);
    }

    public boolean isValidBST_98(TreeNode root) {
        return isValidBST_98_1(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST_98_1(TreeNode root, long minValue, long maxValue) {
        if (root == null) return true;
        if (minValue < root.val && root.val < maxValue) {
            return isValidBST_98_1(root.left, minValue, root.val) && isValidBST_98_1(root.right, root.val, maxValue);
        } else {
            return false;
        }
    }

    public int numTrees_96(int n) {
        // 左子树的数量 X 右子树的数量
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) { // 几个几点
            for (int j = 1; j <= i; j++) { // 第几个节点为root
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public List<TreeNode> generateTrees_95(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees_95_1(1, n);
    }

    private List<TreeNode> generateTrees_95_1(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        // 挑选一个座位root节点
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTrees_95_1(start, i - 1);
            List<TreeNode> rights = generateTrees_95_1(i + 1, end);

            for (TreeNode l : lefts) {
                for (TreeNode r : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    allTrees.add(root);
                }
            }
        }
        return allTrees;
    }

    public List<Integer> inorderTraversal_94(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal_94_recursive(root, res);
        return res;
    }

    private void inorderTraversal_94_recursive(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorderTraversal_94_recursive(root.left, res);
        res.add(root.val);
        inorderTraversal_94_recursive(root.right, res);
    }

    private void inorderTraversal_94_iterate(TreeNode root, List<Integer> res) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        while (!stack.isEmpty()) {
            node = stack.pop();
            res.add(node.val);
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}
