package com.aihaokeji.task;

import com.aihaokeji.entity.Industrycode;
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

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class IndustryCodeProcess  implements PageProcessor {

	@Autowired
	private  IndustryCodePipeline industryCodePipeline;

	private  String url=  "http://37.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112403594654273556146_1602582208978&pn=1&pz=1000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:3+f:!50&fields=f12,f14l";
	//创建任务
//    @Scheduled(cron = "0 47 20 ? * 1-5")
	@Scheduled(cron = "0 0 17 ? * 1-5")
	public void start_Task(){
		//创建下载器
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		//给下载器设置代理服务器
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("","")));
		Spider.create(new IndustryCodeProcess())
				.addUrl(url)
				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
//                .thread(10)//多线程
//                .setDownloader(httpClientDownloader)
				.addPipeline(this.industryCodePipeline)
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
		List<Industrycode> industrycodeList = JSONObject.parseArray(jsonstring,Industrycode.class);
		page.putField("list",industrycodeList);
//		System.out.println(Arrays.toString(industrycodeList.toArray()));

	}
}
