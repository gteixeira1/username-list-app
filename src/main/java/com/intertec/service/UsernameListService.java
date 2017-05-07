package com.intertec.service;

import com.intertec.domain.repository.RestrictedWordRepository;
import com.intertec.domain.repository.UserListRepository;
import com.intertec.util.UsernameUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UsernameListService {

    private static final Log LOG = LogFactory.getLog(UsernameListService.class);

    @Autowired
    private UserListRepository userListRepository;
    @Autowired
    private RestrictedWordRepository restrictedWordRepository;

    private List<String> userList;
    private List<String> restrictedWords;

    public String searchUsername(String username) {
        username = StringUtils.trim(username);
        Boolean userExists = false;

        LOG.info(String.format("Searching username '%s' into the repository", username));
        userList = userListRepository.findUserByUsername(username);

        LOG.info("Retrieving restricted words from repository");
        restrictedWords = restrictedWordRepository.getAllRestrictedWords();

        if (username.length() < UsernameUtil.USERNAME_MIN_LENGTH || userList.size() != 0) {
            LOG.info(String.format("Invalid username: %s", username));
            List<String> validUsernames = generateValidUsernameList(username);
            return "Invalid username - " + validUsernames;
        } else if(restrictedWords.contains(username)){
            LOG.info(String.format("Username '%s'contains a not allowed word", username));
            List<String> validUsernames = generateRandomUsernameList();
            return "Invalid username (word not allowed)" + validUsernames.toString();
        } else {
            return "Valid username";
        }

    }

    private List<String> generateValidUsernameList(String username){
        List<String> validUsernames = new ArrayList<String>();
        List<String> existentUsernames = userListRepository.getAllUsernameList();
        LOG.info(String.format("Generating username list based on user: %s",username));
        for (int i = 0; i < UsernameUtil.USERNAME_GENERATION_RETRY
                && validUsernames.size() < UsernameUtil.USERNAME_MIN_SUGGESTION; i++){
            for (int n = 0; n < UsernameUtil.USERNAME_MIN_SUGGESTION; n++){
                String newUsername = generateUsername(username);
                if(!existentUsernames.contains(newUsername) && !validUsernames.contains(newUsername)){
                    validUsernames.add(newUsername);
                }
            }
        }
        java.util.Collections.sort(validUsernames);
        return validUsernames;
    }

    private List<String> generateRandomUsernameList(){
        List<String> validUsernames = new ArrayList<String>();
        List<String> existentUsernames = userListRepository.getAllUsernameList();
        LOG.info("Generating random username list");
        for (int i = 0; i < UsernameUtil.USERNAME_GENERATION_RETRY
                && validUsernames.size() < UsernameUtil.USERNAME_MIN_SUGGESTION; i++){
            for (int n = 0; n < UsernameUtil.USERNAME_MIN_SUGGESTION; n++){
                String newUsername = generateUsername();
                if(!existentUsernames.contains(newUsername)){
                    validUsernames.add(newUsername);
                }
            }
        }
        java.util.Collections.sort(validUsernames);
        return validUsernames;
    }

    private String generateUsername(String username){
        Random rand = new Random();
        StringBuilder generatedUsername = new StringBuilder();
        generatedUsername.append(username);
        generatedUsername.append(UsernameUtil.End[rand.nextInt(UsernameUtil.End.length)]);
        generatedUsername.append(rand.nextInt(UsernameUtil.USERNAME_RANDOM_RANGE));

        while(generatedUsername.length() < UsernameUtil.USERNAME_MIN_LENGTH) {
            generatedUsername.append(rand.nextInt(UsernameUtil.USERNAME_RANDOM_RANGE));
        }
        LOG.info(String.format("Username generated: %s", generatedUsername));
        return generatedUsername.toString();
    }

    private String generateUsername(){
        Random rand = new Random();
        StringBuilder generatedUsername = new StringBuilder();
        generatedUsername.append(UsernameUtil.Beginning[rand.nextInt(UsernameUtil.Beginning.length)]);
        generatedUsername.append(UsernameUtil.Middle[rand.nextInt(UsernameUtil.Middle.length)]);
        generatedUsername.append(UsernameUtil.End[rand.nextInt(UsernameUtil.End.length)]);
        LOG.info(String.format("Username generated: %s", generatedUsername));
        return generatedUsername.toString();
    }

    private void initLists(){
        userList = new ArrayList<String>();
        restrictedWords = new ArrayList<String>();

        userList.add("Jones");
        userList.add("John");
        userList.add("John1");
        userList.add("John2");
        userList.add("John3");
        userList.add("John4");
        userList.add("John5");
        userList.add("John6");
        userList.add("John7");
        userList.add("John8");
        userList.add("John9");
        userList.add("John10");
        userList.add("John11");
        userList.add("John12");
        userList.add("John13");
        userList.add("John14");
        userList.add("John15");
        userList.add("John16");
        userList.add("John17");

        restrictedWords.add("cannabis");
        restrictedWords.add("abuse");
        restrictedWords.add("crack");
        restrictedWords.add("damn");
        restrictedWords.add("drunk");
        restrictedWords.add("grass");
    }

}
