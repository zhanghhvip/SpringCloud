package com.aihaokeji.task;

import com.aihaokeji.entity.Wencaixuangu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class WenCaiXuanGuProcessor  implements PageProcessor {

    //http://www.iwencai.com/stockpick/search?typed=1&preParams=&ts=1&f=1&qs=result_rewrite&selfsectsn=&querytype=stock&searchfilter=&tid=stockpick&w=%E5%BC%80%E7%9B%983%E5%88%86%E9%92%9F%E6%8D%A2%E6%89%8B%E7%8E%87%3E2%EF%BC%9B%E5%BC%80%E7%9B%983%E5%88%86%E9%92%9F%E6%88%90%E4%BA%A4%E9%A2%9D%3E5000%E4%B8%87%EF%BC%9B%E5%BC%80%E7%9B%9810%E5%88%86%E9%92%9F%E5%89%8D%E5%A4%8D%E6%9D%83%E6%B6%A8%E5%B9%85%EF%BC%9E3%25%EF%BC%9B%E5%BC%80%E7%9B%98%E5%9C%A8-3%25%E4%BB%A5%E4%B8%8A%EF%BC%9B%E5%BC%80%E7%9B%98%E6%88%90%E4%BA%A4%E9%A2%9D%3E2000%E4%B8%87%EF%BC%9B&queryarea=
    private  String url=  "http://www.iwencai.com/stockpick/search?w=%E5%BC%80%E7%9B%983%E5%88%86%E9%92%9F%E6%8D%A2%E6%89%8B%E7%8E%87%3E2%EF%BC%9B%E5%BC%80%E7%9B%983%E5%88%86%E9%92%9F%E6%88%90%E4%BA%A4%E9%A2%9D%3E5000%E4%B8%87%EF%BC%9B%E5%BC%80%E7%9B%9815%E5%88%86%E9%92%9F%E5%89%8D%E5%A4%8D%E6%9D%83%E6%B6%A8%E5%B9%85%EF%BC%9E3%25%EF%BC%9B%E5%BC%80%E7%9B%98%E5%9C%A8-3%25%E4%BB%A5%E4%B8%8A%EF%BC%9B%E5%BC%80%E7%9B%98%E6%88%90%E4%BA%A4%E9%A2%9D%3E2000%E4%B8%87%EF%BC%9B";
    //创建任务
    @Scheduled(cron = "0 45 9 ? * 1-5")
    public void start_Task(){
        //创建下载器
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
        Spider.create(new WenCaiXuanGuProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
//                .thread(10)//多线程
//                .setDownloader(httpClientDownloader)
//                .addPipeline(this.mysqlPipeline)
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
            .addHeader("Upgrade-Insecure-Requests", "0")
            .addCookie("v","Akur70Y0kDvvKMyDS4cPpFno2uQ24F9u2fQjFr1IJwrh3GWSRbDvsunEs2PO")   ;
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
//            System.out.println(code);
//            System.out.println(name);
            }
            page.putField("list",list);
        }
        return;

    }


    }
