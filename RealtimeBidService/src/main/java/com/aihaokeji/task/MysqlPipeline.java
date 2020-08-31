package com.aihaokeji.task;


import com.aihaokeji.entity.RealtimeBid;
import com.aihaokeji.service.RealtimeBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class MysqlPipeline implements Pipeline {
    @Autowired
    private RealtimeBidService realtimeBidService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<RealtimeBid> list  =  resultItems.get("list");
        if(list.size()!=0){
//                    System.out.println("共有   "+list.size());
            realtimeBidService.saveBatch(list);
            //        System.out.println("保存结束");
        }else {
            return;
        }
    }
}
