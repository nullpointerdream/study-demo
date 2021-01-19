package com.mycrawler.mycrawler.design.composite.yes.engine;

import com.mycrawler.mycrawler.design.composite.yes.aggregates.TreeRich;
import com.mycrawler.mycrawler.design.composite.yes.vo.EngineResult;

import java.util.Map;

/**
 *  决策引擎接口定义
 */
public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);

}