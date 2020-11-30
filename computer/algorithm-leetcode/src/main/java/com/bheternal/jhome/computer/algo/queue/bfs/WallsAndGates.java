package com.bheternal.jhome.computer.algo.queue.bfs;

/**
 * WallsAndGates
 * 墙与门
 * <p>
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 * <p>
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
 * <p>
 * 示例：
 * <p>
 * 给定二维网格：
 * <p>
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 * 0  -1 INF INF
 * 运行完你的函数后，该网格应该变成：
 * <p>
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 *
 * @author Zain
 * @date 2019/10/31
 */
public class WallsAndGates {

    private static final String WHITE_ROOM = "INF";

    public void wallsAndGates(int[][] rooms) {

    }

    private static class GraphHandler {

        public Node[] getNextNodes(Node[] nodes, int[][] rooms) {
            return nodes;
        }

    }

    private static class Node {

        private final int value;
        private final Node[] aroundNodes;
        private final int x;
        private final int y;

        public Node(int value, Node[] aroundNodes, int x, int y) {
            this.value = value;
            this.aroundNodes = aroundNodes;
            this.x = x;
            this.y = y;

        }

    }

}
