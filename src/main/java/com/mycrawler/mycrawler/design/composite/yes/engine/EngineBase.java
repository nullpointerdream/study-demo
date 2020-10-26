package com.mycrawler.mycrawler.design.composite.yes.engine;

import com.mycrawler.mycrawler.design.composite.yes.aggregates.TreeRich;
import com.mycrawler.mycrawler.design.composite.yes.logic.LogicFilter;
import com.mycrawler.mycrawler.design.composite.yes.vo.EngineResult;
import com.mycrawler.mycrawler.design.composite.yes.vo.TreeNode;
import com.mycrawler.mycrawler.design.composite.yes.vo.TreeRoot;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @program: study-demo
 * @description: 基础决策引擎功能
 * @author: chenjiale
 * @create: 2020-10-26 11:13
 **/
@Slf4j
public abstract class EngineBase extends EngineConfig implements IEngine  {
    @Override
    public abstract EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter);


    protected TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId, String userId, Map<String, String> decisionMatter) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        // 规则树根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNodeInfo = treeNodeMap.get(rootNodeId);
        //节点类型[NodeType]；1子叶、2果实
        while (treeNodeInfo.getNodeType().equals(1)) {
            String ruleKey = treeNodeInfo.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(treeId, userId, decisionMatter);
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLinkList());
            treeNodeInfo = treeNodeMap.get(nextNode);
            log.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), userId, treeId, treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNodeInfo;
    }
}
