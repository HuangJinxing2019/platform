package cn.lubang.lubangcommon.dao;

import cn.lubang.lubangcommon.entity.SysUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysUserDao {
    SysUserEntity queryObject(String username);
    List<SysUserEntity> queryList(Map<String,Object> params);
}
