CREATE SCHEMA IF NOT EXISTS baby_names;

CREATE TYPE gender AS ENUM ('MALE', 'FEMALE', 'NEUTRAL');

CREATE TABLE IF NOT EXISTS names (
    name varchar(20),
    gender gender,
    origin varchar(30),
    meaning text,
    PRIMARY KEY(name)
);

INSERT INTO names(name, gender, origin, meaning)
VALUES
('Bruno', 'MALE', 'German', 'Brown one'),
('Diego', 'MALE', 'Spanish', 'Short form of San Diego'),
('Willa', 'FEMALE', 'American', 'Feminine form of will'),
('Fabrizio', 'MALE', 'Italian', 'Craftsman'),
('Darcy', 'NEUTRAL', 'French', 'Dark one'),
('Astor', 'NEUTRAL', 'English', 'Hawk'),
('Chiara', 'FEMALE', 'Italian', 'Illustrious')
ON CONFLICT DO NOTHING;
