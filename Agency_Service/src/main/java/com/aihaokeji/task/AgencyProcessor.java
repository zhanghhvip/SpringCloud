package com.aihaokeji.task;

import com.aihaokeji.entity.Agency;
import com.aihaokeji.entity.AgencyRawData;
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
public class AgencyProcessor implements PageProcessor {
    @Autowired
    private MysqlPipeline mysqlPipeline;
    //今天日期
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String curDate = formatter.format(date);
    //http://data.eastmoney.com/DataCenter_V3/stock2016/DailyStockListStatistics/pagesize=500,page=1,sortRule=-1,sortType=PBuy,startDate=2020-07-16,endDate=2020-07-16,gpfw=0,js=var%20data_tab_1.html
    private  String url=  "http://data.eastmoney.com/DataCenter_V3/stock2016/DailyStockListStatistics/pagesize=500,page=1,sortRule=-1,sortType=PBuy,startDate="+curDate+",endDate="+curDate+",gpfw=0,js=var%20data_tab_1.html";
    //创建任务
    @Scheduled(cron = "0 0 17 ? * 2-6")
    public void start_Task(){
        //创建下载器
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
        Spider.create(new AgencyProcessor())
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
        String jsonstring =  page.getRawText().substring(rawText.indexOf("["),rawText.lastIndexOf("]")+1);

        List<AgencyRawData> agencyRawData = JSONObject.parseArray(jsonstring,AgencyRawData.class);
        List<Agency> agencyData =new ArrayList<>(agencyRawData.size());
        for (AgencyRawData data : agencyRawData) {
            Agency agency = new Agency();
            agency.setCode(data.getSCode()); //代码
            agency.setName(data.getSName());    //名称
            agency.setClosePrice(data.getCPrice()); //收盘价
            agency.setChangeRatio(data.getChgradio());   //涨跌幅
            agency.setTurnoverRatio(data.getTurnRate());   //换手率
            agency.setLtsz(data.getAGSZBHXS()); //流通市值
            agency.setAgencyNetBuy(data.getPBuy());    //机构净买额
            agency.setAgencyBuy(data.getBMoney());    //机构买入额
            agency.setAgencySell(data.getSMoney());  //机构卖出额
            agency.setMarketDeal(data.getTurNover());  //市场总成交额
            agency.setAgencyNetBuyRatio(data.getPBRate());    //机构净买额占市场总成交比
            agency.setBuyAgencyNum(data.getBSL());   //买方机构数
            agency.setSellAgencyNum(data.getSSL());   //卖方机构数
            agency.setReason(data.getCTypeDes()); //上榜理由
            agency.setTradeDate(data.getTDate());   //日期
            agencyData.add(agency);
        }
        page.putField("list",agencyData);


    }
}
