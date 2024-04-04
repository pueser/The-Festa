DROP VIEW admin_member_reportstate_1;
DROP VIEW admin_member_reportstate_2;
DROP VIEW admin_member_reportstate_0;
DROP VIEW admin_festa_a;
DROP VIEW admin_festa_b;
DROP TABLE report;
DROP TABLE festa_reply;	
DROP TABLE festa_like;
DROP TABLE festa_image;
DROP TABLE festa_area;
DROP TABLE food_like;
DROP TABLE admin_question;
DROP TABLE festa;
DROP TABLE board_image;	
DROP TABLE board_reply;	
DROP TABLE board;
DROP TABLE food;
DROP TABLE member;
DROP TABLE state;
DROP SEQUENCE seq_ffileno;
DROP SEQUENCE seq_bid;
DROP SEQUENCE seq_brno;
DROP SEQUENCE seq_reportid;
DROP SEQUENCE seq_ano;
DROP SEQUENCE seq_frno;
DROP SEQUENCE seq_questionid;
DROP SEQUENCE food_like_seq;
CREATE TABLE member (
    id             VARCHAR2(50),
    password       VARCHAR2(16)  NOT NULL,
    nickname       VARCHAR2(30),
    profileImg     VARCHAR2(100) DEFAULT 'http://localhost:9090/resources/fileUpload/profile.png',
    agreement      VARCHAR2(1)   NOT NULL,
    joindate       DATE          DEFAULT  SYSDATE,
    finalaccess    DATE          DEFAULT  SYSDATE,
    withdrawdate   DATE,
    reportnum      NUMBER(2)     DEFAULT 0,
    statecode      VARCHAR2(1)   NOT NULL,
    updatedate     DATE          DEFAULT  SYSDATE
);
CREATE TABLE state (
    statecode      VARCHAR2(1) CONSTRAINT member_statecode_pk PRIMARY KEY,
    login          VARCHAR2(1) CONSTRAINT member_login_nn NOT NULL,
    join           VARCHAR2(1) CONSTRAINT member_join_nn NOT NULL
);
CREATE TABLE festa(
    contentid 		VARCHAR2(20) 		NOT NULL,
    title 		VARCHAR2(100) 		NOT NULL,
    eventstartdate 	VARCHAR2(10) 		NOT NULL,
    eventenddate 	VARCHAR2(10) 		NOT NULL,
    addr1 		VARCHAR2(1000),
    eventintro 		CLOB,
    eventtext 		CLOB,
    homepage 		VARCHAR2(1000),
    agelimit 		VARCHAR2(1000),
    sponsor1 		VARCHAR2(1000),
    sponsor1tel 	VARCHAR2(1000),
    sponsor2 		VARCHAR2(1000),
    sponsor2tel 	VARCHAR2(1000),
    usetimefestival 	VARCHAR2(1000),
    playtime 		VARCHAR2(1000),
    firstimage 		VARCHAR2(100) 		DEFAULT 'defaultfestivalimg.jpg',
    firstimage2 	VARCHAR2(100) 		DEFAULT 'defaultfestivalimg.jpg',
    acode 		NUMBER(5),
    scode 		NUMBER(5),
    mapx 		NUMBER,
    mapy 		NUMBER
);




CREATE TABLE festa_area(
    ano 	NUMBER(10) 	NOT NULL,
    acode 	NUMBER(5) 	NOT NULL,
    aname 	VARCHAR2(50) 	NOT NULL,
    scode 	NUMBER(5) 	NOT NULL,
    sname 	VARCHAR2(50) 	NOT NULL
);
CREATE TABLE festa_reply 
(
    	frno 		NUMBER(20) 	    	NOT NULL,
    	contentid 	VARCHAR2(20) 		NOT NULL,
        id 		VARCHAR2(50) 		NOT NULL,
    	nickname 	VARCHAR2(30) 		NOT NULL,
    	frcontent 	VARCHAR2(3000) 		NOT NULL,
    	frregist 	TIMESTAMP     	    	DEFAULT sysdate,
    	fredit 		TIMESTAMP    	    	DEFAULT sysdate
);
CREATE TABLE festa_like
(
	lno           	NUMBER(5)   	NOT NULL,
	contentid       VARCHAR2(20)   	NOT NULL,
	id       	    VARCHAR2(50)   	NOT NULL
);
CREATE TABLE festa_image(
    ffileno 		NUMBER(38) 	NOT NULL,
    contentid 		VARCHAR2(20) 	NOT NULL,
    serialnum 		VARCHAR2(30) 	NOT NULL,
    originimgurl 	VARCHAR2(100) 	DEFAULT 'defaultfestivalimg.jpg',
    smallimageurl 	VARCHAR2(100) 	DEFAULT 'defaultfestivalimg.jpg'
);
CREATE TABLE board 
(
    	bid 		NUMBER(12) 	    NOT NULL,
    	bno 		NUMBER(2) 	    DEFAULT 1,
    	nickname 	VARCHAR2(30) 	NOT NULL,
    	btitle 		VARCHAR2(150) 	NOT NULL,
    	bcontent 	VARCHAR2(3000) 	NOT NULL,
    	bregist 	DATE 	        DEFAULT sysdate,
    	bedit 		DATE 	        DEFAULT sysdate,
    	bviewcnt 	NUMBER 		    DEFAULT 0,
    	breplycnt 	NUMBER 		    DEFAULT 0,
    	id 		    VARCHAR2(50) 	NOT NULL,
        bstatecode   VARCHAR(1)    DEFAULT 'Y'
);
CREATE TABLE board_reply 
(
    	bid 		NUMBER(12) 	    NOT NULL,
    	brno 		NUMBER(12) 	    NOT NULL,
    	nickname 	VARCHAR2(30) 	NOT NULL,
    	brcontent 	VARCHAR2(300) 	NOT NULL,
    	brregist 	DATE 	        DEFAULT sysdate,
    	bredit 		DATE 	        DEFAULT sysdate,
    	id 		    VARCHAR2(50) 	NOT NULL,
        brstatecode   VARCHAR(1)    DEFAULT 'Y'
);
CREATE TABLE board_image 
(
    	bfilename 	VARCHAR2(100) 	NOT NULL,
    	bfileuuid 	VARCHAR2(40) 	NOT NULL,
    	bid 		NUMBER(12) 	    NOT NULL
);
CREATE TABLE report 
(
    reportid 	    NUMBER(12) 	    NOT NULL,
    reportstate 	NUMBER(1) 	    DEFAULT 1,
	reportcontent   VARCHAR2(3000)	NOT NULL,
	reportdate		DATE	        DEFAULT sysdate,
	reporter		VARCHAR2(50)	NOT NULL,
	reported		VARCHAR2(50)	NOT NULL,
	rbid			NUMBER(12),
	rbrno			NUMBER(12),
	rfrno			NUMBER(20),
    reportnumber    VARCHAR2(20)
);


