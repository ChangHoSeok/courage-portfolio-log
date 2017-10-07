package kr.pe.courage.common;

/**
 * <pre>
 * kr.pe.courage.common
 * SearchVO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2017. 6. 28.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2017. 6. 28., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class SearchVO {
	private int page = 1;
	private int size = 15;
	private int offset;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		
		if (this.size > 0) {
			setOffset();
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		
		if (this.page > 0) {
			setOffset();
		}
	}

	public int getOffset() {
		return offset;
	}
	
	private void setOffset() {
		this.offset = (this.page * this.size) - this.size;
	}
}
