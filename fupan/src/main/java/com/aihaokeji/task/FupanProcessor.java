package com.aihaokeji.task;

import com.aihaokeji.entity.Fupan;

import com.aihaokeji.service.FupanService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class FupanProcessor {

	private WebDriver webDriver;

	private String dirverpath = System.getProperty("user.dir")+ "/driver/chromedriver.exe";//jar包所在目录名
	//    @Value(value = "${chrome.dirverpath}")
//	private String dirverpath = (FupanProcessor.class.getClassLoader().getResource("driver/chromedriver.exe")+"").substring(6);

	@Value(value = "${chrome.url}")
	private String url;
	@Autowired
	private FupanService fupanService;

	protected static final Logger logger = LoggerFactory.getLogger(FupanProcessor.class);

//	    @Scheduled(cron = "0 */5  * ? * 1-5")
	@Scheduled(cron = "0 0 17 ? * 1-5")
	public void downloadpage(){
		System.setProperty("webdriver.chrome.driver",dirverpath );
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
//		chromeOptions.addArguments("--disable-gpu");
//		chromeOptions.addArguments("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
//				.addArguments("Connection", "keep-alive")
//				.addArguments("X-Requested-With", "XMLHttpRequest")
//				.addArguments("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
//				.addArguments( "User-Agent",  "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
//				.addArguments("Upgrade-Insecure-Requests", "0")
				;
		webDriver = new ChromeDriver(chromeOptions);
		webDriver.get(url);
		WebElement webElement = webDriver.findElement(By.xpath("/html"));
		String content = webElement.getAttribute("outerHTML");
		Document doc = Jsoup.parse(content);
		Elements elements = doc.body().select("div.static_con_outer").select("div.static_con").select("tbody").select("tr").select("div.em").select("a");
//		System.out.println("元素个数："+elements.size());
		List<String> codelist = new ArrayList<String>(elements.size()*2);
		for(Element element : elements) {
			String href = element.attr("href");
			Integer index = href.lastIndexOf("=") + 1;
			String code = href.substring(index);
			String name = element.text();
			codelist.add(code);
			codelist.add(name);
		}
		List<Fupan> list = new ArrayList<Fupan>(elements.size());
		Elements elements2 = doc.body().select("div.scroll_tbody_con").select("tbody").select("tr");
//		System.out.println("元素个数："+elements.size());
		int i = 0;
		for(Element element : elements2){
			List<String> stringList = element.select("td").eachText();
			BigDecimal change_ratio = BigDecimal.valueOf(Float.parseFloat(stringList.get(0))).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal close_price =  BigDecimal.valueOf(Float.valueOf(stringList.get(1))).setScale(2, BigDecimal.ROUND_HALF_UP);
			String type = stringList.get(3);
			String	zt_days = stringList.get(4);
			LocalTime first_time = LocalTime.parse(stringList.get(5));
			LocalTime last_time = LocalTime.parse(stringList.get(6));
			Integer duration = Integer.parseInt(stringList.get(8));
			String reason = stringList.get(9);
			BigDecimal fdl = BigDecimal.valueOf(Float.valueOf(stringList.get(10).replace('万', ' ').replaceAll(",", ""))*10000).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal fde = BigDecimal.valueOf(stringList.get(11).contains("万")?Float.valueOf(stringList.get(11).replace('万', ' ').replaceAll(",", ""))*10000:Float.valueOf(stringList.get(11).replace('亿', ' ').replaceAll(",", ""))*100000000).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal fdl_vol_ratio =  BigDecimal.valueOf(stringList.get(12).contains("万")?Float.valueOf(stringList.get(12).replaceAll(",", "").replace('万', ' '))*10000:Float.valueOf(stringList.get(12).replaceAll(",",""))).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal fdl_ltg_ratio =  BigDecimal.valueOf(Float.valueOf(stringList.get(13))).setScale(2, BigDecimal.ROUND_HALF_UP);
			Integer kb_num = Integer.parseInt(stringList.get(14));
			BigDecimal ltsz = BigDecimal.valueOf(Float.valueOf(stringList.get(15).replace('亿', ' '))*100000000).setScale(2, BigDecimal.ROUND_HALF_UP);
			Integer ss_days = stringList.get(16).contains("万")? Math.round(Float.parseFloat(stringList.get(16).replaceAll(",", "").replaceAll("万", ""))*10000):stringList.get(16).contains("--")?Integer.parseInt("0"):Integer.parseInt(stringList.get(16).replaceAll(",", ""));
			LocalDate trade_date = LocalDate.now();
			Fupan fupan = new Fupan();
			fupan.setCode(codelist.get(i));
			fupan.setName(codelist.get(i+1));
			fupan.setChangeRatio(change_ratio);
			fupan.setClosePrice(close_price);
			fupan.setType(type);
			fupan.setZtDays(zt_days);
			fupan.setFirstTime(first_time);
			fupan.setLastTime(last_time);
			fupan.setDuration(duration);
			fupan.setReason(reason);
			fupan.setFdl(fdl);
			fupan.setFde(fde);
			fupan.setFdlVolRatio(fdl_vol_ratio);
			fupan.setFdlLtgRatio(fdl_ltg_ratio);
			fupan.setKbNum(kb_num);
			fupan.setLtsz(ltsz);
			fupan.setSsDays(ss_days);
			fupan.setTradeDate(trade_date);
			list.add(fupan);
			i+=2;
		}
		if(list.size()!=0){
				//        System.out.println(list.size());
				fupanService.saveBatch(list);
				logger.info("保存了"+list.size()+"个数据！");
				webDriver.quit();
				//        System.out.println("保存结束");
		}
	}
}
