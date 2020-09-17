package com.aihaokeji.controller;


import com.aihaokeji.entity.Wencaixuangu;
import com.aihaokeji.service.WencaixuanguService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 问财选股表 前端控制器
 * </p>
 *
 * @author zhh
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/wencaixuangu")
public class WencaixuanguController {
    @Autowired
    WencaixuanguService wencaixuanguService;
    public List<String> get_today_code(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("code");
        List<Wencaixuangu> codeList = wencaixuanguService.list(queryWrapper);
        List<String> list = new ArrayList<>(codeList.size());
        for(Wencaixuangu c : codeList){
            list.add(c.getCode());
        }

        return list;
    }

}

