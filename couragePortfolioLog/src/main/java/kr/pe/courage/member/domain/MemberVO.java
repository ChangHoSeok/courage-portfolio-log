
package kr.pe.courage.member.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import kr.pe.courage.common.CommonVO;

/**
 * <pre>
 * kr.pe.courage.member.domain
 * MemberVO.java
 * </pre>
 *
 * @Date	: 2017. 10. 24.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * 사용자 정보
 * </pre>
 */
public class MemberVO extends CommonVO {
	private String sno;
	private String name;
	
	@NotEmpty(
			message = "비밀번호는 필수 항목 입니다.",
			groups = {MemberSignInValidate.class})
	@Pattern(
			regexp = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])).{8,20}",
			message = "숫자, 영문자, 특수문자 조합 8~20자리",
			groups = {MemberSignInValidate.class})
	@JsonInclude(Include.NON_EMPTY)
	private String password;
	private String email;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
