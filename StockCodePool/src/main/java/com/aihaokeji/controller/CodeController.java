package com.aihaokeji.controller;


import com.aihaokeji.entity.Code;
import com.aihaokeji.service.CodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 股票代码表 前端控制器
 * </p>
 *
 * @author zhh
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    private CodeService codeService;
    @RequestMapping("/findall")
    public List<String> findall(){
        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("code");
        List<Code> codeList = codeService.list(queryWrapper);
        List<String> list = new ArrayList<>(codeList.size());
        for(Code c : codeList){
            list.add(c.getCode());
        }
        return list;
    }

}

