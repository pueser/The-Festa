package kr.co.thefesta.admin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class QuestionDTO {
	
	//문의번호
    private int questionid;
    //문의내용
    private String questioncontent;
    //문의일자
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "GMT+9")
    private Date questiondate;
    
    //문의한 회원
    private String id;
    //문의된 축제id
    private String contentid;
    
    //축제이름
    //축제시작일자
    //축제종료일자
    //축제주소
    //문의 count

}
