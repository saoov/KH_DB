CREATE TABLE manager
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	irum                  VARCHAR2(100)  NULL ,
	part                  VARCHAR2(100)  NULL 
);

CREATE SEQUENCE manager_no


CREATE UNIQUE INDEX XPK������ ON manager
(no  ASC);


ALTER TABLE manager
	ADD CONSTRAINT  XPK������ PRIMARY KEY (no);


CREATE TABLE professor
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	irum                  VARCHAR2(100)  NULL ,
	subject               VARCHAR2(100)  NULL 
);

CREATE SEQUENCE professor_no

CREATE UNIQUE INDEX XPK���� ON professor
(no  ASC);


ALTER TABLE professor
	ADD CONSTRAINT  XPK���� PRIMARY KEY (no);


CREATE TABLE student2
(
	no                    NUMBER(3)  NOT NULL ,
	age                   NUMBER(3)  NULL ,
	irum                  VARCHAR2(100)  NULL ,
	hakbun                NUMBER(4)  NULL 
);


CREATE SEQUENCE student2_no

drop table student2

CREATE UNIQUE INDEX XPK�л� ON student
(no  ASC);

select * from manager
select * from professor
select * from student2


ALTER TABLE student
	ADD CONSTRAINT  XPK�л� PRIMARY KEY (no);