CREATE TABLE admin_question
(
    	questionid 	        VARCHAR2(12) 	NOT NULL,
    	questioncontent 	VARCHAR2(3000) 	    NOT NULL,
    	questiondate 	    DATE 	    DEFAULT sysdate,
    	id             	VARCHAR2(50)	NOT NULL,
        contentid           VARCHAR2(20)    NOT NULL
);


CREATE TABLE food
(
    contentid           VARCHAR2(10)     CONSTRAINT food_id_pk PRIMARY KEY,             -- 음식점 번호
    title               VARCHAR2(200)    CONSTRAINT food_title_nn NOT NULL,             -- 음식점명
    addr1               VARCHAR2(200)    CONSTRAINT food_addr_nn NOT NULL,              -- 주소
    infocenterfood      VARCHAR2(500)    DEFAULT 'no info',                             -- 전화
    firstmenu           VARCHAR2(500)    DEFAULT 'no info',                             -- 대표메뉴
    treatmenu           VARCHAR2(1000)    DEFAULT 'no info',                             -- 취급메뉴
    opentimefood        VARCHAR2(500)    DEFAULT 'no info',                             -- 운영시간    
    restdatefood        VARCHAR2(500)    DEFAULT 'no info',                             -- 휴무일
    parkingfood         VARCHAR2(500)    DEFAULT 'no info',                             -- 주차장 유무
    overview            VARCHAR2(3000)   DEFAULT 'no info',                             -- 소개글
    firstimage          VARCHAR2(100)    DEFAULT 'no image',                            -- 대표 이미지
    firstimage2         VARCHAR2(100)    DEFAULT 'no image',                            -- 썸네일 이미지
    mapx                VARCHAR2(20)     CONSTRAINT food_mapx_nn NOT NULL,              -- x 좌표
    mapy                VARCHAR2(20)     CONSTRAINT food_mapy_nn NOT NULL,              -- y 좌표    
    areacode            VARCHAR2(2)      CONSTRAINT food_areacode_nn NOT NULL,          -- 지역 코드
    sigungucode         VARCHAR2(2)      CONSTRAINT food_sigungucode_nn NOT NULL,       -- 시군구 코드    
    cat3                VARCHAR2(10)     DEFAULT 'no info'                              -- 음식 종류
);
CREATE TABLE food_like
(
    lno                 NUMBER(5)        CONSTRAINT food_like_lno_pk PRIMARY KEY,
    contentid           VARCHAR2(10)     NOT NULL,
    title               VARCHAR2(200)    NOT NULL,
    cat3                VARCHAR2(10)     DEFAULT 'no info',
    id                  VARCHAR2(50)     NOT NULL
);


CREATE OR REPLACE VIEW admin_festa_a
AS
SELECT  title,eventstartdate,eventenddate,contentid,addr1,questioncount
FROM (
        SELECT festa.title, festa.eventstartdate, festa.eventenddate, festa.contentid, festa.addr1, count(*) as questioncount
        FROM admin_question 
        INNER JOIN festa
        ON festa.contentid = admin_question.contentid
        GROUP BY  festa.title,festa.eventstartdate,festa.eventenddate,festa.contentid,festa.addr1

);

