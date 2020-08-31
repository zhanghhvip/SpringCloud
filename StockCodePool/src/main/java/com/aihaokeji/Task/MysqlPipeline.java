package com.aihaokeji.Task;
import com.aihaokeji.entity.Code;
import com.aihaokeji.service.CodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class MysqlPipeline implements Pipeline {
    @Autowired
    private CodeService codeService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Code> list  =  resultItems.get("list");
        if(list.size()!=0){
            //查询数据库中的代码集合
//            QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
          List<Code> oldcodes = codeService.list();
            //集合去重
//            Collection newcodes = new ArrayList(list);
//            Collection oldcodes = new ArrayList(list_1);
            oldcodes.retainAll(list);
            if (oldcodes.size() != 0) {
                list.removeAll(oldcodes);
            }

            //剩余保存
            if(list.size()>0){
//                for(int i=0; i<list.size();i++){
//                    System.out.println(list.get(i));
//                }
                codeService.saveBatch(list);
            }
        }else {
            return;
        }
    }
}
