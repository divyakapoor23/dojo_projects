package com.katieoshea.login_reg.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.katieoshea.login_reg.models.User;
import com.katieoshea.login_reg.repositories.UserRepo;

@Service
public class UserService {
    private UserRepo uRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepo uRepo, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.uRepo= uRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void init(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        uRepo.save(user);
    }
    public User findByEmail(String email) {
        return uRepo.findByEmail(email);
    }
}