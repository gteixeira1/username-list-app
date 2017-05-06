package com.intertec.controller;

import com.intertec.service.UsernameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("${url.root.path}")
@RestController
public class UsernameListController {

    @Autowired
    private UsernameListService usernameListService;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String getUsername(@RequestParam(value = "username", required = true) String username){
        return usernameListService.searchUsername(username);
    }
}
