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
    contentid           VARCHAR2(10)     CONSTRAINT food_id_pk PRIMARY KEY,             -- ������ ��ȣ
    title               VARCHAR2(200)    CONSTRAINT food_title_nn NOT NULL,             -- ��������
    addr1               VARCHAR2(200)    CONSTRAINT food_addr_nn NOT NULL,              -- �ּ�
    infocenterfood      VARCHAR2(500)    DEFAULT 'no info',                             -- ��ȭ
    firstmenu           VARCHAR2(500)    DEFAULT 'no info',                             -- ��ǥ�޴�
    treatmenu           VARCHAR2(1000)    DEFAULT 'no info',                             -- ��޸޴�
    opentimefood        VARCHAR2(500)    DEFAULT 'no info',                             -- ��ð�    
    restdatefood        VARCHAR2(500)    DEFAULT 'no info',                             -- �޹���
    parkingfood         VARCHAR2(500)    DEFAULT 'no info',                             -- ������ ����
    overview            VARCHAR2(3000)   DEFAULT 'no info',                             -- �Ұ���
    firstimage          VARCHAR2(100)    DEFAULT 'no image',                            -- ��ǥ �̹���
    firstimage2         VARCHAR2(100)    DEFAULT 'no image',                            -- ����� �̹���
    mapx                VARCHAR2(20)     CONSTRAINT food_mapx_nn NOT NULL,              -- x ��ǥ
    mapy                VARCHAR2(20)     CONSTRAINT food_mapy_nn NOT NULL,              -- y ��ǥ    
    areacode            VARCHAR2(2)      CONSTRAINT food_areacode_nn NOT NULL,          -- ���� �ڵ�
    sigungucode         VARCHAR2(2)      CONSTRAINT food_sigungucode_nn NOT NULL,       -- �ñ��� �ڵ�    
    cat3                VARCHAR2(10)     DEFAULT 'no info'                              -- ���� ����
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

-- �������� �߰�(food�� contentid�� fk�� ���)
ALTER TABLE food_like
ADD CONSTRAINT food_like_contentid_fk FOREIGN KEY(contentid) REFERENCES food(contentid)ON DELETE CASCADE;
-- �������� �߰�(member�� id�� fk�� ���)    
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
-- ���ƿ� ��ȣ ������ ����
CREATE SEQUENCE food_like_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE;






--ȸ�� �׽�Ʈ ������
INSERT INTO state (statecode, login, join) VALUES('0', 'o', 'x');
INSERT INTO state (statecode, login, join) VALUES('1', 'o', 'x');
INSERT INTO state (statecode, login, join) VALUES('2', 'x', 'x');
INSERT INTO state (statecode, login, join) VALUES('3', 'x', 'o');
INSERT INTO state (statecode, login, join) VALUES('4', 'x', 'x');

INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('admin1@naver.com', 'admin1password!', '������', 'o', '0');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user1@naver.com', 'user1password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user2@naver.com', 'user2password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user3@naver.com', 'user3password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user4@naver.com', 'user4password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user5@naver.com', 'user5password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user6@naver.com', 'user6password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user7@naver.com', 'user7password!', '����ĥ', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user8@naver.com', 'user8password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user9@naver.com', 'user9password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user10@naver.com', 'user10password!', '������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user11@naver.com', 'user11password!', '��������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user12@naver.com', 'user12password!', '��������', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user13@naver.com', 'user13password!', '�����ʻ�', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user14@naver.com', 'user14password!', '�����ʻ�', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user15@naver.com', 'user15password!', '�����ʿ�', 'o', '1');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user16@naver.com', 'user16password!', '��������', 'o', '2');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user17@naver.com', 'user17password!', '������ĥ', 'o', '2');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user18@naver.com', 'user18password!', '��������', 'o', '2');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user19@naver.com', 'user19password!', '�����ʱ�', 'o', '2');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user20@naver.com', 'user20password!', '�����̽�', 'o', '2');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user21@naver.com', 'user21password!', '�����̽���', 'o', '3');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user22@naver.com', 'user22password!', '�����̽���', 'o', '3');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user23@naver.com', 'user23password!', '�����̽ʻ�', 'o', '3');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user24@naver.com', 'user24password!', '�����̽ʻ�', 'o', '3');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user25@naver.com', 'user25password!', '�����̽ʿ�', 'o', '3');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user26@naver.com', 'user26password!', '�����̽���', 'o', '4');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user27@naver.com', 'user27password!', '�����̽�ĥ', 'o', '4');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user28@naver.com', 'user28password!', '�����̽���', 'o', '4');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user29@naver.com', 'user29password!', '�����̽ʱ�', 'o', '4');
INSERT INTO member (id, password, nickname, agreement, statecode) VALUES('user30@naver.com', 'user30password!', '�������', 'o', '4');

