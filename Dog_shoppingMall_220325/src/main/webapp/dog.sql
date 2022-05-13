/*
 * (예)비교를 위해 "임초롱초롱빛나리"를 name1, name2로 저장하면
 * 
 *  1. name1 VARCHAR2(20)  : 길이의 의미가 바이트 개수
 *     ▶ 한글 8자리*3 = 24byte이므로 길이초과로 저장할 수 없다.
 * 
 *  2. name2 NVARCHAR2(20) : 길이의 의미가 "문자 개수"
 *     ▶ 한글 8자리를 저장하고 추가로 12자리를 더 저장할 수 있다.
 * 
 */

CREATE TABLE dog(
id NUMBER PRIMARY KEY, -- 상품아이디 (Mysql에서는 auto_increment로 자동 1증가)
kind NVARCHAR2(20) NOT NULL, -- 개품종
country NVARCHAR2(20) NOT NULL, -- 원산지
price NUMBER NOT NULL, -- 개 가격
height NUMBER, -- 평균 개 신장
weight NUMBER, -- 평균 개 체중
content NVARCHAR2(400), -- 개 설명
image NVARCHAR2(40) NOT NULL, -- 개 이미지
readcount NUMBER -- 조회수
);

-- 오라클에서는 직접 SEQUENCE 생성하여 사용함
CREATE SEQUENCE dog_seq; -- 시작 1, 증가 1

INSERT INTO dog VALUES(dog_seq.nextval, '푸들', '프랑스', 1000, 1, 20, '털많다', 'pu.jpg', 0);
INSERT INTO dog VALUES(dog_seq.nextval, '불독', '독일', 2000, 1, 20, '못생겼다', 'bul.jpg', 0);
INSERT INTO dog VALUES(dog_seq.nextval, '진돗개', '대한민국', 3000, 1, 20, '최고다', 'jin.jpg', 0);
INSERT INTO dog VALUES(dog_seq.nextval, '허스키', '북극', 4000, 1, 20, '멋지다', 'h.jpg', 0);

COMMIT  -- ※ 주의 ; 넣으면 오류

SELECT * FROM dog;

----------------------------------------------------------------------------------------
/* MySql */
CREATE TABLE dog(
id int auto_increment PRIMARY KEY, /*상품아이디 (Mysql에서는 auto_increment로 자동 1증가) */
kind NVARCHAR2(20) NOT NULL, /* 개품종 */
country NVARCHAR2(20) NOT NULL, /* 원산지 */
price int NOT NULL, /* 개 가격 */
height int, /* 평균 개 신장 */
weight int, /* 평균 개 체중 */
content NVARCHAR2(400), /* 개 설명 */
image NVARCHAR2(40) NOT NULL, /* 개 이미지 */
readcount int /* 조회수 */
);

/* id는 자동 1증가되므로 insert에서 제외 */
INSERT INTO dog(kind, country, price, height, weight, content, image, readcount)
VALUES('푸들', '프랑스', 1000, 1, 20, '털많다', 'pu.jpg', 0);

INSERT INTO dog(kind, country, price, height, weight, content, image, readcount)
VALUES('불독', '독일', 2000, 1, 20, '못생겼다', 'bul.jpg', 0);

INSERT INTO dog(kind, country, price, height, weight, content, image, readcount)
VALUES('진돗개', '대한민국', 3000, 1, 20, '최고다', 'jin.jpg', 0);

INSERT INTO dog(kind, country, price, height, weight, content, image, readcount)
VALUES('허스키', '북극', 4000, 1, 20, '멋지다', 'h.jpg', 0);


