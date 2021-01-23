package com.aihaokeji.task;

import com.aihaokeji.entity.Industrycode;
import com.aihaokeji.service.IndustrycodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;

@Component
public class IndustryCodePipeline implements Pipeline {
	@Autowired
	private IndustrycodeService industrycodeService;
	protected static final Logger logger = LoggerFactory.getLogger(IndustryCodePipeline.class);
	@Override
	public void process(ResultItems resultItems, Task task) {
		List<Industrycode> list  =  resultItems.get("list");
		if(list.size()!=0){
			//查询数据库中的代码集合
			QueryWrapper<Industrycode> queryWrapper = new QueryWrapper<>();
			queryWrapper.select("industry_code");
			List<Industrycode> oldcodes = industrycodeService.list(queryWrapper);
			List<String> olds = new ArrayList<>(oldcodes.size());
			for (Industrycode code:oldcodes) {
				olds.add(code.getIndustryCode());
			}
			//现在的代码
			List<String> news = new ArrayList<>(list.size());
			for (Industrycode n:list) {
				news.add(n.getIndustryCode());
			}
			//集合去重
			//Collection newcodes = new ArrayList(list);
			//Collection oldcodes = new ArrayList(list_1);
			//  oldcodes.retainAll(list);  oldcodes中的元素为两个集合的交集
			//  list.removeAll(oldcodes);   list删除oldcodes中的元素
			olds.retainAll(news);
			if (olds.size() != 0) {
				news.removeAll(olds);
			}

//            剩余保存
			if(news.size()>0){
				List<Industrycode> savelist  =  new ArrayList<>(news.size());
				for(Industrycode code:list){
					if(news.contains(code.getIndustryCode())){
						savelist.add(code);
					}
				}
				industrycodeService.saveBatch(savelist);
				logger.info("保存了"+savelist.size()+"个数据！");
			}
		}else {
			return;
		}
	}
}
