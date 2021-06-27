
package kr.pe.courage.member.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@Getter
@ToString
public class MemberVO {
	private String sno;
	
	@NotEmpty(
			message = "이름은 필수 항목 입니다.",
			groups = {MemberUpdateValidate.class})
	private String name;
	
	@JsonInclude(Include.NON_EMPTY)
	private String salt;
	
	@NotEmpty(
			message = "비밀번호는 필수 항목 입니다.",
			groups = {MemberSignInValidate.class, MemberChangePasswordValidate.class})
	@Pattern(
			regexp = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])).{8,20}",
			message = "숫자, 영문자, 특수문자 조합 8~20자리",
			groups = {MemberSignInValidate.class, MemberChangePasswordValidate.class})
	@JsonInclude(Include.NON_EMPTY)
	private String password;
	
	@NotEmpty(
			message = "변경 비밀번호는 필수 항목 입니다.",
			groups = {MemberChangePasswordValidate.class})
	@Pattern(
			regexp = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])).{8,20}",
			message = "숫자, 영문자, 특수문자 조합 8~20자리",
			groups = {MemberChangePasswordValidate.class})
	@JsonInclude(Include.NON_EMPTY)
	private String newPassword;
	
	@NotEmpty(
			message = "Email은 필수 항목 입니다.",
			groups = {MemberUpdateValidate.class})
	@Pattern(
			regexp = "^([A-Za-z0-9_\\-\\.\\'])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,6})$",
			message = "Email형식이 맞지 않습니다.",
			groups = {MemberUpdateValidate.class})
	private String email;
	private String gravatarUrl;
}
