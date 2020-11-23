CREATE TABLE manager
(
	no                    int(3) PRIMARY KEY AUTO_INCREMENT,
	age                   int(3)  NULL ,
	irum                  VARCHAR(100)  NULL ,
	part                  VARCHAR(100)  NULL 
);



CREATE TABLE professor
(
	no                    int(3)  PRIMARY KEY AUTO_INCREMENT ,
	age                   int(3)  NULL ,
	irum                  VARCHAR(100)  NULL ,
	subject               VARCHAR(100)  NULL 
);



CREATE TABLE student
(
	no                    int PRIMARY KEY AUTO_INCREMENT ,
	age                   int(3)  NULL ,
	irum                  VARCHAR(100)  NULL ,
	hakbun                int(4)  NULL 
);





