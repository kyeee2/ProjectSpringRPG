select * from customer ;
SELECT * FROM authority ;

DELETE FROM customer ;
DELETE FROM authority ;

INSERT INTO customer (cus_id, cus_pw, cus_phonenum, cus_nickname ,cus_name, cus_birthdaty)
VALUES (?, ?, ? ,? ,?, ?);

INSERT INTO authority (cus_uid, cus_auth)
VALUES (?, ?);

SELECT c.cus_uid uid, c.cus_id id, c.cus_pw pw, c.cus_nickname, a.cus_auth 
FROM customer c, authority a
WHERE c.cus_id = a.cus_id ;

DESC customer ;



SELECT * FROM freeboard ;
SELECT * FROM movieboard ;
SELECT * FROM noticeboard;

DELETE FROM freeboard ;
DELETE FROM movieboard ;
DELETE FROM noticeboard ;


INSERT INTO freeboard (fb_title, fb_content, fb_boardtype, cus_uid)
VALUES (?, ?, ?, ?);

SELECT * FROM fb_comment ;
SELECT * FROM mb_comment ;




SELECT * FROM premiere ;
