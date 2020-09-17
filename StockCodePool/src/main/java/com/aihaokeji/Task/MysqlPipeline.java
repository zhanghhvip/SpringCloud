package com.aihaokeji.Task;
import com.aihaokeji.entity.Code;
import com.aihaokeji.service.CodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected static final Logger logger = LoggerFactory.getLogger(MysqlPipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Code> list  =  resultItems.get("list");

        if(list.size()!=0){
            //查询数据库中的代码集合
            QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("code");
            List<Code> oldcodes = codeService.list(queryWrapper);
            List<String> olds = new ArrayList<>(oldcodes.size());
            for (Code code:oldcodes) {
                olds.add(code.getCode());
            }
            //现在的代码
            List<String> news = new ArrayList<>(list.size());
            for (Code n:list) {
                news.add(n.getCode());
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
                List<Code> savelist  =  new ArrayList<>(news.size());
                for(Code code:list){
                    if(news.contains(code.getCode())){
                        savelist.add(code);
                    }
                }
                codeService.saveBatch(savelist);
               logger.info("保存了"+savelist.size()+"个数据！");
           }
        }else {
            return;
        }
    }
}
