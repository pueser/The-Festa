package kr.co.thefesta.admin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ReportDTO {
	   
	//신고번호
   private int reportid;
   //신고처리상태
   private int reportstate;
   //신고일자
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "GMT+9")
   private Date reportdate;
   //신고내용
   private String reportcontent;
   //신고대상
   private String reportnumber;
   //신고한 회원(이메일)
   private String reporter;
   //신고당한 회원(이메일)
   private String reported;
   //게시글 댓글번호
   private int rbrno; 
   //축제 댓글번호
   private int rfrno;
   //게시글 번호
   private int rbid;
   
   
   //MEMBER DTO
   //마지막 접속일
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "GMT+9")
   private Date finalaccess;
   //상태코드
   private String statecode;
	   
}
