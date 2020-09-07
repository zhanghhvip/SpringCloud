package com.aihaokeji.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class BJProxyProcessor implements PageProcessor {
    private  String url = "http://www.89ip.cn/";
    @Autowired
    private RedisPipeline redisPipeline;
    //定时任务启动方法
    @Scheduled(cron = "0  0   17  ?   *  2-6")
    public void process(){
        //创建下载器
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
        Spider.create(new BJProxyProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
//                .thread(10)//多线程
//                .setDownloader(httpClientDownloader)
                .addPipeline(this.redisPipeline)
//                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .run();
    }
    //设置请求头
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(10000)
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
//
    public Site getSite() {
        return site;
    }
    //解析页面
    public void process(Page page) {
        //当前页数
        int cur_page =Integer.valueOf(page.getHtml().xpath("//div[@id=layui-laypage-1]/a[2]/text()").toString()) ;
//        System.out.println(cur_page);
        //当前页内容
        List<String> proxyes = page.getHtml().xpath("//table[@class=layui-table]/tbody/tr").all();
        if(proxyes.size()>0){
            for(String proxy :proxyes ){
//                System.out.println("============");
                proxy = proxy .replaceAll("\r|\n", "");
                String pattern = "<td> (.*?) </td>  <td> (.*?) </td> ";
                // 创建 Pattern 对象
                Pattern r = Pattern.compile(pattern);
                // 现在创建 matcher 对象
                Matcher m = r.matcher(proxy);
                if (m.find( )) {
                    String ip_port = m.group(1)+":"+m.group(2);
                    page.putField("ip_port",ip_port);
//                    System.out.println("Found value: " + ip_port);
                }

            }
            //添加下一页
            if(cur_page<10){
                page.addTargetRequest((cur_page+1)+".html");
            }
        }
    }
}
