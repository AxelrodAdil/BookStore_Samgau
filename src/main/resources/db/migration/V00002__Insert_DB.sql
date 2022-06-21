INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (1, 'George', 'Martin', '1948-09-20');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (2, 'Frank', 'Herbert', '1920-10-08');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_published_year)
VALUES (1, 1, 'A Game of Thrones', 'AB', '1996-08-01');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_published_year)
VALUES (2, 1, 'The Rise of the Dragon', 'LS', '1969-10-25');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_published_year)
VALUES (3, 2, 'God Emperor of Dune', '1F', '1981-05-28');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_published_year)
VALUES (4, 2, 'Dune Messiah', 'T2', '1969-01-01');

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (1, 'Adil', 'Myktybek', '2001-12-08', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (2, 'WhoAmI', 'Hello', '2001-12-08', FALSE);

INSERT INTO libraries (library_id, student_id, book_id)
VALUES (1, 1, 3);

INSERT INTO libraries (library_id, student_id, book_id)
VALUES (2, 1, 4);

INSERT INTO libraries (library_id, student_id, book_id)
VALUES (3, 1, 1);

INSERT INTO libraries (library_id, student_id, book_id)
VALUES (4, 2, 2);

INSERT INTO libraries (library_id, student_id, book_id)
VALUES (5, 2, 3);
