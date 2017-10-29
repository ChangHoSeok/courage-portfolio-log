
package kr.pe.courage.common.link.gravatar;

import org.junit.Before;
import org.junit.Test;

public class TestGravatar {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMD5Hash() {
		String email = "schkkh@naver.com";
		System.out.println(MD5Util.md5Hex(email));
	}

}
