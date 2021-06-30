
package kr.pe.courage.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * kr.pe.courage.properties
 * CourageProperties.java
 * </pre>
 *
 * @Author : ChangHo Seok
 * @Date : 2017. 9. 30.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2017. 9. 30., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 *      </pre>
 */
@Component
@ConfigurationProperties
public class CourageProperties {
	private Map<String, String> courage = new HashMap<>();

	public Map<String, String> getCourage() {
		return courage;
	}
	
	public String getProp(String key) {
		return courage.get(key);
	}
}
