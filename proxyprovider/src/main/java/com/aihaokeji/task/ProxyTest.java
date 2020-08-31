package com.aihaokeji.task;

import com.aihaokeji.service.RedisService;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
@Component
public class ProxyTest {
    @Autowired
   private RedisService redisService;

    @Scheduled(cron = "* 58 * * * ?")
    public void test_method(){
        Set set  = redisService.search();
        if(set.size()<=0){
            return;
        }
        //创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");
        //设置请求头消息
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        //2.for循环遍历：
        for (Object str : set) {
            String[]  str_list=( (String)str).split(":");
            String url = str_list[0];
            int port = Integer.valueOf( str_list[1]);

            //设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
            HttpHost proxy = new HttpHost(url,port);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(2000)
                    .setSocketTimeout(2000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response =null;
            try {
                response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    //判断内容
                    if (response.getEntity() != null) {
                        String content = EntityUtils.toString(response.getEntity(), "utf8");
                        System.out.println(content);
                        System.out.println(url+" : "+port+"测试通过");
                    }
                }
            } catch (IOException e) {
//                e.printStackTrace();
                System.out.println(url+" : "+port+"测试失败");
                redisService.desc((String) str);
            }finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        redisService.delete();
        if (httpClient != null){
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
    //获取HttpClient对象
//    CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cn).build();

//    //创建HttpGet强求
//    HttpGet httpGet = new HttpGet(url);
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0"); // 设置请求头消息User-Agent
//                //设置请求信息
//                httpGet.setConfig(this.getConfig());
//                //使用HttpClient发起请求
//                CloseableHttpResponse response = null;
//                try {
//                response = httpClient.execute(httpGet);
//                if (response.getStatusLine().getStatusCode() == 200) {
//                //判断内容
//                if (response.getEntity() != null) {
//                String content = EntityUtils.toString(response.getEntity(), "utf8");
//                return content;
//                }
//                }
//                } catch (IOException e) {
//                e.printStackTrace();
//                }finally {
//                //关闭response
//                if(response!=null){
//                try {
//                response.close();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//                }
//                }
