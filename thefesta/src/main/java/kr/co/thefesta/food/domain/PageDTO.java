package kr.co.thefesta.food.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private int realEnd;
	private boolean prev, next;

	private int total;
	private Criteria cri;

	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;

		// 페이지 번호를 표시하기 위해 endPage 및 startPage를 계산
        this.endPage = (int) (Math.ceil(cri.getPageNum() / 5.0)) * 5;
        this.startPage = this.endPage - 4;

        // 총 항목 및 페이지당 항목 수를 기반으로 realEnd 페이지를 계산
        this.realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));

        // realEnd를 초과하면 endPage를 조정
        if (this.realEnd <= this.endPage) {
            this.endPage = this.realEnd;
        }

        // startPage 및 endPage를 기반으로 prev 및 next를 설정
        this.prev = this.startPage > 1;
        this.next = this.endPage < this.realEnd;
	}
}

