package com.heyqing.struct;

import java.util.HashMap;
import java.util.HashSet;

/**
 * ClassName:Graph
 * Package:com.heyqing.struct
 * Description:
 * 图结构
 *
 * @Date:2024/10/28
 * @Author:Heyqing
 */
public class Graph {

    /**
     * 点集
     */
    public HashMap<Integer, Node> nodes;

    /**
     * 边集
     */
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<Integer, Node>();
        edges = new HashSet<Edge>();
    }
}
