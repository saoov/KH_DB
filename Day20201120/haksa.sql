CREATE TABLE manager
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	irum                  VARCHAR2(100)  NULL ,
	part                  VARCHAR2(100)  NULL 
);

CREATE SEQUENCE manager_no


CREATE UNIQUE INDEX XPK관리자 ON manager
(no  ASC);


ALTER TABLE manager
	ADD CONSTRAINT  XPK관리자 PRIMARY KEY (no);


CREATE TABLE professor
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	irum                  VARCHAR2(100)  NULL ,
	subject               VARCHAR2(100)  NULL 
);

CREATE SEQUENCE professor_no

CREATE UNIQUE INDEX XPK교수 ON professor
(no  ASC);


ALTER TABLE professor
	ADD CONSTRAINT  XPK교수 PRIMARY KEY (no);


CREATE TABLE student2
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	irum                  VARCHAR2(100)  NULL ,
	hakbun                NUMBER(4)  NULL 
);


CREATE SEQUENCE student2_no

drop table student2

CREATE UNIQUE INDEX XPK학생 ON student
(no  ASC);

select * from manager
select * from professor
select * from student2


ALTER TABLE student
	ADD CONSTRAINT  XPK학생 PRIMARY KEY (no);


