package com.heyqing.struct;

import java.util.ArrayList;

/**
 * ClassName:Node
 * Package:com.heyqing.struct
 * Description:
 * 点
 *
 * @Date:2024/10/28
 * @Author:Heyqing
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> next;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        next = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
