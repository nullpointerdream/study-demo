package com.mycrawler.mycrawler.design.composite.yes.logic;


import com.mycrawler.mycrawler.design.composite.yes.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 *让每一个提供决策能力的节点都必须实现此接口，保证统一性。
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     *
     * @param matterValue          决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下一个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLink> treeNodeLineInfoList);

    /**
     * 获取决策值
     *
     * @param decisionMatter 决策物料
     * @return 决策值
     */
    String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);

}