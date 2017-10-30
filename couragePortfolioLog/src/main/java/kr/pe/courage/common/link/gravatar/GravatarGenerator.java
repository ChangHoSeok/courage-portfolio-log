package kr.pe.courage.common.link.gravatar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.pe.courage.properties.CourageProperties;

/**
 * <pre>
 * kr.pe.courage.common.link.gravatar
 * GravatarGenerator.java
 * </pre>
 *
 * @Date	: 2017. 10. 30.
 * @Version	: 1.0
 * @see
 * <pre>
 *
 * </pre>
 */
@Component("gravatarGenerator")
public class GravatarGenerator {
	@Autowired
	private CourageProperties courageProperties;
	
	public String getGravatarURL(String email) {
		int size = Integer.parseInt(courageProperties.getLink().getGravatar().getSize());
		
		return this.getGravatarURL(email, size);
	}
	
	public String getGravatarURL(String email, int size) {
		return this.getGravatarURL(email, size, courageProperties.getLink().getGravatar().getDefaultImg());
	}
	
	public String getGravatarURL(String email, int size, String defaultImg) {
		String preURL = courageProperties.getLink().getGravatar().getUrl();
		String resultStr = "";
		String params = "?";
		
		if (email != null && !email.equals("")) {
			resultStr = MD5Util.md5Hex(email);
		}
		
		if (size >= 1 && size <= 2048) {
			params += "s=" + size;
		} else {
			params += "s=200";
		}
		
		if (defaultImg != null && !defaultImg.equals("")) {
			try {
				params += "&d=" + URLEncoder.encode(defaultImg, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				params += "&d=identicon";
			}
		} else {
			params += "&d=identicon";
		}
		
		return preURL + resultStr + params;
	}
}