CREATE OR REPLACE VIEW admin_festa_b
AS
SELECT title, eventstartdate, eventenddate, contentid, addr1, 0 as questioncount
FROM festa 
WHERE contentid not in(
    SELECT contentid
    FROM admin_question
);

CREATE OR REPLACE VIEW admin_member_reportstate_2
AS
SELECT id,statecode,finalaccess, reportnum, totalreportnum
FROM(
    SELECT  member.id, member.statecode, member.finalaccess, member.reportnum, count(*) as totalreportnum
    FROM member
    INNER JOIN report
    ON report.reported = member.id
    WHERE reportstate = 2
    GROUP BY  member.id, member.statecode, member.finalaccess, member.reportnum
);

CREATE OR REPLACE VIEW admin_member_reportstate_0
AS
SELECT  id, statecode, finalaccess, reportnum, 0 as totalreportnum
FROM member
WHERE id not in(
    SELECT reported
    FROM report
);

CREATE OR REPLACE VIEW admin_member_reportstate_1
AS
SELECT id,statecode,finalaccess,reportnum,totalreportnum
FROM(
    SELECT C.id, C.statecode,C.finalaccess, C.reportnum, 0 as totalreportnum
    FROM (
        SELECT id,statecode,finalaccess, reportnum, totalreportnum
        FROM(
                SELECT  member.id, member.statecode, member.finalaccess, member.reportnum, count(*) as totalreportnum
                FROM member
                INNER JOIN report
                ON report.reported = member.id
                WHERE reportstate = 1 OR reportstate = 4
                GROUP BY  member.id, member.statecode, member.finalaccess, member.reportnum
            )
    ) C
    LEFT OUTER JOIN admin_member_reportstate_2 A
    ON C.ID = A.ID
    WHERE A.ID IS NULL
);

ALTER TABLE member
ADD CONSTRAINT member_id_pk PRIMARY KEY(id);
ALTER TABLE member
ADD CONSTRAINT member_nickname_pk UNIQUE(nickname);
ALTER TABLE member
ADD CONSTRAINT member_statecode_fk FOREIGN KEY(statecode) REFERENCES state(statecode);

ALTER TABLE festa_area
ADD CONSTRAINT festa_area_ano_pk PRIMARY KEY(ano);

ALTER TABLE festa
ADD CONSTRAINT festa_contentid_pk PRIMARY KEY(contentid);

ALTER TABLE festa_reply
ADD CONSTRAINT festa_reply_frno_pk PRIMARY KEY(frno);
ALTER TABLE festa_reply
ADD CONSTRAINT festa_reply_contentid_fk FOREIGN KEY(contentid) REFERENCES festa(contentid)ON DELETE CASCADE;
ALTER TABLE festa_reply
ADD CONSTRAINT festa_reply_id_fk FOREIGN KEY(id) REFERENCES member(id);

ALTER TABLE festa_like
ADD CONSTRAINT festa_like_lno_pk PRIMARY KEY(lno);
ALTER TABLE festa_like
ADD CONSTRAINT festa_like_contentid_fk FOREIGN KEY(contentid) REFERENCES festa(contentid)ON DELETE CASCADE;
ALTER TABLE festa_like
ADD CONSTRAINT festa_like_id_fk FOREIGN KEY(id) REFERENCES member(id);

ALTER TABLE festa_image
ADD CONSTRAINT festa_image_ffileno_pk PRIMARY KEY(ffileno);
ALTER TABLE festa_image
ADD CONSTRAINT festa_image_contentid_fk FOREIGN KEY(contentid) REFERENCES festa(contentid) ON DELETE CASCADE;

ALTER TABLE board
ADD CONSTRAINT board_bid_pk PRIMARY KEY (bid);
ALTER TABLE board
ADD CONSTRAINT board_nickname_fk FOREIGN KEY(nickname) REFERENCES member(nickname);
ALTER TABLE board
ADD CONSTRAINT board_id_fk FOREIGN KEY(id) REFERENCES member(id);

ALTER TABLE board_reply
ADD CONSTRAINT board_reply_brno_pk PRIMARY KEY (brno);
ALTER TABLE board_reply
ADD CONSTRAINT board_reply_bid_fk FOREIGN KEY (bid) REFERENCES board(bid);
ALTER TABLE board_reply
ADD CONSTRAINT board_reply_nickname_fk FOREIGN KEY (nickname) REFERENCES member(nickname);
ALTER TABLE board_reply
ADD CONSTRAINT board_reply_id_fk FOREIGN KEY (id) REFERENCES member(id);

ALTER TABLE board_image
ADD CONSTRAINT board_image_bfileuuid_pk PRIMARY KEY (bfileuuid);
ALTER TABLE board_image
ADD CONSTRAINT board_image_bid_fk FOREIGN KEY (bid) REFERENCES board(bid);

