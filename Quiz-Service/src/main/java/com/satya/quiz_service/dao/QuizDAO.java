package com.satya.quiz_service.dao;

import com.satya.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer> {
    List<Quiz> findByUsername(String username);
}
