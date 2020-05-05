DROP TABLE admin_book;
DROP TABLE admin_member;
DROP TABLE book;
DROP TABLE member;
DROP TABLE preference;
DROP TABLE rental;
DROP TABLE reservation;
DROP TABLE worker;

DROP INDEX 엔터티1_PK;
DROP INDEX 엔터티1_PK1;
DROP INDEX 엔터티1_PK2;
DROP INDEX 엔터티1_PK3;
DROP INDEX 엔터티1_PK4;
DROP INDEX 엔터티1_PK5;
DROP INDEX 엔터티1_PK6;

CREATE TABLE admin_book
(
    worker_id    INTEGER NOT NULL,
    book_num    INTEGER NOT NULL,
    genre    VARCHAR2(20) NOT NULL,
    location    VARCHAR2(20) NOT NULL,
    isAvailable    SMALLINT NOT NULL
);

COMMENT ON COLUMN admin_book.worker_id IS 'worker_id';

COMMENT ON COLUMN admin_book.book_num IS 'book_num';

COMMENT ON COLUMN admin_book.genre IS 'genre';

COMMENT ON COLUMN admin_book.location IS 'location';

COMMENT ON COLUMN admin_book.isAvailable IS 'isAvailable';

COMMENT ON TABLE admin_book IS 'admin_book';

CREATE UNIQUE INDEX 엔터티1_PK3 ON admin_book
( worker_id,book_num,genre );

ALTER TABLE admin_book
DROP PRIMARY KEY;

ALTER TABLE admin_book
 ADD CONSTRAINT 엔터티1_PK3 PRIMARY KEY ( worker_id,book_num,genre )
 USING INDEX 엔터티1_PK3;

COMMENT ON COLUMN admin_book.worker_id IS 'worker_id';

COMMENT ON COLUMN admin_book.book_num IS 'book_num';

COMMENT ON COLUMN admin_book.genre IS 'genre';

COMMENT ON COLUMN admin_book.location IS 'location';

COMMENT ON COLUMN admin_book.isAvailable IS 'isAvailable';

COMMENT ON TABLE admin_book IS 'admin_book';


CREATE TABLE admin_member
(
    worker_id    INTEGER NOT NULL,
    member_id    INTEGER NOT NULL,
    register_date    DATE NOT NULL
);

COMMENT ON COLUMN admin_member.worker_id IS 'worker_id';

COMMENT ON COLUMN admin_member.member_id IS 'member_id';

COMMENT ON COLUMN admin_member.register_date IS 'register_date';

COMMENT ON TABLE admin_member IS 'admin_member';

CREATE UNIQUE INDEX 엔터티1_PK ON admin_member
( worker_id,member_id );

ALTER TABLE admin_member
DROP PRIMARY KEY;

ALTER TABLE admin_member
 ADD CONSTRAINT 엔터티1_PK PRIMARY KEY ( worker_id,member_id )
 USING INDEX 엔터티1_PK;

COMMENT ON COLUMN admin_member.worker_id IS 'worker_id';

COMMENT ON COLUMN admin_member.member_id IS 'member_id';

COMMENT ON COLUMN admin_member.register_date IS 'register_date';

COMMENT ON TABLE admin_member IS 'admin_member';


CREATE TABLE book
(
    book_num    INTEGER NOT NULL,
    genre    VARCHAR2(20) NOT NULL,
    book_name    VARCHAR2(40) NOT NULL,
    author    VARCHAR2(30) NOT NULL
);

COMMENT ON COLUMN book.book_num IS 'book_num';

COMMENT ON COLUMN book.genre IS 'genre';

COMMENT ON COLUMN book.book_name IS 'book_name';

COMMENT ON COLUMN book.author IS 'author';

COMMENT ON TABLE book IS 'book';

CREATE UNIQUE INDEX 엔터티1_PK4 ON book
( book_num,genre );

ALTER TABLE book
DROP PRIMARY KEY;

ALTER TABLE book
 ADD CONSTRAINT 엔터티1_PK4 PRIMARY KEY ( book_num,genre )
 USING INDEX 엔터티1_PK4;

COMMENT ON COLUMN book.book_num IS 'book_num';

COMMENT ON COLUMN book.genre IS 'genre';

COMMENT ON COLUMN book.book_name IS 'book_name';

COMMENT ON COLUMN book.author IS 'author';

COMMENT ON TABLE book IS 'book';


CREATE TABLE member
(
    member_id    INTEGER NOT NULL,
    member_name    VARCHAR2(20) NOT NULL,
    m_phone_num    VARCHAR2(20),
    address    VARCHAR2(20)
);

COMMENT ON COLUMN member.member_id IS 'member_id';

COMMENT ON COLUMN member.member_name IS 'member_name';

COMMENT ON COLUMN member.m_phone_num IS 'm_phone_num';

COMMENT ON COLUMN member.address IS 'address';

COMMENT ON TABLE member IS 'member';

CREATE UNIQUE INDEX 엔터티1_PK1 ON member
( member_id );

ALTER TABLE member
DROP PRIMARY KEY;

ALTER TABLE member
 ADD CONSTRAINT 엔터티1_PK1 PRIMARY KEY ( member_id )
 USING INDEX 엔터티1_PK1;

