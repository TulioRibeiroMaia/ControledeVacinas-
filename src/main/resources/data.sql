INSERT INTO citizen (cpf, full_name, birth_date, cns)
VALUES ('53649189046', 'Francisco Vicente Zappa', '1940-12-21', '318596810001714');

INSERT INTO vaccine (vaccine_name, manufacturer, manufacture_date, expiration_date, lot_number)
VALUES ('BCG', 'Fundação Ataulpho de Paiva', '2021-11-15', '2022-02-19', 'FXA81L6');

INSERT INTO employee (cpf, full_name, email, password, birth_date, user_role)
VALUES ('25117428021', 'Carlos Justiniano Ribeiro Chagas', 'cchagas@email.com', '$2a$10$Tl0YlBZ5cKQwVs21yHxuN.cyfD5iO1wAwBgo/3ioogWO.lCjgX0.S', '1979-07-09', 'ADMIN');


INSERT INTO health_center (cnes, name, city, state)
VALUES ('2695464', 'Centro de Saúde Padre Eustáquio', 'Belo Horizonte', 'MG');

INSERT INTO citizen_vaccines (vaccination_date, citizen_id, vaccine_id)
VALUES ('1940-12-22', 1, 1);