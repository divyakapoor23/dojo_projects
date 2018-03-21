package com.katieoshea.auth.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.katieoshea.auth.models.User;
import com.katieoshea.auth.repositories.RoleRepo;
import com.katieoshea.auth.repositories.UserRepo;

@Service
public class UserService {
    private UserRepo uRepo;
    private RoleRepo rRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepo uRepo, RoleRepo rRepo, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.uRepo= uRepo;
        this.rRepo = rRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(rRepo.findByName("ROLE_USER"));
        uRepo.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(rRepo.findByName("ROLE_ADMIN"));
        uRepo.save(user);
    }    
    
    // 3
    public User findByUsername(String username) {
        return uRepo.findByUsername(username);
    }
}