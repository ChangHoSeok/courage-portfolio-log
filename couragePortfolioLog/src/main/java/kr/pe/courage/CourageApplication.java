
package kr.pe.courage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * <pre>
 * kr.pe.courage
 * CourageApplication.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2017. 9. 30.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2017. 9. 30., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Configuration
@ComponentScan(basePackages = "kr.pe.courage")
@EnableTransactionManagement
@EnableAutoConfiguration
public class CourageApplication extends SpringBootServletInitializer {
	private static final Class<CourageApplication> applicationClass = CourageApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public FilterRegistrationBean filterRegistrationBean() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	    characterEncodingFilter.setForceEncoding(true);
	    characterEncodingFilter.setEncoding("UTF-8");
	    registrationBean.setFilter(characterEncodingFilter);
	    return registrationBean;
	}
}