package com.intertec.controller;

import com.intertec.service.RestrictedWordService;
import com.intertec.service.UsernameListService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${url.root.path}")
@RestController
public class UsernameListController {

    private static final Log LOG = LogFactory.getLog(UsernameListController.class);

    @Autowired
    private UsernameListService usernameListService;
    @Autowired
    private RestrictedWordService restrictedWordService;

    @RequestMapping(value = "/validate-username", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsername(@RequestParam(value = "username", required = true) String username){
        LOG.info(String.format("Processing username: %s", username));
        return ResponseEntity.ok(usernameListService.searchUsername(username));
    }

    @RequestMapping(value = "/restricted-word", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveRestrictedWord(@RequestBody final List<String> restrictedWordList){
        LOG.info(String.format("Processing username: %s", restrictedWordList));
        return ResponseEntity.ok(restrictedWordService.saveRestrictedWord(restrictedWordList));
    }
}
