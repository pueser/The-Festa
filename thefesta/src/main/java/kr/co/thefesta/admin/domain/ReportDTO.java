package kr.co.thefesta.admin.domain;

import java.sql.Timestamp;

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
   //등록일자
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
   private Timestamp reportregist;
   //신고내용
   private String reportcontent;
   
   
   
   //신고자(이메일)
   private String id;
   //게시글 댓글번호
   private int brno; 
   //축제 댓글번호
   private int frno;
   //게시글 번호
   private int bid;
	   
}
