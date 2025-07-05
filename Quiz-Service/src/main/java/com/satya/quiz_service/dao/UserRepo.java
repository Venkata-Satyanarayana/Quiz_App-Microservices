package com.satya.quiz_service.dao;

import com.satya.quiz_service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,String> {
}
