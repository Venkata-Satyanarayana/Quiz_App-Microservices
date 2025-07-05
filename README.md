# 🧠 Multi-User Quiz Microservices Application (AI Enabled)
## 📘 Description
This is a Spring Boot microservices-based quiz application designed for multiple users to register, log in, generate quizzes, and attempt questions. Each user’s quiz data (including AI-generated questions) is isolated, ensuring privacy and individual tracking.

The system is broken down into microservices with Spring Cloud, supports RESTful APIs, uses Eureka for service discovery, and integrates Spring AI with Ollama’s Gemma 2B model for generating quiz questions.

## User Interface:
 <img width="885" alt="image" src="https://github.com/user-attachments/assets/8449e817-f16e-4007-8eec-d4e7961896ef" />

 
###  Features:
#### Play Quiz:
This quiz gets the random questions from Database as user requested specifications like programming language and number of questions after user answered all the questions and submitted user will get score and result of the based based on score.
      User can play with already generated quiz or user can generate the quiz the way user required.
      
#### Play Quiz with AI:
This is a new feature where user will get the questions from an AI based on user requests and user will get the score and result based on score post submitting the quiz.Everytime user generates the questions user will get the newly generated questions. This feature requires Ollama : gemma 2b model which is a local AI service.


## 🛠️ Tech Stack
### Java 17, Spring Boot 3.5.x , Eureka Server, Spring Cloud, Spring Security, Spring Data JPA
###  MySQL 8 (3 separate databases for services)
### Spring AI, Ollama with gemma:2b model
### Maven, Thymeleaf (server-side rendered HTML) , Feign Clients for inter-service communication


## 🔄 Working Process
### 🔐 1. User Registration & Authentication
  Users register through the frontend.

  Credentials and roles are stored in the users and authorities tables.

  Spring Security with JDBC handles authentication using MySQL.

### 🌐 2. Microservices Breakdown
  Eureka Server registers all services.

  Quiz Service (User-facing): Handles user quiz creation, management, and rendering via Thymeleaf.

  Question Service: Manages static questions stored in MySQL.

  AI Question Service: Generates MCQs dynamically via Spring AI and Gemma model.

  Each service communicates via Feign Clients and is independently deployable.

### 🤖 3. AI-Generated Quiz Questions
  Upon quiz creation, users can choose to generate questions via AI.

  Questions are parsed from raw AI output, mapped to the user, and stored in the DB.

### 🔍 4. Data Isolation
  All quiz/question data is stored per user, ensuring no leakage across accounts.

  Handled through service-layer filtering and username-based foreign keys.


      

 




