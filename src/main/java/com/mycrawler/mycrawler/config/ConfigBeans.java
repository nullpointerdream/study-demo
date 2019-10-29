package com.mycrawler.mycrawler.config;

import org.elasticsearch.client.transport.TransportClient;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


import java.net.InetAddress;
import java.net.UnknownHostException;

//@Configuration("meta")
public class ConfigBeans {

  // @Value("${MONGODB_URL}")
   private String MONGODB_URL="127.0.0.1";
  // @Value("${MONGODB_PORT}")
   private Integer MONGODB_PORT=27017;

   private MongoClient mongoClient = null;

   private String es_name = "elasticsearch";
   private String[] es_ip = {"127.0.0.1"};
   private int es_port = 9300;

  // @Bean
   public MongoClient mongoClient(){
     MongoClientOptions options = MongoClientOptions.builder().connectTimeout(10000).socketTimeout(10000).build();
     mongoClient = new MongoClient(new ServerAddress(MONGODB_URL, MONGODB_PORT), options);
     return mongoClient;
   }

    //@Bean
    public TransportClient esClient(){
        TransportClient client=null;
        if(es_name != null && !"".equals(es_name)){
            Settings settings = Settings.builder().put("cluster.name", es_name).build();
            client = new PreBuiltTransportClient(settings);
        }else{
            client = new PreBuiltTransportClient(Settings.EMPTY);
        }
        try {
            for (String ip : es_ip) {
                client.addTransportAddress(new TransportAddress(InetAddress.getByName(ip), es_port));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public TransportClient client() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;

    }


}
