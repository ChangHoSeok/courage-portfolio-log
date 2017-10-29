
package kr.pe.courage.common.link.gravatar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 * kr.pe.courage.common.link.gravatar
 * MD5Util.java
 * </pre>
 *
 * @Author : ChangHo Seok
 * @Date : 2017. 10. 29.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2017. 10. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 *      </pre>
 */
public class MD5Util {
	public static String md5Hex(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		
		return null;
	}

	private static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}

		return sb.toString();
	}
}
