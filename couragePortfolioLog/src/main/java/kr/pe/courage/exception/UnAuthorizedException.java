package kr.pe.courage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * kr.pe.courage.exception
 * UnAuthorizedException.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2017. 6. 24.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2017. 6. 24., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "API 이용권한 없음")
public class UnAuthorizedException extends RuntimeException {
	private static final long serialVersionUID = 4335985192692518020L;
}
