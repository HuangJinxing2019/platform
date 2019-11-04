package cn.lubang.lubanggen.service.impl;

import cn.lubang.lubangcommon.entity.SysUserEntity;
import cn.lubang.lubanggen.dao.Test;
import cn.lubang.lubanggen.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestImpl implements TestService {

    @Autowired
    private Test test;

    @Override
    public List<SysUserEntity> queryList(Object obj) {
        return test.queryList(obj);
    }
}
