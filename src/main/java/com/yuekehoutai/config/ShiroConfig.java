package com.yuekehoutai.config;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;

import com.yuekehoutai.shiro.LoginFilter;
import com.yuekehoutai.shiro.WorkerRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;




/**
 * Shiro配置类
 * 
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfig {
	@Bean
	public WorkerRealm initRealm() {
		return new WorkerRealm();
	}
	@Bean
	public SecurityManager initSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 注入领域对象
		securityManager.setRealm(initRealm());
		// 注入rememberMe组件
		securityManager.setRememberMeManager(rememberManager());
		return securityManager;
	}
	@Bean
	public CookieRememberMeManager rememberManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		//创建一个Cookie
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setMaxAge(3*24*60*60);
		cookieRememberMeManager.setCookie(cookie);

		cookieRememberMeManager.setCipherKey(Base64.decode("wGiHplamyXlVB11UXWol8g=="));
		return cookieRememberMeManager;
	}
	@Bean
	@Scope("prototype")
	public Filter loginFilter() {
		return new LoginFilter();
	}
	// 实例化Shiro过滤器工厂组件
	@Bean
	public ShiroFilterFactoryBean shiroFilter() throws UnsupportedEncodingException {
		// 实例化Filter工厂
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 注册securityManager
		shiroFilterFactoryBean.setSecurityManager(initSecurityManager());
		//将自定义的过滤器加入到Shiro的过滤器工厂中
		//创建一个过滤器键值对 用于匹配不同的校验规则
//		HashMap<String, Filter> filters = new HashMap<String,Filter>();
//		filters.put("authc", loginFilter());
//		shiroFilterFactoryBean.setFilters(filters);
		// 设置Shiro过滤器过滤规则
		// LinkHashMap是有序的，shiro会根据添加的顺序进行拦截,匹配到过滤器后就执行该过滤器不会在继续向下查找过滤器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截地址规则
		// anon:所有的url都可以不登陆的情况下访问
		// authc：所有url都必须认证通过才可以访问
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/worker/login", "anon");
		filterChainDefinitionMap.put("/page/login.html", "anon");
		filterChainDefinitionMap.put("/view/**", "anon");
		filterChainDefinitionMap.put("/activity/**", "anon");
		filterChainDefinitionMap.put("/food/**", "anon");
		filterChainDefinitionMap.put("/camp/**", "anon");
		filterChainDefinitionMap.put("/car/**", "anon");
		filterChainDefinitionMap.put("/worker/**", "anon");
		filterChainDefinitionMap.put("/information/**", "anon");
		filterChainDefinitionMap.put("/banner/**", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		//被shiro拦截的swagger资源放行
		filterChainDefinitionMap.put("/**/*", "anon");
		filterChainDefinitionMap.put("/swagger-resources", "anon");
		filterChainDefinitionMap.put("/v2/api-docs", "anon");
		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
		// 如果不满足上方所有的规则 则需要进行登录验证
		filterChainDefinitionMap.put("/logout", "logout");
		//在登陆之后或者通过记住我登陆之后都可以正常访问
		filterChainDefinitionMap.put("/**", "user");
		// 未登录时重定向的网页地址
		shiroFilterFactoryBean.setLoginUrl("/page/login.html");
		// 将地址过滤规则设置到Filter工厂中
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		// 返回
		return shiroFilterFactoryBean;
	}
	//配置控制层的前置通知
	//在通知中判断权限是否足够
	//@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(initSecurityManager());
		return advisor;
	}
	//强制使用CGLIB动态代理
	//@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator app=new DefaultAdvisorAutoProxyCreator();
		app.setProxyTargetClass(true);
		return app;
	}
}