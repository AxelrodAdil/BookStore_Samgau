CREATE TABLE roles(
    role_id BIGINT NOT NULL PRIMARY KEY,
    role_email VARCHAR(45) NOT NULL,
    role_first_name VARCHAR(45) NOT NULL,
    role_last_name VARCHAR(45) NOT NULL,
    role_password VARCHAR(90) NOT NULL,
    role_name VARCHAR(45) NOT NULL,
    role_status VARCHAR(45) NOT NULL
);

--

INSERT INTO roles (role_id, role_email, role_first_name, role_last_name, role_password, role_name, role_status)
VALUES ('1', 'admin@mail.com', 'admin', 'admin', '$2a$12$AU951iduBdXO/x/njEyfsuVgXvwuF9qBSsMGpvjP2fuLZtE4gnQF2', 'ADMIN', 'ACTIVE');

INSERT INTO roles (role_id, role_email, role_first_name, role_last_name, role_password, role_name, role_status)
VALUES ('2', 'user@mail.com', 'user', 'user', '$2a$12$dkJVG4i4yoe03fj9AvkweO9.8S3Jo0reJatVSmv96gt2sHNuIFPt6', 'USER', 'BANNED');