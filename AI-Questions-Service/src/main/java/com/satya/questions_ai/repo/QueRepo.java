package com.satya.questions_ai.repo;

import com.satya.questions_ai.model.Questions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueRepo extends JpaRepository<Questions,Integer> {
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE questions AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

    List<Questions> findByUsername(String username);
    //List<Questions> findByUsername(String username);
    Questions findByIdAndUsername(int id, String username);
    void deleteByUsername(String username);
}
