package cn.lubang.lubanggen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("genTest")
    public String genTest(){
        return "gen Hello World";
    }
}
