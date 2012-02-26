# --- !Ups

CREATE SEQUENCE user_id_seq;
CREATE TABLE user (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    username varchar(255),
    email varchar(255),
    password varchar(255),
    fullname varchar(255)
);

CREATE SEQUENCE book_id_seq;
CREATE TABLE book (
    id integer NOT NULL DEFAULT nextval('book_id_seq'),
    user_id integer,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    title varchar(255)
);

CREATE SEQUENCE chapter_id_seq;
CREATE TABLE chapter (
    id integer NOT NULL DEFAULT nextval('chapter_id_seq'),
    book_id integer,
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    position integer,
    title varchar(255)
);

CREATE SEQUENCE comment_id_seq;
CREATE TABLE comment (
    id integer NOT NULL DEFAULT nextval('comment_id_seq'),
    book_id integer,
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    body TEXT
);

# --- !Downs

DROP TABLE user;
DROP SEQUENCE user_id_seq;

DROP TABLE book;
DROP SEQUENCE book_id_seq;

DROP TABLE chapter;
DROP SEQUENCE chapter_id_seq;

DROP TABLE comment;
DROP SEQUENCE comment_id_seq;
