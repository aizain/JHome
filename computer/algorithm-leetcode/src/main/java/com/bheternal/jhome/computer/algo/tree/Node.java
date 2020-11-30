package com.bheternal.jhome.computer.algo.tree;

import java.util.List;

/**
 * Node
 *
 * @author Zain
 * @date 2020/2/23
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
