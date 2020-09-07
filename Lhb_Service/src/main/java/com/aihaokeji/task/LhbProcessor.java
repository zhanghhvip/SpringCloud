package com.aihaokeji.task;

import com.aihaokeji.entity.LhbRawData;
import com.aihaokeji.entity.Longhubang;
import com.alibaba.fastjson.JSONObject;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Component
public class LhbProcessor implements PageProcessor {
    @Autowired
    private MysqlPipeline mysqlPipeline;
    //今天日期
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String curDate = formatter.format(date);
    //http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=JmMoney,startDate=2020-07-16,endDate=2020-07-16,gpfw=0,js=var%20data_tab_1.html?
    private          String url=  "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=500,page=1,sortRule=-1,sortType=JmMoney,startDate="+curDate+",endDate="+curDate+",gpfw=0,js=var%20data_tab_1.html?";
    //创建任务
    @Scheduled(cron = "0 0 17 ? * 2-6")
    public void start_Task(){
        //创建下载器
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
        Spider.create(new LhbProcessor())
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
        String rawText = page.getRawText();
        String jsonstring =  page.getRawText().substring(rawText.indexOf("["),rawText.lastIndexOf("]")+1);;

        List<LhbRawData> lhbRawData = JSONObject.parseArray(jsonstring, LhbRawData.class);
        List<Longhubang> lhbData =new ArrayList<>(lhbRawData.size());
        for (LhbRawData data : lhbRawData) {
            Longhubang longhubang = new Longhubang();
            longhubang.setCode(data.getSCode()); //代码
            longhubang.setName(data.getSName());    //名称
            longhubang.setClosePrice(data.getClosePrice()); //收盘价
            longhubang.setChangeRatio(data.getChgradio());   //涨跌幅
            longhubang.setTurnoverRatio(data.getDchratio());   //换手率
            longhubang.setLtsz(data.getLtsz()); //流通市值
            longhubang.setLhbNetBuy(data.getJmMoney());    //龙虎榜净买额
            longhubang.setLhbBuy(data.getBmoney());    //龙虎榜买入额
            longhubang.setLhbSell(data.getSmoney());  //龙虎榜卖出额
            longhubang.setLhbDeal(data.getZeMoney()); //龙虎榜成交额
            longhubang.setMarketDeal(data.getTurnover());  //市场总成交额
            longhubang.setNetBuyRatio(data.getJmRate());    //龙虎榜净买额占市场总成交比
            longhubang.setLhbDealRatio(data.getZeRate());   //龙虎榜成交额占市场总成交比
            longhubang.setReason(data.getCtypedes()); //上榜理由
            longhubang.setJd(data.getDP()); //解读
            longhubang.setTradeDate(data.getTdate());   //日期
            lhbData.add(longhubang);
        }
        page.putField("list",lhbData);


    }
}
