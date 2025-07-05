package com.satya.p_question_service.dao;

import com.satya.p_question_service.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategory(String category);

    @Query(value = "select q.id from questions q where q.category=?1 order by RAND() Limit ?2" ,nativeQuery = true)
    List<Integer> findquestionIdsByRandom(String category, int numQuestions);
}
