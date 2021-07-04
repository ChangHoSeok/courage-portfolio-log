
package kr.pe.courage.setup.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import kr.pe.courage.common.CreateValidateGroup;
import kr.pe.courage.common.ModifyValidateGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * kr.pe.courage.setup.domain
 * SetupVO.java
 * </pre>
 *
 * @Author : ChangHo.Seok
 * @Date : 2017. 11. 7.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * PortfoLog 설정 Value Object
 * 
 * << 개정이력 >>
 * 1. 일자 : 2017. 11. 7., 수정자 : ChangHo.Seok, 내용 : 최초등록
 *      </pre>
 */
@Setter
@Getter
@ToString
public class SetupVO {
	@Min(
			message = "설정 일련번호는 필수 항목 입니다.",
			value = 1,
			groups = {ModifyValidateGroup.class})
	private int pbSno;
	
	@NotEmpty(
			message = "제목은 필수 항목 입니다.",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String title;
	
	@NotEmpty(
			message = "부제목은 필수 항목 입니다.",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String subTitle;
	
	private String mainPhotoUrl;
	
	@NotEmpty(
			message = "About Me(은)는 필수 항목 입니다.",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String aboutMe;
	
	private String createBy;
	
	@Pattern(
			regexp = "^((https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])?",
			message = "URL 형식을 입력하세요",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String github;
	
	@Pattern(
			regexp = "^((https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])?",
			message = "URL 형식을 입력하세요",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String facebook;
	
	@Pattern(
			regexp = "^((https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])?",
			message = "URL 형식을 입력하세요",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String googlePlus;
	
	@Pattern(
			regexp = "^((https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])?",
			message = "URL 형식을 입력하세요",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String twitter;
	
	@Pattern(
			regexp = "^^((https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])?",
			message = "URL 형식을 입력하세요",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String linkedIn;
}
