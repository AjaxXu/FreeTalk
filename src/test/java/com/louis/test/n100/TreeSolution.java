package com.louis.test.n100;

import com.louis.test.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeSolution {

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
        inorderTraversal_94_recursive(root.left, res);
        res.add(root.val);
        inorderTraversal_94_recursive(root.right, res);
    }
}
