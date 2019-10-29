package com.mycrawler.mycrawler.crawler;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mycrawler.mycrawler.config.ConfigBeans;
import com.mycrawler.mycrawler.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mycrawler
 * @description: 解析数据
 * @author: 陈家乐
 * @create: 2018-12-19 17:04
 **/
@Slf4j
public class AnalyzerLianjia {
    private static int pageSize=1000;

    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigBeans configBeans = new ConfigBeans();
        MongoClient mongoClient = configBeans.mongoClient();
        TransportClient transportClient = configBeans.client();
        MongoCollection<Document> collection = mongoClient.getDatabase("lianjia").getCollection("house");
        int page=0;
        while (true){
            FindIterable<Document> limit = collection.find().skip(page).limit(pageSize);
            MongoCursor<Document> iterator = limit.iterator();
            if(!iterator.hasNext()){
                break;
            }

            BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
            while (iterator.hasNext()){
                Document user = iterator.next();
                Map<String,Object> map  =new HashMap<>();
                String region = user.get("area").toString();
                String url = user.get("url").toString();
                String routNum = user.get("routNum").toString();
                org.jsoup.nodes.Document detail = Jsoup.parse(user.get("detail").toString());
                String title = detail.select("p.content__title").first().text();
                String desc =null;
                try {
                    desc = detail.select("div#desc").first().html();
                }catch (Exception e){
                }

                List<String>  tags=new ArrayList<>();
                Elements select = detail.select("p.content__aside--tags>i");
                for (Element element : select) {
                    tags.add(element.text());
                }
                Elements house = detail.select("p.content__article__table>span");
                String rentType =getHouse(house,0);
                String houseType =getHouse(house,1);
                Double area =Double.parseDouble(getHouse(house,2).replace("㎡",""));
                List<String> face =getFace(getHouse(house,3));
                int price = Integer.parseInt(detail.select("p.content__aside--title>span").first().text());
                String dateTime_temp = detail.select("div.content__subtitle").first().text();
                String publishTime= null;
                List<String> matches = RegexUtils.getMatches(RegexUtils.REGEX_DATE, dateTime_temp);
                if(matches.size()>0){
                    publishTime=matches.get(0);
                }
                List<String> staticMatches = RegexUtils.getMatches("-(.*?)+\\d+m", detail.select("div#around").text());
                String longitude = RegexUtils.getMatches("longitude: .*", detail.html()).get(0).split("'")[1];
                String latitude = RegexUtils.getMatches("latitude: .*", detail.html()).get(0).split("'")[1];
                List<Map<String,Object>> station=getStation(staticMatches);
                map.put("region",region);
                map.put("url",url);
                map.put("routNum",routNum);
                map.put("title",title);
                map.put("desc",desc);
                map.put("rentType",rentType);
                map.put("houseType",houseType);
                map.put("face",face);
                map.put("area",area);
                map.put("price",price);
                map.put("publishTime",publishTime);
                map.put("tags",tags);
                map.put("station",station);
                map.put("location",latitude+","+longitude);
                bulkRequest.add(transportClient.prepareIndex("house", "_doc").setSource(map));

            }

            BulkResponse bulkItemResponses = bulkRequest.execute().actionGet();
           // log.error(bulkItemResponses.buildFailureMessage());

            page+=pageSize;
        }

    }

    private static List<Map<String, Object>> getStation(List<String> staticMatches) {
        List<Map<String, Object>> list = new ArrayList<>();
        for(String one :staticMatches){
            String[] split = one.replace("m", "").replace("- ", "").split(" ");
            Map<String, Object> map =new HashMap<>();
            map.put("station",split[0]);
            map.put("distance",Integer.parseInt(split[1]));
            list.add(map);
        }
        return list;
    }

    private static List<String> getFace(String house) {
        List<String> list = new ArrayList<>();
        for (String s : house.split(" ")) {
            list.add(s);
        }
        return list;
    }

    private static String getHouse(Elements elements,int cout){
        for(int i=0;i<elements.size();i++){
            if(i==cout){
                return elements.get(i).text();
            }
        }
        return null;
    }
}
