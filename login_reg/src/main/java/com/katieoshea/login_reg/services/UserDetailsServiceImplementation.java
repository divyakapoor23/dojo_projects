package com.katieoshea.login_reg.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.katieoshea.login_reg.models.User;
import com.katieoshea.login_reg.repositories.UserRepo;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private UserRepo uRepo;
    public UserDetailsServiceImplementation(UserRepo uRepo){
        this.uRepo = uRepo;
    }
    // 1
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = uRepo.findByEmail(username);
        System.out.println(user);
    	if(user == null) {
    		throw new UsernameNotFoundException("User not found");
    	}
   	       
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }
    
    // 2
    private List<GrantedAuthority> getAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();      
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
    
    
}

