package kr.co.thefesta.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SchedulerDTO {

	private String 	contentid			;
	private Integer eventstartdate		;
	private Integer eventenddate		;
	private int 	mapx				;
	private int 	mapy				;
	private String 	title				;
	private String 	addrl				;
	private int 	mlevel				;
	private int 	acode				;
	private int 	scode				;
	private String 	homepage			;
	private String 	agelimit			;
	private String 	sponsor1			;
	private String 	sponsor1tel			;
	private String 	sponsor2			;
	private String 	sponsor2tel			;
	private String 	usetimefestival		;
	private String 	playtime			;
	private String 	eventintro			;
	private String 	eventext			;
}
