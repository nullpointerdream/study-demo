package com.mycrawler.mycrawler.http;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;

import java.util.concurrent.*;

public class ProductHttpConcurrent {

    public static void main(String[] args) throws InterruptedException {
        String productNumberList = "[\"WMD00000476\",\"ZZITZTATB4JZA94\",\"22WSP15P5V3\",\"88GMY50157882JQ\",\"11TC1MDM90010FH\",\"11TC1MDM900\",\"PAL80002US\",\"ZA5V0229JP\",\"80DU000HUS\",\"ZA940077US\",\"XXTBXTMI400\",\"XXTBXTMI40020VD\",\"80QL00BJAU\",\"82LU004CUS\",\"30FA0021US\",\"10AAA1DTUS\",\"22TP2X1X1C9\",\"10AAA14A00\",\"10AAA1C2USOUT1L\",\"10AXA08G01\",\"82S900P0GE\",\"88IPF501567\",\"88IPF50156782HV\",\"0585AV2\",\"4Y50R20863\",\"TESTING11\",\"TESTING111\",\"82J10018JP\",\"WMD00000476\",\"ZZITZTATB4JZA94\",\"22WSP15P5V3\",\"88GMY50157882JQ\",\"11TC1MDM90010FH\",\"11TC1MDM900\",\"PAL80002US\",\"ZA5V0229JP\",\"80DU000HUS\",\"ZA940077US\",\"XXTBXTMI400\",\"XXTBXTMI40020VD\",\"80QL00BJAU\",\"82LU004CUS\",\"30FA0021US\",\"10AAA1DTUS\",\"22TP2X1X1C9\",\"10AAA14A00\",\"10AAA1C2USOUT1L\",\"10AXA08G01\",\"82S900P0GE\",\"88IPF501567\",\"88IPF50156782HV\",\"0585AV2\",\"4Y50R20863\",\"TESTING11\",\"TESTING111\",\"82J10018JP\"]\n";
        JSONArray o = JSONArray.parseArray(productNumberList);
        CountDownLatch countDownLatch = new CountDownLatch(o.size()*2);
        ExecutorService executorService = new ThreadPoolExecutor(20, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < o.size()*2; i++) {
            int index = i%(o.size()-1);
            String product_number = o.getString(index);
            String url = "https://openapi.lenovouat.com/us/en/pb/get?productNumber=" + product_number + "&tab=3&operatingSystem=";
            int finalI = i;
            executorService.execute(() -> {

                String body = HttpRequest.get(url)
                        .header(Header.ACCEPT_ENCODING, "gzip, deflate, br")//头信息，多个头信息多次调用此方法即可
                        .header(Header.REFERER, "https://www.gl.lenovouat.com/us/en/pb/index.html?mainCode=20R30029US&guid=40b4272c-f8cd-4a73-a297-306640dc5c86")//头信息，多个头信息多次调用此方法即可
                        .timeout(20000)//超时，毫秒
                        .execute().body();
                System.out.println(body);

                countDownLatch.countDown();
            });


        }

        countDownLatch.await();
        executorService.shutdown();
    }
}
