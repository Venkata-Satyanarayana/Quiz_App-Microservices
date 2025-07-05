package com.satya.quiz_service.dao;

import com.satya.quiz_service.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<Authorities,String> {
}
