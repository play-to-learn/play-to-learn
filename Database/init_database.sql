CREATE TABLE user
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    first_name TEXT,
    last_name TEXT,
    score INTEGER DEFAULT 0 NOT NULL
);
INSERT INTO user (first_name, last_name, score) VALUES ('John', 'Doe', 0);
CREATE TABLE question
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT,
    description TEXT NOT NULL,
    answer INTEGER NOT NULL,
    score INT DEFAULT 1 NOT NULL
);
INSERT INTO question (name, description, answer, score) VALUES ('One', 'Move one step', 1, 5);
INSERT INTO question (name, description, answer, score) VALUES ('Two', 'Move two steps', 2, 10);
INSERT INTO question (name, description, answer, score) VALUES ('Three', 'Move three steps', 3, 5);