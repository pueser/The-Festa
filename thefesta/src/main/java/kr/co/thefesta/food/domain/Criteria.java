package kr.co.thefesta.food.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum; // 현재 페이지 번호
	private int amount; // 페이지당 보여줄 항목 수

	// 기본 생성자: 기본값으로 1페이지에 15개의 항목을 보여주는 경우
	public Criteria() {
		this(1, 15);
	}

	// 매개변수를 받는 생성자: 페이지 번호와 페이지당 보여줄 항목 수를 설정하는 경우
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	// 현재 Criteria 객체의 페이지 정보를 이용하여 URI 문자열을 생성하는 메서드
	public String getListLink() {
		// UriComponentsBuilder를 사용하여 쿼리 파라미터를 추가하고 URI 문자열을 생성
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("").queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount);
		return builder.toUriString();
	}

	// 추가: pageNum * amount 값을 리턴하는 메서드
	public int getPageNumAmount() {
		return pageNum * amount;
	}

	// 추가: pageNum - 1 값을 리턴하는 메서드
	public int getPageNumMinusOne() {
		return pageNum - 1;
	}
}
