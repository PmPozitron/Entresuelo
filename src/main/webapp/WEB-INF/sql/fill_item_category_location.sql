USE entresuelo;

INSERT INTO location (name, description)
VALUES ('лоджия', 'домик для барахла');

INSERT INTO category (name, description)
VALUES ('крепёж', 'винты, болты и даже скотч !!!');

INSERT INTO category (name, description)
VALUES ('сантехника', 'трубы, шланги и т.д.');

INSERT INTO category (name, description)
VALUES ('инструмент', 'плоскогубцы, молотки и т.д.');

INSERT INTO category (name, description)
VALUES ('контейнер', 'в нём можно хранить');

INSERT INTO item (name, description, location_id)
VALUES ('стеллаж', 'многоярусный, легкосборный и разборный', 1);

INSERT INTO item (name, description, location_id)
VALUES ('скотч', 'широкий, целый моток', 1);

INSERT INTO item (name, description, location_id)
VALUES ('молоток', 'Fit, среднего размера, в хорошем состоянии', 1);
