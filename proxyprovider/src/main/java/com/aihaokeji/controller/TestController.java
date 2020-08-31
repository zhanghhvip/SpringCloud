package com.aihaokeji.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(value="/hello/{name}")
    public String sayHello(@PathVariable("name")    String name){
        return "hello, ".concat(name).concat("!");
    }
}
