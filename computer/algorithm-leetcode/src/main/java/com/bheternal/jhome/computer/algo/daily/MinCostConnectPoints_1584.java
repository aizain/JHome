package com.bheternal.jhome.computer.algo.daily;

/**
 * MinCostConnectPoints_1584
 * 1584. 连接所有点的最小费用
 *
 * 给你一个 points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 *
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 *
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 *
 *
 * 示例 2：
 *
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 *
 *
 * 示例 3：
 *
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 *
 *
 * 示例 4：
 *
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 *
 *
 * 示例 5：
 *
 * 输入：points = [[0,0]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * 所有点 (xi, yi) 两两不同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zain
 * @date 2021/1/19
 */
public class MinCostConnectPoints_1584 {

    public static void main(String[] args) {
        int[][] data = new int[][] {new int[]{0,0},new int[]{2,2},new int[]{3,10},new int[]{5,2},new int[]{7,0}};
        int cost = new MinCostConnectPoints_1584().minCostConnectPoints(data);
        System.out.printf("最小生成距离和 %s %n", cost);
    }


    /**
     * 审题：
     * 1 曼哈顿距离
     * 2 只返回距离和
     * 3 所有点连接即可
     *
     * 思路：
     * 1 无，看题解
     * 2 最小生成树 prim\kruskal
     *
     *
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        return primSolution(points);
    }


    /**
     * prim 最小生成树
     *
     * V 原始节点
     * E 边集合
     * Vnew 最小生成树节点
     * Enew 最小生成树边集合
     *
     *
     * @param points
     * @return
     */
    private int primSolution(int[][] points) {
        if (points.length <= 1) {
            return 0;
        }

        // 初始化 最小生成树、最小距离集合、最小距离和
        // int[][] mst = new int[points.length][2];
        int[] mda = new int[points.length];
        int mds = 0;

        // 随机取一个点开始
        // v [x, y]
        // mst[0] = points[0];
        mda[0] = -1;
        // 初始化最小距离集合
        // O(n-1)
        for (int i = 1; i < points.length; i++) {
            mda[i] = distinct(points[0], points[i]);
        }

        // O( (n-1) * (2*(n-1)) ) = O(n^2)
        for (int i = 1; i < points.length; i++) {
            // 找到距离最小的点
            int min = Integer.MAX_VALUE;
            int minIndex = 1;
            for (int j = 1; j < mda.length; j++) {
                if (mda[j] != -1 && mda[j] < min) {
                    min = mda[j];
                    minIndex = j;
                }
            }
            // 更新最小生成树、最小距离集合、最小距离和
            // mst[i] = points[minIndex];
            mda[minIndex] = -1;
            mds += min;
            // 更新最小距离集合
            for (int j = 1; j < mda.length; j++) {
                if (mda[j] == -1) {
                    continue;
                }
                int d = distinct(points[minIndex], points[j]);
                if (d < mda[j]) {
                    mda[j] = d;
                }
            }

        }

        return mds;
    }

    /**
     * 计算距离
     * 曼哈顿距离
     *
     * @return
     */
    private int distinct(int[] v1, int[] v2) {
        return Math.abs(v1[0] - v2[0]) + Math.abs(v1[1] - v2[1]);
    }


}
