package com.message;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @create: 2020-05-19 16:46
 **/
public class Main {

    public static void main(String[] args) {
        ValidationPPMResultExcelDTO rows = new ValidationPPMResultExcelDTO();
        rows.setContractCurrency("aaa");
        ValidationPPMSummaryExcelDTO rows2 = new ValidationPPMSummaryExcelDTO("2","2","3");
        List<ValidationPPMSummaryExcelDTO> list = new ArrayList<>();
        List<ValidationPPMResultExcelDTO> list2 = new ArrayList<>();
        list2.add(rows);
        list.add(rows2);
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTest5.xlsx");
        setSheet(writer,ValidationPPMResultExcelDTO.class);
        setSheet(writer,ValidationPPMSummaryExcelDTO.class);
        writer.setSheet("aaa");
        writer.write(list2);
        writer.setSheet("bbb");
        writer.write(list);
        Workbook workbook = writer.getWorkbook();
        //移除第一个默认的sheet
        workbook.removeSheetAt(0);
        writer.close();
    }

    private static void setSheet(ExcelWriter writer,  Class classz) {

        Field[] declaredFields = classz.getDeclaredFields();
        for (Field fd : declaredFields) {
            if (fd.isAnnotationPresent(Excel.class)) {
                Excel annotation = fd.getAnnotation(Excel.class);
                writer.addHeaderAlias(fd.getName(), annotation.name());
            }
        }
    }


}
