package com.mycrawler.mycrawler.http;

import cn.hutool.http.HttpUtil;

import java.util.Date;
import java.util.concurrent.*;

public class ProductHttp {

    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date(1660199907315L);
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(date1);
       // String productNumberList = "[\"WMD00000476\",\"ZZITZTATB4JZA94\",\"22WSP15P5V3\",\"88GMY50157882JQ\",\"11TC1MDM90010FH\",\"11TC1MDM900\",\"PAL80002US\",\"ZA5V0229JP\",\"80DU000HUS\",\"ZA940077US\",\"XXTBXTMI400\",\"XXTBXTMI40020VD\",\"80QL00BJAU\",\"82LU004CUS\",\"30FA0021US\",\"10AAA1DTUS\",\"22TP2X1X1C9\",\"10AAA14A00\",\"10AAA1C2USOUT1L\",\"10AXA08G01\",\"82S900P0GE\",\"88IPF501567\",\"88IPF50156782HV\",\"0585AV2\",\"4Y50R20863\",\"TESTING11\",\"TESTING111\",\"82J10018JP\",\"WMD00000476\",\"ZZITZTATB4JZA94\",\"22WSP15P5V3\",\"88GMY50157882JQ\",\"11TC1MDM90010FH\",\"11TC1MDM900\",\"PAL80002US\",\"ZA5V0229JP\",\"80DU000HUS\",\"ZA940077US\",\"XXTBXTMI400\",\"XXTBXTMI40020VD\",\"80QL00BJAU\",\"82LU004CUS\",\"30FA0021US\",\"10AAA1DTUS\",\"22TP2X1X1C9\",\"10AAA14A00\",\"10AAA1C2USOUT1L\",\"10AXA08G01\",\"82S900P0GE\",\"88IPF501567\",\"88IPF50156782HV\",\"0585AV2\",\"4Y50R20863\",\"TESTING11\",\"TESTING111\",\"82J10018JP\"]\n";
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 2; i++) {
            String url = "http://ofp-productadmin-web.docker-ofp-uat.lefile.cn/admin/product/inhibitorProducts4PPMList.jhtm";
            executorService.execute(() -> {
                String json = "{\n" +
                        "    \"soldToList\":[\"1213066999\"],\n" +
                        "    \"pageNum\":1,\n" +
                        "    \"pageSize\":100\n" +
                        "}";
                String post = HttpUtil.post(url, json);
                System.out.println(post);
                countDownLatch.countDown();
            });


        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("执行完毕耗时，");
    }
}
