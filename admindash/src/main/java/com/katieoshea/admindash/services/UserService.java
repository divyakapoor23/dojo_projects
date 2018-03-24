package com.katieoshea.admindash.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.katieoshea.admindash.models.Role;
import com.katieoshea.admindash.models.User;
import com.katieoshea.admindash.repositories.RoleRepo;
import com.katieoshea.admindash.repositories.UserRepo;

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

    public void savePleb(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(rRepo.findByType("ROLE_USER"));
        user.setRoles(roles);
        uRepo.save(user);
    }
    public void saveAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(rRepo.findByType("ROLE_USER"));
        roles.add(rRepo.findByType("ROLE_ADMIN"));
        user.setRoles(roles);
        uRepo.save(user);
    }    
    public void saveSuper(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(rRepo.findByType("ROLE_USER"));
        roles.add(rRepo.findByType("ROLE_ADMIN"));
        roles.add(rRepo.findByType("ROLE_SUPER"));
        user.setRoles(roles);
        uRepo.save(user);
    } 

    public User findByEmail(String email) {
        return uRepo.findByEmail(email);
    }
    public User findById(Long id) {
        return uRepo.findOne(id);
    }
    public List<User> allUsers() {
    	return (List<User>) uRepo.findAll();
    }

    public void updatePleb(User user) {
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(rRepo.findByType("ROLE_USER"));
        user.setRoles(roles);
        uRepo.save(user);
    }
    public void updateAdmin(User user) {
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(rRepo.findByType("ROLE_USER"));
        roles.add(rRepo.findByType("ROLE_ADMIN"));
        user.setRoles(roles);
        uRepo.save(user);
    }
    public void updateSignIn(User user) {
    	user.setLastSignIn(new Date());
    	uRepo.save(user);
    }
    public void deleteUser(Long id) {
        uRepo.delete(id);
    }
}