ALTER TABLE report
ADD CONSTRAINT report_reportid_pk PRIMARY KEY (reportid);
ALTER TABLE report
ADD CONSTRAINT report_reporter_fk FOREIGN KEY (reporter) REFERENCES member(id);
ALTER TABLE report
ADD CONSTRAINT report_reported_fk FOREIGN KEY (reported) REFERENCES member(id);
ALTER TABLE report
ADD CONSTRAINT report_rbid_fk FOREIGN KEY (rbid) REFERENCES board(bid);
ALTER TABLE report
ADD CONSTRAINT report_rbrno_fk FOREIGN KEY (rbrno) REFERENCES board_reply(brno);
ALTER TABLE report
ADD CONSTRAINT report_rfrno_fk FOREIGN KEY (rfrno) REFERENCES festa_reply(frno) ON DELETE CASCADE;

ALTER TABLE admin_question
ADD CONSTRAINT admin_question_questionid_pk PRIMARY KEY(questionid);
ALTER TABLE admin_question
ADD CONSTRAINT admin_question_id_fk FOREIGN KEY(id) REFERENCES member(id);
ALTER TABLE admin_question
ADD CONSTRAINT admin_question_contentid_fk FOREIGN KEY(contentid) REFERENCES festa(contentid) ON DELETE CASCADE;

-- 제약조건 추가(food의 contentid를 fk로 사용)
ALTER TABLE food_like
ADD CONSTRAINT food_like_contentid_fk FOREIGN KEY(contentid) REFERENCES food(contentid)ON DELETE CASCADE;
-- 제약조건 추가(member의 id를 fk로 사용)    
ALTER TABLE food_like
ADD CONSTRAINT food_like_id_fk FOREIGN KEY(id) REFERENCES member(id);

CREATE SEQUENCE seq_ffileno
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
CREATE SEQUENCE seq_bid
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
CREATE SEQUENCE seq_brno
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
CREATE SEQUENCE seq_reportid
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
CREATE SEQUENCE seq_ano
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
CREATE SEQUENCE seq_frno
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
CREATE SEQUENCE seq_questionid
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
-- 좋아요 번호 시퀀스 생성
CREATE SEQUENCE food_like_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE;



--------------------
insert into food_like(lno,contentid,title,id) values(food_like_seq.nextval,'2843664','2843664 title','user1@naver.com');
insert into food_like(lno,contentid,title,id) values(food_like_seq.nextval,'2843664','2843664 title','user2@naver.com');
insert into food_like(lno,contentid,title,id) values(food_like_seq.nextval,'2899585','2899585 title','user3@naver.com');
insert into food_like(lno,contentid,title,id) values(food_like_seq.nextval,'2642826','2642826 title','user4@naver.com');
insert into food_like(lno,contentid,title,id) values(food_like_seq.nextval,'2840068','2840068 title','user5@naver.com');
insert into food_like(lno,contentid,title,id) values(food_like_seq.nextval,'134414','134414 title','user6@naver.com');
--회원 test data
INSERT INTO state (statecode, login, join) VALUES('0', 'o', 'x');
INSERT INTO state (statecode, login, join) VALUES('1', 'o', 'x');
INSERT INTO state (statecode, login, join) VALUES('2', 'x', 'x');
INSERT INTO state (statecode, login, join) VALUES('3', 'x', 'o');
INSERT INTO state (statecode, login, join) VALUES('4', 'x', 'x');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('admin1@naver.com', 'admin1password!', 'admin1', 'o', '0');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user1@naver.com', 'user1password!', 'user1', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user2@naver.com', 'user2password!', 'user2', 'o', '2');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user3@naver.com', 'user3password!', 'user3', 'o', '3');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user4@naver.com', 'user4password!', 'user4', 'o', '4');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user5@naver.com', 'user5password!', 'user5', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user6@naver.com', 'user6password!', 'user6', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user7@naver.com', 'user7password!', 'user7', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user8@naver.com', 'user8password!', 'user8', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user9@naver.com', 'user9password!', 'user9', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user10@naver.com', 'user10password!', 'user10', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user11@naver.com', 'user11password!', 'user11', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user12@naver.com', 'user12password!', 'user12', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user13@naver.com', 'user13password!', 'user13', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user14@naver.com', 'user14password!', 'user14', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user15@naver.com', 'user15password!', 'user15', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user16@naver.com', 'user16password!', 'user16', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user17@naver.com', 'user17password!', 'user17', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user18@naver.com', 'user18password!', 'user18', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user19@naver.com', 'user19password!', 'user19', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user21@naver.com', 'user21password!', 'user21', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user22@naver.com', 'user22password!', 'user22', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user23@naver.com', 'user23password!', 'user23', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user24@naver.com', 'user24password!', 'user24', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user25@naver.com', 'user25password!', 'user25', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user26@naver.com', 'user26password!', 'user26', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user27@naver.com', 'user27password!', 'user27', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user28@naver.com', 'user28password!', 'user28', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user29@naver.com', 'user29password!', 'user29', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user30@naver.com', 'user30password!', 'user30', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user31@naver.com', 'user31password!', 'user31', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user32@naver.com', 'user32password!', 'user32', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user33@naver.com', 'user33password!', 'user33', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user34@naver.com', 'user34password!', 'user34', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user35@naver.com', 'user35password!', 'user35', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user36@naver.com', 'user36password!', 'user36', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user37@naver.com', 'user370password!', 'user37', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user38@naver.com', 'user38password!', 'user38', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user39@naver.com', 'user39password!', 'user39', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user40@naver.com', 'user40password!', 'user40', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user41@naver.com', 'user41password!', 'user41', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user42@naver.com', 'user42password!', 'user42', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user43@naver.com', 'user43password!', 'user43', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user44@naver.com', 'user44password!', 'user44', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user45@naver.com', 'user45password!', 'user45', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user46@naver.com', 'user46password!', 'user46', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user47@naver.com', 'user47password!', 'user47', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user48@naver.com', 'user48password!', 'user48', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user49@naver.com', 'user49password!', 'user49', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user50@naver.com', 'user50password!', 'user50', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user51@naver.com', 'user51password!', 'user51', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user52@naver.com', 'user52password!', 'user52', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user53@naver.com', 'user53password!', 'user53', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user54@naver.com', 'user54password!', 'user54', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user55@naver.com', 'user55password!', 'user55', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user56@naver.com', 'user56password!', 'user56', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user57@naver.com', 'user57password!', 'user57', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user58@naver.com', 'user58password!', 'user58', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user59@naver.com', 'user59password!', 'user59', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user60@naver.com', 'user60password!', 'user60', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user61@naver.com', 'user61password!', 'user61', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user62@naver.com', 'user62password!', 'user62', 'o', '1');

