package com.mycrawler.mycrawler.design.composite.yes.engine.impl;


import com.mycrawler.mycrawler.design.composite.yes.aggregates.TreeRich;
import com.mycrawler.mycrawler.design.composite.yes.engine.EngineBase;
import com.mycrawler.mycrawler.design.composite.yes.vo.EngineResult;
import com.mycrawler.mycrawler.design.composite.yes.vo.TreeNode;

import java.util.Map;

public class TreeEngineHandle extends EngineBase {

    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        // 决策流程
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId, decisionMatter);
        // 决策结果
        return new EngineResult(userId, treeId, treeNode.getTreeNodeId(), treeNode.getNodeValue());
    }

}