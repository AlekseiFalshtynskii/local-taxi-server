INSERT INTO queue (id, number, driver_id, number_passengers, start_dt, start_first_dt, end_dt, version)
VALUES (1, 1, 1, 4, {ts '2019-05-20 17:01:00'}, {ts '2019-05-20 17:01:00'}, {ts '2019-05-20 17:35:55'}, 0);

INSERT INTO queue (id, number, driver_id, number_passengers, start_dt, start_first_dt, end_dt, version)
VALUES (2, 1, 2, 4, {ts '2019-05-20 17:30:31'}, {ts '2019-05-20 17:35:55'}, {ts '2019-05-20 18:15:42'}, 0);

INSERT INTO queue (id, number, driver_id, number_passengers, start_dt, start_first_dt, end_dt, version)
VALUES (3, 1, 3, 4, {ts '2019-05-20 17:35:12'}, {ts '2019-05-20 18:15:42'}, {ts '2019-05-20 18:59:02'}, 0);

INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 6);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 7);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 8);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (1, 9);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 10);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 11);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 12);
INSERT INTO place_in_queue_passenger_ids (place_in_queue_id, passenger_id) VALUES (2, 13);