COMMENT ON COLUMN member.member_id IS 'member_id';

COMMENT ON COLUMN member.member_name IS 'member_name';

COMMENT ON COLUMN member.m_phone_num IS 'm_phone_num';

COMMENT ON COLUMN member.address IS 'address';

COMMENT ON TABLE member IS 'member';


CREATE TABLE preference
(
    book_num    INTEGER NOT NULL,
    genre    VARCHAR2(20) NOT NULL,
    rental_count    INTEGER NOT NULL
);

COMMENT ON COLUMN preference.book_num IS 'book_num';

COMMENT ON COLUMN preference.genre IS 'genre';

COMMENT ON COLUMN preference.rental_count IS 'rental_count';

COMMENT ON TABLE preference IS 'preference';

DROP INDEX 엔터티1_PK7;

CREATE UNIQUE INDEX 엔터티1_PK7 ON preference
( book_num,genre );

ALTER TABLE preference
DROP PRIMARY KEY;

ALTER TABLE preference
 ADD CONSTRAINT 엔터티1_PK7 PRIMARY KEY ( book_num,genre )
 USING INDEX 엔터티1_PK7;

COMMENT ON COLUMN preference.book_num IS 'book_num';

COMMENT ON COLUMN preference.genre IS 'genre';

COMMENT ON COLUMN preference.rental_count IS 'rental_count';

COMMENT ON TABLE preference IS 'preference';


CREATE TABLE rental
(
    member_id    INTEGER NOT NULL,
    book_num    INTEGER NOT NULL,
    genre    VARCHAR2(20) NOT NULL,
    rental_date    DATE NOT NULL
);

COMMENT ON COLUMN rental.member_id IS 'member_id';

COMMENT ON COLUMN rental.book_num IS 'book_num';

COMMENT ON COLUMN rental.genre IS 'genre';

COMMENT ON COLUMN rental.rental_date IS 'rental_date';

COMMENT ON TABLE rental IS 'rental';

CREATE UNIQUE INDEX 엔터티1_PK5 ON rental
( member_id,book_num,genre );

ALTER TABLE rental
DROP PRIMARY KEY;

ALTER TABLE rental
 ADD CONSTRAINT 엔터티1_PK5 PRIMARY KEY ( member_id,book_num,genre )
 USING INDEX 엔터티1_PK5;

COMMENT ON COLUMN rental.member_id IS 'member_id';

COMMENT ON COLUMN rental.book_num IS 'book_num';

COMMENT ON COLUMN rental.genre IS 'genre';

COMMENT ON COLUMN rental.rental_date IS 'rental_date';

COMMENT ON TABLE rental IS 'rental';


CREATE TABLE reservation
(
    member_id    INTEGER NOT NULL,
    book_num    INTEGER NOT NULL,
    genre    VARCHAR2(20) NOT NULL,
    reserve_date    DATE NOT NULL
);

COMMENT ON COLUMN reservation.member_id IS 'member_id';

COMMENT ON COLUMN reservation.book_num IS 'book_num';

COMMENT ON COLUMN reservation.genre IS 'genre';

COMMENT ON COLUMN reservation.reserve_date IS 'reserve_date';

COMMENT ON TABLE reservation IS 'reservation';

CREATE UNIQUE INDEX 엔터티1_PK6 ON reservation
( member_id,book_num,genre );

ALTER TABLE reservation
DROP PRIMARY KEY;

ALTER TABLE reservation
 ADD CONSTRAINT 엔터티1_PK6 PRIMARY KEY ( member_id,book_num,genre )
 USING INDEX 엔터티1_PK6;

COMMENT ON COLUMN reservation.member_id IS 'member_id';

COMMENT ON COLUMN reservation.book_num IS 'book_num';

COMMENT ON COLUMN reservation.genre IS 'genre';

COMMENT ON COLUMN reservation.reserve_date IS 'reserve_date';

COMMENT ON TABLE reservation IS 'reservation';


CREATE TABLE worker
(
    worker_id    INTEGER NOT NULL,
    password    INTEGER NOT NULL,
    name    VARCHAR2(20) NOT NULL,
    w_phone_num    VARCHAR2(20)
);

COMMENT ON COLUMN worker.worker_id IS 'worker_id';

COMMENT ON COLUMN worker.password IS 'password';

COMMENT ON COLUMN worker.name IS 'name';

COMMENT ON COLUMN worker.w_phone_num IS 'w_phone_num';

COMMENT ON TABLE worker IS 'worker';

CREATE UNIQUE INDEX 엔터티1_PK2 ON worker
( worker_id );

ALTER TABLE worker
DROP PRIMARY KEY;

ALTER TABLE worker
 ADD CONSTRAINT 엔터티1_PK2 PRIMARY KEY ( worker_id )
 USING INDEX 엔터티1_PK2;

COMMENT ON COLUMN worker.worker_id IS 'worker_id';

COMMENT ON COLUMN worker.password IS 'password';

COMMENT ON COLUMN worker.name IS 'name';

COMMENT ON COLUMN worker.w_phone_num IS 'w_phone_num';

COMMENT ON TABLE worker IS 'worker';