package sample.web.ui.config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import sample.web.ui.security.SampleRealm;
import sample.web.ui.security.SampleRealmWithoutCaptcha;

@Configuration
public class SecurityConfig {


//	@Autowired
//	private SampleRealm sampleRealm;


	@Bean
	public  SampleRealm sampleRealm() {
		return new SampleRealm();
	}

	@Bean
	public  SampleRealmWithoutCaptcha sampleRealmWithoutCaptcha() {
		return new SampleRealmWithoutCaptcha();
	}

	@Bean
	public static  LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("entityManagerFactory")
	public WebSecurityManager securityManager() {
		List<Realm> list = new ArrayList<Realm>();
		list.add(sampleRealm());
		list.add(sampleRealmWithoutCaptcha());
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealms(list);
//		securityManager.setRealm(sampleRealmWithoutCaptcha());
		return securityManager;
	}

	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		methodInvokingFactoryBean.setArguments(new Object[] { securityManager() });
		return methodInvokingFactoryBean;
	}


	@Bean
	public  AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}


/*
	@Bean
	public ShiroDialect securityDialect() {
		return new ShiroDialect();
	}
	*/
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor","requestContextFilter"})
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl("/login");
		shiroFilter.setSuccessUrl("/");
		shiroFilter.setUnauthorizedUrl("/login");

		HashMap<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("authc", new PassThruAuthenticationFilter());
//		filters.put("authc", new FormAuthenticationCaptchaFilter());
		shiroFilter.setFilters(filters);

		Map<String, String> definitions = shiroFilter.getFilterChainDefinitionMap();
		definitions.put("/flat/**", "anon");
		definitions.put("/test/noLogin/**", "anon");
		definitions.put("/login", "anon");
		definitions.put("/signup", "anon");
		definitions.put("/signup.*", "anon");
		definitions.put("/captcha", "anon");
		definitions.put("/**", "anon");
//		definitions.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(definitions);

		return shiroFilter;
	}
	
	
	
}
