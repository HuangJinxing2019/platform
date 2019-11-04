package cn.lubang.lubanggen.dao;

import cn.lubang.lubangcommon.entity.SysUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Test {
    List<SysUserEntity> queryList(Object obj);
}
