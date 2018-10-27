package com.demo.springbootshirodemo;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("开始设置过滤器-");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainMap = new HashMap<>();
        filterChainMap.put("/login","anon");    //匿名访问登陆页面
        filterChainMap.put("/register","anon");     //匿名访问注册页面
//      filterChainMap.put("/static/**","anon");
        filterChainMap.put("/defaultKaptcha","anon");
        filterChainMap.put("/favicon.ico","anon");
        filterChainMap.put("/css/**","anon");    //配置不拦截的链接  顺序判断
        filterChainMap.put("/js/**","anon");    //配置不拦截的链接  顺序判断
        filterChainMap.put("/img/**","anon");
        filterChainMap.put("/doLogin","anon");
        filterChainMap.put("/doRegister","anon");
        filterChainMap.put("/","user"); //配置记住我或者认证后可以访问的地址
        filterChainMap.put("/logout","logout");     //配置退出过滤器
        filterChainMap.put("/**","authc");  //antuc表示需要认证，anno表示允许匿名访问

        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyRealm myShiroRealm(){
        MyRealm myShiroRealm = new MyRealm();
        return myShiroRealm;
    }

    @Bean
    public SimpleCookie remembermMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(120);    //单位：s
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remembermMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager =
                new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myShiroRealm());
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultWebSecurityManager;
    }

}
