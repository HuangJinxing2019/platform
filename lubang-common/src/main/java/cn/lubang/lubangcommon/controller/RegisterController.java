package cn.lubang.lubangcommon.controller;

import cn.lubang.lubangcommon.entity.SysUserEntity;
import cn.lubang.lubangcommon.service.SysUserService;
import cn.lubang.lubangcommon.utils.R;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("register")
    @ResponseBody
    public Object register (SysUserEntity userEntity){
        Map<String,Object> map = new HashMap<>();
        SysUserEntity sysUserEntity = sysUserService.queryObject(userEntity.getUsername());

        if(sysUserEntity!=null){
            map.put("status", 0);
            map.put("errMsg", "该用户已存在");
            return map;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("mobile",userEntity.getMobile());
        List<SysUserEntity> sysUserEntities = sysUserService.queryList(params);
        if(sysUserEntities.size()>0){
            map.put("status", 0);
            map.put("errMsg", "该手机号码已被绑定");
            return map;
        }

        ByteSource byteSource = ByteSource.Util.bytes(userEntity.getUsername()+"lubang");
        Object obj = new SimpleHash("MD5", userEntity.getPassword(), byteSource, 1024);
        userEntity.setPassword(obj.toString());
        userEntity.setStatus(1);
        userEntity.setCreateTime(new Date());
        try {
            sysUserService.save(userEntity);
        }catch (Exception e){
            map.put("status", 0);
            map.put("errMsg", "注册失败");
            return map;
        }
        map.put("status", 200);
        map.put("errMsg", "注册成功");
        return map;
    }


    public static void main(String[] args) {
        ByteSource byteSource = ByteSource.Util.bytes("adminlubang");
        Object obj = new SimpleHash("MD5", "admin", byteSource, 1024);
        System.out.println(obj.toString());
    }
}
