package com.louis.test.n200.list;

import org.junit.Test;

import java.util.*;

public class ListSolutionN200 {

    @Test
    public void test1() {
        System.out.println(getRow_119(4).toString());
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