--게시글 test data
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, 'user1', '테스트0fdsfdsfdsfdsfdfffffffffffffffffffff1dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd', '테스트를 위한 0001', 'user1@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, 'user2', '테스트02', '테스트를 위한 0002', 'user2@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, 'user3', '테스트03', '테스트를 위한 0003', 'user3@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, 'user4', '테스트04', '테스트를 위한 0004', 'user4@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, 'user5', '테스트05', '테스트를 위한 0005', 'user5@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, 'user6', '테스트06', '테스트를 위한 0006', 'user6@naver.com');

--게시글 댓글 test data
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(1, seq_brno.nextval, 'user1', '테스트를 위한 댓글 0001', 'user1@naver.com');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(1, seq_brno.nextval, 'user1', '테스트를 위한 댓글 0002', 'user2@naver.com');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(2, seq_brno.nextval, 'user1', '테스트를 위한 댓글 0003', 'user3@naver.com');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(3, seq_brno.nextval, 'user1', '테스트를 위한 댓글 0004', 'user4@naver.com');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(4, seq_brno.nextval, 'user1', '테스트를 위한 댓글 0001', 'user1@naver.com');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(5, seq_brno.nextval, 'user1', '테스트를 위한 댓글 0003', 'user3@naver.com');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(6, seq_brno.nextval, 'user1', '테스트를 위한 댓글 0004', 'user4@naver.com');

commit;

