-- senha 123456a $2a$10$kqjrSqjDd8MQXIVzlxu/NeJU5N6GKDJ4v3ZFesBKj54N4o7gnPWEm
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO public.users(id, email, password, username, role_id)
VALUES (nextval('users_id_seq'), 'admin@email.com', '$2a$10$kqjrSqjDd8MQXIVzlxu/NeJU5N6GKDJ4v3ZFesBKj54N4o7gnPWEm', 'admin', 1);



