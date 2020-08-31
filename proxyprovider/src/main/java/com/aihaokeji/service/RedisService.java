package com.aihaokeji.service;

import java.util.List;
import java.util.Set;

public interface RedisService {
    //保存
    public void save(String proxy);
    //查询
    public Set<String> search();
    //对外接口
    public Set<String> provide();
    //删除
    public void delete();
    //检测降分
    public void desc(String proxy);
}
