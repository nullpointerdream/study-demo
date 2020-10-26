package com.mycrawler.mycrawler.design.composite.yes.aggregates;

import com.mycrawler.mycrawler.design.composite.yes.vo.TreeNode;
import com.mycrawler.mycrawler.design.composite.yes.vo.TreeRoot;
import lombok.Data;

import java.util.Map;

/**
 * @program: study-demo
 * @description: 规则树聚合
 * @author: chenjiale
 * @create: 2020-10-26 10:55
 **/
@Data
public class TreeRich {
    private TreeRoot treeRoot;                          //树根信息
    private Map<Long, TreeNode> treeNodeMap;        //树节点ID -> 节点信息

    public TreeRich(TreeRoot treeRoot, Map<Long, TreeNode> treeNodeMap) {
        this.treeRoot = treeRoot;
        this.treeNodeMap = treeNodeMap;
    }
}
