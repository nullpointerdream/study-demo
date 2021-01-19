package com.mycrawler.mycrawler.design.composite.yes.vo;

import lombok.Data;

/**
 * 树节点链接链路
 * 规则树线信息
 */
@Data
public class TreeNodeLink {

    private Long nodeIdFrom;        //节点From
    private Long nodeIdTo;          //节点To
    private Integer ruleLimitType;  //限定类型；1:等于;2:大于;3:小于;4:大于等于;5 小于等于;6:enum[枚举范围]
    private String ruleLimitValue;  //限定值


}
