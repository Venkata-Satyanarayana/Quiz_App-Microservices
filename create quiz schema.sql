create database quizzdb;

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(255) NOT NULL PRIMARY KEY,
    enabled INT NOT NULL,
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(255) NOT NULL,
    authority VARCHAR(255),
    PRIMARY KEY (username, authority),
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS quiz (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    user_id VARCHAR(255),
    username VARCHAR(255),
    PRIMARY KEY (id),
    KEY idx_user_id (user_id)
);

CREATE TABLE IF NOT EXISTS quiz_questions_ids (
    quiz_id INT NOT NULL,
    questions_ids INT,
    KEY idx_quiz_id (quiz_id)
);

