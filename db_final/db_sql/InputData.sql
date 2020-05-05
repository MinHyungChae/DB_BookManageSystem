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

insert into member values (1, 'a', '010-2345-6789', '����');
insert into member values (2, 'b', '010-3456-7890', '����');
insert into member values (3, 'c', '010-4567-8901', '�Ⱦ�');
insert into member values (4, 'd', '010-6789-0123', '�λ�');

insert into admin_member values (1, 1, TO_DATE('20151203', 'YYYYMMDD'));
insert into admin_member values (2, 2, TO_DATE('20161016', 'YYYYMMDD'));
insert into admin_member values (1, 3, TO_DATE('20170503', 'YYYYMMDD'));
insert into admin_member values (2, 4, TO_DATE('20180720', 'YYYYMMDD'));

insert into book values (1, 'essay', '�ϸ��͸� ������ �� ���ߴ�', '�Ͽ�');
insert into book values (2, 'literature', '����ũ������ ����', '�˷���帣 �ڸ�');
insert into book values (3, 'writing', 'Ÿ��ź�� ������', '�� �丮��');
insert into book values (4, 'literature', 'Jane Eyre', 'Charlotte Bronte');
insert into book values (5, 'writing', '�츮�� ������ ������', 'ä����');
insert into book values (6, 'essay', '�ȴ� ���, ������', '����');
insert into book values (7, 'writing', '150�� �Ϲ��� �۾��� ���', '�ۼ���');
insert into book values (8, 'essay', '�����ϰ�ʹ�','������');
insert into book values (9, 'novel', '�ϰ��� �Ⱥ�','�Ű���');
insert into book values (10, 'travel', '�ϰ�ȣ ����', '����' );
insert into book values (11, 'novel', '�ٻ����� �ູ����', '�������� ���θ�');
insert into book values (12, 'novel', '�ۺ�', '�Ѱ� ��');
insert into book values (13, 'novel', '���� �� ���� ������ ������', '�ж� �ﵥ��');
insert into book values (14, 'novel', '�����1', '�������� ��������');
insert into book values (15, 'travel', '�ε��� ��ȥ�� ����, �ƻ�', '�ƻ�');
insert into book values (16, 'travel', '��Ȧ�� ���� �����ú�', '���ֿ�');
insert into book values (17, 'travel', '�������� ����', '������');

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