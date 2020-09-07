package com.aihaokeji.task;

import com.aihaokeji.entity.Wencaixuangu;
import com.aihaokeji.service.WencaixuanguService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WencxgProcessor {

    private WebDriver webDriver;
    @Value(value = "${chrome.dirverpath}")
    private String dirverpath;
    @Value(value = "${chrome.url}")
    private String url;
    @Autowired
    private WencaixuanguService wencaixuanguService;

    @Scheduled(cron = "0 45 10 ? * 2-6")
    public void downloadpage(){
        System.setProperty("webdriver.chrome.driver",dirverpath );
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
        .addArguments("Connection", "keep-alive")
                .addArguments("X-Requested-With", "XMLHttpRequest")
                .addArguments("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .addArguments( "User-Agent",  "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                .addArguments("Upgrade-Insecure-Requests", "0");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(url);
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        Document doc = Jsoup.parse(content);
        Elements elements = doc.body().select("div.static_con_outer").select("tbody").select("div.em").select("a");
//        System.out.println("元素个数："+elements.size());
        if(elements.size()>0) {
            List<Wencaixuangu> list = new ArrayList<>(elements.size());
            for (Element element : elements) {
                Wencaixuangu wencaixuangu = new Wencaixuangu();
                String href = element.attr("href");
                Integer index = href.lastIndexOf("=") + 1;
                String code = href.substring(index);
                String name = element.text();
                LocalDate localDate = LocalDate.now();
                wencaixuangu.setCode(code);
                wencaixuangu.setName(name);
                wencaixuangu.setDate(localDate);
                list.add(wencaixuangu);
//                System.out.println(code);
//                System.out.println(name);
            }
            if(list.size()!=0){
                //        System.out.println(list.size());
                wencaixuanguService.saveBatch(list);
                //        System.out.println("保存结束");
            }
        }
    }



}
