package com;

import cn.hutool.poi.excel.ExcelReader;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author chenjl40
 * @Date 2024/5/17 15:07
 */
public class ExcelDD {
    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader("C:\\Users\\chenjl40\\Downloads\\products (1).xls",0);
        List<Map<String, Object>> maps = excelReader.readAll();
        Map<String, String> pnmap = new HashMap<>();
        for (Map<String, Object> map : maps) {
            pnmap.put(map.get("_ManufacturerCode").toString(),map.get("_CategoryName").toString());
        }
        System.out.println(JSON.toJSONString(pnmap));


    }
}
