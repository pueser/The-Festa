package kr.co.thefesta.board.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
	    this.total = total;

	    // 현재 페이지를 기준으로 5의 배수로 올림하여 endPage를 설정합니다.
	    this.endPage = (int) (Math.ceil(cri.getPageNum() / 5.0)) * 5;
	    
	    // startPage는 endPage에서 4를 뺀 값으로 설정합니다.
	    this.startPage = this.endPage - 4;

	    // 전체 페이지 수를 계산합니다.
	    int realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));

	    // 만약 계산된 실제 끝 페이지가 endPage보다 작거나 같으면 endPage를 조정합니다.
	    if (realEnd <= this.endPage) {
	        this.endPage = realEnd;
	    }

	    // 이전 페이지가 있는지 여부를 설정합니다.
	    this.prev = this.startPage > 1;

	    // 다음 페이지가 있는지 여부를 설정합니다.
	    this.next = this.endPage < realEnd;
	}
}
