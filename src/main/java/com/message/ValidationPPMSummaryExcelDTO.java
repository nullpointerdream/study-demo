package com.message;

import lombok.Data;

import java.io.Serializable;

/*
 * @description: ValidationPPMResultDTO
 * @author: chenjl40
 * @date: 2022/10/18 9:37
 **/
@Data
public class ValidationPPMSummaryExcelDTO implements Serializable {
    private static final long serialVersionUID = -7135608582148846897L;

    public ValidationPPMSummaryExcelDTO(String key, String keyCount, String percent) {
        this.category = key;
        this.count = keyCount;
        this.percent = percent;
    }



    @Excel(name = "Category")
    private String category;

    @Excel(name = "Number of parts")
    private String count;

    @Excel(name = "Percent")
    private String percent;



}
