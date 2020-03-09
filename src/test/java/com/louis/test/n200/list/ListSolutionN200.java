package com.louis.test.n200.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSolutionN200 {

    @Test
    public void test1() {
        System.out.println(getRow_119(4).toString());
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
