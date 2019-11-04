package cn.lubang.lubangcommon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ErrorController {

    @RequestMapping("unauth")
    @ResponseBody
    public Object unauth(ServletResponse response){
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map = new HashMap<>();
        map.put("errMsg","未登录");
        map.put("status", 0);
        return map;
    }

}
