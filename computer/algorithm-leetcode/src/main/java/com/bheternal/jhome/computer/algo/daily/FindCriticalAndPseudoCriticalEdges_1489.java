package com.bheternal.jhome.computer.algo.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * FindCriticalAndPseudoCriticalEdges
 * 1489. 找到最小生成树里的关键边和伪关键边
 * hard
 *
 * @author Zain
 * @date 2021/1/21
 */
public class FindCriticalAndPseudoCriticalEdges_1489 {

    /**
     * 审题
     * 1 edges // from, to, weight
     * 2 最小生成树以权重为主
     * 3 关键边就是每个最小生成树都包含的边
     *
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // 结果集
        List<List<Integer>> ret = new ArrayList<>(2);
        List<Integer> keyEdges = new ArrayList<>(edges.length);
        List<Integer> otherEdges = new ArrayList<>(edges.length);
        ret.add(keyEdges);
        ret.add(otherEdges);


        // 点集合
        List<Integer> vertexList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) { vertexList.add(i); }

        // 边集合
        List<Edge> edgeList = new ArrayList<>(edges.length);
        for (int i = 0; i < edges.length; i++) { edgeList.add(new Edge(i, edges[i])); }
        // 边按权重排序 AES
        edgeList.sort(Comparator.comparingInt(a -> a.weight));


        // 1 计算最小生成树权重
        int min = kruskalMstMinWeight(new ArrayList<>(vertexList), edgeList, 0);

        // 2 枚举，检查关键变
        List<Edge> calcList = new ArrayList<>(edgeList);
        for (Edge edge : edgeList) {
            calcList.remove(edge);
            if (min != kruskalMstMinWeight(new ArrayList<>(vertexList), calcList, 0)) {
                keyEdges.add(edge.index);
            }
            calcList = new ArrayList<>(edgeList);
        }

        // 3 枚举，检查伪关键边
        for (Edge edge : edgeList) {
            if (keyEdges.contains(edge.index)) { continue; }
            List<Integer> findList = new ArrayList<>(vertexList);
            findList.set(edge.from, edge.to);
            if (min == kruskalMstMinWeight(findList, edgeList, edge.weight)) {
                otherEdges.add(edge.index);
            }
        }

        return ret;
    }

    public int kruskalMstMinWeight(List<Integer> vertexList, List<Edge> edgeList, int weight) {

        for (Edge edge : edgeList) {
            int from = find(vertexList, edge.from);
            int to = find(vertexList, edge.to);

            if (from != to) {
                weight += edge.weight;
                vertexList.set(from, to);
            }
        }

        return weight;
    }

    /**
     * 并查集，求当前元素所在集合的根元素
     */
    public int find(List<Integer> vertexList, int vertex) {
        if (vertexList.get(vertex) != vertex) {
            vertexList.set(vertex, find(vertexList, vertexList.get(vertex)));
        }

        return vertexList.get(vertex);
    }


    static class Edge {

        public final int index, from, to, weight;

        Edge(int index, int[] triple) {
            this.index = index;
            this.from = triple[0];
            this.to = triple[1];
            this.weight = triple[2];
        }

    }


}
