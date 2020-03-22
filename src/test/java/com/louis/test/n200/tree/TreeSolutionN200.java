package com.louis.test.n200.tree;

import com.louis.test.base.ListNode;
import com.louis.test.base.TreeNode;
import com.louis.test.base.Node;
import org.junit.Test;

import java.util.*;

public class TreeSolutionN200 {

    @Test
    public void test() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

    }

    // 146: https://leetcode-cn.com/problems/lru-cache/
    class LRUCache {
        class Node {
            int key;
            int val;
            Node prev, next;

            public Node() {}

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        private int mSize;
        private int mCapacity;
        private Node start;
        private Node end;
        Map<Integer, Node> map;
        public LRUCache(int capacity) {
            mSize = 0;
            mCapacity = capacity;
            start = new Node();
            end = new Node();
            start.next = end;
            map = new HashMap<>();
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                moveToHead(node);
                return node.val;
            } else {
                return -1;
            }
        }

        private void moveToHead(Node node) {
            if (node.prev == start) return;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            insertToStart(node);
        }

        private void insertToStart(Node node) {
            Node next = start.next;
            node.next = next;
            next.prev = node;
            start.next = node;
            node.prev = start;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                moveToHead(map.get(key));
                map.get(key).val = value;
            } else {
                Node node = new Node(key, value);
                if (mSize >= mCapacity) {
                    clearCache();
                }
                insertToStart(node);
                mSize++;
                map.put(key, node);
            }
        }

        private void clearCache() {
            while (mSize >= mCapacity) {
                Node tail = end.prev;
                if (tail == start) break;
                tail.prev.next = end;
                end.prev = tail.prev;
                map.remove(tail.key);
                mSize--;
            }
        }
    }

    // https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
    public List<Integer> postorderTraversal_145(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    // https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
    public List<Integer> preorderTraversal_144(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

    // https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
    public int sumNumbers_129(TreeNode root) {
        return sumNumbers_129_1(root, 0);
    }

    private int sumNumbers_129_1(TreeNode root, int p) {
        if (root == null) return p;
        int n = p * 10 + root.val;
        if (root.left == null && root.right == null) return n;
        if (root.left == null) return sumNumbers_129_1(root.right, n);
        if (root.right == null) return sumNumbers_129_1(root.left, n);
        return sumNumbers_129_1(root.left, n) + sumNumbers_129_1(root.right, n);
    }

    // https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
    int maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum_124(TreeNode root) {
        maxPathSum_124_1(root);
        return maxPathSum;
    }

    private int maxPathSum_124_1(TreeNode root) {
        if (root == null)
            return 0;
        int leftSum = Math.max(maxPathSum_124_1(root.left), 0);
        int rightSum = Math.max(maxPathSum_124_1(root.right), 0);
        maxPathSum = Math.max(leftSum + rightSum + root.val, maxPathSum);
        return Math.max(leftSum, rightSum) + root.val;
    }

    // https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
    Node prev117, leftmost117;
    public Node connect_117(Node root) {
        if (root == null) return root;

        leftmost117 = root;
        while (leftmost117 != null) {
            this.prev117 = null;
            Node cur = leftmost117;
            leftmost117 = null;
            while (cur != null) {
                connect_117_1(cur.left);
                connect_117_1(cur.right);
                cur = cur.next;
            }
        }
        return root;
    }

    private void connect_117_1(Node node) {
        if (node != null) {
            if (prev117 != null) {
                prev117.next = node;
            } else {
                leftmost117 = node;
            }
            prev117 = node;
        }
    }

    // https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
    public Node connect_116(Node root) {
        if (root == null) return root;
        List<Node> record = new ArrayList<>();
        record.add(root);
        while (!record.isEmpty()) {
            List<Node> tmp = new ArrayList<>();

            for (int i = 0; i < record.size(); i++) {
                Node node = record.get(i);

                node.next = i + 1 == record.size() ? null : record.get(i + 1);
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            record = tmp;
        }
        return root;
    }

    // https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
    public void flatten_114(TreeNode root) {
        flatten_114_1(root);
    }

    private TreeNode flatten_114_1(TreeNode root) {
        if (root == null) return null;
        TreeNode ltail = flatten_114_1(root.left);
        TreeNode rtail = flatten_114_1(root.right);
        if (root.left != null) {
            ltail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        if (rtail != null) return rtail;
        if (ltail != null) return ltail;
        return root;
    }

    // https://leetcode-cn.com/problems/path-sum-ii/
    public List<List<Integer>> pathSum_113(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        pathSum_113_1(res, data, root, sum);
        return res;
    }

    private void pathSum_113_1(List<List<Integer>> res, List<Integer> data, TreeNode root, int sum) {
        if (root == null) return;
        if (root.left == null && root.right == null && root.val == sum) {
            List<Integer> t = new ArrayList<>(data);
            t.add(root.val);
            res.add(t);
            return;
        }
        data.add(root.val);
        if (root.left != null) {
            pathSum_113_1(res, data, root.left, sum - root.val);
        }
        if (root.right != null) {
            pathSum_113_1(res, data, root.right, sum - root.val);
        }
        data.remove(data.size() - 1);
    }

    // https://leetcode-cn.com/problems/path-sum/
    public boolean hasPathSum_112(TreeNode root, int sum) {
        if (root == null) return false;
        return hasPathSum_112_1(root, sum);
    }

    private boolean hasPathSum_112_1(TreeNode root, int left) {
        if (root == null && left == 0) return true;
        if (root == null) return false;
        if (root.left == null) return hasPathSum_112_1(root.right, left - root.val);
        if (root.right == null) return hasPathSum_112_1(root.left, left - root.val);
        return hasPathSum_112_1(root.left, left - root.val) || hasPathSum_112_1(root.right, left - root.val);
    }

    // https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
    public int minDepth_111(TreeNode root) {
        return minDepth_111_1(root);
    }

    private int minDepth_111_1(TreeNode root) {
        if (root == null) return 0;
        if (root.right == null) {
            return 1 + minDepth_111_1(root.left);
        }
        if (root.left == null) {
            return 1 + minDepth_111_1(root.right);
        }
        int l = minDepth_111_1(root.left);
        int r = minDepth_111_1(root.right);
        return 1 + Math.min(l, r);
    }

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
