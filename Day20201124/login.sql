CREATE TABLE member(
 id varchar2(12) primary key,
 pw varchar2(8),
 addr varchar2(100),
 tel varchar2(12)
);
alter table member modify tel varchar2(12);


select * from member

