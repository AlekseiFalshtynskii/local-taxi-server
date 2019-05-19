INSERT INTO trips (id, direction, driver_id, number_passengers, start_dt, end_dt, version)
VALUES (1, 'Поселок -> Город', 1, 4, {ts '2019-05-20 06:33:56'}, {ts '2019-05-20 07:30:12'}, 1);

INSERT INTO trips (id, direction, driver_id, number_passengers, start_dt, end_dt, version)
VALUES (2, 'Поселок -> Город', 2, 4, {ts '2019-05-20 06:45:11'}, {ts '2019-05-20 07:40:45'}, 1);

INSERT INTO trips (id, direction, driver_id, number_passengers, start_dt, end_dt, version)
VALUES (3, 'Поселок -> Город', 3, 4, {ts '2019-05-20 07:13:56'}, {ts '2019-05-20 8:05:12'}, 1);

INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (1, 6);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (1, 7);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (1, 8);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (1, 9);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (2, 10);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (2, 11);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (2, 12);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (2, 13);

INSERT INTO trips (id, direction, driver_id, number_passengers, start_dt, end_dt, version)
VALUES (4, 'Город -> Поселок', 1, 4, {ts '2019-05-20 17:35:55'}, {ts '2019-05-20 18:21:34'}, 1);

INSERT INTO trips (id, direction, driver_id, number_passengers, start_dt, end_dt, version)
VALUES (5, 'Город -> Поселок', 2, 4, {ts '2019-05-20 18:15:42'}, {ts '2019-05-20 19:01:55'}, 1);

INSERT INTO trips (id, direction, driver_id, number_passengers, start_dt, end_dt, version)
VALUES (6, 'Город -> Поселок', 3, 4, {ts '2019-05-20 18:59:02'}, {ts '2019-05-20 19:44:35'}, 1);

INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (4, 6);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (4, 7);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (4, 8);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (4, 9);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (5, 10);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (5, 11);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (5, 12);
INSERT INTO trip_passenger_ids (trip_id, passenger_id) VALUES (5, 13);
