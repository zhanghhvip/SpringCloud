package com.aihaokeji.task;

import com.aihaokeji.entity.Wencaixuangu;
import com.aihaokeji.service.WencaixuanguService;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

public class MysqlPipeline implements Pipeline {
    @Autowired
    private WencaixuanguService wencaixuanguService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Wencaixuangu> list  =  resultItems.get("list");
        if(list.size()!=0){
            //        System.out.println(list.size());
            wencaixuanguService.saveBatch(list);
            //        System.out.println("保存结束");
        }else {
            return;
        }
    }
}
