package kr.pe.courage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * <pre>
 * kr.pe.courage.config
 * TilesConfiguration.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2017. 10. 8.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * Tiles 설정
 * 
 * << 개정이력 >>
 * 1. 수정일 : 2017. 10. 8., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Configuration
public class TilesConfiguration {
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[] { "WEB-INF/tiles/tiles.xml" });
        configurer.setCheckRefresh(true);
        
        return configurer;
	}
	
	@Bean
    public TilesViewResolver tilesViewResolver() {
        final TilesViewResolver resolver = new TilesViewResolver();
        resolver.setViewClass(TilesView.class);
        
        return resolver;
    }
}
