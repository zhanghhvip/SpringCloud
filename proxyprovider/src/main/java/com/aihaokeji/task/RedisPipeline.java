package com.aihaokeji.task;

import com.aihaokeji.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class RedisPipeline implements Pipeline {
    @Autowired
    private RedisService redisService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的信息
        String proxy = (String)resultItems.get("ip_port");
        System.out.println(proxy);
        if(proxy!=null){
            redisService.save(proxy);
//            System.out.println("保存到redis");
        }

    }
}
