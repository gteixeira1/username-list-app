package com.intertec.service;

import com.intertec.domain.repository.RestrictedWordRepository;
import com.intertec.domain.repository.UsernameListRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UsernameListServiceTest {

    @InjectMocks
    private UsernameListService usernameListService;
    @Mock
    private UsernameListRepository usernameListRepository;
    @Mock
    private RestrictedWordRepository restrictedWordRepository;
    private List<String> userList;
    private List<String> restrictedWords;

    private final String validUsername = "MrJohn99";
    private final String repeatedUsername = "MrJohn";
    private final String invalidUsername = "grass";

    @Before
    public void setUp() throws Exception {
        builLists();
        when(usernameListRepository.findUserByUsername(validUsername)).thenReturn(new ArrayList<String>());
        when(usernameListRepository.findUserByUsername(repeatedUsername)).thenReturn(userList);
        when(usernameListRepository.findUserByUsername(invalidUsername)).thenReturn(new ArrayList<String>());
        when(usernameListRepository.getAllUsernameList()).thenReturn(userList);
        when(restrictedWordRepository.getAllRestrictedWords()).thenReturn(restrictedWords);
    }

    @Test
    public void shouldReturnTrue_WhenUsernameIsValid() throws Exception {
        String result = usernameListService.searchUsername(validUsername);
        assertEquals(result,"Valid username");
    }

    @Test
    public void shouldReturnFalse_WhenUsernameIsInvalid() throws Exception {
        String result = usernameListService.searchUsername(repeatedUsername);
        assertTrue(result.contains("Invalid username - "));
    }

    @Test
    public void shouldReturnFalse_WhenUsernameHasRestrictedWord() throws Exception {
        String result = usernameListService.searchUsername(invalidUsername);
        assertTrue(result.contains("Invalid username (word not allowed) -"));
    }

    private void builLists(){
        userList = new ArrayList<String>();
        restrictedWords = new ArrayList<String>();

        userList.add("MrJohn");
        userList.add("MrJohn1");
        userList.add("MrJohn2");
        userList.add("MrJohn3");
        userList.add("MrJohn4");
        userList.add("MrJohn5");
        userList.add("MrJohn6");
        userList.add("MrJohn7");
        userList.add("MrJohn8");
        userList.add("MrJohn9");
        userList.add("MrJohn10");
        userList.add("MrJohn11");
        userList.add("MrJohn12");
        userList.add("MrJohn13");
        userList.add("MrJohn14");
        userList.add("MrJohn15");
        userList.add("MrJohn16");
        userList.add("MrJohn17");

        restrictedWords.add("cannabis");
        restrictedWords.add("abuse");
        restrictedWords.add("crack");
        restrictedWords.add("damn");
        restrictedWords.add("drunk");
        restrictedWords.add("grass");
    }

}