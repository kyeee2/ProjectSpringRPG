SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS apply;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS fb_comment;
DROP TABLE IF EXISTS fb_good;
DROP TABLE IF EXISTS freeboard;
DROP TABLE IF EXISTS mb_comment;
DROP TABLE IF EXISTS mb_good;
DROP TABLE IF EXISTS movieboard;
DROP TABLE IF EXISTS noticeboard;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS premiere;
DROP TABLE IF EXISTS primiereWinBoard;




/* Create Tables */

CREATE TABLE apply
(
	pr_uid int NOT NULL,
	cus_uid int NOT NULL,
	
	cus_email varchar(100) NOT NULL,
	pr_win boolean DEFAULT false
);


CREATE TABLE authority
(
	cus_auth varchar(45) NOT NULL,
	cus_id varchar(45) NOT NULL,
	UNIQUE (cus_id)
);


CREATE TABLE customer
(
	cus_uid int NOT NULL AUTO_INCREMENT,
	cus_id varchar(45) NOT NULL,
	cus_pw varchar(256) NOT NULL,
	cus_phonenum varchar(12) NOT NULL,
	cus_nickname varchar(45) NOT NULL,
	cus_name varchar(45) NOT NULL,
	cus_birthday int NOT NULL,
	cus_profile varchar(256),
	-- 회원가입시 1로 디폴트, 탈퇴하면 0
	cus_enable int DEFAULT '1' COMMENT '회원가입시 1로 디폴트, 탈퇴하면 0',
	cus_profile_origin varchar(256),
	PRIMARY KEY (cus_uid),
	UNIQUE (cus_id),
	UNIQUE (cus_nickname)
);


CREATE TABLE fb_comment
(
	fb_co_uid int NOT NULL AUTO_INCREMENT,
	fb_co_content text,
	fb_uid int NOT NULL,
	cus_uid int NOT NULL,
	fb_co_datetime datetime DEFAULT now(),
	PRIMARY KEY (fb_co_uid)
);


CREATE TABLE fb_good
(
	cus_uid int NOT NULL,
	fb_uid int NOT NULL
);


CREATE TABLE freeboard
(
	fb_uid int NOT NULL AUTO_INCREMENT,
	fb_title varchar(150) NOT NULL,
	fb_content text NOT NULL,
	fb_viewcnt int DEFAULT '0',
	fb_datetime datetime DEFAULT now(),
	fb_boardtype varchar(45),
	cus_uid int NOT NULL,
	fb_reportcnt int DEFAULT '0',
	PRIMARY KEY (fb_uid)
);


CREATE TABLE mb_comment
(
	mb_co_uid int NOT NULL AUTO_INCREMENT,
	mb_co_content text,
	mb_uid int NOT NULL,
	cus_uid int NOT NULL,
	mb_co_datetime datetime DEFAULT now(),
	PRIMARY KEY (mb_co_uid)
);


CREATE TABLE mb_good
(
	cus_uid int NOT NULL,
	mb_uid int NOT NULL
);


CREATE TABLE movieboard
(
	mb_uid int NOT NULL AUTO_INCREMENT,
	mb_title varchar(150) NOT NULL,
	mb_content text NOT NULL,
	mb_subject varchar(45),
	mb_viewcnt int DEFAULT '0',
	mb_reportcnt int DEFAULT '0',
	mb_datetime datetime DEFAULT now(),
	mb_boardtype varchar(45),
	cus_uid int NOT NULL,
	PRIMARY KEY (mb_uid)
);


CREATE TABLE noticeboard
(
	nb_uid int NOT NULL AUTO_INCREMENT,
	nb_title varchar(150) NOT NULL,
	nb_content text NOT NULL,
	nb_viewcnt int DEFAULT '0',
	nb_datetime datetime DEFAULT now(),
	nb_boardtype varchar(45),
	cus_uid int NOT NULL,
	PRIMARY KEY (nb_uid)
);


CREATE TABLE premiere
(
	pr_uid int NOT NULL AUTO_INCREMENT,
	pr_title varchar(50) NOT NULL,
	pr_photo varchar(256),
	pr_content text,
	pr_photo_origin varchar(256),
	PRIMARY KEY (pr_uid)
);


CREATE TABLE primiereWinBoard
(
	pwb_uid int NOT NULL AUTO_INCREMENT,
	pwb_title varchar(150) NOT NULL,
	pwb_content text NOT NULL,
	pwb_datetime datetime DEFAULT now(),
	PRIMARY KEY (pwb_uid)
);



/* Create Foreign Keys */

ALTER TABLE apply
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE authority
	ADD FOREIGN KEY (cus_id)
	REFERENCES customer (cus_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE fb_comment
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE fb_good
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE freeboard
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mb_comment
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mb_good
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE movieboard
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE noticeboard
	ADD FOREIGN KEY (cus_uid)
	REFERENCES customer (cus_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE fb_comment
	ADD FOREIGN KEY (fb_uid)
	REFERENCES freeboard (fb_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE fb_good
	ADD FOREIGN KEY (fb_uid)
	REFERENCES freeboard (fb_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mb_comment
	ADD FOREIGN KEY (mb_uid)
	REFERENCES movieboard (mb_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mb_good
	ADD FOREIGN KEY (mb_uid)
	REFERENCES movieboard (mb_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE apply
	ADD FOREIGN KEY (pr_uid)
	REFERENCES premiere (pr_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



