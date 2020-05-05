delete from worker;
delete from member;
delete from admin_member;
delete from book;
delete from admin_book;
delete from preference;
delete from rental;
delete from reservation;

insert into worker values (1, 1234, 'worker1', '010-1234-5678');
insert into worker values (2, 5678, 'worker2', '010-5678-1234');

insert into member values (1, 'a', '010-2345-6789', '서울');
insert into member values (2, 'b', '010-3456-7890', '대전');
insert into member values (3, 'c', '010-4567-8901', '안양');
insert into member values (4, 'd', '010-6789-0123', '부산');

insert into admin_member values (1, 1, TO_DATE('20151203', 'YYYYMMDD'));
insert into admin_member values (2, 2, TO_DATE('20161016', 'YYYYMMDD'));
insert into admin_member values (1, 3, TO_DATE('20170503', 'YYYYMMDD'));
insert into admin_member values (2, 4, TO_DATE('20180720', 'YYYYMMDD'));

insert into book values (1, 'essay', '하마터면 열심히 살 뻔했다', '하완');
insert into book values (2, 'literature', '몽테크리스토 백작', '알렉상드르 뒤마');
insert into book values (3, 'writing', '타이탄의 도구들', '팀 페리스');
insert into book values (4, 'literature', 'Jane Eyre', 'Charlotte Bronte');
insert into book values (5, 'writing', '우리는 언젠가 만난다', '채사장');
insert into book values (6, 'essay', '걷는 사람, 하정우', '하정');
insert into book values (7, 'writing', '150년 하버드 글쓰기 비법', '송숙희');
insert into book values (8, 'essay', '종강하고싶다','박현우');
insert into book values (9, 'novel', '일곱시 팔분','신공학');
insert into book values (10, 'travel', '일감호 투어', '오리' );
insert into book values (11, 'novel', '꾸뻬씨의 행복여행', '프랑수아 르로르');
insert into book values (12, 'novel', '작별', '한강 외');
insert into book values (13, 'novel', '참을 수 없는 존재의 가벼움', '밀란 쿤데라');
insert into book values (14, 'novel', '고양이1', '베르나르 베르베르');
insert into book values (15, 'travel', '인도와 결혼한 여자, 아샤', '아샤');
insert into book values (16, 'travel', '나홀로 여행 컨설팅북', '이주영');
insert into book values (17, 'travel', '생각없이 경주', '최정선');

/* location
* literature: 100, essay: 200, writing: 300, novel: 400, travel: 500
*/
insert into admin_book values (1, 1, 'essay', '200.01', 1);
insert into admin_book values (1, 2, 'literature', '100.01', 1);
insert into admin_book values (1, 3, 'writing', '300.01', 1);
insert into admin_book values (1, 4, 'literature', '100.02', 1);
insert into admin_book values (1, 5, 'writing', '300.02', 1);
insert into admin_book values (1, 6, 'essay', '200.02', 1);
insert into admin_book values (1, 7, 'writing', '300.03', 1);
insert into admin_book values (1, 8, 'essay', '200.03', 1);
insert into admin_book values (2, 9, 'novel', '400.01', 1);
insert into admin_book values (2, 10, 'travel', '500.01', 1);
insert into admin_book values (2, 11, 'novel', '400.02', 1);
insert into admin_book values (2, 12, 'novel', '400.03', 1);
insert into admin_book values (2, 13, 'novel', '400.04', 1);
insert into admin_book values (2, 14, 'novel', '400.05', 1);
insert into admin_book values (2, 15, 'travel', '500.02', 1);
insert into admin_book values (2, 16, 'travel', '500.03', 1);
insert into admin_book values (2, 17, 'travel', '500.04', 1);

insert into preference values (1, 'essay', 4);
insert into preference values (2, 'literature', 5);
insert into preference values (3, 'writing', 2);
insert into preference values (4, 'literature', 3);
insert into preference values (5, 'writing', 7);
insert into preference values (6, 'essay', 6);
insert into preference values (7, 'writing', 3);
insert into preference values (8, 'essay', 7);
insert into preference values (9, 'novel', 2);
insert into preference values (10, 'travel', 1);
insert into preference values (11, 'novel', 5);
insert into preference values (12, 'novel', 4);
insert into preference values (13, 'novel', 1);
insert into preference values (14, 'novel', 6);
insert into preference values (15, 'travel', 3);
insert into preference values (16, 'travel', 5);
insert into preference values (17, 'travel', 4);