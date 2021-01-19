package com.mycrawler.mycrawler.design.composite.yes.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-10-26 10:57
 **/
@Data
public class TreeNode {
    private Long treeId;            //规则树ID
    private Long treeNodeId;        //规则树节点ID
    private Integer nodeType;       //节点类型；1子叶、2果实
    private String nodeValue;       //节点值[nodeType=2]；果实值
    private String ruleKey;         //规则Key
    private String ruleDesc;        //规则描述
    private List<TreeNodeLink> treeNodeLinkList; //节点链路
}
