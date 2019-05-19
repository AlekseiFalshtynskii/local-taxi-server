INSERT INTO cars (id, model, reg_number, color, version)
VALUES (1, 'Kia Rio', 'A123AA199', 'Черный', 1);
INSERT INTO cars (id, model, reg_number, color, version)
VALUES (2, 'Renault Logan', 'B234BB199', 'Красный', 1);
INSERT INTO cars (id, model, reg_number, color, version)
VALUES (3, 'Ford Focus', 'C345CC199', 'Синий', 1);
INSERT INTO cars (id, model, reg_number, color, version)
VALUES (4, 'Skoda Yeti', 'E456EE199', 'Белый', 1);
INSERT INTO cars (id, model, reg_number, color, version)
VALUES (5, 'Peugeot 408', 'H567HH199', 'Зеленый', 1);

INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (1, 'q', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, 'q@q.ru', 'Иван', 'Иванов', NULL, 1, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (2, 'w', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, 'w@w.ru', 'Петр', 'Петров', NULL, 2, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (3, 'e', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, 'e@e.ru', 'Михаил', 'Михайлов', NULL, 3, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (4, 'r', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, 'r@r.ru', 'Сергей', 'Сергеев', NULL, 4, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (5, 't', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, 't@t.ru', 'Ололош', 'Ололоев', NULL, 5, 1);

INSERT INTO authorities (id, authority, user_id, version) VALUES (1, 'AUTHORITY_DRIVER', 1, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (2, 'AUTHORITY_DRIVER', 2, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (3, 'AUTHORITY_DRIVER', 3, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (4, 'AUTHORITY_DRIVER', 4, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (5, 'AUTHORITY_DRIVER', 5, 1);

INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (6, '1', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '1@1.ru', 'Дмитрий', 'Петров', NULL, NULL, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (7, '2', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '2@2.ru', 'Сергей', 'Зуев', NULL, NULL, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (8, '3', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '3@3.ru', 'Анна', 'Гусева', NULL, NULL, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (9, '4', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '4@4.ru', 'Степан', 'Будников', NULL, NULL, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (10, '5', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '5@5.ru', 'Виталий', 'Хозяинов', NULL, NULL, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (11, '6', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '6@6.ru', 'Алексей', 'Фальштынский', NULL, NULL, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (12, '7', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '7@7.ru', 'Светлана', 'Шпинькова', NULL, NULL, 1);
INSERT INTO users (id, username, password, enabled, email, firstname, lastname, middlename, car_id, version)
VALUES (13, '8', '$2a$08$88ps4gLoleW9q47x/4DgA.YVT6C7Xfyuhn7EoDZPV06X7AwFgHZoG', TRUE, '8@8.ru', 'Алла', 'Багринцева', NULL, NULL, 1);

INSERT INTO authorities (id, authority, user_id, version) VALUES (6, 'AUTHORITY_PASSENGER', 6, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (7, 'AUTHORITY_PASSENGER', 7, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (8, 'AUTHORITY_PASSENGER', 8, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (9, 'AUTHORITY_PASSENGER', 9, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (10, 'AUTHORITY_PASSENGER', 10, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (11, 'AUTHORITY_PASSENGER', 11, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (12, 'AUTHORITY_PASSENGER', 12, 1);
INSERT INTO authorities (id, authority, user_id, version) VALUES (13, 'AUTHORITY_PASSENGER', 13, 1);
