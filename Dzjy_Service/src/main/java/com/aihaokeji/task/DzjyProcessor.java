package com.aihaokeji.task;


import com.aihaokeji.entity.Dzjy;
import com.aihaokeji.entity.DzjyRawData;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.URLEncoder;
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


import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;


@Component
public class DzjyProcessor implements PageProcessor {
    @Autowired
    private MysqlPipeline mysqlPipeline;
    //今天日期
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String curDate = formatter.format(date);
    private          String url=  "http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?type=DZJYXQ&token=70f12f2f4f091e459a279469fe49eca5&filter=%28Stype=%27EQA%27%29%28TDATE=%5E"+curDate+"%5E%29";
    //创建任务
    @Scheduled(cron = "0 0 17 ? * 1-5")
    public void start_Task(){
        //创建下载器
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
        Spider.create(new DzjyProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
//                .thread(10)//多线程
//                .setDownloader(httpClientDownloader)
                .addPipeline(this.mysqlPipeline)
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
    @Override
    public Site getSite() {
        return site;
    }
    //解析页面
    @Override
    public void process(Page page) {

        String jsonsting =  page.getJson().toString();
        List<DzjyRawData> dzjyRawData = JSONObject.parseArray(jsonsting, DzjyRawData.class);
        List<Dzjy> dzjyData =new ArrayList<>(dzjyRawData.size());
//        Dzjy dzjy = new Dzjy();
        for (DzjyRawData d : dzjyRawData) {
            Dzjy dzjy = new Dzjy();
            dzjy.setCode(d.getSECUCODE());
            dzjy.setName(d.getSNAME());
            dzjy.setChangeRatio(d.getRCHANGE());
            dzjy.setClosePrice(d.getCPRICE());
            dzjy.setPrice(d.getPRICE());
            dzjy.setVolume(d.getTVOL());
            dzjy.setDeal(d.getTVAL());
            dzjy.setZyRatio(d.getZyl());
            dzjy.setDealLtszRatio(d.getCjeltszb());
            dzjy.setBuyAgencyName(d.getBUYERNAME());
            dzjy.setSellAgencyName(d.getSALESNAME());
            dzjy.setTradeDate(d.getTDATE());
            dzjyData.add(dzjy);
        }
        page.putField("list",dzjyData);


    }


}
