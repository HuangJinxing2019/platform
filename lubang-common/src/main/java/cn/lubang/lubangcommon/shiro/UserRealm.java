package cn.lubang.lubangcommon.shiro;

import cn.lubang.lubangcommon.entity.SysUserEntity;
import cn.lubang.lubangcommon.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
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
        System.out.println("授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取登录用户信息
        Subject subject = SecurityUtils.getSubject();
        SysUserEntity user = (SysUserEntity) subject.getPrincipal();


        return info;
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
           return null;
        }
        if(sysUserEntity.getStatus()==0){
            //账户被禁用
            throw new LockedAccountException();
        }
        //判断密码
        return new SimpleAuthenticationInfo(sysUserEntity,sysUserEntity.getPassword(),"");
    }
}