--�Խ��� �׽�Ʈ ������

INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�����̰� �ۼ��� �����Խ��� �׽�Ʈ 02 �Դϴ�.', '�׽�Ʈ�� ���� 0002', 'user2@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�������� �ۼ��� �����Խ��� �׽�Ʈ 03 �Դϴ�.', '�׽�Ʈ�� ���� 0003', 'user3@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�����簡 �ۼ��� �����Խ��� �׽�Ʈ 04 �Դϴ�.', '�׽�Ʈ�� ���� 0004', 'user4@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�������� �ۼ��� �����Խ��� �׽�Ʈ 05 �Դϴ�.', '�׽�Ʈ�� ���� 0005', 'user5@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�������� �ۼ��� �����Խ��� �׽�Ʈ 06 �Դϴ�.', '�׽�Ʈ�� ���� 0006', 'user6@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '����ĥ', '�������� �ۼ��� �����Խ��� �׽�Ʈ 07 �Դϴ�.', '�׽�Ʈ�� ���� 0007', 'user7@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�����̰� �ۼ��� �����Խ��� �׽�Ʈ 08 �Դϴ�.', '�׽�Ʈ�� ���� 0008', 'user8@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�������� �ۼ��� �����Խ��� �׽�Ʈ 09 �Դϴ�.', '�׽�Ʈ�� ���� 0009', 'user9@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�����簡 �ۼ��� �����Խ��� �׽�Ʈ 10 �Դϴ�.', '�׽�Ʈ�� ���� 00010', 'user10@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '��������', '�������� �ۼ��� �����Խ��� �׽�Ʈ 11 �Դϴ�.', '�׽�Ʈ�� ���� 00011', 'user11@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '��������', '�������� �ۼ��� �����Խ��� �׽�Ʈ 12 �Դϴ�.', '�׽�Ʈ�� ���� 00012', 'user12@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '�����ʻ�', '�����簡 �ۼ��� �����Խ��� �׽�Ʈ 13 �Դϴ�.', '�׽�Ʈ�� ���� 00013', 'user13@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '�����ʻ�', '�������� �ۼ��� �����Խ��� �׽�Ʈ 14 �Դϴ�.', '�׽�Ʈ�� ���� 00014', 'user14@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '�����ʿ�', '�������� �ۼ��� �����Խ��� �׽�Ʈ 15 �Դϴ�.', '�׽�Ʈ�� ���� 00015', 'user15@naver.com');

INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�������� �ۼ��� ����Խ��� �׽�Ʈ 01 �Դϴ�.', '�׽�Ʈ�� ���� 0001', 'user1@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�����̰� �ۼ��� ����Խ��� �׽�Ʈ 02 �Դϴ�.', '�׽�Ʈ�� ���� 0002', 'user2@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�������� �ۼ��� ����Խ��� �׽�Ʈ 03 �Դϴ�.', '�׽�Ʈ�� ���� 0003', 'user3@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�����簡 �ۼ��� ����Խ��� �׽�Ʈ 04 �Դϴ�.', '�׽�Ʈ�� ���� 0004', 'user4@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�������� �ۼ��� ����Խ��� �׽�Ʈ 05 �Դϴ�.', '�׽�Ʈ�� ���� 0005', 'user5@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�������� �ۼ��� ����Խ��� �׽�Ʈ 06 �Դϴ�.', '�׽�Ʈ�� ���� 0006', 'user6@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '����ĥ', '�������� �ۼ��� ����Խ��� �׽�Ʈ 07 �Դϴ�.', '�׽�Ʈ�� ���� 0007', 'user7@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�����̰� �ۼ��� ����Խ��� �׽�Ʈ 08 �Դϴ�.', '�׽�Ʈ�� ���� 0008', 'user8@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�������� �ۼ��� ����Խ��� �׽�Ʈ 09 �Դϴ�.', '�׽�Ʈ�� ���� 0009', 'user9@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '������', '�����簡 �ۼ��� ����Խ��� �׽�Ʈ 10 �Դϴ�.', '�׽�Ʈ�� ���� 00010', 'user10@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '��������', '�������� �ۼ��� ����Խ��� �׽�Ʈ 11 �Դϴ�.', '�׽�Ʈ�� ���� 00011', 'user11@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '��������', '�������� �ۼ��� ����Խ��� �׽�Ʈ 12 �Դϴ�.', '�׽�Ʈ�� ���� 00012', 'user12@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '�����ʻ�', '�����簡 �ۼ��� ����Խ��� �׽�Ʈ 13 �Դϴ�.', '�׽�Ʈ�� ���� 00013', 'user13@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '�����ʻ�', '�������� �ۼ��� ����Խ��� �׽�Ʈ 14 �Դϴ�.', '�׽�Ʈ�� ���� 00014', 'user14@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(2, seq_bid.nextval, '�����ʿ�', '�������� �ۼ��� ����Խ��� �׽�Ʈ 15 �Դϴ�.', '�׽�Ʈ�� ���� 00015', 'user15@naver.com');

INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 01 �Դϴ�.', '�׽�Ʈ�� ���� 0001', 'user1@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�����̰� �ۼ��� ���ǰԽ��� �׽�Ʈ 02 �Դϴ�.', '�׽�Ʈ�� ���� 0002', 'user2@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 03 �Դϴ�.', '�׽�Ʈ�� ���� 0003', 'user3@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�����簡 �ۼ��� ���ǰԽ��� �׽�Ʈ 04 �Դϴ�.', '�׽�Ʈ�� ���� 0004', 'user4@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 05 �Դϴ�.', '�׽�Ʈ�� ���� 0005', 'user5@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 06 �Դϴ�.', '�׽�Ʈ�� ���� 0006', 'user6@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '����ĥ', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 07 �Դϴ�.', '�׽�Ʈ�� ���� 0007', 'user7@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�����̰� �ۼ��� ���ǰԽ��� �׽�Ʈ 08 �Դϴ�.', '�׽�Ʈ�� ���� 0008', 'user8@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 09 �Դϴ�.', '�׽�Ʈ�� ���� 0009', 'user9@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '������', '�����簡 �ۼ��� ���ǰԽ��� �׽�Ʈ 10 �Դϴ�.', '�׽�Ʈ�� ���� 00010', 'user10@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '��������', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 11 �Դϴ�.', '�׽�Ʈ�� ���� 00011', 'user11@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '��������', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 12 �Դϴ�.', '�׽�Ʈ�� ���� 00012', 'user12@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '�����ʻ�', '�����簡 �ۼ��� ���ǰԽ��� �׽�Ʈ 13 �Դϴ�.', '�׽�Ʈ�� ���� 00013', 'user13@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '�����ʻ�', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 14 �Դϴ�.', '�׽�Ʈ�� ���� 00014', 'user14@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(3, seq_bid.nextval, '�����ʿ�', '�������� �ۼ��� ���ǰԽ��� �׽�Ʈ 15 �Դϴ�.', '�׽�Ʈ�� ���� 00015', 'user15@naver.com');

INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 01 �Դϴ�.', '�׽�Ʈ�� ���� 0001', 'user1@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�����̰� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 02 �Դϴ�.', '�׽�Ʈ�� ���� 0002', 'user2@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 03 �Դϴ�.', '�׽�Ʈ�� ���� 0003', 'user3@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�����簡 �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 04 �Դϴ�.', '�׽�Ʈ�� ���� 0004', 'user4@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 05 �Դϴ�.', '�׽�Ʈ�� ���� 0005', 'user5@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 06 �Դϴ�.', '�׽�Ʈ�� ���� 0006', 'user6@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '����ĥ', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 07 �Դϴ�.', '�׽�Ʈ�� ���� 0007', 'user7@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�����̰� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 08 �Դϴ�.', '�׽�Ʈ�� ���� 0008', 'user8@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 09 �Դϴ�.', '�׽�Ʈ�� ���� 0009', 'user9@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '������', '�����簡 �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 10 �Դϴ�.', '�׽�Ʈ�� ���� 00010', 'user10@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '��������', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 11 �Դϴ�.', '�׽�Ʈ�� ���� 00011', 'user11@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '��������', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 12 �Դϴ�.', '�׽�Ʈ�� ���� 00012', 'user12@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '�����ʻ�', '�����簡 �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 13 �Դϴ�.', '�׽�Ʈ�� ���� 00013', 'user13@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '�����ʻ�', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 14 �Դϴ�.', '�׽�Ʈ�� ���� 00014', 'user14@naver.com');
INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(4, seq_bid.nextval, '�����ʿ�', '�������� �ۼ��� ��Ÿ�Խ��� �׽�Ʈ 15 �Դϴ�.', '�׽�Ʈ�� ���� 00015', 'user15@naver.com');

INSERT INTO board(bno, bid, nickname, btitle, bcontent, id)
VALUES(1, seq_bid.nextval, '������', '�������� �ۼ��� �����Խ��� �׽�Ʈ 01 �Դϴ�.', '�׽�Ʈ�� ���� 0001', 'user1@naver.com');

--�Խ��� ��� �׽�Ʈ ������
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(60, seq_brno.nextval, '������', '�������� �ۼ��� �����Խ��ǿ� ���� ��� 0002', 'user2@naver.com');
INSERT INTO board_reply(bid, brno, nickname, brcontent, id)
VALUES(60, seq_brno.nextval, '������', '�������� �ۼ��� �����Խ��ǿ� ���� ��� 0003', 'user3@naver.com');


--���� ��� �׽�Ʈ ������
INSERT INTO festa_reply (frno, contentid, id, nickname, frcontent)
VALUES (seq_frno.nextval, '2565883', 'user1@naver.com', '������', '�������� �ۼ��� �޾ָ� �������� �׽�Ʈ ��� ����');
INSERT INTO festa_reply (frno, contentid, id, nickname, frcontent)
VALUES (seq_frno.nextval, '2565883', 'user3@naver.com', '������', '�������� �ۼ��� �޾ָ� �������� �׽�Ʈ ��� ����');

--�Խñ� �Ű�
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user2@naver.com', 'user02�� user01�� �ۼ��� 15�� �Խñ��� �Ű��� �����Դϴ�.', '15');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user3@naver.com', 'user03�� user01�� �ۼ��� 30�� �Խñ��� �Ű��� �����Դϴ�.', '30');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user4@naver.com', 'user04�� user01�� 45�� �Խñ� �Ű��� �����Դϴ�.', '45');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com', 'user5@naver.com', 'user05�� user01�� 60�� �Խñ� �Ű��� �����Դϴ�.', '60');

INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user2@naver.com', 'user3@naver.com', 'user03�� user02�� �ۼ��� 15�� �Խñ��� �Ű��� �����Դϴ�.', '1');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user2@naver.com', 'user4@naver.com', 'user04�� user02�� �ۼ��� 30�� �Խñ��� �Ű��� �����Դϴ�.', '16');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user2@naver.com', 'user5@naver.com', 'user05�� user02�� 45�� �Խñ� �Ű��� �����Դϴ�.', '31');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent, rbid) 
VALUES(seq_reportid.nextval, '1', 'user2@naver.com', 'user6@naver.com', 'user06�� user02�� 60�� �Խñ� �Ű��� �����Դϴ�.', '46');

--�Խñ� ��� �Ű�
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent,rbrno) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com','user6@naver.com', 'user06���� user01 �ۼ��� ����� �Ű��� �����Դϴ�.','1');
INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent,rbrno) 
VALUES(seq_reportid.nextval, '1', 'user1@naver.com','user7@naver.com', 'user07���� user01 �ۼ��� ����� �Ű��� �����Դϴ�.','2');

INSERT INTO report(reportid, reportstate, reported, reporter, reportcontent,rbrno) 
VALUES(seq_reportid.nextval, '1', 'user2@naver.com','user7@naver.com', 'user07���� user02 �ۼ��� ����� �Ű��� �����Դϴ�.','1');



commit;