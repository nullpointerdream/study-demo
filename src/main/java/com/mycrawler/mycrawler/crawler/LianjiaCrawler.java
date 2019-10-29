package com.mycrawler.mycrawler.crawler;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mycrawler.mycrawler.config.ConfigBeans;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: mycrawler
 * @description: 链家爬虫
 * @author: 陈家乐
 * @create: 2018-12-19 13:48
 **/

public class LianjiaCrawler {

    private static String baseUrl="https://bj.lianjia.com/ditiezufang";
    private static String baseHOST="https://bj.lianjia.com";
    private static Map<String,String> map  =new HashMap<>();



    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigBeans configBeans = new ConfigBeans();
        MongoClient mongoClient = configBeans.mongoClient();
        MongoCollection<org.bson.Document> collection = mongoClient.getDatabase("lianjia").getCollection("house");
        Document document = Jsoup.connect(baseUrl).get();
        document.select("#filter > ul:nth-child(3)>li").forEach(a->{//获取地铁线
            if(a.attr("data-id").equals("0")){//跳过 不限
                return;
            }
            map.put(a.text(),baseUrl+a.select("a").attr("href"));
        });

        for (String key : map.keySet()) {//遍历每条地铁线
            String routNum=key;
            String url =map.get(key);
            int page = 1;
            System.out.println(key+"--"+page);
            String myurl = getUrl(page, url);//没有找到相关的内容
            Document mylistdocument = Jsoup.connect(myurl).get();
            String text = mylistdocument.select("span.content__title--hl").first().text();
            int count = Integer.parseInt(text)/30+1;//计算总页数
            while (page<=count){
                    String pageUrl = getUrl(page++, url);
                    Document listdocument = Jsoup.connect(pageUrl).get();
                    listdocument.select("div.content__list--item").forEach(a->{
                    String detailUrl = baseHOST+a.select("a").attr("href");

                    BasicDBObject query = new BasicDBObject("url", detailUrl);
                    long size = collection.count(query);
                    if(size>0){//根据url排重
                        return;
                    }

                    String area = a.select("p.content__list--item--des>a").first().text();
                    Map house=new HashMap();
                    house.put("area",area);
                    house.put("url",detailUrl);
                    house.put("routNum",routNum);
                    try {
                       // Thread.sleep(200);
                        house.put("detail",Jsoup.connect(detailUrl).get().html());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    collection.insertOne(new org.bson.Document(house));

                });
                Thread.sleep(200);


            }
        }




    }

    private static String getUrl(int page,String url){
        return url+"/pg"+page+"#contentList";
    }
}
