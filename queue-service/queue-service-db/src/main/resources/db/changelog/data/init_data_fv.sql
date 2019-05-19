INSERT INTO queue (id, number, driver_id, number_passengers, start_dt, start_first_dt, end_dt, version)
VALUES (1, 1, 1, 4, {ts '2019-05-20 06:05:31'}, {ts '2019-05-20 06:05:31'}, {ts '2019-05-20 06:33:56'}, 1);

INSERT INTO queue (id, number, driver_id, number_passengers, start_dt, start_first_dt, end_dt, version)
VALUES (2, 1, 2, 4, {ts '2019-05-20 06:10:15'}, {ts '2019-05-20 06:33:56'}, {ts '2019-05-20 06:45:11'}, 1);

INSERT INTO queue (id, number, driver_id, number_passengers, start_dt, start_first_dt, end_dt, version)
VALUES (3, 1, 3, 4, {ts '2019-05-20 06:35:43'}, {ts '2019-05-20 06:45:11'}, {ts '2019-05-20 07:13:56'}, 1);

INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 6);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 7);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 8);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 9);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 10);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 11);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 12);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 13);
