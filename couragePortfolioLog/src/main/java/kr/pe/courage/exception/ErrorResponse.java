
package kr.pe.courage.exception;

/**
 * <pre>
 * kr.pe.courage.exception
 * ErrorResponse.java
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
public class ErrorResponse {
	private long timestamp;
	private int status;
	private String error;
	private String exception;
	private String message;
	private String path;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
