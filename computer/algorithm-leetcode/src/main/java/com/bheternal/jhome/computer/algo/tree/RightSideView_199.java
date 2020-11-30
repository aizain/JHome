package com.bheternal.jhome.computer.algo.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * RightSideView_199
 * 199. 二叉树的右视图
 * <p>
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2020/4/22
 */
public class RightSideView_199 {

    private final List<Integer> rightSideViewList = new LinkedList<>();
    int maxDeep = 0;

    /**
     * 审题：
     * 1 节点可能为空
     * 2 不能只取右节点
     * 3 树的深度不同，节点取得不同
     * <p>
     * 思路：
     * 1 没思路，直接看题解
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        // return dfsTraverseSolution(root);
        // return dfsRecursiveSolution(root);
        // return bfsTraverseSolution(root);
        return dfsRecursiveFastSolution(root);
    }

    /**
     * dfs递归快速
     *
     * @param root
     * @return
     */
    private List<Integer> dfsRecursiveFastSolution(TreeNode root) {
        dfsHelper(root, 0);
        return rightSideViewList;
    }

    private void dfsHelper(TreeNode node, int deep) {
        // 1 终结
        if (Objects.isNull(node)) {
            return;
        }
        // 2 当前（同深度取最右，没有的话依次往左取）
        if (maxDeep == deep) {
            rightSideViewList.add(node.val);
            maxDeep++;
        }
        // 3 子问题
        dfsHelper(node.right, deep + 1);
        dfsHelper(node.left, deep + 1);
        // 4 整理
        // 5 清理
    }

    /**
     * bfs迭代
     *
     * @param root
     * @return
     */
    private List<Integer> bfsTraverseSolution(TreeNode root) {
        // 1 边界
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        // 2 数据
        Map<Integer, Integer> deepMap = new HashMap<>(32);
        LinkedList<TreeNodeWithDeep> bfsQueue = new LinkedList<>();
        bfsQueue.addFirst(new TreeNodeWithDeep(root, 0));
        // 3 bfs
        while (bfsQueue.size() > 0) {
            // 3.1 bfs遍历
            TreeNodeWithDeep node = bfsQueue.removeLast();
            if (Objects.nonNull(node.node.left)) {
                bfsQueue.addFirst(node.getNextLeft());
            }
            if (Objects.nonNull(node.node.right)) {
                bfsQueue.addFirst(node.getNextRight());
            }
            // 3.2 设值
            deepMap.put(node.deep, node.node.val);
        }

        return new LinkedList<>(deepMap.values());
    }

    /**
     * dfs递归
     *
     * @param root
     * @return
     */
    private List<Integer> dfsRecursiveSolution(TreeNode root) {
        Map<Integer, Integer> deepMap = new HashMap<>(32);
        dfsHelper(root, 0, deepMap);
        return new LinkedList<>(deepMap.values());
    }

    private void dfsHelper(TreeNode node, int deep, Map<Integer, Integer> deepMap) {
        // 1 终结
        if (Objects.isNull(node)) {
            return;
        }
        // 2 当前
        deepMap.put(deep, node.val);
        // 3 子问题
        dfsHelper(node.left, deep + 1, deepMap);
        dfsHelper(node.right, deep + 1, deepMap);
        // 4 整理
        // 5 清理
    }

    /**
     * dfs迭代
     *
     * @param root
     * @return
     */
    public List<Integer> dfsTraverseSolution(TreeNode root) {
        // 1 验证参数
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        // 2 准备数据
        Map<Integer, Integer> deepMap = new HashMap<>(32);
        LinkedList<TreeNodeWithDeep> dfsStack = new LinkedList<>();
        dfsStack.push(new TreeNodeWithDeep(root, 0));

        // 3 dsf
        while (dfsStack.size() > 0) {
            // 3.1 dsf 根左右（同深度最右节点，最后被访问）
            TreeNodeWithDeep node = dfsStack.pop();
            if (Objects.nonNull(node.node.right)) {
                dfsStack.push(node.getNextRight());
            }
            if (Objects.nonNull(node.node.left)) {
                dfsStack.push(node.getNextLeft());
            }

            // 3.2 更新深度映射节点
            deepMap.put(node.deep, node.node.val);
        }

        // 4 制作结果数据
        return new LinkedList<>(deepMap.values());
    }

    static class TreeNodeWithDeep {
        public TreeNode node;
        public int deep;

        public TreeNodeWithDeep(TreeNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }

        public TreeNodeWithDeep getNextRight() {
            return new TreeNodeWithDeep(node.right, deep + 1);
        }

        public TreeNodeWithDeep getNextLeft() {
            return new TreeNodeWithDeep(node.left, deep + 1);
        }
    }

}
