package kr.pe.courage.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageInfoVO {
	private int offset;
	private int pageSize;
}
