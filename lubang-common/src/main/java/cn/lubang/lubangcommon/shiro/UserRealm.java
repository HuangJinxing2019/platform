package cn.lubang.lubangcommon.shiro;

import cn.lubang.lubangcommon.entity.SysUserEntity;
import cn.lubang.lubangcommon.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行逻辑");
        return null;
    }

    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证逻辑");
        //判断用户名密码是否一致
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUserEntity sysUserEntity = sysUserService.queryObject(token.getUsername());

        if(sysUserEntity==null){
            //用户不存在
        }
        //判断密码
        return new SimpleAuthenticationInfo("",sysUserEntity.getPassword(),"");
    }
}
