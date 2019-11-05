package cn.lubang.lubangcommon.controller;

import cn.lubang.lubangcommon.entity.SysUserEntity;
import cn.lubang.lubangcommon.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public Object login(SysUserEntity user){
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        ByteSource byteSource = ByteSource.Util.bytes(user.getUsername()+"lubang");
        Object obj = new SimpleHash("MD5", user.getPassword(), byteSource, 1024);

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),obj.toString());
        Map<String,Object> data = new HashMap<>();
        //执行登录方法
        try {
            subject.login(token);
            //登录成功
            data.put("status", 200);
            data.put("errMsg","登录成功");
            return data;
        }catch (UnknownAccountException e){
            //登录失败 用户名不存在
            data.put("status", 0);
            data.put("errMsg","用户名不存在");
            return data;
        } catch (IncorrectCredentialsException e) {
            //登录失败 密码错误
            data.put("status", 0);
            data.put("errMsg","密码错误");
            return data;
        }
    }
}
