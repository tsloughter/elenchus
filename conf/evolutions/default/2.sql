# --- Sample dataset

# --- !Ups

insert into user (username, email, password, fullname) values ('tristan', 'tristan.sloughter@gmail.com', 'gibberish', 'Tristan Sloughter');

insert into book (user_id, title) values (1, 'Real World Haskell');
insert into book (user_id, title) values (1, 'EJB Cookbook');
insert into book (user_id, title) values (1, 'Erlang and OTP');
insert into book (user_id, title) values (1, 'Mastering Algorithms in C');

insert into chapter (book_id, position, title) values (1, 1, 'Types');
insert into chapter (book_id, position, title) values (1, 2, 'Monads');

insert into chapter (book_id, position, title) values (2, 1, 'What are Beans?');

insert into chapter (book_id, position, title) values (3, 1, 'Supervision');

# --- !Downs

delete from user;
delete from book;
delete from chapter;
delete from comment;
