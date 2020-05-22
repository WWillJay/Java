package com.example.demo.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class Solution {

    public int calculate(String s) {
        s.trim();
        char[] chars = s.toCharArray();
        Stack<Object> stack = new Stack<>();

        for (char c : chars) {
            if (")".equals(c)){
                Object pop = stack.pop();
                int res = popRes();
                stack.push(res);
            } else {
                stack.push(c);
            }
        }

        return 0;
    }

    public int popRes(){
        return 0;
    }


    public static void main(String[] args) {

    }
}
