package com;

import com.alibaba.fastjson.JSON;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author chenjl40
 * @Date 2024/6/25 9:29
 */
public class GG {
    public static void main(String[] args) throws Exception {
        Map<String,String> countryMap =new HashMap<>();
        countryMap.put("AU","26d69f18bad80-4b93-9491-0b92c49ca7b");
        countryMap.put("NZ","26d69f18bad80-4b93-9491-0b92c49ca7b");

        countryMap.put("HK","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("ID","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("KR","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("MY","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("PH","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("SG","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("TW","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("TH","58d541febb5cb-48a7-aad2-fedb37ffbc22");
        countryMap.put("VN","58d541febb5cb-48a7-aad2-fedb37ffbc22");


        countryMap.put("AT", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("BE", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("DK", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("FI", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("FR", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("DE", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("IE", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("IT", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("NL", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("NO", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("PT", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("ES", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("SE", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("CH", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");
        countryMap.put("GB", "b5a1bec5b5c9c-4fcb-9173-55050ae4ae32");

        countryMap.put("DZ", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("AO", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("AM", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("AZ", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("BD", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("BY", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("BG", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("HR", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("CZ", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("EG", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("EE", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("GE", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("GH", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("GR", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("HU", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("IL", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("JO", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("KZ", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("KE", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("LV", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("LB", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("LT", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("MK", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("MD", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("MA", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("NG", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("PK", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("PL", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("RO", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("RU", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("SA", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("RS", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("SK", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("SI", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("ZA", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("LK", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("TZ", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("TN", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("TR", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("UA", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("AE", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("UZ", "4b714b0eb0a02-4c0c-aa35-3842c3c3ad8d");
        countryMap.put("JP", "dffe9ed0bd313-4ab5-8e2b-e946df1e1c62");

        countryMap.put("AR", "04677bc1bc641-4b83-aeb5-667f8c40acdf");
        countryMap.put("BR", "04677bc1bc641-4b83-aeb5-667f8c40acdf");
        countryMap.put("CL", "04677bc1bc641-4b83-aeb5-667f8c40acdf");
        countryMap.put("CO", "04677bc1bc641-4b83-aeb5-667f8c40acdf");
        countryMap.put("MX", "04677bc1bc641-4b83-aeb5-667f8c40acdf");
        countryMap.put("PE", "04677bc1bc641-4b83-aeb5-667f8c40acdf");

        countryMap.put("US", "96c4222c-2865-4938-810a-f4edc930603e");
        countryMap.put("CA", "96c4222c-2865-4938-810a-f4edc930603e");
        countryMap.put("RF", "a3e061acb1abf-49aa-9ffd-7f385b3b287d");
        countryMap.put("IN", "9d11f9bfb4c03-48b4-bfc5-bacd3e71b226");

        countryMap.put("BZ", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("BO", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("CR", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("DO", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("EC", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("SV", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("GT", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("HN", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("NI", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("PA", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("PY", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("UY", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");
        countryMap.put("VE", "5400aec7b5872-4f90-be02-b49ac2ddf0ae");


        String inputFilePath = "C:\\Users\\chenjl40\\Desktop\\20号新增mtmOpt产品-1719278836053.csv";
        String outputFilePath = "C:\\Users\\chenjl40\\Desktop\\20号新增mtmOpt产品-new.csv";
        Map<String,String> map = FF.main2();
        try (Reader reader = new FileReader(inputFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(outputFilePath), CSVFormat.DEFAULT.withHeader())) {

            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            try (org.apache.commons.csv.CSVParser csvParser = new org.apache.commons.csv.CSVParser(reader, csvFileFormat)) {

                for (org.apache.commons.csv.CSVRecord record : csvParser) {
                    String display = record.get(1);
                    List<String> displayTO = JSON.parseArray(display, String.class);
                    String country = record.get(2);
                    String s = "B2C_"+countryMap.get(country);
                    boolean flag = false;
                    List<String> displayTO2 =new ArrayList<>();
                    for (String s1 : displayTO) {
                        if(s1.equals(s)){
                            flag=true;
                        }
                    }

                    if(!flag) {
                        csvPrinter.printRecord(record);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
