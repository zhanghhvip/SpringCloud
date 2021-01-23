package com.aihaokeji.task;

import com.aihaokeji.entity.Conceptcode;
import com.aihaokeji.service.ConceptcodeService;
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
public class ConceptCodePipeline implements Pipeline {

	@Autowired
	private ConceptcodeService conceptcodeService;
	protected static final Logger logger = LoggerFactory.getLogger(ConceptCodePipeline.class);
	@Override
	public void process(ResultItems resultItems, Task task) {
		List<Conceptcode> list  =  resultItems.get("list");
		if(list.size()!=0){
			//查询数据库中的代码集合
			QueryWrapper<Conceptcode> queryWrapper = new QueryWrapper<>();
			queryWrapper.select("concept_code");
			List<Conceptcode> oldcodes = conceptcodeService.list(queryWrapper);
			List<String> olds = new ArrayList<>(oldcodes.size());
			for (Conceptcode code:oldcodes) {
				olds.add(code.getConceptCode());
			}
			//现在的代码
			List<String> news = new ArrayList<>(list.size());
			for (Conceptcode n:list) {
				news.add(n.getConceptCode());
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
				List<Conceptcode> savelist  =  new ArrayList<>(news.size());
				for(Conceptcode code:list){
					if(news.contains(code.getConceptCode())){
						savelist.add(code);
					}
				}
				conceptcodeService.saveBatch(savelist);
				logger.info("保存了"+savelist.size()+"个数据！");
			}
		}else {
			return;
		}
	}
}
