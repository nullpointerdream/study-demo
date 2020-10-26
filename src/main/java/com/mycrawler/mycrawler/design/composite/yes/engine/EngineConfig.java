package com.mycrawler.mycrawler.design.composite.yes.engine;


import com.mycrawler.mycrawler.design.composite.yes.logic.LogicFilter;
import com.mycrawler.mycrawler.design.composite.yes.logic.impl.UserAgeFilter;
import com.mycrawler.mycrawler.design.composite.yes.logic.impl.UserGenderFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *将可提供服务的决策节点配置到map结构中，
 * 对于这样的map结构可以抽取到数据库中，那么就可以非常方便的管理。
 *
 */
public class EngineConfig {

    static Map<String, LogicFilter> logicFilterMap;

    static {
        logicFilterMap = new ConcurrentHashMap<>();
        logicFilterMap.put("userAge", new UserAgeFilter());
        logicFilterMap.put("userGender", new UserGenderFilter());
    }

    public Map<String, LogicFilter> getLogicFilterMap() {
        return logicFilterMap;
    }

    public void setLogicFilterMap(Map<String, LogicFilter> logicFilterMap) {
        this.logicFilterMap = logicFilterMap;
    }

}