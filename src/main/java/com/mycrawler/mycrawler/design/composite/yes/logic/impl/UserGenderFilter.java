package com.mycrawler.mycrawler.design.composite.yes.logic.impl;

import com.mycrawler.mycrawler.design.composite.yes.logic.BaseLogic;

import java.util.Map;

public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("gender");
    }

}