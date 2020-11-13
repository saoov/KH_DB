SELECT irum, addr FROM kh

CREATE TABLE kh01(
 irum VARCHAR(100),
 addr VARCHAR(500)
 )
 
 INSERT INTO kh01 (irum, addr) VALUES('KH당산', '서울시특별시 영등포구 당산')
 
 SELECT irum, addr FROM kh01
 
 DROP TABLE kh01
 
 SELECT CONCAT(irum, addr) FROM kh01;
 
 SELECT * FROM emp