rollback;
--축제 test data
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2930662' ,'20221216' ,'20221217','부산원아시아페스티벌', '서울특별시 관악구 봉천로 505 서울원당초등학교');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2867345' ,'20221210' ,'20221217','영종국제도시 세계전통음식축제', '경상북도 예천군 예천읍 남본리');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2861656' ,'20221116' ,'20221217','관악형 광장문화 [상상하는 대로]', '강원특별자치도 양양군 양양읍 남문리 226-2번지');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('141984' ,'20221016' ,'20221217','정동진 해맞이 축제', '경기도 김포시 김포대로 1466-48');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2932765' ,'20221216' ,'20221217','이월드 호러 어드벤처 :  좀비 스테이션', '서울특별시 강남구  영동대로 (513, 코엑스)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('229052' ,'20221116' ,'20221217','항공여행마켓 with 마켓움 크리스마스 페어', '인천광역시 미추홀구 주안로 114-1 구두수선대21');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('140962' ,'20221216' ,'20221212','양평 단월 고로쇠축제', '부산광역시 해운대구 APEC로 55 벡스코');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2769942' ,'20220911' ,'20221217','외계인 대축제', '경상북도 안동시 축제장길 200 탈춤공연장');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2951686' ,'20221211' ,'20221212','동장군축제', '제주특별자치도 서귀포시 성산읍 일출로 284-12');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2498459' ,'20221216' ,'20221217','부안 설(雪)숭어 축제', '인천광역시 강화군 중앙로787번길 8-1 관리소매점');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('2805988' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('700000' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800000' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800001' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800002' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800003' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800004' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800005' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800006' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800007' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800008' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800009' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800010' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800011' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800012' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800013' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800014' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800015' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800016' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800017' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800018' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800019' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800020' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800021' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800022' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800023' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800024' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800025' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800026' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800027' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800030' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800031' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800032' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800033' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800034' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800035' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800036' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800037' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800038' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800039' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800040' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800041' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1,  )
VALUES('800042' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');
INSERT INTO festa(contentid,eventstartdate, eventenddate, title, addr1 )
VALUES('800044' ,'20221213' ,'20221217','눈내림 별내림 불빛축제 (산들소리)', '충청남도 보령시 장밭길 62 신경섭가옥(문화재자료 제1291호)');


commit;

--festa_area text data
INSERT INTO festa_area(ano ,acode, aname, scode, sname )
VALUES(seq_ano.nextval ,1 ,'a',1,'b');

--festa_like test data
INSERT INTO festa_like(lno ,contentid, id)
VALUES(1 ,'1765272','user1@naver.com');
INSERT INTO festa_like(lno ,contentid, id)
VALUES(7 ,'386053','user2@naver.com');
INSERT INTO festa_like(lno ,contentid, id)
VALUES(8 ,'386053','user3@naver.com');
INSERT INTO festa_like(lno ,contentid, id)
VALUES(4 ,'507383','user4@naver.com');
INSERT INTO festa_like(lno ,contentid, id)
VALUES(5 ,'507383','user5@naver.com');
INSERT INTO festa_like(lno ,contentid, id)
VALUES(6 ,'1266642','user6@naver.com');
INSERT INTO festa_like(lno ,contentid, id)
VALUES(9 ,'3033816','user6@naver.com');
INSERT INTO festa_like(lno ,contentid, id)
VALUES(10 ,'3030629','user6@naver.com');
rollback;

--festa_image test data
INSERT INTO festa_image(ffileno ,contentid, serialnum, originimgurl,smallimageurl)
VALUES(seq_ffileno.nextval ,'1765272','defaultfestivalimg.jpg','defaultfestivalimg.jpg','defaultfestivalimg.jpg');
INSERT INTO festa_image(ffileno ,contentid, serialnum, originimgurl,smallimageurl)
VALUES(seq_ffileno.nextval ,'386053','defaultfestivalimg.jpg','defaultfestivalimg.jpg','defaultfestivalimg.jpg');
INSERT INTO festa_image(ffileno ,contentid, serialnum, originimgurl,smallimageurl)
VALUES(seq_ffileno.nextval ,'507599','defaultfestivalimg.jpg','defaultfestivalimg.jpg','defaultfestivalimg.jpg');
INSERT INTO festa_image(ffileno ,contentid, serialnum, originimgurl,smallimageurl)
VALUES(seq_ffileno.nextval ,'1762432','defaultfestivalimg.jpg','defaultfestivalimg.jpg','defaultfestivalimg.jpg');
INSERT INTO festa_image(ffileno ,contentid, serialnum, originimgurl,smallimageurl)
VALUES(seq_ffileno.nextval ,'3062300','defaultfestivalimg.jpg','defaultfestivalimg.jpg','defaultfestivalimg.jpg');
INSERT INTO festa_image(ffileno ,contentid, serialnum, originimgurl,smallimageurl)
VALUES(seq_ffileno.nextval ,'3062259','defaultfestivalimg.jpg','defaultfestivalimg.jpg','defaultfestivalimg.jpg');
INSERT INTO festa_image(ffileno ,contentid, serialnum, originimgurl,smallimageurl)
VALUES(seq_ffileno.nextval ,'507383','defaultfestivalimg.jpg','defaultfestivalimg.jpg','defaultfestivalimg.jpg');

    
    
-- 축제 댓글 test data
INSERT INTO festa_reply (frno, contentid, id, nickname, frcontent)
VALUES (seq_frno.nextval, '800039', 'user1@naver.com', 'user1', '테스트 댓글 내용01');
INSERT INTO festa_reply (frno, contentid, id, nickname, frcontent)
VALUES (seq_frno.nextval, '800039', 'user2@naver.com', 'user2', '테스트 댓글 내용02');
INSERT INTO festa_reply (frno, contentid, id, nickname, frcontent)
VALUES (seq_frno.nextval, '800039', 'user3@naver.com', 'user3', '테스트 댓글 내용03');
INSERT INTO festa_reply (frno, contentid, id, nickname, frcontent)
VALUES (seq_frno.nextval, '800039', 'user3@naver.com', 'user3', '테스트 댓글 내용03');
commit;
rollback;
--건의 테이블
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user1이 2951686 문의내용ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎzzzzzzzzzfdsfsdfdsd', 'user1@naver.com', '1762432');
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user2이 2951686 문의내용', 'user2@naver.com', '800039');
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user3이 140962 문의내용', 'user3@naver.com', '800032');
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user4 2805988 문의내용', 'user4@naver.com', '800031');
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user5 2805988 문의내용', 'user5@naver.com', '800030');
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user1 2951686 문의내용', 'user1@naver.com', '800032');
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user7 2861656문의내용', 'user7@naver.com', '800038');
INSERT INTO admin_question (questionid, questioncontent, id, contentid)
VALUES (seq_questionid.nextval, 'user1 2930662문의내용', 'user1@naver.com', '800032');

--신고 테이블<자유게시판 만 작성함>
--게시글 신고
--user01 : 1번 7번 신고O
--user05 : 5,11번 신고O
--user06 : 12번 신고O, 6번신고X
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user8@naver.com', 
'ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ','1');

INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user8@naver.com', 'user 03이 user01을 1번 게시글 신고한 내용입니다.', '1');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user8@naver.com', 'user 04이 user01을 1번 게시글 신고한 내용입니다.', '1');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user8@naver.com', 'user 02이 user01의 7번 게시글 신고한 내용입니다.', '7');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user3@naver.com', 'user1@naver.com', 'user 01이 user03의 3번 게시글 신고한 내용입니다.', '3');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user5@naver.com', 'user4@naver.com', 'user 04이 user05의 5번 게시글 신고한 내용입니다.', '5');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user6@naver.com', 'user1@naver.com', 'user 01이 user06의 12번 게시글 신고한 내용입니다.', '12');
commit;

INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '2', 'user1@naver.com', 'user2@naver.com', 'user 02이 user01을 31번 게시글 신고한 내용입니다.','31');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '2', 'user1@naver.com', 'user3@naver.com', 'user 03이 user01을 31번 게시글 신고한 내용입니다.', '31');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '2', 'user1@naver.com', 'user4@naver.com', 'user 04이 user01을 31번 게시글 신고한 내용입니다.', '31');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '2', 'user1@naver.com', 'user2@naver.com', 'user 02이 user01의 37번 게시글 신고한 내용입니다.', '37');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '2', 'user5@naver.com', 'user1@naver.com', 'user 01이 user05의 41번 게시글 신고한 내용입니다.', '1');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '2', 'user5@naver.com', 'user4@naver.com', 'user 04이 user05의 47번 게시글 신고한 내용입니다.', '3');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '2', 'user6@naver.com', 'user1@naver.com', 'user 01이 user06의 48번 게시글 신고한 내용입니다.', '4');
--게시글 댓글 신고
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent,rbrno) VALUES(seq_reportid.nextval, '1', 'user3@naver.com', 'user1@naver.com', 'user05번이 user01가 작성한 댓글을 신고한 내용입니다.','37');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent,rbrno) VALUES(seq_reportid.nextval, '1', 'user3@naver.com', 'user3@naver.com', 'user06번이 user02가 작성한 댓글을 신고한 내용입니다.','30');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent,rbrno) VALUES(seq_reportid.nextval, '1', 'user3@naver.com','user3@naver.com', 'user01번이 user03게시글에 작성된 댓글을 신고한 내용입니다.','31');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent,rbrno) VALUES(seq_reportid.nextval, '1', 'user4@naver.com','user3@naver.com', 'user03번이 user04게시글에 작성된 댓글을 신고한 내용입니다.','32');

--축제 댓글 신고
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rfrno) VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user3@naver.com', 'user04번이 user01이 작성한 댓글을 신고 내용입니다.','3');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rfrno) VALUES(seq_reportid.nextval, '1', 'user2@naver.com', 'user3@naver.com', 'user03번이 user02이 작성한 댓글을 신고 내용입니다.','6');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rfrno) VALUES(seq_reportid.nextval, '1', 'user3@naver.com', 'user3@naver.com', 'user05번이 user03이 작성한 댓글을 신고 내용입니다.','10');

commit;
------------------------------------------------------------------------------
UPDATE member
SET statecode = 2, reportnum = 3
WHERE id = 'user12@naver.com';

UPDATE report
SET reportstate = 3
WHERE reportid = 34;

UPDATE board
SET bregist = '23/01/20'
WHERE bid = 30;

UPDATE board_reply
SET brstatecode = 'N'
WHERE bid = 34;

UPDATE report
SET reportstate = '4'
WHERE reported = 'user1@naver.com' AND rbid = '1';

SELECT bregist
FROM board
WHERE bid = 1;

UPDATE  board
SET bstatecode = 'N'
WHERE   bid = 11;

INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, 11, '유저이', '테스트02', '테스트를 위한 0002', 'user2@naver.com');

UPDATE  board_reply
SET brregist = '23/01/20'
WHERE   brno = 3;

INSERT INTO board_reply(bid, brno, nickname, brcontent, id, brstatecode)
VALUES(16, 35, '유저일', '테스트를 위한 댓글 0001', 'user1@naver.com', 'N');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id, brstatecode)
VALUES(16, 34, '유저이', '테스트를 위한 댓글 0002', 'user2@naver.com', 'N');

SELECT *
FROM board_reply
WHERE   brregist = '23/01/20' AND brno in(35, 34);
commit;
---------------------------------------------------
insert into board(bid, bno, nickname, btitle, bcontent, id)
(select seq_bid.nextval, bno, nickname, btitle, bcontent, id from board);

select count(*)
from board
where bstatecode= 'N';

