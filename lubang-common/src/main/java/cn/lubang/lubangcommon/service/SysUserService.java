package cn.lubang.lubangcommon.service;

import cn.lubang.lubangcommon.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

public interface SysUserService {
    SysUserEntity queryObject(String username);
    List<SysUserEntity> queryList(Map<String,Object> params);
    int save(SysUserEntity sysUserEntity);
}
