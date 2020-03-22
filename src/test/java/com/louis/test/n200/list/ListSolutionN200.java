package com.louis.test.n200.list;

import com.louis.test.base.ListNode;
import org.junit.Test;

import java.util.*;

public class ListSolutionN200 {

    @Test
    public void test1() {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n1 = sortList_148(n1);
        while (n1 != null) {
            System.out.println(n1.val);
            n1 = n1.next;
        }
    }

    // https://leetcode-cn.com/problems/sort-list/
    public ListNode sortList_148(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        ListNode l2 = slow.next;
        if (l2 == null) return head;
        slow.next = null;
        ListNode l1 = sortList_148(head);
        l2 = sortList_148(l2);
        ListNode l3 = dummy;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    l3.next = l1;
                    l1 = l1.next;
                } else {
                    l3.next = l2;
                    l2 = l2.next;
                }
            } else if (l1 != null) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        return dummy.next;
    }

    // https://leetcode-cn.com/problems/insertion-sort-list/
    public ListNode insertionSortList_147(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode prev;
        while (head != null && head.next != null) {
            if (head.val > head.next.val) {
                ListNode temp = head.next;
                head.next = head.next.next;
                prev = dummy;
                while (prev.next.val < temp.val) {
                    prev = prev.next;
                }
                temp.next = prev.next;
                prev.next = temp;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    // https://leetcode-cn.com/problems/reorder-list/
    public void reorderList_143(ListNode head) {
        if (head == null) return;
        ListNode p = head;
        ListNode q = head.next;
        while (q != null) {
            p = p.next;
            q = q.next;
            if (q != null) {
                q = q.next;
            }
        }

        q = p.next;
        p.next = null;

        ListNode l2 = null;
        while (q != null) {
            ListNode t = q.next;
            q.next = l2;
            l2 = q;
            q = t;
        }
        ListNode tail = new ListNode(-1);
        while (head != null || l2 != null) {
            if (head != null) {
                tail.next = head;
                head = head.next;
                tail = tail.next;
            }
            if (l2 != null) {
                tail.next = l2;
                l2 = l2.next;
                tail = tail.next;
            }
        }
    }

    // https://leetcode-cn.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle_142(ListNode head) {
        ListNode one = head;
        ListNode two = head;
        while (two != null) {
            if (two.next == null) {
                return null;
            }
            one = one.next;
            two = two.next.next;
            if (one == two) {
                two = head;
                while (one != two) {
                    one = one.next;
                    two = two.next;
                }
                return one;
            }
        }
        return null;
    }

    // https://leetcode-cn.com/problems/linked-list-cycle/
    public boolean hasCycle_141(ListNode head) {
        ListNode one = head;
        ListNode two = head;
        while (two != null) {
            if (two.next == null) {
                return false;
            }
            one = one.next;
            two = two.next.next;
            if (one == two) return true;
        }
        return false;
    }

    // https://leetcode-cn.com/problems/copy-list-with-random-pointer/
    public Node copyRandomList_138(Node head) {
        Map<Node, Node> origin2Copy = new HashMap<>();
        Node tmp = head;
        Node start = null;
        Node prev = null;
        while (tmp != null) {
            Node copy = new Node(tmp.val);
            if (start == null) {
                start = copy;
            }
            if (prev != null) {
                prev.next = copy;
            }
            prev = copy;
            origin2Copy.put(tmp, copy);
            tmp = tmp.next;
        }

        while (head != null) {
            if (head.random != null) {
                origin2Copy.get(head).random = origin2Copy.get(head.random);
            }
            head = head.next;
        }
        return start;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // https://leetcode-cn.com/problems/word-ladder/
    public int ladderLength_127(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> dict = new HashSet<>(wordList);
        queue.add(beginWord);
        int len = 0;
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            Set<String> tempSet = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                if (s.equals(endWord)) return len;
                tempSet.addAll(getNeighbors_127(s, dict));
            }
            queue.addAll(tempSet);
        }
        return 0;
    }

    private List<String> getNeighbors_127(String node, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chs = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch)
                    continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }

    // https://leetcode-cn.com/problems/word-ladder-ii/
    public List<List<String>> findLadders_126(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return ans;
        }
        bfs_126(beginWord, endWord, wordList, ans);
        return ans;
    }

    private void bfs_126(String beginWord, String endWord, List<String> wordList, List<List<String>> ans) {
        Queue<List<String>> queue = new LinkedList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        // 队列中加入当前路径
        queue.offer(path);
        boolean isFound = false;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Set<String> dict = new HashSet<>(wordList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> p = queue.poll();
                // 取出路径中的最后一个单词
                String temp = p.get(p.size() - 1);
                // 一次性得到所有的下一个的节点
                List<String> neighbors =  getNeighbors_126(temp, dict);
                for (String neighbor: neighbors) {
                    //只考虑之前没有出现过的单词
                    if (!visited.contains(neighbor)) {
                        // 到达结束单词
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                            p.add(neighbor);
                            ans.add(new ArrayList<>(p));
                            p.remove(p.size() - 1);
                        }
                        // 加入当前单词
                        p.add(neighbor);
                        queue.offer(new ArrayList<>(p));
                        p.remove(p.size() - 1);
                        subVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(subVisited);
            if (isFound) {
                break;
            }
        }
    }

    private List<String> getNeighbors_126(String node, Set<String> dict) {
        List<String> res = new ArrayList<String>();
        char[] chs = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch)
                    continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }


    // https://leetcode-cn.com/problems/triangle/
    public int minimumTotal_120(List<List<Integer>> triangle) {
        List<Integer> data = new ArrayList<>();
        data.add(0);
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> list = triangle.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                if (j == 0) {
                    data.set(j, data.get(j) + list.get(j));
                } else if (j != list.size() - 1) {
                    data.set(j, Math.min(data.get(j - 1), data.get(j)) + list.get(j));
                } else {
                    data.add(data.get(j - 1) + list.get(j));
                }
            }
        }
        return Collections.max(data);
    }

    // https://leetcode-cn.com/problems/pascals-triangle-ii/
    public List<Integer> getRow_119(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex);
        for (int i = 0; i <= rowIndex; i++) {
            res.add(i, 1);
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }

    // https://leetcode-cn.com/problems/pascals-triangle/
    public List<List<Integer>> generate_118(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> record = new ArrayList<>();
            record.add(1);
            if (i > 1) {
                List<Integer> prev = res.get(res.size() - 1);
                for (int j = 2; j < i; j++) {
                    record.add(prev.get(j - 2) + prev.get(j - 1));
                }
                record.add(1);
            }
            res.add(record);
        }
        return res;
    }
}
