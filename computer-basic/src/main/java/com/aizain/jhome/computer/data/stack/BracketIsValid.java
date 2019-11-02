package com.aizain.jhome.computer.data.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * BracketIsValid
 *
 * @author Zain
 * @date 2019/11/2
 */
public class BracketIsValid {

    private static final String ONE = "()";
    private static final String TWO = "[]";
    private static final String THREE = "{}";

    public boolean isValid(String s) {
        if (s == null) {
            return true;
        }
        s = s.trim();
        if (s.length() <= 0) {
            return true;
        }
        Stack<String> bracketStack = new Stack<>();
        for (String one : s.split("")) {
            if (bracketStack.empty()) {
                bracketStack.push(one);
                continue;
            }
            String latest = bracketStack.pop();
            if (!isDouble(latest, one)) {
                bracketStack.push(latest);
                bracketStack.push(one);
            }
        }
        return bracketStack.empty();
    }

    public boolean isValidFast(String s) {
        if (s == null) {
            return true;
        }
        Map<Character, Character> convert = new HashMap<>(3);
        convert.put(')', '(');
        convert.put(']', '[');
        convert.put('}', '{');
        Stack<Character> bracketStack = new Stack<>();
        bracketStack.push('?');
        for (int i = 0; i < s.length(); i++) {
            char one = s.charAt(i);
            if (one == ' ') {
                continue;
            }
            if (bracketStack.peek().equals(convert.get(one))) {
                bracketStack.pop();
            } else {
                bracketStack.push(one);
            }
        }
        return bracketStack.size() == 1;
    }

    private boolean isDouble(String first, String second) {
        switch (first + second) {
            case ONE:
            case TWO:
            case THREE:
                return true;
            default:
                return false;
        }
    }

}
