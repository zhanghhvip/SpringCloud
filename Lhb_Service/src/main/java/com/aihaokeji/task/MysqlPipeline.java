package com.aihaokeji.task;

import com.aihaokeji.entity.Longhubang;
import com.aihaokeji.service.LonghubangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
@Component
public class MysqlPipeline implements Pipeline {
    @Autowired
    private LonghubangService  longhubangService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Longhubang> list  =  resultItems.get("list");
        if(list.size()!=0){
            //        System.out.println(list.size());
            longhubangService.saveBatch(list);
            //        System.out.println("保存结束");
        }else {
            return;
        }
    }
}
