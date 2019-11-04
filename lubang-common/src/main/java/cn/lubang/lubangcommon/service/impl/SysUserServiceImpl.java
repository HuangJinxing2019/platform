package cn.lubang.lubangcommon.service.impl;

import cn.lubang.lubangcommon.dao.SysUserDao;
import cn.lubang.lubangcommon.entity.SysUserEntity;
import cn.lubang.lubangcommon.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUserEntity queryObject(String username) {
        return sysUserDao.queryObject(username);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> params) {

        return sysUserDao.queryList(params);
    }
}
