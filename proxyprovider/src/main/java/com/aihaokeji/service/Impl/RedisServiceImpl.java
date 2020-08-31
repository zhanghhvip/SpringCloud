package com.aihaokeji.service.Impl;

import com.aihaokeji.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void save(String proxy) {
        redisTemplate.opsForZSet().add("proxy",proxy,100);
    }

    @Override
    public Set<String> search() {
        return redisTemplate.opsForZSet().range("proxy",0,-1);
    }

    @Override
    public Set<String> provide() {
        return redisTemplate.opsForZSet().reverseRangeByScore("proxy",90,101);
    }

    @Override
    public void delete() {
//        redisTemplate.opsForZSet().remove("proxy", proxy);
        redisTemplate.opsForZSet().removeRangeByScore("proxy",0,90);

    }

    @Override
    public void desc(String proxy) {
        redisTemplate.opsForZSet().incrementScore("proxy",proxy,-10);

    }
}
