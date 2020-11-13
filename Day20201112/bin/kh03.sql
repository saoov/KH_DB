CREATE TABLE board(
	no number(3) primary key,
	title VARCHAR2(100),
	content VARCHAR2(500),
	author VARCHAR2(100),
	nal VARCHAR2(10),
	readcount number(3)
)

CREATE SEQUENCE board_no

INSERT INTO board (no,title,content,author,nal,readcount) VALUES(board_no.nextval, '제목1', '내용1','kh','2020.11.12',0)

SELECT * FROM board