update board
set bstatecode = 'N'
where bno = '1';
commit;
CREATE TABLE board 
(
    	bid 		NUMBER(12) 	    NOT NULL,
    	bno 		NUMBER(2) 	    DEFAULT 1,
    	nickname 	VARCHAR2(30) 	NOT NULL,
    	btitle 		VARCHAR2(150) 	NOT NULL,
    	bcontent 	VARCHAR2(3000) 	NOT NULL,
    	bregist 	DATE 	        DEFAULT sysdate,
    	bedit 		DATE 	        DEFAULT sysdate,
    	bviewcnt 	NUMBER 		    DEFAULT 0,
    	breplycnt 	NUMBER 		    DEFAULT 0,
    	id 		    VARCHAR2(50) 	NOT NULL,
        bstatecode   VARCHAR(1)    DEFAULT 'Y'
);
EXEC DBMS_STATS.GATHER_TABLE_STATS('scott', 'BOARD', CASCADE=>TRUE);
EXEC DBMS_STATS.GATHER_TABLE_STATS('scott', 'REPORT', CASCADE=>TRUE);
EXEC DBMS_STATS.GATHER_TABLE_STATS('scott', 'BOARD_REPLY', CASCADE=>TRUE);
------------------------------------------------------------
        
        EXPLAIN PLAN FOR
        SET AUTOTRACE ON TRACEONLY EXPLAIN;
        SELECT  c.bid
        FROM (
            SELECT bid
            FROM (
                SELECT  board.bid
                FROM    board
                WHERE   board.bstatecode = 'N'
            )a
            LEFT JOIN (
                SELECT  report.rbid
                FROM    report
            )b
            ON a.bid = b.rbid
            WHERE b.rbid is NULL
        )c
        LEFT JOIN (
            SELECT  board_reply.bid
            FROM    board_reply
            WHERE   board_reply.brstatecode = 'Y'
            UNION
            SELECT  bid
            FROM    board_reply
            INNER JOIN   (
                SELECT  report.rbrno
                FROM    report
                WHERE   report.reportstate <> 4
            )a
            ON  board_reply.brno = a.rbrno
        )d
        ON c.bid = d.bid
        WHERE   d.bid is NULL;
        SET AUTOTRACE OFF;
        
        CREATE INDEX idx_board_bstatecode ON board(bstatecode);
        drop index idx_board_bstatecode;
        CREATE INDEX idx_report_rbid ON report(rbid);
        CREATE INDEX idx_board_reply_brstatecode ON board_reply(brstatecode);
        CREATE INDEX idx_board_reply_brno ON board_reply(brno);
-- 새로운 임시 테이블스페이스 생성
CREATE TEMPORARY TABLESPACE temp_ts
TEMPFILE 'C:/Users/hwang/Festa2.sql' SIZE 100M;

-- 현재 세션에서 사용하는 임시 테이블스페이스 변경
ALTER SESSION SET CURRENT_SCHEMA = scott;

-- 인덱스 생성
CREATE INDEX idx_board_bstatecode ON board(bstatecode);

CREATE TEMPORARY TABLESPACE new_temp_ts TEMPFILE 'C:/Users/hwang/new_temp_file.dbf' SIZE 100M;
ALTER SESSION SET TEMPORARY_TABLESPACE = scott;
ALTER DATABASE DATAFILE 'C:\ORACLEXE\APP\ORACLE\ORADATA\XE\USERS.DBF' RESIZE 500M;
ALTER DATABASE DATAFILE 'C:/Users/hwang/Festa1.dbf' RESIZE 500M;

SELECT * FROM V$DATAFILE WHERE NAME = 'C:/Users/hwang/Festa1.sql';
SELECT FILE_NAME FROM DBA_DATA_FILES;
ALTER TABLESPACE USERS ADD DATAFILE 'C:/Users/hwang/Festa1.sql' SIZE 500M;
ALTER TABLESPACE USERS ADD DATAFILE 'C:/Users/hwang/Festa1.dbf' SIZE 500M;
SELECT TABLESPACE_NAME FROM DBA_TABLESPACES;
-------------------------------------------------------------------------------

SELECT  title, eventstartdate, eventenddate, contentid, addr1, questioncount
FROM (
    SELECT title, eventstartdate, eventenddate, contentid, addr1, questioncount, rownum  as rn
    FROM (
        SELECT  title,eventstartdate,eventenddate,contentid,addr1,questioncount
        FROM (
            SELECT festa.title, festa.eventstartdate, festa.eventenddate, festa.contentid, festa.addr1, count(*) as questioncount
            FROM admin_question 
            INNER JOIN festa
            ON festa.contentid = admin_question.contentid
            GROUP BY  festa.title,festa.eventstartdate,festa.eventenddate,festa.contentid,festa.addr1
        )
        UNION
        SELECT title, eventstartdate, eventenddate, contentid, addr1, 0 as questioncount
        FROM festa 
        WHERE contentid not in(
            SELECT contentid
            FROM admin_question
        )
        ORDER BY questioncount DESC
    )
    WHERE rownum > 0
)
WHERE rn > 100;


SELECT title, eventstartdate, eventenddate, contentid, addr1, questioncount, rownum  as rn
    FROM (
        SELECT  title,eventstartdate,eventenddate,contentid,addr1,questioncount
        FROM (
            SELECT festa.title, festa.eventstartdate, festa.eventenddate, festa.contentid, festa.addr1, count(*) as questioncount
            FROM admin_question 
            INNER JOIN festa
            ON festa.contentid = admin_question.contentid
            GROUP BY  festa.title,festa.eventstartdate,festa.eventenddate,festa.contentid,festa.addr1
        )
        UNION
        SELECT title, eventstartdate, eventenddate, contentid, addr1, 0 as questioncount
        FROM festa 
        WHERE contentid not in(
            SELECT contentid
            FROM admin_question
        )
        ORDER BY questioncount DESC
    )
    WHERE rownum < 0;