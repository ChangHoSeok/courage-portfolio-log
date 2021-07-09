package kr.pe.courage.common;

import java.util.List;

/**
 * <pre>
 * kr.pe.courage.common
 * PaginationVO.java
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
public class PaginationVO {
	private int currentPage = 1;
	private int pageSize = 15;
	private int offset;
	
	private int totalCnt;
	private int totalPageSize;
	private boolean hasNextPage;
	
	private List<Object> resultList;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int page) {
		this.currentPage = page;
		
		if (this.pageSize > 0) {
			setOffset();
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int size) {
		this.pageSize = size;
		
		if (this.currentPage > 0) {
			setOffset();
		}
	}

	public int getOffset() {
		return offset;
	}
	
	private void setOffset() {
		this.offset = (this.currentPage * this.pageSize) - this.pageSize;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
		
		if (this.totalCnt > 0) {
			this.totalPageSize = Math.round(this.totalCnt / this.pageSize);
			
			if (this.currentPage < this.totalPageSize) {
				this.hasNextPage = true;
			} else {
				this.hasNextPage = false;
			}
		}
	}

	public int getTotalPageSize() {
		return totalPageSize;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}
}
