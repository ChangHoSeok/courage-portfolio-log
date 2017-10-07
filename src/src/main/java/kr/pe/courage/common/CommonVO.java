
package kr.pe.courage.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <pre>
 * kr.pe.courage.common
 * CommonVO.java
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
public class CommonVO {
	@Override
	public String toString() {
		Class cls = this.getClass();
		Method[] arrMethod = cls.getMethods();
		StringBuffer sb = new StringBuffer(this.getClass().toString());
		
		sb.append(" => \n");

		try {
			for (Method m : arrMethod) {
				if (m.getName().startsWith("get") && !m.getName().equals("getClass")) {
					sb.append(m.getName());
					sb.append(" : ");
					sb.append(m.invoke(this, null));
					sb.append("\n");
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return sb.toString().substring(0, sb.length() - 2);
	}
}
