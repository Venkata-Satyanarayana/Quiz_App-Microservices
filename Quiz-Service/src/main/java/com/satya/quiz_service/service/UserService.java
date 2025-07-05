package com.satya.quiz_service.service;

import com.satya.quiz_service.dao.AuthRepo;
import com.satya.quiz_service.dao.UserRepo;
import com.satya.quiz_service.model.Authorities;
import com.satya.quiz_service.model.UserDto;
import com.satya.quiz_service.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthRepo authRepo;

    public void register(UserDto userDto) {
        Users users =new Users();
        users.setUsername(userDto.getUsername());
        users.setPassword("{noop}"+userDto.getPassword());
        users.setEnabled(1);

        userRepo.save(users);

        Authorities authority = new Authorities(userDto.getUsername(), "ROLE_USER");
        authRepo.save(authority);

    }
}
