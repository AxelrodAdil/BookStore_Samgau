CREATE TABLE authors
(
    author_id BIGINT NOT NULL PRIMARY KEY,
    author_name VARCHAR(150) NOT NULL,
    author_surname VARCHAR(150) NOT NULL,
    author_birthday DATE NOT NULL
);

CREATE SEQUENCE authors_seq START WITH 1;

--

CREATE TABLE books
(
    book_id BIGINT NOT NULL PRIMARY KEY,
    author_id BIGINT NOT NULL REFERENCES authors(author_id),
    book_name VARCHAR(150) NOT NULL,
    bookshelf VARCHAR(150) NOT NULL,
    book_published_year DATE NOT NULL
);

CREATE SEQUENCE books_seq START WITH 1;

CREATE INDEX authors_ref_idx ON books(author_id);

--

CREATE TABLE students
(
    student_id BIGINT NOT NULL PRIMARY KEY,
    student_name VARCHAR(150) NOT NULL,
    student_surname VARCHAR(150) NOT NULL,
    student_birthday DATE NOT NULL,
    student_status BOOLEAN NOT NULL
);

CREATE SEQUENCE students_seq START WITH 1;

--

CREATE TABLE libraries
(
    library_id BIGINT NOT NULL PRIMARY KEY,
    student_id BIGINT NOT NULL REFERENCES students(student_id),
    book_id BIGINT NOT NULL REFERENCES books(book_id)
);

CREATE SEQUENCE libraries_seq START WITH 1;

CREATE INDEX students_ref_idx ON libraries(student_id);
CREATE INDEX books_ref_idx ON libraries(book_id);
