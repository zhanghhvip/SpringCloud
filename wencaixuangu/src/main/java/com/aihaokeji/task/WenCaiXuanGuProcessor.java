package com.aihaokeji.task;


import com.aihaokeji.downloader.MySeleniumDownloader;
import com.aihaokeji.entity.Wencaixuangu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WenCaiXuanGuProcessor  implements PageProcessor {

//    @Value(value = "${chrome.path}")
//    private String path;

    @Value(value = "${chrome.dirverpath}")
    private String dirverpath;
    @Value(value = "${chrome.url")
    private  String url;

    @Autowired
    private MysqlPipeline mysqlPipeline;



    //创建任务
    @Scheduled(cron = "0 */2 * ? * 1-5")
    public void start_Task(){
        System.setProperty("webdriver.chrome.driver",dirverpath );
        //创建下载器
//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
        Spider.create(new WenCaiXuanGuProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
//                .thread(10)//多线程
//                .setDownloader(new SeleniumDownloader(dirverpath))
                .setDownloader(new MySeleniumDownloader(dirverpath))
                .addPipeline(this.mysqlPipeline)
//                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .run();
    }
    //设置请求头
    private Site site = Site.me()
            .setSleepTime(1000)
            .setTimeOut(10000)
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .addHeader("Connection", "keep-alive")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
            .addHeader( "User-Agent",  "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
            .addHeader("Upgrade-Insecure-Requests", "0");
    //
    @Override
    public Site getSite() {
        return site;
    }
    //解析页面
    @Override
    public void process(Page page) {
        String rawText = page.getRawText();
        Document doc = Jsoup.parse(rawText);
        Elements elements = doc.body().select("div.static_con_outer").select("tbody").select("div.em").select("a");
        System.out.println("元素个数："+elements.size());
        if(elements.size()>0){
            List<Wencaixuangu> list = new ArrayList<>(elements.size());
            for(Element element : elements){
                Wencaixuangu wencaixuangu = new Wencaixuangu();
                String href =  element.attr("href");
                Integer index = href.lastIndexOf("=")+1;
                String code = href.substring(index);
                String name = element.text();
                LocalDate localDate = LocalDate.now();
                wencaixuangu.setCode(code);
                wencaixuangu.setName(name);
                wencaixuangu.setDate(localDate);
                list.add(wencaixuangu);
                System.out.println(code);
                System.out.println(name);
            }
            page.putField("list",list);
        }
        return;

    }


    }
