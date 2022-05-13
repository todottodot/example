CREATE TABLE movie_1(
me_id varchar2(12) NOT NULL PRIMARY KEY,
me_pass varchar2(15) NOT NULL,
me_name varchar2(20) NOT NULL,
gender char(1),
birth date,
address varchar2(100),
tel varchar2(13)
);

INSERT INTO movie_1 VALUES('kus1', 'sin1', '김유신', 'M', '2004-02-29', '경북 경산시 중방동' , '010-1111-2222');
INSERT INTO movie_1 VALUES('sgj2', 'joo2', '신경주', 'M', '2002-06-18', '대구시 수성구 만촌동', '010-2222-1111');
INSERT INTO movie_1 VALUES('ymg3', 'me3' , '양미경', 'F', '2000-01-01', '경북 경산시 남천면' , '010-3333-4444');
INSERT INTO movie_1 VALUES('kjs4', 'soo4', '김지수', 'F', '2005-11-22', '경북 경산시 진량읍' , '010-4444-3333');
INSERT INTO movie_1 VALUES('jsw5', 'jang5', '장선우','M', '1999-12-31', '대구시 수성구 사월동', '010-5555-6666');
INSERT INTO movie_1 VALUES('gig6', 'go6' , '고인규', 'M', '2002-06-24', '경북 영천시 금호음' , '010-6666-5555');
INSERT INTO movie_1 VALUES('bsy7', 'year7', '박시연','F', '2008-08-28', '경북 경산시 와촌읍' , '010-7777-9999');

DROP TABLE movie_1;

SELECT * FROM movie_1;

--------------------------------------------------------------------------------------------------------------

CREATE TABLE movie_2(
me_id varchar2(12) NOT NULL,
me_pass varchar2(15) NOT NULL,
mo_name varchar2(50) NOT NULL,
rm_date date NOT NULL
);

INSERT INTO movie_2 VALUES('kus1', 'sin1', '겨울왕국2', '2019-11-25');
INSERT INTO movie_2 VALUES('kus1', 'sin1', '늑대아이', '2019-09-20');
INSERT INTO movie_2 VALUES('sgj2', 'joo2', '겨울왕국2', '2019-11-24');
INSERT INTO movie_2 VALUES('ymg3', 'me3',  '김복동', '2019-08-15');
INSERT INTO movie_2 VALUES('ymg3', 'me3',  '썸머 워즈', '2019-08-22');
INSERT INTO movie_2 VALUES('kjs4', 'soo4', '겨울왕국2', '2019-11-28');
INSERT INTO movie_2 VALUES('jsw5', 'jang5','김복동', '2019-08-14');
INSERT INTO movie_2 VALUES('gig6', 'go6',  '늑대아이', '2019-09-20');
INSERT INTO movie_2 VALUES('bsy7', 'year7','늑대아이', '2019-09-20');
INSERT INTO movie_2 VALUES('bsy7', 'year7','시간을 달리는 소녀', '2019-01-20');

DROP TABLE movie_2;

SELECT * FROM movie_2;
--------------------------------------------------------------------------------------------------------------

CREATE TABLE movie_3(
mo_no number NOT NULL,
mo_name varchar2(50) NOT NULL,
mo_poster varchar2(20),
mo_limit varchar2(20) NOT NULL,
mo_date date,
mo_memo varchar2(100),
PRIMARY KEY(mo_no, mo_name)
);

INSERT INTO movie_3 VALUES(1, '겨울왕국2', '겨울왕국.jsp', '전체 관람가', '2019-11-21', '애니메이션 겨울왕국 2탄');
INSERT INTO movie_3 VALUES(2, '늑대아이', '늑대아이.jsp', '전체 관람가', '2019-09-13', '애니메이션 늑대아이');
INSERT INTO movie_3 VALUES(3, '김복동', '김복동.jsp', '12세 관람가', '2019-08-08', '위안부 피해자 고 김복동 할머니를 취재한 다큐멘터리');
INSERT INTO movie_3 VALUES(4, '시간을 달리는 소녀', '시달소.jsp', '전체 관람가', '2019-01-14', '애니메이션 시간을 달리는 소녀');
INSERT INTO movie_3 VALUES(5, '썸머 워즈', '써머 워즈.jsp', '전체 관람가', '2019-08-13', '애니메이션 썸머 워즈');
INSERT INTO movie_3 VALUES(6, '눈의 여왕4', '눈의 여왕4.jsp', '전체 관람가', '2019-12-24', '애니메이션 눈의 여왕 4탄');

DROP TABLE movie_3;

SELECT * FROM movie_3;

-- [select.jsp] ----------------------------------------------------
SELECT me_id, me_name, mo_name, mo_limit, COUNT(*)
FROM movie_2 NATURAL JOIN movie_3 JOIN movie_1
USING (me_id)
GROUP BY me_id, me_name, mo_name, mo_limit
ORDER BY me_name;

-- [select2.jsp] ----------------------------------------------------
SELECT mo_name, mo_limit, COUNT(*) AS mo_cnt
FROM movie_2 JOIN movie_3
USING (mo_name)
GROUP BY mo_name, mo_limit
ORDER BY mo_cnt DESC;
