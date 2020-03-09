package com.louis.test.n200.string;

import org.junit.Test;

public class StringSolutionN200 {

    @Test
    public void test1() {
        System.out.println(isPalindrome_125("A man, a plan, a canal: Panama"));
    }

    // https://leetcode-cn.com/problems/valid-palindrome/
    public boolean isPalindrome_125(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
                left++;
                right--;
            }
        }
        return true;
    }
}
