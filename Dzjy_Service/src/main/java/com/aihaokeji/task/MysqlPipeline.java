package com.aihaokeji.task;


import com.aihaokeji.entity.Dzjy;
import com.aihaokeji.service.DzjyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class MysqlPipeline implements Pipeline {

    @Autowired
    private  DzjyService dzjyService;

    protected static final Logger logger = LoggerFactory.getLogger(MysqlPipeline.class);

        @Override
    public void process(ResultItems resultItems, Task task) {
        List<Dzjy> list  =  resultItems.get("list");
        if(list.size()!=0){
            //        System.out.println(list.size());
            dzjyService.saveBatch(list);
            logger.info("保存了"+list.size()+"个数据！");
            //        System.out.println("保存结束");
        }else {
            logger.info("没有数据！");
        }
    }
}
