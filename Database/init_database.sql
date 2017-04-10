CREATE TABLE question
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT,
    description TEXT NOT NULL,
    answer INTEGER NOT NULL,
    score INTEGER DEFAULT 0 NOT NULL
);
CREATE TABLE question_category
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT NOT NULL
);
CREATE UNIQUE INDEX question_category_name_uindex ON question_category (name);
CREATE TABLE sqlite_master
(
    type text,
    name text,
    tbl_name text,
    rootpage integer,
    sql text
);
CREATE TABLE sqlite_sequence
(
    name ,
    seq
);
CREATE TABLE user
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    first_name TEXT,
    last_name TEXT,
    score INTEGER DEFAULT 0 NOT NULL
)