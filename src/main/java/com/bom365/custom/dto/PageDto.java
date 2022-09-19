package com.bom365.custom.dto;

import lombok.*;

/*
 * 	/test
 * 			****페이징 구상도****	
 * 
 * 		case 1:
 * 		<	1	2	3	4	5	>
 * 		ㄴprePage				ㄴnextPage
 * 
 * 		<	1	2	3	..	99	>
 * 			ㄴstartPage		 ㄴendPage
 * 
 * 		
 * 
 * 		case 2:
 * 		<	11	2	3	..	20	>
 * 			ㄴstartPage		 ㄴendPage			
 * 
 *		case 3:
 * 		<	6	2	3	4	10	>
 * 		ㄴprePage				ㄴnextPage
 * 
 * 		prePage = starpage -1; if(prePage < 1) prePage = defaultStartPage;
 * 		nextpage = endpage +1;	i
 * 
 */



@Getter
@Setter
@ToString
public class PageDto {
	//https://po9357.github.io/spring/2019-05-28-Board_Paging/
/*
	
	
	private final Long defaultSize = (long) 10L;

	private final Long defaultCountPage = (long) 5L;
	private final Long defaultStartPage = (long) 1L;
*/	
	
	
	//현재 페이지 
	private Long Page;
	
	//보여줄 페이지 갯수
	private Long pageSize = 5L; 
	// 페이지에 보여줄 글 갯수
	private Long pagePerText;
	private Long totalPage;
	
	private Long prePage;
	private Long nextPage;
	
	private Long startPage =1L;
	private Long endPage;
	
	//마지막 페이지 계산
	private Long lastPage;
	
	private Long start;
	private Long end;
	
	/*
	 * 	1 - 1~10
	 * 	2 - 11~20
	 * 	3 - 21~30
	 */
	
	public PageDto() {
		
	}
	
	// 코드 리팩토링 하기 
	public PageDto(Long page,Long pagePerText,Long totalPage) {
		
		
		this.Page = (page == null) ? 1L : page;
		this.pagePerText = (pagePerText == null) ? 5L : pagePerText;
		this.totalPage = totalPage;
		this.lastPage =(this.totalPage/this.pagePerText)+1;
		this.endPage = this.Page + this.pageSize;
		calcStartEndPage();
		calcStartEnd();
		
		setNextPage(getEndPage()+getPageSize());
		setPrePage((getStartPage()-getPageSize())<= 1L ? 1L :  getStartPage()-getPageSize());
		//setEndPage((getStartPage()+getPageSize()) > getLastPage() ? getLastPage() :(getStartPage()+getPageSize()));
		
		
	}
	public void calcStartEndPage() {
		Long var = (long) Math.ceil((double)getPage()/getPageSize()) * getPageSize();
		if(var <= getLastPage())
		setEndPage(var);
		setStartPage(getEndPage()-getPageSize()+1);
		
		
	}
	
	/*
	public void calcStartEndPage() {
		
		setStartPage((long) Math.ceil((double)getPage()/getPageSize())*getPageSize() -getPageSize() );
		setEndPage(getStartPage()+getPageSize()-1);
		if(getEndPage()>= getLastPage()) {
			setEndPage(getLastPage());
		}
		
	}
	*/
	
	/*
	 * public void calcStartEndPage() {
		
		if(this.endPage <= this.lastPage) {
			this.endPage = this.Page + this.pageSize;
		}
		this.startPage = this.Page;
		
		
		if(this.startPage < 1L) {
			this.startPage = 1L;
		}
	}
	 */
	
	//DB 조회용 메서드 계산 
	public void calcStartEnd() {
		
		setEnd(getPage()*getPagePerText());
		setStart((getEnd()-getPagePerText())+1);
	}
	
	
	
	public void calNextPage() {
		
		if(this.Page == this.nextPage) {
			
			this.endPage = this.Page + this.pageSize;
			this.startPage = this.endPage - this.pageSize;
		}

	}
	
	public void calPrevPage() {
		setStartPage(getStartPage()-getPageSize());
		setEndPage(getStartPage()+getPageSize());
		if(getStartPage() <= 1) {
			setStartPage(1L);
			return;
		}
		
		
		
	}
	
	

	
	
	
}
