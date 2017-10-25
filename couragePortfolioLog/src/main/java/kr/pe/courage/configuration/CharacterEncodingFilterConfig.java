package kr.pe.courage.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * <pre>
 * kr.pe.courage.configuration
 * CharacterEncodingFilterConfig.java
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
public class CharacterEncodingFilterConfig {
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	    characterEncodingFilter.setForceEncoding(true);
	    characterEncodingFilter.setEncoding("UTF-8");
	    
	    registrationBean.setFilter(characterEncodingFilter);
	    registrationBean.setOrder(1);
	    
	    return registrationBean;
	}
}
