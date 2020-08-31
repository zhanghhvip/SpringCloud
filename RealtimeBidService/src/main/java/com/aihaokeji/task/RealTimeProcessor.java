package com.aihaokeji.task;


import com.aihaokeji.entity.RealtimeBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Component
public class RealTimeProcessor implements PageProcessor {


    @Autowired
    private MysqlPipeline mysqlPipeline;
    @Autowired
    private RestTemplate restTemplate;

    private  String url_=  "http://hq.sinajs.cn/list=";
    private static   List<String> codelistall ;


    //创建任务
    @Scheduled(cron = "0 20,25 9 ? * 1-5")
    public void start_Task(){
            codelistall = restTemplate.getForObject("http://localhost:8001/code/findall", List.class);
            List<String> codelist = codelistall.subList(0,500);
            codelistall  = codelistall.subList(500,codelistall.size());
            String url = url_+String.join(",",codelist);
//            System.out.println(url);
//            System.out.println(codelistall);
//            System.out.println("   ");

        //创建下载器
//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));

        Spider.create(new RealTimeProcessor())
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
        String rawText = page.getRawText().replaceAll("var hq_str_","").replaceAll("\n","");
        String[] rawlist = rawText.substring(0,rawText.length()-1).split(";");
        List<RealtimeBid> realtimeBidList = new ArrayList<>(rawlist.length);
        for( String  list : rawlist){
            String code = list.substring(0,list.indexOf("="));
            String[] sublist = list.substring(list.indexOf("=")+1).replaceAll("\"","").split(",");
//            System.out.println(Arrays.toString(sublist));
            RealtimeBid realtimeBid = new RealtimeBid();
            realtimeBid.setCode(code);
            realtimeBid.setName(sublist[0]);
            realtimeBid.setTodayOpenPrice(BigDecimal.valueOf(Float.parseFloat(sublist[1])));
            realtimeBid.setYesterdayClosePrice(BigDecimal.valueOf(Float.parseFloat(sublist[2])));
            realtimeBid.setCurPrice(BigDecimal.valueOf(Float.parseFloat(sublist[3])));
            realtimeBid.setTodayHigh(BigDecimal.valueOf(Float.parseFloat(sublist[4])));
            realtimeBid.setTodayLow(BigDecimal.valueOf(Float.parseFloat(sublist[5])));
            realtimeBid.setBuyPrice(BigDecimal.valueOf(Float.parseFloat(sublist[6])));
            realtimeBid.setSellPrice(BigDecimal.valueOf(Float.parseFloat(sublist[7])));
            realtimeBid.setVolume(Long.valueOf(sublist[8]));
            realtimeBid.setDeal(BigDecimal.valueOf(Float.parseFloat(sublist[9])));
            realtimeBid.setBuyOneVolume(Long.valueOf(sublist[10]));
            realtimeBid.setBuyOnePrice(BigDecimal.valueOf(Float.parseFloat(sublist[11])));
            realtimeBid.setBuyTwoVolume( Long.valueOf(sublist[12]));
            realtimeBid.setBuyTwoPrice(BigDecimal.valueOf(Float.parseFloat(sublist[13])));
            realtimeBid.setBuyThreeVolume(Long.valueOf(sublist[14]));
            realtimeBid.setBuyThreePrice(BigDecimal.valueOf(Float.parseFloat(sublist[15])));
            realtimeBid.setBuyFourVolume(Long.valueOf(sublist[16]));
            realtimeBid.setBuyFourPrice(BigDecimal.valueOf(Float.parseFloat(sublist[17])));
            realtimeBid.setBuyFiveVolume(Long.valueOf(sublist[18]));
            realtimeBid.setBuyFivePrice(BigDecimal.valueOf(Float.parseFloat(sublist[19])));
            realtimeBid.setSellOneVolume(Long.valueOf(sublist[20]));
            realtimeBid.setSellOnePrice(BigDecimal.valueOf(Float.parseFloat(sublist[21])));
            realtimeBid.setSellTwoVolume(Long.valueOf(sublist[22]) );
            realtimeBid.setSellTwoPrice(BigDecimal.valueOf(Float.parseFloat(sublist[23])));
            realtimeBid.setSellThreeVolume(Long.valueOf(sublist[24]));
            realtimeBid.setSellThreePrice(BigDecimal.valueOf(Float.parseFloat(sublist[25])));
            realtimeBid.setSellFourVolume(Long.valueOf(sublist[26]));
            realtimeBid.setSellFourPrice(BigDecimal.valueOf(Float.parseFloat(sublist[27])));
            realtimeBid.setSellFiveVolume(Long.valueOf(sublist[28]));
            realtimeBid.setSellFivePrice(BigDecimal.valueOf(Float.parseFloat(sublist[29])));
            realtimeBid.setTradeDate(LocalDate.parse(sublist[30], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            realtimeBid.setTradeTime(LocalTime.parse(sublist[31], DateTimeFormatter.ofPattern("HH:mm:ss")));
            realtimeBidList.add(realtimeBid);
        }
      page.putField("list",realtimeBidList);
        //添加下一页
        if(codelistall.size()>0){
            List<String> codelist = codelistall.subList(0,codelistall.size()>500?500:codelistall.size());
            codelistall = codelistall.subList(codelist.size(),codelistall.size());
            String url = url_+String.join(",",codelist);
            page.addTargetRequest(url);
        }





    }


}
