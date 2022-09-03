package com.bom365.custom.dto;

import lombok.*;

/*
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
	/*
	//default  total page 
	private final Long defaultTotalPage = (long) 50L;
	//default page per text 
	private final Long defaultSize = (long) 10L;
	//default page count 
	private final Long defaultCountPage = (long) 5L;
	private final Long defaultStartPage = (long) 1L;
	
	private Long prePage = 1L;
	private Long nextPage = this.endPage +1L;
	
	//현재 페이지 
	private Long Page = 1L;
	https://po9357.github.io/spring/2019-05-28-Board_Paging/
	*/
	//custom
	private Long totalPage = 0L;
	private Long lastPage;
	//page per text 
	private Long pageSize = 5L; 
	
	private Long countPage;
	
	
	private Long startPage ;
	private Long Page;
	private Long endPage;
	
	
	private Long start= 1L;
	private Long end;
	
	public PageDto() {
		
	}
	
	public PageDto(Long total, Long page, Long countPage) {
		setPage(page);
		setCountPage(countPage);
		setTotalPage(total);
		
		calcLastPage(getTotalPage(),getCountPage());
		calcStartEndPage(getPage(),pageSize);
		calcStartEnd(getPage(), getCountPage());
		
	}
	
	public void calcLastPage(Long total, Long countPage) {
		long set = (long) Math.ceil((double)(total /countPage));
		
		setLastPage(set);
	}
	
	
	public void calcStartEndPage(Long page, Long countPage) {
		
		long set = (page+countPage)/2;
		System.out.println((page)+" "+countPage);
		System.out.println((page /countPage));
		
		setEndPage(set * countPage);
		
		if(getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		setStartPage((getEndPage()-countPage)+1);
		
		if(getStartPage() < 1) {
			setStart(1L);    
		}
		
	}
	
	public void calcStartEnd(Long page, Long countPage) {
		setEnd(page*countPage);
		setStart((getEnd() - countPage)+1);
	}
	
	
	
	/*

	
	public void changePrePage() {
		
		this.prePage = this.startPage - 1L; 
		if(this.prePage <= 1L) {
			
			this.prePage += this.defaultStartPage;
			return;
		}
		
		
	}
	public void setStartEnd() {
		this.start = this.startPage + this.defaultSize;
		
		if(this.size == 0L) {
			this.end = (this.start+ this.defaultSize)-1;
			return;
		}
		this.end = (this.start + this.defaultSize)-1;
		return;
	}
	
	
	public void changeStartUp() {
		if(this.countPage ==  0L) {
			
			this.startPage += this.defaultCountPage; 
			return;
		}
		
		this.startPage += this.countPage;
		return;
	}
	
	
	public void changeStartDown() {
		
		if(this.countPage == 0L) {
			if((this.startPage - this.defaultCountPage) < this.defaultStartPage) {
				
				this.startPage = this.defaultStartPage;
				
				return;
			}
			this.startPage -= this.defaultCountPage; 
			return;
		}
		if((this.startPage - this.defaultCountPage) < this.defaultStartPage) {
			this.startPage = this.defaultStartPage;
			
			return;
		}
		
		this.startPage -=this.countPage;
		return;
	}
	
	public void changenextPage() {
		this.nextPage = this.endPage +1L;
		
	}
	
	
	public void changeEndPage() {
		
		if(this.countPage == 0L) {
			this.endPage = (this.startPage+this.defaultCountPage)-1;
			return;
		}
		
		this.endPage = (this.startPage+this.countPage)-1;
		return;
	}
	*/
	
	
	

	
	
	
}
