package kr.pe.courage.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.pe.courage.common.web.filter.WebLoggingFilter;

/**
 * <pre>
 * kr.pe.courage.configuration
 * WebLoggingFilterConfig.java
 * </pre>
 *
 * @Date	: 2017. 10. 25.
 * @Version	: 1.0
 * @see
 * <pre>
 * ServletFilter 설정
 * </pre>
 */
@Configuration
public class WebLoggingFilterConfig {
	
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		WebLoggingFilter webLoggingFilter = new WebLoggingFilter();
		Map<String, String> initParams = new HashMap<>();
		
		initParams.put("excludeStartWithURI", "/images/, /css/, /webjars/, /js/, /favicon.ico, /vendors/");
//		initParams.put("os-logging", "true"); // default false
//		initParams.put("mem-logging", "true"); // default false
//		initParams.put("param-logging", "true"); // default true
//		initParams.put("javaProperties-logging", "true"); // default false
		
		registrationBean.setInitParameters(initParams);
		registrationBean.setFilter(webLoggingFilter);
		registrationBean.setOrder(2);
		
		return registrationBean;
	}
}
