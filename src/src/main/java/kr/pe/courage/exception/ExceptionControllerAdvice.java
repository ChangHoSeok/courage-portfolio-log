package kr.pe.courage.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <pre>
 * kr.pe.courage.exception
 * ExceptionControllerAdvice.java
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
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(BadSqlGrammarException.class)
	public ResponseEntity<ErrorResponse> badSqlGrammarExceptionHandler(Exception ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError(HttpStatus.INTERNAL_SERVER_ERROR.name());
		error.setException(ex.getClass().getName());
		error.setMessage("SQL관련 오류가 발생되었습니다. 관리자에게 문의하세요.");
		error.setPath(request.getRequestURI());
		
		ex.printStackTrace();
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> httpMessageNotReadableExceptionHandler(Exception ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError(HttpStatus.BAD_REQUEST.name());
		error.setException(ex.getClass().getName());
		error.setMessage("데이터 형식이 잘못되었습니다.");
		error.setPath(request.getRequestURI());
		
		ex.printStackTrace();
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
