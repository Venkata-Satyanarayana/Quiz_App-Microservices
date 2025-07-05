create database aique;

CREATE TABLE IF NOT EXISTS user_questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255),
    option1 VARCHAR(255) NOT NULL,
    option2 VARCHAR(255) NOT NULL,
    option3 VARCHAR(255) NOT NULL,
    option4 VARCHAR(255) NOT NULL,
    answer VARCHAR(255) NOT NULL,
    username VARCHAR(255)
);
