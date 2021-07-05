package kr.pe.courage.contact.domain;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import kr.pe.courage.common.CreateValidateGroup;
import kr.pe.courage.common.ModifyValidateGroup;
import kr.pe.courage.common.PaginationVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ContactVO {
	private int sno;
	
	@NotEmpty(
			message = "이름은 필수 항목 입니다.",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String name;
	
	@NotEmpty(
			message = "이름은 필수 항목 입니다.",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String email;
	
	private String phone;
	
	@NotEmpty(
			message = "메시지는 필수 항목 입니다.",
			groups = {CreateValidateGroup.class, ModifyValidateGroup.class})
	private String message;
	
	@Pattern(
			message = "회신의 값은 Y 또는 N 이어야 합니다.",
			regexp = "^(Y|N)$",
			groups = ContactReplyModifyValidateGroup.class)
	private String reply;
	
	private Date registerDate;
}
