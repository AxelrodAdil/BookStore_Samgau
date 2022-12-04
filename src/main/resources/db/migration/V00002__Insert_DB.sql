INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (1, 'George', 'Martin', '1948-09-20');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (2, 'Frank', 'Herbert', '1920-10-08');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (3, 'Agatha', 'Christie', '1890-09-15');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (4, 'William', 'Shakespeare', '1564-04-26');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (5, 'Barbara', 'Cartland', '1901-07-09');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (6, 'Nora', 'Roberts', '1990-07-09');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (7, 'J. R. R.', 'Tolkien', '1940-02-15');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (8, 'Karl', 'May', '1790-10-10');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (9, 'Dan', 'Brown', '1901-07-09');

INSERT INTO authors (author_id, author_name, author_surname, author_birthday)
VALUES (10, 'Ken', 'Follet', '1801-01-25');

--

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count, book_publisher)
VALUES (1, 1, 'A Game of Thrones', 'AB', 3990, '1996-08-01', 25, 100, 'Atamura');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count, book_publisher)
VALUES (2, 1, 'The Rise of the Dragon', 'LS', 4990, '1969-10-25', 0, 23, 'Kazakh');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count, book_publisher)
VALUES (3, 2, 'God Emperor of Dune', '1F', 12590, '1981-05-28', 0, 40, 'Alash');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count, book_publisher)
VALUES (4, 2, 'Dune Messiah', 'T2', 9990, '1969-01-01', 0, 10, 'Alash');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count, book_publisher)
VALUES (5, 2, 'Dune Second', 'T1', 9990, '1970-01-01', 0, 5, 'Almaty');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count,book_publisher)
VALUES (6, 4, 'Life', 'AB', 15990, '1999-10-01', 50, 70, 'Astana');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count,book_publisher)
VALUES (7, 8, 'Adil', 'TY', 1590, '2000-12-01', 20, 80, 'Balbyraun');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count,book_publisher)
VALUES (8, 9, 'Java', 'RO', 1490, '2005-10-01', 10, 500, 'Athens');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count,book_publisher)
VALUES (9, 6, 'C#', 'F0', 10000, '2001-12-30', 15, 100, 'KazNU');

INSERT INTO books (book_id, author_id, book_name, bookshelf, book_price, book_published_year, book_discount, book_count,book_publisher)
VALUES (10, 3, 'Postgres', 'AE', 50990, '1949-02-20', 9, 0, 'Astana');

--

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (1, 'Adil', 'Myktybek', '2001-12-08', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (2, 'WhoAmI', 'Hello', '2001-12-08', FALSE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (3, 'Adil3', 'Myktybek3', '2001-11-08', FALSE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (4, 'Adil4', 'Myktybek3', '2001-12-01', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (5, 'Botakoz', 'Kadyrbay', '2003-10-02', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (6, 'Akzhuzyk', 'IDK', '2001-11-25', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (7, 'Aidana', 'Shamshabayeva', '2001-11-25', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (8, 'Dauren', 'Kylysh', '2003-12-10', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (9, 'Symbat', 'Kubaytova', '2004-08-21', TRUE);

INSERT INTO students (student_id, student_name, student_surname, student_birthday, student_status)
VALUES (10, 'Ainel', 'Shaizhan', '2005-11-05', TRUE);

--

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (1, 'Sagyn', 'Karabay', DATE '2001-12-17', 5000);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (2, 'Akzhuzik', 'Umirzakova', DATE '2001-12-01', 5500);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (3, 'Aruzhan', 'Asan', DATE '2001-12-01', 21000);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (4, 'Ayazhan', 'Espembetova', DATE '2001-12-01', 21010);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (5, 'Ainel', 'Ainel', DATE '2002-05-23', 2500);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (6, 'Erasyl', 'Karabay', DATE '2001-12-17', 15000);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (7, 'Ayakoz', 'Umirzakova', DATE '2001-10-01', 4999);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (8, 'Aruzhan', 'Ainel', DATE '2001-07-01', 1502);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (9, 'Sagynysh', 'Espembetova', DATE '2001-05-01', 12001);

INSERT INTO employees (employee_id, employee_name, employee_surname, employee_birthday, employee_salary)
VALUES (10, 'Symbat', 'Ainel', DATE '2002-04-23', 1020);

--

INSERT INTO libraries (library_id, student_id, book_id, employee_id)
VALUES (1, 1, 3, 1);

INSERT INTO libraries (library_id, student_id, book_id, employee_id)
VALUES (2, 1, 4, 1);

INSERT INTO libraries (library_id, student_id, book_id, employee_id)
VALUES (3, 1, 1, 2);

INSERT INTO libraries (library_id, student_id, book_id, employee_id)
VALUES (4, 2, 2, 2);

INSERT INTO libraries (library_id, student_id, book_id, employee_id)
VALUES (5, 2, 3, 2);
