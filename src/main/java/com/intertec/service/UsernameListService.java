package com.intertec.service;

import org.springframework.stereotype.Service;

@Service
public class UsernameListService {

    public String searchUsername(String username){
        return "Searched username: " + username;
    }

}
