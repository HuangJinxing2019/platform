package cn.lubang.lubangadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan({"cn.lubang.lubangadmin","cn.lubang.lubanggen","cn.lubang.lubangcommon"})
public class LubangAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LubangAdminApplication.class, args);
    }

    @RequestMapping("test")
    public String test(){
        return "Hello World";
    }

}
