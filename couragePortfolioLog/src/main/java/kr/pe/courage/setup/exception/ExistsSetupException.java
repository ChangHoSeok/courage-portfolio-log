package kr.pe.courage.setup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "설정 정보가 이미 등록되어 있습니다.")
public class ExistsSetupException extends Exception {
	private static final long serialVersionUID = 968737369256044729L;

	public ExistsSetupException() {
		super();
	}
	
	public ExistsSetupException(String message) {
		super(message);
	}
	
	public ExistsSetupException(Throwable cause) {
		super(cause);
	}
	
	public ExistsSetupException(String message, Throwable cause) {
		super(message, cause);
	}
}
