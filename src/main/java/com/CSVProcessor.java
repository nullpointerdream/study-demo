package com;

import com.alibaba.fastjson.JSON;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVProcessor {

    public static void main(String[] args) throws Exception {
        String inputFileName = "C:\\Users\\chenjl40\\Desktop\\20号新增mtmOpt产品-new.csv";
        String outputFileName = "C:\\Users\\chenjl40\\Desktop\\20号新增mtmOpt产品-new2.csv";
        Map<String,String> map = FF.main2();

        try (Reader in = new FileReader(inputFileName);
             CSVParser csvParser = new CSVParser(in, CSVFormat.DEFAULT);
             Writer out = new FileWriter(outputFileName);
             CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT)) {
            // 打印CSV头部
            for (CSVRecord csvRecord : csvParser) {
                // 创建一个新的记录列表
                List<String> record = new ArrayList<>(csvRecord.size());
                // 遍历原始记录并复制值
                for (int i = 0; i < csvRecord.size(); i++) {
                    String display = csvRecord.get(i);
                    // 检查是否是country列，假设它是第二列
                    if (i == 1 && !display.contains("disp")) {
                        List<String> displayTO = JSON.parseArray(display, String.class);
                        List<String> displayTO2 =new ArrayList<>();
                        for (String s1 : displayTO) {
                            String[] s2 = s1.split("_");
                            for(int j=1;j<s2.length;j++){
                                s2[j]=map.get(s2[j]);
                            }
                            displayTO2.add(String.join("_", s2));
                        }
                        display=JSON.toJSONString(displayTO2);

                    }
                    record.add(display);
                }
                // 写入修改后的记录
                csvPrinter.printRecord(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}