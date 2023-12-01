package kr.co.thefesta.scheduler.domain;

import lombok.Data;

@Data
public class SchedulerCri {
	private int date;
	private int countyValue;
	private int districtValue;
	private String keyword;
	
	public String[] getKeywordArr() {
		return keyword == null ? new String[] {""} : keyword.split(" ");
	}
}


