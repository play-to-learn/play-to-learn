CREATE TABLE question
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT,
    description TEXT NOT NULL,
    answer INTEGER NOT NULL,
    question_category_id INTEGER,
    question_difficulty_id INTEGER,
    CONSTRAINT question_question_category_id_fk FOREIGN KEY (question_category_id) REFERENCES question_category (id) ON UPDATE CASCADE,
    CONSTRAINT question_question_difficulty_id_fk FOREIGN KEY (question_difficulty_id) REFERENCES question_difficulty (id) ON UPDATE CASCADE
);
CREATE TABLE question_category
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT NOT NULL
);
CREATE UNIQUE INDEX question_category_name_uindex ON question_category (name);
CREATE TABLE question_difficulty
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    difficulty INTEGER NOT NULL,
    name TEXT NOT NULL,
    score INTEGER DEFAULT 1 NOT NULL
);
CREATE TABLE user
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    first_name TEXT,
    last_name TEXT,
    current_score INTEGER DEFAULT 0 NOT NULL
);
INSERT INTO user (first_name, last_name, current_score) VALUES ('John', 'Doe', 0);
INSERT INTO question_category (name) VALUES ('Loops');
INSERT INTO question_category (name) VALUES ('Conditional structures');
INSERT INTO question_difficulty (difficulty, name, score) VALUES (1, 'Very easy', 1);
INSERT INTO question_difficulty (difficulty, name, score) VALUES (2, 'Easy', 2);
INSERT INTO question_difficulty (difficulty, name, score) VALUES (3, 'Medium', 3);
INSERT INTO question_difficulty (difficulty, name, score) VALUES (4, 'Hard', 4);
INSERT INTO question_difficulty (difficulty, name, score) VALUES (5, 'Very hard', 5);
INSERT INTO question_difficulty (difficulty, name, score) VALUES (6, 'Expert', 6);
INSERT INTO question (name, description, answer, question_category_id, question_difficulty_id) VALUES ('One', 'X = 1
Y = 2
Z = X + Y
GO Z steps forward', 3, 2, 1);
INSERT INTO question (name, description, answer, question_category_id, question_difficulty_id) VALUES ('Two', 'A = 3
B = 4
C = 5
X = A - B + C
GO X steps forward', 4, 2, 1);
INSERT INTO question (name, description, answer, question_category_id, question_difficulty_id) VALUES ('Three', 'X = 10
Y = 17
Z = (X+Y)%X
GO Z steps forward', 7, 2, 2);
INSERT INTO question (name, description, answer, question_category_id, question_difficulty_id) VALUES ('Four', 'A = 25
B = 13
C = 11
IF (B+C < A) THEN
X = 7
ELSE
X = 9
GO X steps forward', 7, 1, 2);
INSERT INTO question (name, description, answer, question_category_id, question_difficulty_id) VALUES ('Five', 'X = 2
WHILE X < 10
	X = X + 1
	GO 1 step forward', 8, 1, 3);
INSERT INTO question (name, description, answer, question_category_id, question_difficulty_id) VALUES ('Six', 'X = 5
Y = 6
WHILE X > 3
	X = X - 1
	Y = Y + 1
GO Y steps forward', 8, 1, 3);