package com.aihaokeji.Task;


import com.aihaokeji.entity.Code;
import com.aihaokeji.entity.CodeRawData;
import com.aihaokeji.service.CodeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.util.Map;

@Component
public class StockCodeProcessor implements PageProcessor {
    @Autowired
    private MysqlPipeline mysqlPipeline;

    private  String url="http://push2.eastmoney.com/api/qt/clist/get?pn=1&pz=5000&po=1&np=1&fltt=2&invt=2&fid0=f4001&fid=f62&fs=m:0+t:6+f:!2,m:0+t:13+f:!2,m:0+t:80+f:!2,m:1+t:2+f:!2,m:1+t:23+f:!2&stat=1&fields=f12,f14,f100";
    //创建任务
//    @Scheduled(cron = "0 */1 *  ?  * 1-5")
    @Scheduled(cron = "0 15 9 ? * 1-5")
    public void start_Task(){
        //创建下载器
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
        Spider.create(new StockCodeProcessor())
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
//        Integer total = Integer.parseInt(rawText.substring(rawText.indexOf("total")+7,rawText.lastIndexOf("diff")-2));
        String jsonstring =  page.getRawText().substring(rawText.indexOf("["),rawText.lastIndexOf("]")+1);
        List<CodeRawData> codeRawData = JSONObject.parseArray(jsonstring,CodeRawData.class);
        List<Code> codeData =new ArrayList<>(codeRawData.size());
        for (CodeRawData data : codeRawData) {
           Code code = new Code();
           String stackcode = data.getF12().startsWith("6")?"sh"+data.getF12():"sz"+data.getF12();
           code.setCode(stackcode);
           code.setName(data.getF14());
           code.setIndustry(data.getF100());
           codeData.add(code);
        }
        page.putField("list",codeData);
//                //json对象转字符串
//                String codeString =JSON.toJSONString(diff.get(String.valueOf(i + 1)));
//                // 字符串转json对象
//                Code code = JSONObject.parseObject(codeString,Code.class);

    }
}
