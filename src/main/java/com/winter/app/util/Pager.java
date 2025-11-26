package com.winter.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {
	
	private Long page;

	private Long startNum;
//	private Long countNum; // perPage와 같은거라 주석처리or지움

//	private Long totalPage; // 전체 페이지 갯수
	private Long perPage; // 한 페이지당 보여줄 글의 갯수
	private Long perBlock;// 한 블럭당 출력할 번호의 갯수
	
	private Long begin;
	private Long end;
	
	public Long getPerBlock() {
		if(this.perBlock == null || this.perBlock < 1 || this.perBlock % 5 != 0) {
			this.perBlock = 5L;
		}
		return this.perBlock;
	}
	
	public Long getPerPage() { //게터에서 유효성검사를 다해버리네.
		if(this.perPage == null || this.perPage < 1 || this.perPage % 5 != 0) {
			this.perPage = 10L;
		}
		return this.perPage;
	}
	
	public Long getPage() { // 데이터의 은닉화, 데이터의 안정성을위해 게터,세터씀
		if(this.page == null || this.page < 1) {
			this.page=1L;
		}
		
		return this.page;
	}
	
	// 1. 페이징 계산 (멤버 변수 처음나오는 것들은 get을 이용하기로 했음)
//	a) 총 글의 갯수
	public void pageing(Long totalCount) throws Exception {
//	b) 총 글의 갯수로 총 페이지 수 구하기
		Long totalPage = totalCount / this.getPerPage();
		if(totalCount % this.perPage != 0) {
			totalPage++;
		}
		
//	page 값이 totalpage 값의 범위를 벗어난 경우 (보다 큰 경우)
		if(this.getPage() > totalPage ) {
			this.page = totalPage;
		}
		
//	c) 총 페이지 수로 총 블럭수 구하기
		Long totalBlock = totalPage / this.getPerBlock();
		if(totalPage % this.perBlock != 0) {
			totalBlock++;
		}
//	d) 페이지 번호로 현재 블럭 번호 구하기
		Long curBlock = this.getPage()/this.perBlock;
		if(this.page % this.perBlock != 0) {
			curBlock++;
		}
//	e) 현재 블럭 번호로 시작 번호와 끝 번호 구하기
		this.begin = 1 + this.perBlock*(curBlock-1);
		this.end = curBlock * this.perBlock;
//	f) 마지막 블럭이라면 끝 번호를 총 페이지 수로 대입하기
		if(curBlock >= totalBlock) {
			this.begin = 1 + this.perBlock*(totalBlock-1); //curBlock을 totalBlock으로 바꾸었음.
			this.end = totalPage;
		}
		
		this.makeStartNum();
	}
	
	// 2. DB에서 일정한 갯수만큼 조회하도록 계산
	private void makeStartNum() throws Exception {
		this.startNum = (page-1) * perPage;
	}
}
