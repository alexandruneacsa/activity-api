INSERT INTO calendar (day, month, year)
VALUES (1, 1, 2025),
       (2, 1, 2025),
       (3, 1, 2025),
       (4, 1, 2025),
       (5, 1, 2025),
       (6, 1, 2025),
       (7, 1, 2025),
       (8, 1, 2025),
       (9, 1, 2025),
       (10, 1, 2025),
       (11, 1, 2025),
       (12, 1, 2025),
       (13, 1, 2025),
       (14, 1, 2025),
       (15, 1, 2025),
       (16, 1, 2025),
       (17, 1, 2025),
       (18, 1, 2025),
       (19, 1, 2025),
       (20, 1, 2025),
       (21, 1, 2025),
       (22, 1, 2025),
       (23, 1, 2025),
       (24, 1, 2025),
       (25, 1, 2025),
       (26, 1, 2025),
       (27, 1, 2025),
       (28, 1, 2025),
       (29, 1, 2025),
       (30, 1, 2025),
       (31, 1, 2025);

INSERT INTO weather (city, temperature, pressure, humidity, timestamp)
VALUES ('Bucharest', 18.4, 1013, 60, '2023-04-01 08:00:00'),
       ('Bucharest', 20.1, 1011, 55, '2023-04-01 09:00:00'),
       ('Bucharest', 19.8, 1012, 58, '2023-04-01 10:00:00'),
       ('Bucharest', 21.0, 1010, 53, '2023-04-01 11:00:00'),
       ('Bucharest', 22.5, 1009, 50, '2023-04-01 12:00:00'),
       ('Bucharest', 23.3, 1008, 48, '2023-04-01 13:00:00');


INSERT INTO activity (name, description, calendar_id)
VALUES ('Meeting', 'Project kickoff meeting', 1),
       ('Workshop', 'Technical workshop on new tools', 2),
       ('Conference', 'Annual tech conference', 3);
