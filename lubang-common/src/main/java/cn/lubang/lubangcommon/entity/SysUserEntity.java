package cn.lubang.lubangcommon.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserEntity {
    //用户ID
    private Integer id;
    //用户名称
    private String username;
    //用户密码
    private String password;
    //用户手机
    private String mobile;
    //用户状态
    private Integer status;
    //创建时间
    private Date createTime;

}
