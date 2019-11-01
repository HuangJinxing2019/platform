package cn.lubang.lubangcommon.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 */

@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBane
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBane(@Qualifier("securityManager")DefaultWebSecurityManager securityManager ){
        ShiroFilterFactoryBean shiroFilterFactoryBane = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBane.setSecurityManager(securityManager);
        shiroFilterFactoryBane.setLoginUrl("/unauth");


        //添加shiro内置过滤器
        /**
         * 常用过滤器：
         *      anon:无需认证（登录）可以访问
         *      authc: 必须认证才可以访问
         *      user: 如果使用rememberMe的功能才可以直接访问
         *      perms：该资源必须得到资源权限才可以访问
         *      role: 该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/**","authc");
        shiroFilterFactoryBane.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBane;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
