package com.message;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @description: ValidationPPMResultDTO
 * @author: chenjl40
 * @date: 2022/10/18 9:37
 **/
@Data
public class ValidationPPMResultExcelDTO implements Serializable {
    private static final long serialVersionUID = -7135608582148846897L;

    @Excel(name = "Country")
    private String country;
    @Excel(name = "Sold to")
    private String soldTo;
    @Excel(name = "P/N")
    private String productNumber;
    /**
     * 合同价
     */
    @Excel(name = "Contract price")
    private String contractPrice;
    /**
     * 合同货币
     */
    @Excel(name = "Contract currency")
    private String contractCurrency;

    /**
     * web价
     */
    @Excel(name = "Web price")
    private BigDecimal webPrice;
    /**
     * web货币
     */
    @Excel(name = "Web currency")
    private String webCurrency;

    @Excel(name = "PPM Status")
    private String ppmStatus;

    @Excel(name = "LPC Marketing Description")
    private String lpcMarketingDesc;

    @Excel(name = "Web Marketing Description")
    private String webMarketingDesc;

    @Excel(name = "LPC Sales Status")
    private String lpcSalesStatus;

    @Excel(name = "Web Sales Status")
    private String webSalesStatus;

    @Excel(name = "CC contract item end date")
    private String contractItemEndDateStr;


    @Excel(name = "CC Contract number")
    private String contractNum;

    @Excel(name = "CC contract changed")
    private String contractChangeDateStr;

    /**
     * 阻断原因
     */
    @Excel(name = "inhibitors")
    private String inhibitors;

    @Excel(name = "duplicates")
    private Boolean duplicates;

}
