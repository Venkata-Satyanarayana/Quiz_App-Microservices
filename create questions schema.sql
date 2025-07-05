CREATE DATABASE IF NOT EXISTS quizque;

USE quizque;

CREATE TABLE IF NOT EXISTS questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(255),
    difficultylevel VARCHAR(255),
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    question_title VARCHAR(255),
    right_answer VARCHAR(255)
);
