
package kr.pe.courage.common.util.encrypt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class TestSHAEncryptString {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testSHA512() {
		String encString = SHAEncryptString.encSHA512("test1234!");
		System.out.println(encString);
		assertNotNull(encString);
		assertEquals("jIey0DNUy+wPz9SkpcP8W47EGbsVQs8A/hprAqJI63QgSttc/3Bx/9GeXilw5LvUNMPwvMMhxyCRsQiuodXNog==", encString);
	}
	
	@Test
	public void testSHA512Salt() {
		String salt = "1891978f-b19c-4194-b0c9-c322f894f097";
		String password = "test1234!";
		
		String encString = SHAEncryptString.encSHA512(password, salt);
		System.out.println(encString);

		assertNotNull(encString);
		assertEquals("rjLWgkHdDume2RBCvdAH/ASw9bJSbG0W7n0meXrLDKMiFWfbyWbaHRaVg3swPW6Ky7O2R9OTeetW5gtvElAKFA==", encString);
	}
	
	@Test
	public void testMD5() {
		System.out.println(SHAEncryptString.encMD5("schkkh@naver.com"));
	}
}
